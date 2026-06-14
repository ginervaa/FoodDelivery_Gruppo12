package Boundary.GestioneProfiloRistorante;

import Controller.ControllerGestioneAttivita;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FormAggiungiRistorante extends JFrame {

    private JButton confermaButton;
    private JLabel esitoLabel;
    private JLabel nomeLabel;
    private JLabel descrizioneLabel;
    private JLabel indirizzoLabel;
    private JTextField nomeTextField;
    private JTextField descrizioneTextField;
    private JTextField indirizzoTextField;
    private JPanel contentPane;
    private JTable orariTable;
    private JButton rimuoviTurnoButton;
    private JButton aggiungiTurnoButton;

    private DefaultTableModel tableModel;

    /* Riceve come parametro il form di gestione profilo da cui è stato aperto.
       Questo riferimento consente di aggiornare immediatamente la tabella dei
       ristoranti visualizzata nel form padre dopo la creazione di un nuovo
       ristorante, senza dover ricaricare l'intera schermata. */
    public FormAggiungiRistorante(FormGestioneProfilo formPadre) {

        setContentPane(contentPane);
        setTitle("Aggiungi Ristorante");
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Posiziona la finestra al centro del form da cui è stata aperta.
        setLocationRelativeTo(null);

        esitoLabel.setText(" ");

        // Configuro le colonne della tabella.
        String[] colonne = {"Giorno della Settimana", "Ora Apertura (HH:MM)", "Ora Chiusura (HH:MM)"};

        // Inizializzo il modello con zero righe all'inizio.
        tableModel = new DefaultTableModel(colonne, 0);
        orariTable.setModel(tableModel);

        // Attivo il ComboBox per la prima colonna, ossia Giorno della Settimana.
        JComboBox<DayOfWeek> comboGiorni = new JComboBox<>(DayOfWeek.values());
        orariTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboGiorni));

        // Riga di esempio standard all'apertura del form.
        tableModel.addRow(new Object[]{DayOfWeek.MONDAY, "12:00", "15:00"});

        // Aggiungo un'azione al click del pulsante "Aggiungi Turno".
        aggiungiTurnoButton.addActionListener(e -> {

            // Aggiungo una riga precompilata modificabile dall'utente.
            tableModel.addRow(new Object[]{DayOfWeek.MONDAY, "19:00", "23:00"});
        });

        // Aggiungo un'azione al click del pulstante "Rimuovi Turno Selezionato".
        rimuoviTurnoButton.addActionListener(e -> {
            int rigaSelezionata = orariTable.getSelectedRow();
            if(rigaSelezionata != -1){
                tableModel.removeRow(rigaSelezionata);
            } else {
                esitoLabel.setText("Seleziona prima una riga della tabella da rimuovere.");
                esitoLabel.setForeground(Color.RED);
            }
        });

        // Aggiungo un'azione al click del pulsante di conferma.
        confermaButton.addActionListener(e -> {

            // Forza la tabella a salvare l'ultimo dato scritto.
            if (orariTable.isEditing()) {
                orariTable.getCellEditor().stopCellEditing();
            }

            // Prelevo i dati inseriti dall'utente nei campi di testo.
            String nome = nomeTextField.getText();
            String descrizione = descrizioneTextField.getText();
            String indirizzo = indirizzoTextField.getText();

            // Controllo che i campi obbligatori siano stati compilati.
            if (nome.isBlank() || indirizzo.isBlank()) {

                // Mostra messaggio di errore.
                esitoLabel.setText("Nome e indirizzo sono obbligatori.");
                esitoLabel.setForeground(Color.RED);
                return;
            }

            // Lista temporanea in cui vengono memorizzati i turni all'interno della tabella.
            List<String[]> turniInseriti = new ArrayList<>();

            // Ciclo che estrae ogni singola riga della JTable per estrarre gli orari.
            for(int i = 0; i < tableModel.getRowCount(); i++) {
                String giorno = tableModel.getValueAt(i, 0).toString();
                String apertura = tableModel.getValueAt(i, 1).toString();
                String chiusura = tableModel.getValueAt(i, 2).toString();

                // Validazione della formattazione dell'orario.
                try {
                    LocalTime.parse(apertura);
                    LocalTime.parse(chiusura);
                } catch (DateTimeParseException ex) {
                    esitoLabel.setText("Inserisci gli orari nel formato HH:MM (ad esempio 11.:30).");
                    esitoLabel.setForeground(Color.RED);
                    return; // Blocca il salvataggio se c'è un errore di digitazione.
                }

                // Se è valido lo aggiunge alla lista in formato stringa.
                turniInseriti.add(new String[]{giorno, apertura, chiusura});
            }

            // Richiede al controller la creazione del nuovo profilo ristorante.
            Long idNuovoRistorante = ControllerGestioneAttivita.creaProfiloRistorante(
                    nome,
                    descrizione,
                    indirizzo,
                    turniInseriti
            );

            // Se il controller restituisce un id valido:
            if (idNuovoRistorante != null) {

                //Aggiorna immediatamente la tabella del form padre aggiungendo il nuovo ristorante appena creato.
                formPadre.aggiungiRigaRistorante(
                        String.valueOf(idNuovoRistorante),
                        nome,
                        descrizione,
                        indirizzo
                );

                // Chiude la finestra di inserimento ristorante.
                dispose();
            }
            // Se la creazione del ristorante fallisce:
            else {

                // Mostra messaggio di errore.
                esitoLabel.setText("Errore durante il salvataggio.");
                esitoLabel.setForeground(Color.RED);
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        nomeLabel = new JLabel();
        nomeLabel.setText("Nome");
        contentPane.add(nomeLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        descrizioneLabel = new JLabel();
        descrizioneLabel.setText("Descrizione");
        contentPane.add(descrizioneLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        indirizzoLabel = new JLabel();
        indirizzoLabel.setText("Indirizzo");
        contentPane.add(indirizzoLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        confermaButton = new JButton();
        confermaButton.setText("Conferma");
        contentPane.add(confermaButton, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        esitoLabel = new JLabel();
        esitoLabel.setText("");
        contentPane.add(esitoLabel, new GridConstraints(6, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nomeTextField = new JTextField();
        contentPane.add(nomeTextField, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        descrizioneTextField = new JTextField();
        contentPane.add(descrizioneTextField, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPane.add(scrollPane1, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        orariTable = new JTable();
        scrollPane1.setViewportView(orariTable);
        rimuoviTurnoButton = new JButton();
        rimuoviTurnoButton.setText("Rimuovi Turno Selezionato");
        contentPane.add(rimuoviTurnoButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        aggiungiTurnoButton = new JButton();
        aggiungiTurnoButton.setText("Aggiungi Turno");
        contentPane.add(aggiungiTurnoButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        indirizzoTextField = new JTextField();
        contentPane.add(indirizzoTextField, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
