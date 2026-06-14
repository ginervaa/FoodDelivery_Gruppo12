package Entity.Gestori;

import Database.GestorePersistenza;
import Entity.*;

import java.util.Map;

// Questa classe è Information Expert della classe Utente, non diventerà una tabella nel DB.
public class GestoreUtenti {

    // Il GestoreUtenti ha una propria istanza del GestorePersistenza.
    private GestorePersistenza gestorePersistenza;

    /* Il GestoreUtenti possiede una lista degli utenti, tuttavia in questo codice, poiché
       il GestoreUtenti si interfaccia direttamente con il database, è superflua. */
    // private List<Utente> utenti;

    // Costruttore di GestoreUtenti.
    public GestoreUtenti() {
        // Ogni volta che un GestoreUtenti viene istanziato, viene istanziato anche il suo GestorePersistenza.
        gestorePersistenza = new GestorePersistenza();
    }


    public Utente ricercaUtentePerId(Long idUtente){
        return gestorePersistenza.trovaPerId(Utente.class, idUtente);
    }


    // Restituisce l'oggetto Utente, se presente nella lista di utenti, che ha l'username passato per parametro.
    public Utente ricercaUtentePerUsername(String username){

        // Ricerca nella tabella Utenti del DB la prima riga che ha come campo "username" il valore passato per parametro.
        Utente utente = gestorePersistenza.cercaPrimoPerCampi(Utente.class, Map.of("username", username));

        return utente;
    }

    // Crea un oggetto Utente e lo salva nel DB, restituisce true se l'inserimento va a buon fine.
    public boolean creaUtente(String username,
                              String password,
                              String nome,
                              String cognome,
                              String email,
                              String indirizzo,
                              RuoloUtente ruolo)
    {
        Utente utente;

        // Controllo quale ruolo assegnare all'utente da creare.
        if (ruolo == RuoloUtente.CLIENTE){
            utente = new Cliente(username, password, nome, cognome, email, indirizzo);
        }
        else if (ruolo == RuoloUtente.RISTORATORE){
            utente = new Ristoratore(username, password, nome, cognome, email, indirizzo);
        }
        else {
            utente = new Amministratore(username, password, nome, cognome, email, indirizzo);
        }

        // Salvo nel DB l'oggetto Utente appena creato.
        return gestorePersistenza.salva(utente);
    }
}
