package Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestRistorante {

    Ristoratore ristoratore;
    Ristorante ristorante;

    @BeforeEach
    void setup(){
        ristoratore = new Ristoratore("mario", "mario", "Mario", "Rossi", "mario@rossi.com", "Via Roma, 1");
        ristorante = new Ristorante("La vela", "Ristorante di pesce.", "Corso Umberto, 2", ristoratore);
        ristorante.aggiungiOrarioApertura(DayOfWeek.MONDAY, LocalTime.of(12,0), LocalTime.of(18,0));
    }

    @Test
    void ristoranteAperto(){
        Assertions.assertTrue(ristorante.isAperto(LocalDateTime.of(LocalDate.of(2026, 6, 8), LocalTime.of(13,30))));
    }

    @Test
    void ristoranteChiuso(){
        Assertions.assertFalse(ristorante.isAperto(LocalDateTime.of(LocalDate.of(2026, 6, 8), LocalTime.of(10,30))));
    }

}
