package Controller;

import Entity.*;
import Entity.Gestori.GestoreUtenti;

public class ControllerSessione {

    // Memorizza l'id dell'utente attualmente autenticato nel sistema.
    //private static Long idUtenteLoggato = null;

    // Memorizza il riferimento all'oggetto Utente attualmente autenticato.
    private static Utente utenteLoggato = null;

    // Realizza il caso d'uso "Accesso Utente".
    public static boolean login(String username,
                                String password,
                                RuoloUtente ruolo)
    {
        // Genero una istanza di GestoreUtenti.
        GestoreUtenti gestoreUtenti = new GestoreUtenti();

        // GestoreUtenti restituisce l'oggetto Utente ricercato in base all'username.
        Utente utente = gestoreUtenti.ricercaUtentePerUsername(username);

        // Controllo per verificare che l'Utente restituito non sia nullo.
        if (utente == null){
            return false;
        }

        // L'Utente verifica che username, password e ruolo corrispondano ai dati
        // registrati nel sistema.
        boolean esito = utente.verificaCredenziali(username, password, ruolo);

        // Se la verifica delle credenziali va a buon fine:
        if (esito){

            // Aggiorno l'id dell'utente attualmente loggato.
            //idUtenteLoggato = utente.getId();

            // Memorizzo il riferimento all'utente loggato.
            utenteLoggato = utente;
        }

        // Restituisce true se il login è stato effettuato correttamente.
        return esito;
    }

    // Realizza il caso d'uso "Registrazione Utente".
    public static boolean registrazione(String username,
                                        String password,
                                        RuoloUtente ruolo,
                                        String nome,
                                        String cognome,
                                        String email,
                                        String indirizzo)
    {
        // Genero una istanza di GestoreUtenti.
        GestoreUtenti gestoreUtenti = new GestoreUtenti();

        /* GestoreUtenti crea il nuovo utente e lo salva nel sistema.
         Restituisce true se la registrazione viene completata correttamente. */
        return gestoreUtenti.creaUtente(
                username,
                password,
                nome,
                cognome,
                email,
                indirizzo,
                ruolo
        );
    }

    // Restituisce l'id dell'utente attualmente loggato.
    //public static Long getIdUtenteLoggato(){
        //return idUtenteLoggato;
    //}

    // Restituisce l'oggetto Utente attualmente loggato.
    public static Utente getUtenteLoggato(){
        return utenteLoggato;
    }
}
