package Controller;

import Entity.*;
import Entity.Gestori.GestoreOrdini;
import Entity.Gestori.GestoreRistoranti;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Realizza i casi d'uso "Effettua Ordine" e "Ricerca Ristoranti Aperti".
public class ControllerEffettuaOrdine {

    // Restituisce alla GUI (Boundary) la lista dei ristoranti aperti.
    /* NOTA: Si tratta di una lista di array di String, non una lista di oggetti Ristorante,
       questo per garantire disaccoppiamento tra Boundary ed Entity, evitare che la GUI
       possa manipolare direttamente lo stato interno delle entità, o di risentire di futuri
       modifiche strutturali del database. */
    public static List<String[]> ricercaRistorantiAperti(){

        // Istanzio un GestoreRistoranti.
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();

        // Prelevo la lista di tutti i ristoranti aperti dal GestoreRistoranti.
        List<Ristorante> ristorantiAperti = gestoreRistoranti.getRistorantiAperti();

        // Creo la lista da restituire alla Boundary, ogni elemento sarà una riga della tabella nel DB.
        List<String[]> righe = new ArrayList<>();

        // Converto la lista di elementi Ristorante in una lista di String, che verrà restituita alla Boundary.
        for (Ristorante ristorante : ristorantiAperti) {

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

    // Restituisce alla GUI (Boundary) la lista dei piatti presenti nel menu di un ristorante.
    /* NOTA: Si tratta di una lista di array di String, non una lista di oggetti Piatto,
       questo per garantire disaccoppiamento tra Boundary ed Entity, evitare che la GUI
       possa manipolare direttamente lo stato interno delle entità, o di risentire di futuri
       modifiche strutturali del database. */
    public static List<String[]> consultaMenuRistorante(Long idRistorante){

        // Istanzio un GestoreRistoranti.
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();

        // Prelevo l'oggetto Ristorante ricercato in base all'id dal GestoreRistoranti.
        Ristorante ristorante = gestoreRistoranti.ricercaRistorantePerId(idRistorante);

        // Prelevo la lista dei piatti del ristorante.
        Menu menu = ristorante.getMenu();
        List<Piatto> piatti = menu.getPiatti();

        // Creo la lista da restituire alla Boundary, ogni elemento sarà una riga della tabella nel DB.
        List<String[]> righe = new ArrayList<>();

        // Converto la lista di elementi Piatto in una lista di String, che verrà restituita alla Boundary.
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

    // Consente all'utente di aggiungere un piatto al proprio carrello.
    public static boolean aggiungiPiattoAlCarrello(Long idRistorante, Long idPiatto)
    {
        boolean esito;

        // Utilizzo la variabile interna del ControllerSessione per gestire il carrello dell'utente loggato.
        Cliente cliente = (Cliente) ControllerSessione.getUtenteLoggato();

        // Controllo per verificare che l'Utente restituito non sia null.
        if (cliente == null){
            System.out.println("Utente nullo");
            return false;
        }

        // Crea e ottiene l'oggetto Carrello legato all'utente loggato attualmente.
        Carrello carrello = cliente.getCarrello();

        // Controllo per verificare che il Carrello restituito non sia null.
        if(carrello == null){
            System.out.println(cliente.getId());
            System.out.println("Carrello nullo");
            return false;
        }

        // Prelevo l'oggetto Piatto che sarà aggiunto al Carrello, ricercandolo per id.
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();
        Ristorante ristorante = gestoreRistoranti.ricercaRistorantePerId(idRistorante);
        Menu menu = ristorante.getMenu();
        Piatto piatto = menu.ricercaPiatto(idPiatto);

        // Se l'aggiunta del piatto va a buon fine, restituisce "true".
        esito = carrello.aggiungiPiatto(piatto);

        System.out.println(cliente.getCarrello().toString());

        return esito;
    }

    public static boolean isCarrelloVuoto() {
        Cliente cliente = (Cliente) ControllerSessione.getUtenteLoggato();
        Carrello carrello = cliente.getCarrello();
        if (carrello.getPiatti().isEmpty()){
            return true;
        }
        return false;
    }

    public static List<Piatto> getPiattiCarrello(){
        Cliente cliente = (Cliente) ControllerSessione.getUtenteLoggato();
        Carrello carrello = cliente.getCarrello();
        List<Piatto> piattiCarrello = carrello.getPiatti();
        return piattiCarrello;
    }

    // Permette di inviare l'ordine, fa parte del caso d'uso "Effettua Ordine".
    public static boolean inviaOrdine(Long idRistorante, String indirizzo)
    {
        boolean esito;

        Cliente cliente = (Cliente) ControllerSessione.getUtenteLoggato();

        // Genero una istanza di GestoreOrdini.
        GestoreOrdini gestoreOrdini = new GestoreOrdini();

        // Ottengo l'oggetto Carrello dell'utente attualmente loggato, in cui sono presenti i piatti da ordinare.
        // Carrello carrello = utenteLoggato.getCarrello();

        // Ottengo la lista dei piatti presenti nel Carrello.
        // List<Piatto> piatti = carrello.getPiatti();

        // Ottengo l'oggetto Ristorante da passare al metodo creaOrdine.
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();
        Ristorante ristorante = gestoreRistoranti.ricercaRistorantePerId(idRistorante);

        // Creo l'oggetto Ordine associato al nuovo ordine, e se questo viene creato con successo, ritorna "true".
        esito = gestoreOrdini.creaOrdine(cliente, ristorante, indirizzo, LocalDateTime.now());

        // Se l'ordine viene creato correttamente, il carrello del cliente loggato viene svuotato.
        if (esito == true) {
            cliente.getCarrello().svuotaCarrello();
            System.out.println("Carrello di utente " + cliente.getId() + " svuotato.");
        }

        return esito;
    }

}
