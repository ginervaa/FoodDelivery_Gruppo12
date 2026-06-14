package Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AMMINISTRATORE") /* Nonostante sia taggato come @Entity, l'annotazione
                                  @DiscriminatorValue farà in modo che la tabella creata nel DB
                                  sarà unica ("Utenti"), e nella colonna "Ruolo" della tabella
                                  gli oggetti Amministratore avranno il valore "AMMINISTRATORE". */

public class Amministratore extends Utente { // La classe Amministratore è specializzazione della classe Utente.

    // Costruttore vuoto, serve a JPA.
    public Amministratore() {
    }

    // Costruttore di Amministratore.
    public Amministratore(String username,
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
