package Boundary.Registrazione;

import Boundary.Login.FormLogin;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import Controller.ControllerSessione;
import Entity.RuoloUtente;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.Locale;

public class FormRegistrazione {
    private JPanel contentPane;
    private JTextField usernameTextField;
    private JLabel usernameLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JComboBox ruoloComboBox;
    private JLabel ruoloLabel;
    private JLabel cognomeLabel;
    private JTextField cognomeTextField;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JLabel nomeLabel;
    private JTextField nomeTextField;
    private JLabel indirizzoLabel;
    private JTextField indirizzoTextField;
    private JButton registerButton;
    private JLabel errorLabel;

    public boolean registrazioneAvvenuta = false;

    public JFrame frame;

    public JFrame apriFormRegistrazione() {

        frame = new JFrame("Registrazione");

        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        errorLabel.setForeground(Color.RED);
        errorLabel.setText(" ");

        // Il pulsante di registrazione parte disabilitato.
        // Verrà abilitato soltanto quando tutti i campi saranno compilati.
        registerButton.setEnabled(false);

        contentPane.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );
        frame.pack();
        frame.setLocationRelativeTo(null);

        for (Component c : contentPane.getComponents()) {
            c.setFont(new Font(
                    c.getFont().getName(),
                    Font.PLAIN,
                    14
            ));
        }

        // Listener utilizzato per controllare i campi ogni volta
        // che l'utente inserisce o modifica del testo.
        KeyAdapter onType = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                controllaCampiNonVuoti();
            }
        };

        // Associa il listner a tutti i campi testuali della form.
        usernameTextField.addKeyListener(onType);
        passwordField.addKeyListener(onType);
        nomeTextField.addKeyListener(onType);
        cognomeTextField.addKeyListener(onType);
        emailTextField.addKeyListener(onType);
        indirizzoTextField.addKeyListener(onType);

        // Controlla nuovamente i campi quando cambia il ruolo selezionato.
        ruoloComboBox.addActionListener(e -> controllaCampiNonVuoti());

        // Avvia la procedura di registrazione quando viene premuto il pulsante.
        registerButton.addActionListener(e -> registraUtente());

        return frame;
    }

    // Gestisce il processo di registrazione dell'utente.
    private void registraUtente() {

        // Recupera i dati inseriti dall'utente nella form.
        String username = usernameTextField.getText();
        String password = new String(passwordField.getPassword());
        String ruoloString = (String) ruoloComboBox.getSelectedItem();
        String nome = nomeTextField.getText();
        String cognome = cognomeTextField.getText();
        String email = emailTextField.getText();
        String indirizzo = indirizzoTextField.getText();

        RuoloUtente ruolo;

        // Converte il ruolo selezionato nella relativa costante dell'enum.
        try {
            ruolo = RuoloUtente.valueOf(
                    ruoloString.toUpperCase()
            );

        // Se il ruolo non è valido mostra un messaggio di errore.
        } catch (IllegalArgumentException ex) {
            errorLabel.setText("Ruolo non valido.");
            return;
        }

        // Richiede la registrazione al Controller.
        boolean esito = ControllerSessione.registrazione(
                username,
                password,
                ruolo,
                nome,
                cognome,
                email,
                indirizzo
        );

        // Se la registrazione va a buon fine:
        if (esito) {

            // Mostra un messaggio di conferma.
            JOptionPane.showMessageDialog(
                    frame,
                    "Registrazione completata!",
                    "Successo",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Apre la schermata di login.
            //new FormLogin().setVisible(true);

            registrazioneAvvenuta = true;
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

            // Chiude la schermata corrente.
            //dispose();

        }
        // Se la registrazione fallisce:
        else {

            // Mostra un messaggio di errore.
            errorLabel.setText("Registrazione non riuscita.");
        }
    }

    // Verifica che tutti i campi obbligatori siano stati compilati.
    private void controllaCampiNonVuoti() {

        // Recupera il contenuto dei campi della form.
        String username = usernameTextField.getText();
        String password = new String(passwordField.getPassword());
        String nome = nomeTextField.getText();
        String cognome = cognomeTextField.getText();
        String email = emailTextField.getText();
        String indirizzo = indirizzoTextField.getText();

        // Controlla che nessun campo sia vuoto.
        boolean tuttiPieni =
                !username.isEmpty()
                        && !password.isEmpty()
                        && !nome.isEmpty()
                        && !cognome.isEmpty()
                        && !email.isEmpty()
                        && !indirizzo.isEmpty();

        // Abilita o disabilita il pulsante di registrazione.
        registerButton.setEnabled(tuttiPieni);

        // Se tutti i campi sono compilati, rimuove eventuali messaggi di errore.
        if (tuttiPieni) {
            errorLabel.setText(" ");
        }

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
        contentPane.setLayout(new GridLayoutManager(10, 2, new Insets(0, 0, 0, 0), 15, 12));
        contentPane.setBackground(new Color(-394501));
        contentPane.setEnabled(true);
        contentPane.setForeground(new Color(-11840157));
        contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        usernameTextField = new JTextField();
        contentPane.add(usernameTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        contentPane.add(usernameLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        contentPane.add(passwordLabel, new GridConstraints(4, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordField = new JPasswordField();
        contentPane.add(passwordField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ruoloComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Amministratore");
        defaultComboBoxModel1.addElement("Cliente");
        defaultComboBoxModel1.addElement("Ristoratore");
        ruoloComboBox.setModel(defaultComboBoxModel1);
        contentPane.add(ruoloComboBox, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ruoloLabel = new JLabel();
        ruoloLabel.setText("Ruolo");
        contentPane.add(ruoloLabel, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cognomeLabel = new JLabel();
        cognomeLabel.setText("Cognome");
        contentPane.add(cognomeLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cognomeTextField = new JTextField();
        contentPane.add(cognomeTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        emailLabel = new JLabel();
        emailLabel.setText("Email");
        contentPane.add(emailLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emailTextField = new JTextField();
        contentPane.add(emailTextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        nomeLabel = new JLabel();
        nomeLabel.setText("Nome");
        contentPane.add(nomeLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nomeTextField = new JTextField();
        contentPane.add(nomeTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        indirizzoLabel = new JLabel();
        indirizzoLabel.setText("Indirizzo");
        contentPane.add(indirizzoLabel, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        indirizzoTextField = new JTextField();
        contentPane.add(indirizzoTextField, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        registerButton = new JButton();
        registerButton.setBackground(new Color(-14326805));
        Font registerButtonFont = this.$$$getFont$$$(null, Font.BOLD, 14, registerButton.getFont());
        if (registerButtonFont != null) registerButton.setFont(registerButtonFont);
        registerButton.setForeground(new Color(-1));
        registerButton.setText("Registrati");
        contentPane.add(registerButton, new GridConstraints(9, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(120, 40), null, 0, false));
        errorLabel = new JLabel();
        errorLabel.setText("");
        contentPane.add(errorLabel, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(250, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormRegistrazione().setVisible(true);
        });
    }*/
}
