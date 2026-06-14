package Entity;

import jakarta.persistence.*;

@Entity // Specifica che sarà un'entità da tradurre in una tabella.
@Table(name = "Utenti") // Specifica il nome della tabella nel DB.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) /* Serve a specificare che nel DB non verranno create più tabelle
                                                        per ogni tipo di utente, ma una sola tabella con ogni tipo
                                                        (Cliente, Ristoratore e Amministratore). */
@DiscriminatorColumn(name = "ruolo",
        discriminatorType = DiscriminatorType.STRING) /* "ruolo" sarà la colonna nella tabella del DB
                                                          che specificherà il ruolo dell'Utente. */

public abstract class Utente { // Utente è abstract poiché non può esistere un utente senza ruolo.

    @Id // Indica che l'attributo sarà la chiave primaria.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Delega la creazione automatica dell'id al DB.
    private Long id;

    @Column(nullable = false) // Specifica che questa colonna sarà "NOT NULL" nel DB.
    private String username;

    @Column(nullable = false) // Specifica che questa colonna sarà "NOT NULL" nel DB.
    private String password;

    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;

    // Costruttore vuoto, serve a JPA.
    public Utente() {
    }

    // Costruttore di Utente.
    public Utente(String username,
                  String password,
                  String nome,
                  String cognome,
                  String email,
                  String indirizzo)
    {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.indirizzo = indirizzo;
    }

    /* Metodo che restituisce "true" se le credenziali passate per parametro corrispondono alle
       credenziali dell'oggetto Utente. */
    public boolean verificaCredenziali(String username,
                                       String password,
                                       RuoloUtente ruolo)
    {
        // Verifica innanzitutto se username o password non corrispondono, e in tal caso ritorna "false".
        if (!username.equals(this.username) || !password.equals(this.password)) {
            return false;
        }

        // Verifica se il ruolo passato per parametro corrisponda al ruolo dell'utente.
        switch (ruolo) {
            case CLIENTE:
                return this instanceof Cliente;
            case RISTORATORE:
                return this instanceof Ristoratore;
            case AMMINISTRATORE:
                return this instanceof Amministratore;
            default:
                return false;
        }
    }

    // Metodo getter dell'id.
    public Long getId()
    {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
