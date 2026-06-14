package Entity;

import Database.GestorePersistenza;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity // Specifica che sarà un'entità da tradurre in una tabella.
@Table(name = "Ristoranti") // Specifica il nome della tabella nel DB.
public class Ristorante {

    @Id // Indica che l'attributo sarà la chiave primaria.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Delega la creazione automatica dell'id al DB.
    private Long id;

    @ManyToOne // Un ristorante può avere un unico proprietario.
    @JoinColumn(name = "proprietario_id") /* La FK associata al Ristoratore proprietario nella tabella "Ristoranti"
                                             si chiamerà "proprietario_id". */
    private Ristoratore proprietario;

    @Column(nullable = false) // Specifica che questa colonna sarà "NOT NULL" nel DB.
    private String nome;

    private String descrizione;

    @Column(nullable = false) // Specifica che questa colonna sarà "NOT NULL" nel DB.
    private String indirizzo;

    /* Il ristorante possiede più ordini, nella classe Ordine
     il riferimento a Ristorante è denominato "ristorante". */
    @OneToMany(mappedBy = "ristorante", fetch = FetchType.EAGER)
    private List<Ordine> ordini = new ArrayList<>();

    @OneToMany(mappedBy = "ristorante", fetch = FetchType.EAGER)
    /* Il ristorante possiede più orari di apertura, nella classe OrarioApertura
       il riferimento a Ristorante è denominato "ristorante".
       FetchType.EAGER indica che ogni volta che viene istanziato un Ristorante
       verranno automaticamente prelevati dal DB tutti i suoi OrarioApertura
       e aggiunti alla sua lista "orariApertura" */
    private List<OrarioApertura> orariApertura = new ArrayList<>();

    @OneToOne(mappedBy = "ristorante", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    /* Il ristorante possiede un unico menù, e nella classe Menu vi è un
       riferimento a Ristorante denominato "ristorante".
       FetchType.EAGER indica che ogni volta che viene istanziato un Ristorante
       verrà automaticamente prelevato dal database il suo Menu. */
    private Menu menu;

    // Costruttore vuoto, serve a JPA.
    public Ristorante() {
    }

    // Costruttore di Ristorante.
    public Ristorante(String nome,
                      String descrizione,
                      String indirizzo,
                      Ristoratore proprietario)
    {
        this.nome = nome;
        this.descrizione = descrizione;
        this.indirizzo = indirizzo;
        this.proprietario = proprietario;

        this.menu = new Menu(this);
    }

    // Metodo getter dell'id.
    public Long getId(){
        return id;
    }

    // Metodo getter dell'oggetto Ristoratore proprietario.
    public Ristoratore getProprietario(){
        return proprietario;
    }

    // Metodo getter del nome del ristorante.
    public String getNome(){
        return nome;
    }

    public String getDescrizione(){
        return descrizione;
    }

    public String getIndirizzo(){
        return indirizzo;
    }

    // Metodo per aggiungere un orario di apertura al Ristorante.
    public boolean aggiungiOrarioApertura (DayOfWeek giornoSettimana,
                                                  LocalTime oraApertura,
                                                  LocalTime oraChiusura)
    {
        // Creo l'oggetto OrarioApertura e lo aggiungo alla lista degli orari.
        OrarioApertura orarioApertura = new OrarioApertura(giornoSettimana, oraApertura, oraChiusura, this);
        boolean aggiuntoAgliOrari = orariApertura.add(orarioApertura);

        /* Gestisco anche il salvataggio nel DB del nuovo oggetto OrarioApertura,
           poiché Ristorante è Information Expert e Creator di OrarioApertura */
        GestorePersistenza gestorePersistenza = new GestorePersistenza();
        boolean aggiuntoAlDB = gestorePersistenza.salva(orarioApertura);

        return aggiuntoAgliOrari && aggiuntoAlDB;
    }

    public boolean isAperto(LocalDateTime momento) {

        // La variabile momento indica l'orario attuale.
        //LocalDateTime momento = LocalDateTime.now();

        // Nella variabile giornoOggi conservo il giorno attuale.
        DayOfWeek giornoOggi = momento.getDayOfWeek();

        // Nella variabile oraAttuale conservo l'orario attuale.
        LocalTime oraAttuale = momento.toLocalTime();

        /* Verifico per ogni orario di apertura del Ristorante, se l'orario attuale è compreso
           all'interno in uno dei suoi orari di apertura. */
        for (OrarioApertura o : orariApertura) {
            //System.out.println(o.getGiornoSettimana());
            if (giornoOggi == o.getGiornoSettimana()){
                //System.out.println(oraAttuale);
                //System.out.println(o.getOraApertura() + " " + o.getOraChiusura());
                if (oraAttuale.isAfter(o.getOraApertura()) && oraAttuale.isBefore(o.getOraChiusura())){
                    //System.out.println((this.id + " aperto"));
                    return true;
                }
            }
        }
        return false;
    }

    // Permette di aggiornare i dati del ristorante.
    public void aggiornaDatiRistorante(String nome,
                                       String descrizione,
                                       String indirizzo)
    {
        this.nome = nome;
        this.descrizione = descrizione;
        this.indirizzo = indirizzo;
    }

    public Menu getMenu(){
        return menu;
    }

    // Restituisce il numero di ordini ricevuti.
    public int getNumeroOrdini() {
        return ordini.size();
    }

}
