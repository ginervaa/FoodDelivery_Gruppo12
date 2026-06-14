package Entity.StatoOrdine;

import Entity.Ordine;

// Lo stato concreto implementa i metodi dell'interfaccia StatoOrdine.
public class StatoConsegnato implements StatoOrdine {

    @Override
    public void avanza(Ordine ordine) {
        // Nessuna transizione prevista, l'ordine ha raggiunto uno stato finale.
    }

    @Override
    public void annulla(Ordine ordine) {
        // Nessuna transizione prevista, l'ordine ha raggiunto uno stato finale.
    }

    @Override
    public NomeStatoOrdine getNomeStato() {
        return NomeStatoOrdine.CONSEGNATO;
    }
}
