package Entity;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity // Specifica che sarà un'entità da tradurre in una tabella.
@Table(name = "Orari_Apertura") // Specifica il nome della tabella nel DB.
public class OrarioApertura {

    @Id // Indica che l'attributo sarà la chiave primaria.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Delega la creazione automatica dell'id al DB.
    private Long id;

    @Enumerated(EnumType.STRING) // Salva nel DB come stringa (es. "MONDAY").
    @Column(name = "giorno_settimana", nullable = false) /* Specifica che questa colonna sarà "NOT NULL" nel DB
                                                            e si chiamerà "giorno_settimana". */
    private DayOfWeek giornoSettimana;

    @Column(name = "ora_apertura", nullable = false) /* Specifica che questa colonna sarà "NOT NULL" nel DB
                                                        e si chiamerà "ora_apertura". */
    private LocalTime oraApertura;

    @Column(name = "ora_chiusura", nullable = false) /* Specifica che questa colonna sarà "NOT NULL" nel DB
                                                        e si chiamerà "ora_chiusura". */
    private LocalTime oraChiusura;

    @ManyToOne // Un OrarioApertura può appartenere a un solo Ristorante.
    @JoinColumn(name = "ristorante_id") /* La FK associata al Ristorante nella tabella "Orari_Apertura"
                                             si chiamerà "ristorante_id". */
    private Ristorante ristorante;

    // Costruttore vuoto, serve a JPA.
    public OrarioApertura() {
    }

    // Costruttore di OrarioApertura
    public OrarioApertura(DayOfWeek giornoSettimana,
                          LocalTime oraApertura,
                          LocalTime oraChiusura,
                          Ristorante ristorante)
    {
        this.giornoSettimana = giornoSettimana;
        this.oraApertura = oraApertura;
        this.oraChiusura = oraChiusura;
        this.ristorante = ristorante;
    }

    public DayOfWeek getGiornoSettimana() {
        return giornoSettimana;
    }

    public LocalTime getOraApertura() {
        return oraApertura;
    }

    public LocalTime getOraChiusura() {
        return oraChiusura;
    }
}
