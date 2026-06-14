package Entity;

import Database.GestorePersistenza;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Menu")
public class Menu {

    // Chiave primaria.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Il Menu appartiene a un solo Ristorante.
    @OneToOne
    @JoinColumn(name = "ristorante_id", referencedColumnName = "id")
    private Ristorante ristorante;

    // Ogni Menu contiene una lista di piatti.
    @OneToMany(mappedBy = "menu",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, // Se Menu viene cancellato, lo sono anche i suoi piatti.
            orphanRemoval = true) // Quando viene aggiornato o eliminato un piatto della classe Menu, si aggiorna automaticamente il DB.
    private List<Piatto> piatti = new ArrayList<>();

    // Costruttore per JPA.
    public Menu(){
    }

    // Costruttore di Menu.
    public Menu(Ristorante ristorante){

        // Associo il ristorante proprietario del menu.
        this.ristorante = ristorante;

        // Messaggio di esito.
        System.out.println("Menu del ristorante " + this.ristorante.getNome() + " creato.");
    }

    // Getter e Setter dell'ID.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Funzione getter della lista dei piatti.
    public List<Piatto> getPiatti(){
        return piatti;
    }

    // Restituisce l'oggetto Piatto ricercato in base all'id.
    public Piatto ricercaPiatto(Long idPiatto){

        // Ciclo che controlla ogni piatto del menù.
        for(Piatto p : piatti){
            if(p.getId().equals(idPiatto)){
                return p;
            }
        }

        // Se non è stato trovato il piatto del menù, restituisce null.
        return null;
    }

    // Consente di aggiungere un piatto alla lista dei piatti del Menu.
    public boolean aggiungiPiatto(String nome,
                                  String descrizione,
                                  float prezzo)
    {
        // Creo il nuovo piatto.
        Piatto piatto = new Piatto(
                nome,
                descrizione,
                prezzo,
                this // Passiamo l'istanza di questo Menu
        );

        // Lo aggiungo al menu.
        boolean aggiuntoAlMenu = piatti.add(piatto);

        // Lo salvo nel DB.
        GestorePersistenza gestorePersistenza = new GestorePersistenza();

        boolean aggiuntoAlDB = gestorePersistenza.salva(piatto);

        return aggiuntoAlMenu && aggiuntoAlDB;
    }

    // Consente di aggiornare un piatto del Menu.
    public boolean aggiornaDatiPiatto(Long idPiatto,
                                      String nome,
                                      String descrizione,
                                      float prezzo)
    {
        Piatto piatto = ricercaPiatto(idPiatto);

        if(piatto == null){
            return false;
        }

        // Aggiorno il piatto in memoria.
        boolean aggiornatoInMemoria = piatto.aggiornaDati(
                nome,
                descrizione,
                prezzo
        );

        // Aggiorno il piatto nel DB.
        GestorePersistenza gestorePersistenza = new GestorePersistenza();

        Piatto piattoAggiornato = gestorePersistenza.aggiorna(piatto);

        boolean aggiornatoNelDB = (piattoAggiornato != null);

        return aggiornatoInMemoria && aggiornatoNelDB;
    }

    // Consente di rimuovere un piatto dal Menu.
    public boolean rimuoviPiatto(Long idPiatto){

        Piatto piatto = ricercaPiatto(idPiatto);

        if(piatto == null){
            return false;
        }

        // Lo rimuovo dal menu.
        boolean rimossoDalMenu = piatti.remove(piatto);

        // Lo rimuovo dal DB.
        /*GestorePersistenza gestorePersistenza = new GestorePersistenza();

        boolean rimossoDalDB = gestorePersistenza.elimina(
                Piatto.class,
                idPiatto
        );*/

        GestorePersistenza gestorePersistenza = new GestorePersistenza();
        Menu menuAggiornato = gestorePersistenza.aggiorna(this);

        //return rimossoDalMenu && rimossoDalDB;
        return rimossoDalMenu  && (menuAggiornato != null);
    }

    // Utilizzato da Ristorante per associare al Menu i piatti caricati da JPA.
    public void setPiatti(List<Piatto> piatti){
        this.piatti = piatti;
    }
}
