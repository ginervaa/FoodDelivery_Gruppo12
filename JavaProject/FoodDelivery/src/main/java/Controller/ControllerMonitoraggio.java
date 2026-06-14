// file: Controller/ControllerMonitoraggio.java
package Controller;

import Entity.Gestori.GestoreOrdini;
import Entity.Gestori.GestoreRistoranti;
import Entity.Ristorante;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller dedicato al caso d'uso "Monitoraggio Attività Piattaforma".
 * Accessibile solo dopo login con ruolo AMMINISTRATORE.
 *
 * Segue il pattern GRASP Controller: coordina esclusivamente
 * le tre funzionalità del caso d'uso di monitoraggio.
 * Delega al GestoreOrdini e al GestoreRistoranti le operazioni
 * sui dati, in accordo con il pattern GRASP Information Expert.
 */
public class ControllerMonitoraggio {

    /**
     * Scenario "Visualizza ordini in un intervallo di tempo".
     * Delega al GestoreOrdini che è l'Information Expert degli ordini.
     *
     * param dataInizioStr data inizio nel formato "YYYY-MM-DD"
     * param dataFineStr   data fine nel formato "YYYY-MM-DD"
     * return numero totale di ordini, oppure -1 in caso di errore
     */
    public static long visualizzaOrdiniTotali(LocalDate dataInizio, LocalDate dataFine) {

        GestoreOrdini gestoreOrdini = new GestoreOrdini();
        return gestoreOrdini.getNumeroOrdini(dataInizio, dataFine);
    }

    /**
     * Scenario "Visualizza ristoranti più attivi".
     * Delega al GestoreRistoranti che è l'Information Expert dei ristoranti.
     *
     * Ogni String[] contiene: [id, nome, numeroOrdini].
     *
     * return lista di righe pronte per la JTable, ordinata per attività
     */
    public static List<String[]> visualizzaRistorantiPiuAttivi() {
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();
        List<Ristorante> ristoranti = gestoreRistoranti.ristorantiPiuAttivi();

        List<String[]> righe = new ArrayList<>();
        for (Ristorante r : ristoranti) {
            righe.add(new String[]{
                    String.valueOf(r.getId()),
                    r.getNome(),
                    String.valueOf(r.getNumeroOrdini())
            });
        }
        return righe;
    }

    /**
     * Scenario "Visualizza volume medio degli ordini".
     * Delega al GestoreOrdini che è l'Information Expert degli ordini.
     *
     * @return stringa con la media formattata (es. "3.50"), o "N/D" se errore
     */
    public static String visualizzaVolumeMedioOrdini() {
        GestoreOrdini gestoreOrdini = new GestoreOrdini();
        double media = gestoreOrdini.volumeMedioOrdini();
        if (media < 0) return "N/D";
        return String.format("%.2f", media);
    }
}