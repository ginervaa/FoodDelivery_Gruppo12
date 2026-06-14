package Setup;

import Database.GestorePersistenza;
import Entity.Cliente;
import Entity.Gestori.GestoreOrdini;
import Entity.Gestori.GestoreRistoranti;
import Entity.Gestori.GestoreUtenti;
import Entity.Ristorante;
import Entity.Ristoratore;
import Entity.RuoloUtente;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DatiTestDelivery {


    public static void popolaUtenti(){

        GestoreUtenti gestoreUtenti = new GestoreUtenti();

        gestoreUtenti.creaUtente("admin",
                "admin","Admin","Istrator",
                "admin@admin.com","Via Admin, 1", RuoloUtente.AMMINISTRATORE);


        gestoreUtenti.creaUtente("silvato",
                "silvato","Silverio","Marrazzo",
                "silverio@ids.it","Via Del Software, 10", RuoloUtente.CLIENTE);

        gestoreUtenti.creaUtente("michele",
                "michele","Michele","Pizzaiolo",
                "michele@pizza.it","Via Napoli, 22", RuoloUtente.RISTORATORE);

        gestoreUtenti.creaUtente("sorbillo",
                "sorbillo","Gino","Sorbillo",
                "gino@pizza.it","Via Dei Tribunali, 4", RuoloUtente.RISTORATORE);

        gestoreUtenti.creaUtente("don_antonio",
                "don_antonio","Antonio","Polese",
                "donantonio@castello.it"," Via Stabia, 500", RuoloUtente.RISTORATORE);

        gestoreUtenti.creaUtente("mcdonald",
                "mcdonald","Mr.","McDonald",
                "mcdonald@mcdonald.com","Via Roma, 30", RuoloUtente.RISTORATORE);

        gestoreUtenti.creaUtente("gigino",
                "gigino","Gigino","Legend",
                "gig@gino.it","Via Resina, 14", RuoloUtente.RISTORATORE);

        gestoreUtenti.creaUtente("dorian",
                "dorian","Dorian","Gray",
                "dorian@gray.it","Via Giorgio, 1", RuoloUtente.RISTORATORE);

        gestoreUtenti.creaUtente("admin2",
                "admin2","Alessandro","Fratmo","admin2@delivery.it",
                "Via Roma, 100", RuoloUtente.AMMINISTRATORE);

        gestoreUtenti.creaUtente("mario_rossi",
                "mario","Mario","Rossi","mario@gmail.com",
                "Via Toledo, 50", RuoloUtente.CLIENTE);

        gestoreUtenti.creaUtente("sara_v",
                "sara","Sara","Viggiano","sara@books.it",
                "Corso Umberto, 12", RuoloUtente.CLIENTE);

        gestoreUtenti.creaUtente("luca_g",
                "luca","Luca","Gialli","luca@outlook.it",
                "Via dei Mille, 5", RuoloUtente.CLIENTE);

        gestoreUtenti.creaUtente("ciruzz",
                "ciruzz","Ciro","Esposito","ciro@uzzo.it",
                "Via Chiaia, 80", RuoloUtente.RISTORATORE);

        gestoreUtenti.creaUtente("astronauta00",
                "frootloops","Howard","Wolowitz","howard@rocket.com",
                "Piazza Municipio, 1", RuoloUtente.RISTORATORE);

    }

    public static void popolaRistoranti() {

        GestorePersistenza gestorePersistenza = new GestorePersistenza();
        GestoreUtenti gestoreUtenti = new GestoreUtenti();
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();

        Ristoratore ristoratore1 = (Ristoratore) gestoreUtenti.ricercaUtentePerUsername("michele");
        Ristorante ristorante1 = gestoreRistoranti.creaRistoranteRitornaOggetto("Pizzeria Da Michele",
                "Dal 1870","Via Napoli, 22", ristoratore1);
        ristorante1.aggiungiOrarioApertura(DayOfWeek.MONDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante1.aggiungiOrarioApertura(DayOfWeek.TUESDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante1.aggiungiOrarioApertura(DayOfWeek.WEDNESDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante1.aggiungiOrarioApertura(DayOfWeek.THURSDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante1.aggiungiOrarioApertura(DayOfWeek.FRIDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante1.aggiungiOrarioApertura(DayOfWeek.SATURDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante1.aggiungiOrarioApertura(DayOfWeek.SUNDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante1.getMenu().aggiungiPiatto("Margherita", "Pomodoro, fiordilatte, basilico.", 6f);
        ristorante1.getMenu().aggiungiPiatto("Marinara","Pomodoro, origano, aglio.", 5f);

        Ristoratore ristoratore2 = (Ristoratore) gestoreUtenti.ricercaUtentePerUsername("sorbillo");
        Ristorante ristorante2 = gestoreRistoranti.creaRistoranteRitornaOggetto("Pizzeria Sorbillo",
                "La pizzeria più famosa di Napoli","Via Dei Tribunali, 4", ristoratore2);
        ristorante2.aggiungiOrarioApertura(DayOfWeek.MONDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante2.aggiungiOrarioApertura(DayOfWeek.TUESDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante2.aggiungiOrarioApertura(DayOfWeek.WEDNESDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante2.aggiungiOrarioApertura(DayOfWeek.THURSDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante2.aggiungiOrarioApertura(DayOfWeek.FRIDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante2.aggiungiOrarioApertura(DayOfWeek.SATURDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante2.aggiungiOrarioApertura(DayOfWeek.SUNDAY, LocalTime.of(12, 0), LocalTime.of(23, 30));
        ristorante2.getMenu().aggiungiPiatto("Margherita","Pomodoro, fiordilatte, basilico.", 6f);
        ristorante2.getMenu().aggiungiPiatto("Pizza fritta","Ricotta, provola, cicoli.", 7f);

        Ristoratore ristoratore3 = (Ristoratore) gestoreUtenti.ricercaUtentePerUsername("don_antonio");
        Ristorante ristorante3 = gestoreRistoranti.creaRistoranteRitornaOggetto("Castello La Sonrisa",
                "Nu matrimonio napulitano","Via Stabia, 500", ristoratore3);
        ristorante3.aggiungiOrarioApertura(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(23, 30));
        ristorante3.aggiungiOrarioApertura(DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(23, 30));
        ristorante3.aggiungiOrarioApertura(DayOfWeek.WEDNESDAY, LocalTime.of(9, 0), LocalTime.of(23, 30));
        ristorante3.aggiungiOrarioApertura(DayOfWeek.THURSDAY, LocalTime.of(9, 0), LocalTime.of(23, 30));
        ristorante3.aggiungiOrarioApertura(DayOfWeek.FRIDAY, LocalTime.of(9, 0), LocalTime.of(23, 30));
        ristorante3.aggiungiOrarioApertura(DayOfWeek.SATURDAY, LocalTime.of(9, 0), LocalTime.of(23, 30));
        ristorante3.aggiungiOrarioApertura(DayOfWeek.SUNDAY, LocalTime.of(9, 0), LocalTime.of(23, 30));
        ristorante3.getMenu().aggiungiPiatto("Pasta e patate","'A past e patan ca provol.", 15f);
        ristorante3.getMenu().aggiungiPiatto("Pasta e fagioli","'A past e fasul che cozz.", 15f);
        ristorante3.getMenu().aggiungiPiatto("Antipasto di mare","Cozze, crudo di pesce, frittura.", 15f);
        ristorante3.getMenu().aggiungiPiatto("Linguine con astice","I famosi linguini con l'astice", 20f);

        Ristoratore ristoratore4 = (Ristoratore) gestoreUtenti.ricercaUtentePerUsername("mcdonald");
        Ristorante ristorante4 = gestoreRistoranti.creaRistoranteRitornaOggetto("McDonald's",
                "Fast food americano","Via Roma, 30", ristoratore4);
        ristorante4.aggiungiOrarioApertura(DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(23, 30));
        ristorante4.aggiungiOrarioApertura(DayOfWeek.TUESDAY, LocalTime.of(8, 0), LocalTime.of(23, 30));
        ristorante4.aggiungiOrarioApertura(DayOfWeek.WEDNESDAY, LocalTime.of(8, 0), LocalTime.of(23, 30));
        ristorante4.aggiungiOrarioApertura(DayOfWeek.THURSDAY, LocalTime.of(8, 0), LocalTime.of(23, 30));
        ristorante4.aggiungiOrarioApertura(DayOfWeek.FRIDAY, LocalTime.of(8, 0), LocalTime.of(23, 30));
        ristorante4.aggiungiOrarioApertura(DayOfWeek.SATURDAY, LocalTime.of(8, 0), LocalTime.of(23, 30));
        ristorante4.aggiungiOrarioApertura(DayOfWeek.SUNDAY, LocalTime.of(8, 0), LocalTime.of(23, 30));
        ristorante4.getMenu().aggiungiPiatto("Big Mac","Hamburger, insalata, salsa Big Mac.", 7f);
        ristorante4.getMenu().aggiungiPiatto("Crispy McBacon","Hamburger, Bacon, salsa Crispy.", 7f);
        ristorante4.getMenu().aggiungiPiatto("Patatine fritte","Patate, olio, sale", 5f);
        ristorante4.getMenu().aggiungiPiatto("Coca Cola","Drink Cola", 3f);

        Ristoratore ristoratore5 = (Ristoratore) gestoreUtenti.ricercaUtentePerUsername("gigino");
        Ristorante ristorante5 = gestoreRistoranti.creaRistoranteRitornaOggetto("Gigino",
                "Gigino è sempre un amico","Via Resina, 14", ristoratore5);
        ristorante5.aggiungiOrarioApertura(DayOfWeek.MONDAY, LocalTime.of(22, 0), LocalTime.of(23, 59, 59));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.MONDAY, LocalTime.of(0, 0), LocalTime.of(7, 0));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.TUESDAY, LocalTime.of(22, 0), LocalTime.of(23, 59, 59));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.TUESDAY, LocalTime.of(0, 0), LocalTime.of(7, 0));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.WEDNESDAY, LocalTime.of(22, 0), LocalTime.of(23, 59, 59));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.WEDNESDAY, LocalTime.of(0, 0), LocalTime.of(7, 0));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.THURSDAY, LocalTime.of(22, 0), LocalTime.of(23, 59, 59));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.THURSDAY, LocalTime.of(0, 0), LocalTime.of(7, 0));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.FRIDAY, LocalTime.of(22, 0), LocalTime.of(23, 59, 59));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.FRIDAY, LocalTime.of(0, 0), LocalTime.of(7, 0));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.SATURDAY, LocalTime.of(22, 0), LocalTime.of(23, 59, 59));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.SATURDAY, LocalTime.of(0, 0), LocalTime.of(7, 0));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.SUNDAY, LocalTime.of(22, 0), LocalTime.of(23, 59, 59));
        ristorante5.aggiungiOrarioApertura(DayOfWeek.SUNDAY, LocalTime.of(0, 0), LocalTime.of(7, 0));
        ristorante5.getMenu().aggiungiPiatto("Gigino","Hamburger, provola, patatine.", 7f);

        Ristoratore ristoratore6 = (Ristoratore) gestoreUtenti.ricercaUtentePerUsername("dorian");
        Ristorante ristorante6 = gestoreRistoranti.creaRistoranteRitornaOggetto("Dorian Gray Pub",
                "Pub irlandese","Via Giorgio, 1", ristoratore6);
        ristorante6.aggiungiOrarioApertura(DayOfWeek.MONDAY, LocalTime.of(19, 0), LocalTime.of(23, 59, 59));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.MONDAY, LocalTime.of(0, 0), LocalTime.of(2, 0));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.TUESDAY, LocalTime.of(19, 0), LocalTime.of(23, 59, 59));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.TUESDAY, LocalTime.of(0, 0), LocalTime.of(2, 0));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.WEDNESDAY, LocalTime.of(19, 0), LocalTime.of(23, 59, 59));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.WEDNESDAY, LocalTime.of(0, 0), LocalTime.of(2, 0));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.THURSDAY, LocalTime.of(19, 0), LocalTime.of(23, 59, 59));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.THURSDAY, LocalTime.of(0, 0), LocalTime.of(2, 0));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.FRIDAY, LocalTime.of(19, 0), LocalTime.of(23, 59, 59));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.FRIDAY, LocalTime.of(0, 0), LocalTime.of(2, 0));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.SATURDAY, LocalTime.of(19, 0), LocalTime.of(23, 59, 59));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.SATURDAY, LocalTime.of(0, 0), LocalTime.of(2, 0));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.SUNDAY, LocalTime.of(19, 0), LocalTime.of(23, 59, 59));
        ristorante6.aggiungiOrarioApertura(DayOfWeek.SUNDAY, LocalTime.of(0, 0), LocalTime.of(2, 0));
        ristorante6.getMenu().aggiungiPiatto("George Best","Hamburger, cheddar, bacon.", 10f);
        ristorante6.getMenu().aggiungiPiatto("Freddie Mercury","Hamburger, pomodoro, insalata.", 10f);
        ristorante6.getMenu().aggiungiPiatto("David Bowie","Porchetta, provola, patatine.", 10f);

        Ristoratore ristoratore7 = (Ristoratore) gestoreUtenti.ricercaUtentePerUsername("ciruzz");
        Ristorante ristorante7 = gestoreRistoranti.creaRistoranteRitornaOggetto("Trattoria Da Ciro",
                "Cucina tipica napoletana","Via Chiaia, 80", ristoratore7);
        ristorante7.aggiungiOrarioApertura(DayOfWeek.MONDAY, LocalTime.of(12, 30), LocalTime.of(15, 30));
        ristorante7.aggiungiOrarioApertura(DayOfWeek.MONDAY, LocalTime.of(19, 30), LocalTime.of(23, 30));
        ristorante7.aggiungiOrarioApertura(DayOfWeek.TUESDAY, LocalTime.of(12, 30), LocalTime.of(15, 30));
        ristorante7.aggiungiOrarioApertura(DayOfWeek.TUESDAY, LocalTime.of(19, 30), LocalTime.of(23, 30));
        ristorante7.aggiungiOrarioApertura(DayOfWeek.WEDNESDAY, LocalTime.of(12, 30), LocalTime.of(15, 30));
        ristorante7.aggiungiOrarioApertura(DayOfWeek.WEDNESDAY, LocalTime.of(19, 30), LocalTime.of(23, 30));
        ristorante7.getMenu().aggiungiPiatto("Ziti alla Genovese", "Cipolla e carne di manzo.", 12f);
        ristorante7.getMenu().aggiungiPiatto("Ragù Napoletano", "Cottura lenta di 6 ore.", 13f);

        Ristoratore ristoratore8 = (Ristoratore) gestoreUtenti.ricercaUtentePerUsername("astronauta00");
        Ristorante ristorante8 = gestoreRistoranti.creaRistoranteRitornaOggetto("Raj Sushi Fusion",
                "Il giapponese indiano","Via Partenope, 12", ristoratore8);
        ristorante8.aggiungiOrarioApertura(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(23, 30));
        ristorante8.aggiungiOrarioApertura(DayOfWeek.TUESDAY, LocalTime.of(10, 0), LocalTime.of(23, 30));
        ristorante8.aggiungiOrarioApertura(DayOfWeek.WEDNESDAY, LocalTime.of(10, 0), LocalTime.of(23, 30));
        ristorante8.aggiungiOrarioApertura(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(23, 30));
        ristorante8.aggiungiOrarioApertura(DayOfWeek.FRIDAY, LocalTime.of(10, 0), LocalTime.of(23, 30));
        ristorante8.aggiungiOrarioApertura(DayOfWeek.SATURDAY, LocalTime.of(10, 0), LocalTime.of(23, 30));
        ristorante8.aggiungiOrarioApertura(DayOfWeek.SUNDAY, LocalTime.of(10, 0), LocalTime.of(23, 30));
        ristorante8.getMenu().aggiungiPiatto("Uramaki", "Riso, salmone e tartufo.", 14f);
        ristorante8.getMenu().aggiungiPiatto("Sashimi Mix", "Pezzi di pesce crudo misto.", 16f);

    }

    public static void popolaOrdini() {

        GestoreOrdini gestoreOrdini = new GestoreOrdini();
        GestoreUtenti gestoreUtenti = new GestoreUtenti();
        GestoreRistoranti gestoreRistoranti = new GestoreRistoranti();

        // Recuperiamo un cliente per effettuare tutti gli ordini di test
        Cliente cliente = (Cliente) gestoreUtenti.ricercaUtentePerUsername("silvato");

        // Recuperiamo i tre ristoranti richiesti dal Test Plan
        Ristorante daMichele = gestoreRistoranti.ricercaRistorantePerId(1L); // Pizzeria Da Michele
        Ristorante sorbillo = gestoreRistoranti.ricercaRistorantePerId(2L);  // Pizzeria Sorbillo
        Ristorante mcDonalds = gestoreRistoranti.ricercaRistorantePerId(4L); // McDonald's

        // --- RISTORANTE 1: Da Michele (3 Ordini) ---

        // Ordine 1: 1 Piatto
        cliente.getCarrello().aggiungiPiatto(daMichele.getMenu().ricercaPiatto(1L));
        gestoreOrdini.creaOrdine(cliente, daMichele, "Via Del Software, 10",
                LocalDateTime.of(2026, 6, 12, 13, 30));
        cliente.getCarrello().svuotaCarrello(); // <-- SVUOTIAMO IL CARRELLO

        // Ordine 2: 2 Piatti
        cliente.getCarrello().aggiungiPiatto(daMichele.getMenu().ricercaPiatto(1L));
        cliente.getCarrello().aggiungiPiatto(daMichele.getMenu().ricercaPiatto(2L));
        gestoreOrdini.creaOrdine(cliente, daMichele, "Via Del Software, 10",
                LocalDateTime.of(2026, 6, 13, 13, 0));
        cliente.getCarrello().svuotaCarrello(); // <-- SVUOTIAMO IL CARRELLO

        // Ordine 3: 2 Piatti
        cliente.getCarrello().aggiungiPiatto(daMichele.getMenu().ricercaPiatto(2L));
        cliente.getCarrello().aggiungiPiatto(daMichele.getMenu().ricercaPiatto(2L));
        gestoreOrdini.creaOrdine(cliente, daMichele, "Via Del Software, 10",
                LocalDateTime.of(2026, 6, 13, 13, 30));
        cliente.getCarrello().svuotaCarrello(); // <-- SVUOTIAMO IL CARRELLO


        // --- RISTORANTE 2: Sorbillo (2 Ordini) ---

        // Ordine 4: 3 Piatti
        cliente.getCarrello().aggiungiPiatto(sorbillo.getMenu().ricercaPiatto(3L));
        cliente.getCarrello().aggiungiPiatto(sorbillo.getMenu().ricercaPiatto(4L));
        cliente.getCarrello().aggiungiPiatto(sorbillo.getMenu().ricercaPiatto(4L));
        gestoreOrdini.creaOrdine(cliente, sorbillo, "Piazza del Plebiscito, 1",
                LocalDateTime.of(2026, 6, 12, 13, 30));
        cliente.getCarrello().svuotaCarrello(); // <-- SVUOTIAMO IL CARRELLO

        // Ordine 5: 1 Piatto
        cliente.getCarrello().aggiungiPiatto(sorbillo.getMenu().ricercaPiatto(3L));
        gestoreOrdini.creaOrdine(cliente, sorbillo, "Piazza del Plebiscito, 1",
                LocalDateTime.of(2026, 6, 12, 13, 45));
        cliente.getCarrello().svuotaCarrello(); // <-- SVUOTIAMO IL CARRELLO


        // --- RISTORANTE 3: McDonald's (1 Ordine) ---

        // Ordine 6: 3 Piatti
        cliente.getCarrello().aggiungiPiatto(mcDonalds.getMenu().ricercaPiatto(9L));  // Big Mac
        cliente.getCarrello().aggiungiPiatto(mcDonalds.getMenu().ricercaPiatto(10L)); // Crispy McBacon
        cliente.getCarrello().aggiungiPiatto(mcDonalds.getMenu().ricercaPiatto(11L)); // Patatine fritte
        gestoreOrdini.creaOrdine(cliente, mcDonalds, "Via Roma, 100",
                LocalDateTime.of(2026, 6, 14, 14, 55));
        cliente.getCarrello().svuotaCarrello(); // <-- SVUOTIAMO IL CARRELLO

        System.out.println("Popolamento ordini completato: 6 ordini indipendenti inseriti con successo.");
    }

}
