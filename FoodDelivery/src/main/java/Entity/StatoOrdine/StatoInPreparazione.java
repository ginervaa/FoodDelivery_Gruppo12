package Entity.StatoOrdine;

import Entity.Ordine;

// Lo stato concreto implementa i metodi dell'interfaccia StatoOrdine.
public class StatoInPreparazione implements StatoOrdine {

    @Override
    public void avanza(Ordine ordine) {
        // L'ordine passa dallo stato "InPreparazione" a "InConsegna".
        ordine.setStato(new StatoInConsegna());
    }

    @Override
    public void annulla(Ordine ordine) {
        // Nessuna transizione prevista poiché non si può annullare un ordine già accettato.
    }

    @Override
    public NomeStatoOrdine getNomeStato() {
        return NomeStatoOrdine.IN_PREPARAZIONE;
    }
}
