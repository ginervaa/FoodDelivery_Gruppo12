package Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestUtente {

    Utente utente;

    @BeforeEach
    void setup(){
        utente = new Cliente("mario","mario","Mario","Rossi","mario@rossi.com","Via Roma, 1");
    }

    @Test
    void credenzialiCorrette(){
        Assertions.assertTrue(utente.verificaCredenziali("mario","mario",RuoloUtente.CLIENTE));
    }

    @Test
    void usernameErrato(){
        Assertions.assertFalse(utente.verificaCredenziali("marip","mario",RuoloUtente.CLIENTE));
    }

    @Test
    void passwordErrata(){
        Assertions.assertFalse(utente.verificaCredenziali("mario","wario",RuoloUtente.CLIENTE));
    }

    @Test
    void ruoloErrato(){
        Assertions.assertFalse(utente.verificaCredenziali("mario","wario",RuoloUtente.AMMINISTRATORE));
    }

}
