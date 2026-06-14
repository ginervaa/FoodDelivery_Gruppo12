package Entity.Gestori;

import Database.GestorePersistenza;
import Entity.*;
import Entity.StatoOrdine.StatoInviato;
import Entity.StatoOrdine.StatoOrdine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Questa classe è Information Expert della classe Ordine, non diventerà una tabella nel DB.
public class GestoreOrdini {

    // Il GestoreOrdini ha una propria istanza del GestorePersistenza.
    private GestorePersistenza gestorePersistenza;

    /* Il GestoreOrdini possiede una lista degli ordini, tuttavia in questo codice, poiché
           il GestoreOrdini si interfaccia direttamente con il database, è superflua. */
    // private List<Ordine> ordini;

    // Costruttore di GestoreOrdini.
    public GestoreOrdini() {
        // Ogni volta che un GestoreOrdini viene istanziato, viene istanziato anche il suo GestorePersistenza.
        gestorePersistenza = new GestorePersistenza();
    }

    // Crea un oggetto Ordine e lo salva nel DB, restituisce true se l'inserimento va a buon fine.
    public boolean creaOrdine(Cliente cliente,
                              Ristorante ristorante,
                              String indirizzoConsegna,
                              LocalDateTime momento)
    {
        // Preleva automaticamente il carrello e la lista dei piatti dall'oggetto Cliente.
        Carrello carrello = cliente.getCarrello();
        List<Piatto> listaPiatti = carrello.getPiatti();

        // Verifica che il carrello non sia vuoto.
        if (listaPiatti.isEmpty()) {
            return false;
        }

        // Crea il nuovo oggetto Ordine con lo stato "INVIATO".
        Ordine ordine = new Ordine(cliente, ristorante, momento, indirizzoConsegna, listaPiatti, new StatoInviato());

        // Salva l'oggetto Ordine nel DB.
        return gestorePersistenza.salva(ordine);
    }

    /**
     * Conta gli ordini il cui campo dataOrdine è compreso tra le due date.
     */
    public long getNumeroOrdini(LocalDate dataInizio, LocalDate dataFine) {

        try {

            // Serve a convertire il tipo "Date" in ingresso in "DateTime", poiché nel DB gli ordini hanno
            // un attributo "dataOrdine" di tipo DateTime.
            LocalDateTime dataTimeInizio = LocalDateTime.of(dataInizio, LocalTime.of(0,0,0));
            LocalDateTime dataTimeFine   = LocalDateTime.of(dataFine, LocalTime.of(23,59,59));

            if (dataInizio.isAfter(dataFine)) return -1;

            String jpql = "SELECT COUNT(o) FROM Ordine o WHERE o.dataOrdine >= :inizio AND o.dataOrdine <= :fine";

            // Usiamo Map.of per passare i parametri in modo pulito
            java.util.Map<String, Object> parametri = java.util.Map.of(
                    "inizio", dataTimeInizio,
                    "fine", dataTimeFine
            );

            Long risultato = gestorePersistenza.eseguiQuerySingola(jpql, Long.class, parametri);
            return risultato != null ? risultato : 0L;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * Calcola il numero medio di piatti per ordine.
     */
    public double volumeMedioOrdini() {
        String jpql = "SELECT AVG(SIZE(o.listaPiatti)) FROM Ordine o";

        // Passiamo null perché non ci sono parametri dinamici
        Double risultato = gestorePersistenza.eseguiQuerySingola(jpql, Double.class, null);
        return risultato != null ? risultato : 0.0;
    }

}
