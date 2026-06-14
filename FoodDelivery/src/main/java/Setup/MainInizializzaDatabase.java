package Setup;

import Boundary.EffettuaOrdine.FrameRistorantiAperti;
import Boundary.FormHome;
import Controller.ControllerSessione;
import Database.JpaUtil;
import Entity.RuoloUtente;

import javax.swing.*;

public class MainInizializzaDatabase {

    public static void main(String[] args) {

        /*
         * Avvia Hibernate.
         *
         * Con hibernate.hbm2ddl.auto = update, Hibernate crea o aggiorna
         * le tabelle senza cancellare i dati già presenti.
         *
         * Con hibernate.hbm2ddl.auto = create, Hibernate elimina e ricrea
         * le tabelle a ogni avvio dell'applicazione.
         */
        JpaUtil.getInstance();

        // Popolamento di alcuni utenti per test.
        DatiTestDelivery.popolaUtenti();

        // Popolamento di ristoranti e piatti per test.
        DatiTestDelivery.popolaRistoranti();

        // Popolamenti di ordini per test.
        DatiTestDelivery.popolaOrdini();

        FormHome formHome = new FormHome();
        formHome.apriFormHome();

    }

    public static void inizializza() {

        /*
         * Avvia Hibernate.
         *
         * Con hibernate.hbm2ddl.auto = update, Hibernate crea o aggiorna
         * le tabelle senza cancellare i dati già presenti.
         *
         * Con hibernate.hbm2ddl.auto = create, Hibernate elimina e ricrea
         * le tabelle a ogni avvio dell'applicazione.
         */
        JpaUtil.getInstance();

        // Popolamento di alcuni utenti per test.
        DatiTestDelivery.popolaUtenti();

        // Popolamento di ristoranti e piatti per test.
        DatiTestDelivery.popolaRistoranti();

        // Popolamenti di ordini per test.
        DatiTestDelivery.popolaOrdini();

    }
}
