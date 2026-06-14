package Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CLIENTE") /* Nonostante sia taggato come @Entity, l'annotazione
                                  @DiscriminatorValue farà in modo che la tabella creata nel DB
                                  sarà unica ("Utenti"), e nella colonna "Ruolo" della tabella
                                  gli oggetti Cliente avranno il valore "CLIENTE". */

public class Cliente extends Utente { // La classe Cliente è specializzazione della classe Utente.

    @Transient // L'annotazione @Transient indica che l'attributo non sarà presente nella tabella del DB.
    private Carrello carrello;

    @OneToMany(mappedBy = "cliente") /* Il cliente possiede più ordini, nella classe Ordine il riferimento a
                                        Cliente è denominato "cliente". */
    private List<Ordine> storicoOrdini = new ArrayList<>(); // Ogni Cliente ha una lista di ordini denominata "storicoOrdini".

    // Costruttore vuoto, serve a JPA.
    public Cliente() {
    }

    // Costruttore di Cliente
    public Cliente(String username,
                   String password,
                   String nome,
                   String cognome,
                   String email,
                   String indirizzo)
    {
        // Chiama il costruttore della superclasse.
        super(username, password, nome, cognome, email, indirizzo);

        // Creo l'oggetto Carrello di cui il cliente è proprietario.
        carrello = new Carrello(this);
    }

    // Metodo getter dell'oggetto Carrello.
    public Carrello getCarrello(){

        // Verifica se non è stato ancora istanziato l'oggetto Carrello.
        if (carrello == null){
            // Se non esiste nessun carrello per il cliente, lo crea.
            carrello = new Carrello(this);
        }

        return carrello;
    }

    // Metodo getter dello storicoOrdini.
    public List<Ordine> getStoricoOrdini(){
        return storicoOrdini;
    }
}
