package Controller;

import Entity.*;
import Entity.Gestori.GestoreRistoranti;

import java.util.ArrayList;
import java.util.List;

public class ControllerGestioneAttivita {

    // Restituisce alla GUI (Boundary) la lista dei ristoranti di cui l'utente loggato è proprietario.
    public static List<String[]> ottieniRistoranti(){

        // Genero una istanza di GestoreRistoranti.
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();

        // Ottengo il ristoratore attualmente loggato.
        Ristoratore proprietario = (Ristoratore) ControllerSessione.getUtenteLoggato();

        // Il GestoreRistoranti restituisce la lista dei ristoranti associati al proprietario.
        List<Ristorante> ristoranti = gestoreRistoranti.ricercaRistorantiPerProprietario(proprietario);

        // Creo la lista che verrà restituita alla Boundary.
        List<String[]> righe = new ArrayList<>();

        // Converto la lista di elementi Ristorante in array di stringhe.
        for (Ristorante ristorante : ristoranti) {

            String[] riga = new String[]{
                    String.valueOf(ristorante.getId()),
                    ristorante.getNome(),
                    ristorante.getDescrizione(),
                    ristorante.getIndirizzo()
            };

            righe.add(riga);
        }

        return righe;
    }


    // Realizza il caso d'uso "Creazione Profilo Ristorante".
    public static Long creaProfiloRistorante(String nome,
                                             String descrizione,
                                             String indirizzo,
                                             List<String[]> turniOrari)
    {
        // Genero una istanza di GestoreRistoranti.
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();

        // Ottengo il ristoratore attualmente loggato.
        Ristoratore proprietario = (Ristoratore) ControllerSessione.getUtenteLoggato();

        // Creo il nuovo ristorante associandolo al proprietario.
        Ristorante nuovoRistorante = gestoreRistoranti.creaRistoranteRitornaOggetto(
                nome,
                descrizione,
                indirizzo,
                proprietario
        );

        // Se il ristorante è stato creato correttamente, restituisce il suo identificativo.
        if(nuovoRistorante != null){

            // Ciclo su tutti i turni provenienti dalla JTable grafica della Boundary
            for (String[] turno : turniOrari) {
                java.time.DayOfWeek giorno = java.time.DayOfWeek.valueOf(turno[0]);
                java.time.LocalTime apertura = java.time.LocalTime.parse(turno[1]);
                java.time.LocalTime chiusura = java.time.LocalTime.parse(turno[2]);

                // Ristorante si occupa dell'associazione e del salvataggio nel DB.
                nuovoRistorante.aggiungiOrarioApertura(giorno, apertura, chiusura);
            }

            // Restituisce l'identificativo del ristorante associato ai suoi orari.
            return nuovoRistorante.getId();
        }

        // In caso di errore restituisce null.
        return null;
    }

    // Realizza il caso d'uso "Modifica Profilo Ristorante".
    public static boolean aggiornaProfiloRistorante(Long idRistorante,
                                                    String nome,
                                                    String descrizione,
                                                    String indirizzo)
    {
        // Genero una istanza di GestoreRistoranti.
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();

        return gestoreRistoranti.aggiornaRistorante(idRistorante, nome, descrizione, indirizzo);

        /*
        // GestoreRistoranti restituisce l'oggetto Ristorante ricercato in base all'id.
        Ristorante ristorante = gestoreRistoranti.ricercaRistorantePerId(idRistorante);

        // Controllo per verificare che il ristorante restituito non sia nullo.
        if (ristorante == null){
            return false;
        }

        // Il Ristorante aggiorna i propri dati e ritorna "true" se va a buon fine.
        return ristorante.aggiornaDatiRistorante(
                nome,
                descrizione,
                indirizzo
        );*/
    }

    public static boolean rimuoviRistorante(Long idRistorante)
    {
        // Genero una istanza di GestoreRistoranti.
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();

        // GestoreRistoranti restituisce l'oggetto Ristorante ricercato in base all'id.
        Ristorante ristorante = gestoreRistoranti.ricercaRistorantePerId(idRistorante);

        // Controllo per verificare che il ristorante restituito non sia nullo.
        if (ristorante == null){
            return false;
        }

        // Il GestoreRistoranti rimuove il ristorante.
        return gestoreRistoranti.rimuoviRistorante(idRistorante);
    }

