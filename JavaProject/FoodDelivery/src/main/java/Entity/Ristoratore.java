package Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("RISTORATORE") /* Nonostante sia taggato come @Entity, l'annotazione
                                  @DiscriminatorValue farà in modo che la tabella creata nel DB
                                  sarà unica ("Utenti"), e nella colonna "Ruolo" della tabella
                                  gli oggetti Ristoratore avranno il valore "RISTORATORE". */
public class Ristoratore extends Utente{

    @OneToMany(mappedBy = "proprietario") /* Il ristoratore possiede più ristoranti, nella classe Ristorante
                                             il riferimento a Ristoratore è denominato "proprietario". */
    private List<Ristorante> ristoranti; // Ogni ristoratore ha una lista di ristoranti.

    // Costruttore vuoto, serve a JPA.
    public Ristoratore() {
    }

    // Costruttore di Ristoratore.
    public Ristoratore(String username,
                       String password,
                       String nome,
                       String cognome,
                       String email,
                       String indirizzo)
    {
        // Richiamo il costruttore della superclasse.
        super(username, password, nome, cognome, email, indirizzo);
    }
}
