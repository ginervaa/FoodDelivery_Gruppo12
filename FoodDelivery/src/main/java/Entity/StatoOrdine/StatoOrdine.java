package Entity.StatoOrdine;

import Entity.Ordine;

public interface StatoOrdine {

    void avanza(Ordine ordine);

    void annulla(Ordine ordine);

    NomeStatoOrdine getNomeStato();
}
