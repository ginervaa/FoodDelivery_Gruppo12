package Entity;

import Entity.StatoOrdine.NomeStatoOrdine;
import Entity.StatoOrdine.StatoOrdine;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // Specifica che sarà un'entità da tradurre in una tabella.
@Table(name = "Ordini") // Specifica il nome della tabella nel DB.
public class Ordine {

    @Id // Indica che l'attributo sarà la chiave primaria.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Delega la creazione automatica dell'id al DB.
    private Long id;

    @ManyToOne // Un Ordine può avere un unico Cliente che l'ha inviato.
    @JoinColumn(name = "cliente_id") // La FK associata al cliente nella tabella "Ordini" si chiamerà "cliente_id".
    private Cliente cliente;

    @ManyToOne // Un Ordine può essere effettuato per un unico Ristorante.
    @JoinColumn(name = "ristorante_id") // La FK associata al ristorante nella tabella "Ordini" si chiamerà "ristorante_id".
    private Ristorante ristorante;

    @Column(nullable = false)
    private LocalDateTime dataOrdine;

    @Column(nullable = false) // Specifica che questa colonna sarà "NOT NULL" nel DB.
    private String indirizzoConsegna;

    @ManyToMany /* Tra Ordine e Piatto vi è un'associazione molti-a-molti, che verrà tradotta
                   nel DB in una tabella associativa. */
    @JoinTable(
            name = "Righe_Ordini", // Nome della tabella associativa.
            joinColumns = @JoinColumn(name = "ordine_id"),  // La FK che si collega alla tabella "Ordini".
            inverseJoinColumns = @JoinColumn(name = "piatto_id")  // La FK che si collega alla tabella "Piatti".
    )
    private List<Piatto> listaPiatti = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) // Specifica che questa colonna sarà "NOT NULL" nel DB.
    private NomeStatoOrdine nomeStato;

    @Transient // L'annotazione @Transient indica che l'attributo non sarà presente nella tabella del DB.
    private StatoOrdine stato;

    // Costruttore vuoto, serve a JPA.
    public Ordine(){
    }

    // Costruttore di Ordine
    public Ordine(Cliente cliente,
                  Ristorante ristorante,
                  LocalDateTime dataOrdine,
                  String indirizzoConsegna,
                  List<Piatto> listaPiatti,
                  StatoOrdine stato)
    {
        this.cliente = cliente;
        this.ristorante = ristorante;
        this.dataOrdine = dataOrdine;
        this.indirizzoConsegna = indirizzoConsegna;
        this.listaPiatti.addAll(listaPiatti);
        this.stato = stato;
        this.nomeStato = stato.getNomeStato();
    }

    public Long getId(){
        return id;
    }

    public String getIndirizzoConsegna(){
        return indirizzoConsegna;
    }

    public LocalDateTime getDataOrdine() { return dataOrdine; }

    public List<Piatto> getListaPiatti(){
        return listaPiatti;
    }

    public NomeStatoOrdine getNomeStato() {
        return nomeStato;
    }

    public void setStato(StatoOrdine stato) {
        this.stato = stato;
    }

}
