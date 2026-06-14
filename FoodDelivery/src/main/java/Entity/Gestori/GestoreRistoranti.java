package Entity.Gestori;

import Database.GestorePersistenza;
import Entity.Ristorante;
import Entity.Ristoratore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Questa classe è Information Expert della classe Ristorante, non diventerà una tabella nel DB.
public class GestoreRistoranti {

    // Il GestoreRistoranti ha una propria istanza del GestorePersistenza.
    private GestorePersistenza gestorePersistenza;

    /* Il GestoreRistoranti possiede una lista dei ristoranti, tuttavia in questo codice, poiché
           il GestoreRistoranti si interfaccia direttamente con il database, è superflua. */
    // private List<Ristorante> ristoranti;

    // Costruttore di GestoreRistoranti.
    public GestoreRistoranti(){
        // Ogni volta che un GestoreRistoranti viene istanziato, viene istanziato anche il suo GestorePersistenza.
        gestorePersistenza = new GestorePersistenza();
    }

    public Ristorante ricercaRistorantePerId(Long idRistorante)
    {
        return gestorePersistenza.trovaPerId(Ristorante.class, idRistorante);
    }

    public List<Ristorante> ricercaRistorantiPerProprietario(Ristoratore proprietario)
    {
        return gestorePersistenza.cercaPerCampo(Ristorante.class, "proprietario.id", proprietario.getId());
    }

    // Crea un oggetto Ristorante e lo salva nel DB, restituisce true se l'inserimento va a buon fine.
    public boolean creaRistorante(String nome,
                                  String descrizione,
                                  String indirizzo,
                                  Ristoratore proprietario)
    {
        return creaRistoranteRitornaOggetto(nome, descrizione, indirizzo, proprietario) != null;
    }

    // Crea un oggetto Ristorante e lo salva nel DB, restituisce l'oggetto Ristorante.
    public Ristorante creaRistoranteRitornaOggetto(String nome,
                                  String descrizione,
                                  String indirizzo,
                                  Ristoratore proprietario)
    {
        // Creo il nuovo oggetto Ristorante.
        Ristorante ristorante = new Ristorante(nome, descrizione, indirizzo, proprietario);

        // Salvo l'oggetto Ristorante appena creato nel DB.
        gestorePersistenza.salva(ristorante);

        // Restituisco il Ristorante creato.
        return ristorante;
    }

    public boolean aggiornaRistorante(Long idRistorante,
                                      String nome,
                                      String descrizione,
                                      String indirizzo)
    {

        // Ricerco il ristorante per id.
        Ristorante ristorante = gestorePersistenza.trovaPerId(Ristorante.class, idRistorante);

        // Aggiorno l'oggetto ristorante.
        ristorante.aggiornaDatiRistorante(nome, descrizione, indirizzo);

        // Aggiorno il database.
        ristorante = gestorePersistenza.aggiorna(ristorante);

        return ristorante != null;
    }

    public boolean rimuoviRistorante(Long idRistorante)
    {
        return gestorePersistenza.elimina(
                Ristorante.class,
                idRistorante
        );
    }

    public List<Ristorante> getRistoranti()
    {
        return gestorePersistenza.cercaPerCampi(Ristorante.class, Map.of());
    }

    // Restituisce la una List di ristoranti aperti.
    public List<Ristorante> getRistorantiAperti(){

        // Prelevo dal DB la lista di tutti i ristoranti.
        List<Ristorante> ristoranti = gestorePersistenza.cercaPerCampi(Ristorante.class, Map.of());

        // Creo una lista che andrà a contenere solamente i ristoranti aperti.
        List<Ristorante> ristorantiAperti = new ArrayList<>();

        // Verifico per ogni ristorante se questo è aperto, e in tal caso lo aggiungo alla lista ristorantiAperti.
        for (Ristorante r : ristoranti) {
            System.out.println(r.getNome());
            if (r.isAperto(LocalDateTime.now())) {
                ristorantiAperti.add(r);
            }
        }

        return ristorantiAperti;
    }

    /**
     * Restituisce tutti i ristoranti ordinati per numero di ordini ricevuti (decrescente).
     */
    public List<Ristorante> ristorantiPiuAttivi() {
        String jpql = "SELECT o.ristorante FROM Ordine o GROUP BY o.ristorante ORDER BY COUNT(o) DESC";

        // Eseguiamo la query senza parametri aggiuntivi
        List<Ristorante> risultati = gestorePersistenza.eseguiQueryLista(jpql, Ristorante.class, null);

        // Restituisce la lista, o una lista vuota in caso di problemi (evitando NullPointerException)
        return risultati != null ? risultati : new ArrayList<>();
    }
}
