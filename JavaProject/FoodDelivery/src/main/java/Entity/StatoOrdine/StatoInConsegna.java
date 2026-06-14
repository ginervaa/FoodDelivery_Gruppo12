package Entity.StatoOrdine;

import Entity.Ordine;

// Lo stato concreto implementa i metodi dell'interfaccia StatoOrdine.
public class StatoInConsegna implements StatoOrdine {

    @Override
    public void avanza(Ordine ordine) {
        // L'ordine passa dallo stato "InConsegna" a "Consegnato".
        ordine.setStato(new StatoConsegnato());
    }

    @Override
    public void annulla(Ordine ordine) {
        // Nessuna transizione prevista poiché non si può annullare un ordine già in consegna.
    }

    @Override
    public NomeStatoOrdine getNomeStato() {
        return NomeStatoOrdine.IN_CONSEGNA;
    }
}
