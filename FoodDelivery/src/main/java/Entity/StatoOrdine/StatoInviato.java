package Entity.StatoOrdine;

import Entity.Ordine;

// Lo stato concreto implementa i metodi dell'interfaccia StatoOrdine.
public class StatoInviato implements StatoOrdine {

    @Override
    public void avanza(Ordine ordine){
        // L'ordine passa dallo stato "Inviato" a "InPreparazione".
        ordine.setStato(new StatoInPreparazione());
    }

    @Override
    public void annulla(Ordine ordine) {
        // Se il ristorante rifiuta l'ordine, questo passa da "Inviato" a "Rifiutato".
        ordine.setStato(new StatoRifiutato());
    }

    @Override
    public NomeStatoOrdine getNomeStato() {
        return NomeStatoOrdine.INVIATO;
    }
}
