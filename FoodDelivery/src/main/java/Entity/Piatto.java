package Entity;

import jakarta.persistence.*;

@Entity // Specifica che sarà un'entità da tradurre in una tabella.
@Table(name = "Piatti") // Specifica il nome della tabella nel DB.
public class Piatto {

    @Id // Indica che l'attributo sarà la chiave primaria.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Delega la creazione automatica dell'id al DB.
    private Long id;

    @Column(nullable = false) // Specifica che questa colonna sarà "NOT NULL" nel DB.
    private String nome;

    private String descrizione;

    @Column(nullable = false) // Specifica che questa colonna sarà "NOT NULL" nel DB.
    private float prezzo;

    @ManyToOne // Un Piatto può appartenere a un solo Menu.
    @JoinColumn(name = "menu_id") /* La FK associata al Menu nella tabella "Piatti"
                                           si chiamerà "menu_id". */
    private Menu menu;

    // Costruttore vuoto, serve a JPA.
    public Piatto(){
    }

    // Costruttore di Piatto.
    public Piatto(String nome,
                  String descrizione,
                  float prezzo,
                  Menu menu)
    {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.menu = menu;
    }

    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public Menu getMenu() { return menu; }

    public void setMenu(Menu menu) { this.menu = menu; }

    public boolean aggiornaDati(String nome,
                                String descrizione,
                                float prezzo)
    {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;

        return true;
    }
}