    // Restituisce alla GUI (Boundary) la lista dei piatti presenti nel menu di un ristorante.
    public static List<String[]> ottieniPiatti(Long idRistorante){

        // Genero una istanza di GestoreRistoranti.
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();

        // Ottengo il ristorante ricercandolo tramite id.
        Ristorante ristorante = gestoreRistoranti.ricercaRistorantePerId(idRistorante);

        // Recupero il menu del ristorante.
        Menu menu = ristorante.getMenu();

        // Recupero la lista dei piatti presenti nel menu.
        List<Piatto> piatti = menu.getPiatti();

        // Creo la lista che verrà restituita alla Boundary.
        List<String[]> righe = new ArrayList<>();

        // Converto ogni piatto in una riga composta da stringhe.
        for (Piatto piatto : piatti) {

            String[] riga = new String[]{
                    String.valueOf(piatto.getId()),
                    piatto.getNome(),
                    piatto.getDescrizione(),
                    String.valueOf(piatto.getPrezzo())
            };

            righe.add(riga);
        }

        return righe;
    }

    // Realizza il caso d'uso "Gestione Menu Inserimento Piatto".
    public static boolean inserisciPiattoMenu(Long idRistorante,
                                              String nome,
                                              String descrizione,
                                              float prezzo)
    {
        // Genero una istanza di GestoreRistoranti.
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();

        // Ottengo il ristorante ricercandolo tramite id.
        Ristorante ristorante = gestoreRistoranti.ricercaRistorantePerId(idRistorante);

        // Controllo che il ristorante esista.
        if (ristorante == null){
            return false;
        }

        // Ottengo il menu associato al ristorante.
        Menu menu = ristorante.getMenu();

        // Aggiungo il nuovo piatto al menu.
        return menu.aggiungiPiatto(
                nome,
                descrizione,
                prezzo
        );
    }

    // Realizza il caso d'uso "Gestione Menu Modifica Piatto".
    public static boolean modificaPiattoMenu(Long idRistorante,
                                             Long idPiatto,
                                             String nome,
                                             String descrizione,
                                             float prezzo)
    {
        // Genero una istanza di GestoreRistoranti.
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();

        // GestoreRistoranti restituisce l'oggetto Ristorante ricercato in base all'id.
        Ristorante ristorante = gestoreRistoranti.ricercaRistorantePerId(idRistorante);

        // Controllo per verificare che il ristorante restituito non sia nullo.
        if (ristorante == null){
            return false;
        }

        // Ottengo il menu associato al ristorante selezionato.
        Menu menu = ristorante.getMenu();

        /* Il Menu ricerca il piatto tramite il suo id e ne aggiorna nome, descrizione
         e prezzo. Restituisce true se l'operazione viene completata correttamente. */
        return menu.aggiornaDatiPiatto(
                idPiatto,
                nome,
                descrizione,
                prezzo
        );
    }

    // Realizza il caso d'uso "Gestione Menu Elimina Piatto".
    public static boolean eliminaPiattoMenu(Long idRistorante,
                                            Long idPiatto)
    {
        // Genero una istanza di GestoreRistoranti.
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();

        // GestoreRistoranti restituisce l'oggetto Ristorante ricercato in base all'id.
        Ristorante ristorante = gestoreRistoranti.ricercaRistorantePerId(idRistorante);

        // Controllo per verificare che il ristorante restituito non sia nullo.
        if (ristorante == null){
            return false;
        }

        // Ottengo il menu associato al ristorante selezionato.
        Menu menu = ristorante.getMenu();

        /* Il Menu ricerca il piatto tramite il suo id e lo rimuove dall'elenco dei piatti disponibili.
         Restituisce true se la rimozione viene effettuata correttamente. */
        return menu.rimuoviPiatto(idPiatto);
    }
}
