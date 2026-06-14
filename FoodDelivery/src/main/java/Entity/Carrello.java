package Entity;

import java.util.ArrayList;
import java.util.List;

// Questa classe non viene annotata con @Entity poiché non rappresenterà una tabella del DB.
public class Carrello {

    private Cliente cliente; // Ogni Carrello ha un proprio utente di tipo Cliente come proprietario.

    private List<Piatto> piatti = new ArrayList<>();; // Ogni Carrello ha una lista di piatti.

    // Costruttore di Carrello
    public Carrello(Cliente cliente){

        // Pongo il Cliente passato per parametro come "proprietario" del carrello.
        this.cliente = cliente;

        // Messaggio di esito in console.
        System.out.println("Carrello di " + cliente.getUsername() + " creato.");
    }

    // Funzione getter della lista dei piatti.
    public List<Piatto> getPiatti(){
        return piatti;
    }

    // Restituisce l'oggetto Piatto ricercato in base all'id, se si trova nel carrello.
    public Piatto ricercaPiatto(Long idPiatto) {

        // Ciclo che controlla ogni piatto del carrello.
        for (Piatto p : piatti) {
            if (p.getId().equals(idPiatto)) {
                return p;
            }
        }

        // Se non è stato trovato il piatto nel carrello, restituisce null.
        return null;

    }

    // Consente di aggiungere un piatto, passato per parametro, alla lista dei piatti del Carrello.
    public boolean aggiungiPiatto(Piatto piatto)
    {
        // Verifica prima se la lista dei piatti esiste.
        if(this.piatti == null){
            // Se non esiste, la crea.
            this.piatti = new ArrayList<>();
        }

        // Aggiunge il piatto alla lista dei piatti.
        piatti.add(piatto);

        return true;
    }

    // Serve a svuotare la lista dei piatti una volta che il Cliente invia l'ordine.
    public void svuotaCarrello() {
        piatti.clear();
    }

    // Override della funzione toString, solo per stampare a video i piatti presenti nel Carrello.
    @Override
    public String toString(){
        StringBuilder stringa = new StringBuilder();
        for (Piatto piatto : piatti){
            stringa.append(piatto.getNome());
            stringa.append("\n");
        }
        return stringa.toString();
    }
}
