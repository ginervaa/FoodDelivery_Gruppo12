package Boundary.Login;

import Boundary.EffettuaOrdine.FrameRistorantiAperti;
import Boundary.FormPannelloRistoratore;
import Boundary.Monitoraggio.FormMonitoraggio;
import Boundary.Registrazione.FormRegistrazione;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import Controller.ControllerSessione;
import Entity.RuoloUtente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormLogin {

    private JButton loginButton;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JComboBox ruoloComboBox;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel ruoloLabel;
    private JLabel errorLabel;
    private JPanel contentPane;
    private JButton registratiButton;
    private JLabel errorLabel2;

    public boolean avviaRegistrazione;

    private JFrame frame;

    private FormMonitoraggio classFormMonitoraggio;
    private JFrame formMonitoraggio;
    private FrameRistorantiAperti classFrameRistorantiAperti;
    private JFrame frameRistorantiAperti;
    private FormPannelloRistoratore classPannelloRistoratore;
    private JFrame formPannelloRistoratore;

    public JFrame apriFormLogin() {

        frame = new JFrame("Login");

        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );
        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        for (Component c : contentPane.getComponents()) {
            c.setFont(new Font(
                    c.getFont().getName(),
                    Font.PLAIN,
                    14
            ));
        }

        // Nasconde il pulsante all'avvio. Verrà mostrato solo in caso di credenziali errate.
        registratiButton.setVisible(false);

        // Associa l'evento di click sul pulsante "Registrati".
        registratiButton.addActionListener(e -> {
            //new FormRegistrazione().setVisible(true);
            avviaRegistrazione = true;
            //frame.dispose();
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });
        errorLabel2.setVisible(false);

        // Quando viene premuto il pulsante di login vengono validate le credenziali inserite.
        loginButton.addActionListener(e -> validaEAccedi());

        return frame;
    }

    // Verifica i dati inseriti dall'utente ed effettua la richiesta di autenticazione al controller.
    private void validaEAccedi() {

        // Recupero username e password ineriti.
        String username = usernameTextField.getText();
        String password = new String(passwordField.getPassword());

        // Controllo che username e password siano stati inseriti.
        if (username.isBlank() || password.isBlank()) {

            registratiButton.setVisible(false);
            errorLabel2.setVisible(false);

            errorLabel.setText("Inserisci username e password.");
            errorLabel.setForeground(Color.RED);
            return;
        }

        // Recupero del ruolo selezionato dalla ComboBox.
        String ruoloString = (String) ruoloComboBox.getSelectedItem();

        RuoloUtente ruolo;

        // Conversione della stringa selezionata nel corrispondente valore dell'enum.
        try {
            ruolo = RuoloUtente.valueOf(ruoloString.toUpperCase());
        } catch (IllegalArgumentException ex) {

            errorLabel.setText("Ruolo non valido.");
            errorLabel.setForeground(Color.RED);
            return;
        }

        // Richiesta di autenticazione al controller.
        boolean esito = ControllerSessione.login(
                username,
                password,
                ruolo
        );

        // Se il login è avvenuto correttamente.
        if (esito) {
            registratiButton.setVisible(false);
            errorLabel2.setVisible(false);

            errorLabel.setText("Accesso effettuato!");
            errorLabel.setForeground(Color.GREEN);

            // Apertura del pannello dedicato al ristoratore.
            if (ruolo == RuoloUtente.RISTORATORE) {
                apriPannelloRistoratore();
                // Apertura della schermata di ricerca ristoranti per il cliente.
            } else if (ruolo == RuoloUtente.CLIENTE) {
                apriEffettuaOrdine();

                // Apertura della schermata di monitoraggio dell'attività complessiva del sistema.
            } else if (ruolo == RuoloUtente.AMMINISTRATORE) {
                apriMonitoraggio();
            }

            // Se le credenziali non sono corrette.
        } else {
            errorLabel.setText("Credenziali errate o ruolo non corrispondente.");
            errorLabel.setForeground(Color.RED);

            errorLabel2.setText("Non hai ancora un account?");
            errorLabel2.setForeground(Color.BLUE);
            registratiButton.setVisible(true);
            errorLabel2.setVisible(true);

        }
    }

    private void apriPannelloRistoratore(){

        if (formPannelloRistoratore == null || !formPannelloRistoratore.isDisplayable()) {

            classPannelloRistoratore = new FormPannelloRistoratore();
            formPannelloRistoratore = classPannelloRistoratore.apriFormPannelloRistoratore();

            // Serve a centrare la finestra sullo schermo.
            formPannelloRistoratore.setLocationRelativeTo(null);

            // Rende visibile la finestra a schermo.
            formPannelloRistoratore.setVisible(true);
        }else {
            formPannelloRistoratore.toFront();
            formPannelloRistoratore.requestFocus();
        }

        // Rendo invisibile la Home.
        frame.setVisible(false);

        formPannelloRistoratore.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    private void apriEffettuaOrdine(){

        if (frameRistorantiAperti == null || !frameRistorantiAperti.isDisplayable()) {

            classFrameRistorantiAperti = new FrameRistorantiAperti();
            frameRistorantiAperti = classFrameRistorantiAperti.apriFrameRistorantiAperti();

            // Serve a centrare la finestra sullo schermo.
            frameRistorantiAperti.setLocationRelativeTo(null);

            // Rende visibile la finestra a schermo.
            frameRistorantiAperti.setVisible(true);
        }else {
            frameRistorantiAperti.toFront();
            frameRistorantiAperti.requestFocus();
        }

        // Chiudo la schermata Home.
        //dispose();

        // Rendo invisibile la Home.
        frame.setVisible(false);

        frameRistorantiAperti.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    private void apriMonitoraggio(){

        if (formMonitoraggio == null || !formMonitoraggio.isDisplayable()) {

            classFormMonitoraggio = new FormMonitoraggio();
            formMonitoraggio = classFormMonitoraggio.apriFormMonitoraggio();

            // Serve a centrare la finestra sullo schermo.
            formMonitoraggio.setLocationRelativeTo(null);

            // Rende visibile la finestra a schermo.
            formMonitoraggio.setVisible(true);
        }else {
            formMonitoraggio.toFront();
            formMonitoraggio.requestFocus();
        }

        // Rendo invisibile la Home.
        frame.setVisible(false);

        formMonitoraggio.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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
        contentPane.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
        usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        contentPane.add(usernameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        contentPane.add(passwordLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ruoloLabel = new JLabel();
        ruoloLabel.setText("Ruolo");
        contentPane.add(ruoloLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loginButton = new JButton();
        loginButton.setText("Login");
        contentPane.add(loginButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        errorLabel = new JLabel();
        errorLabel.setText("");
        contentPane.add(errorLabel, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        usernameTextField = new JTextField();
        contentPane.add(usernameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordField = new JPasswordField();
        contentPane.add(passwordField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ruoloComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Cliente");
        defaultComboBoxModel1.addElement("Ristoratore");
        defaultComboBoxModel1.addElement("Amministratore");
        ruoloComboBox.setModel(defaultComboBoxModel1);
        contentPane.add(ruoloComboBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        registratiButton = new JButton();
        registratiButton.setText("Registrati");
        contentPane.add(registratiButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        errorLabel2 = new JLabel();
        errorLabel2.setText("");
        contentPane.add(errorLabel2, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

    /*public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new FormLogin().setVisible(true));
    }*/
}
