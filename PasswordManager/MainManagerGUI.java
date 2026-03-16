import java.io.IOException;
import java.util.prefs.Preferences;
import javax.swing.*;

public class MainManagerGUI {
    private final JFrame frame;
    private final Preferences prefs = Preferences.userNodeForPackage(MainManagerGUI.class);
    private static final String KEY_SAVED_PASSWORD = "saved_password";
    @SuppressWarnings("unused")
    private PasswordManagerGUI passwordManagerGUI;
    private OttieniDatiGUI ottieniDatiGUI;
    private String passAccessoProva = "PasswordProva";

    public MainManagerGUI() throws IOException{
        frame = new JFrame("Main");
        // Imposta la chiusura completa dell'app quando premi la X della finestra.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Imposta la dimensione iniziale della finestra: larghezza 500, altezza 300.
        frame.setSize(400, 400);
        // Blocca il ridimensionamento da parte dell'utente.
        frame.setResizable(false);
        // Centra la finestra sullo schermo (null = centro del monitor).
        frame.setLocationRelativeTo(null);
        // Rende la finestra visibile all'utente.
        frame.setVisible(true);

        frame.setLayout(null);

        /*
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
        */
        

        JButton butAggiungi = new JButton("Sezione Aggiunta");
        butAggiungi.setBounds(40, 10, 150, 25);
        JButton buttonOttieni = new JButton("Ottieni Dati");
        buttonOttieni.setBounds(200, 10, 150, 25);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(40, 40, 90, 25);
        JPasswordField passField = new JPasswordField(1);
        passField.setBounds(140, 40, 150, 25);

        JLabel labelMessaggio = new JLabel("");
        labelMessaggio.setBounds(40, 100, 180, 25);

        JCheckBox cb = new JCheckBox("Ricordami");
        cb.setBounds(40, 70, 100, 25);

        // Se presente, precompila la password salvata e attiva la checkbox.
        String savedPassword = prefs.get(KEY_SAVED_PASSWORD, "");
        if(!savedPassword.isEmpty()){
            passField.setText(savedPassword);
            cb.setSelected(true);
        }


        frame.add(butAggiungi);
        frame.add(buttonOttieni);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(labelMessaggio);
        frame.add(cb);

        butAggiungi.addActionListener(e -> {
            String enteredPassword = String.valueOf(passField.getPassword());
            if(enteredPassword.equals(passAccessoProva)){
                updateRememberedPassword(cb, enteredPassword);
                // Chiude la finestra corrente e apre la schermata di inserimento password.
                frame.dispose();
                try {
                    passwordManagerGUI = new PasswordManagerGUI();
                } catch (IOException ex) {
                    throw new RuntimeException("Errore durante l'avvio della GUI", ex);
                }
            }else if(enteredPassword.equals("")){
                labelMessaggio.setText("Inserire la password");
            }else{
                labelMessaggio.setText("Password errata, riprovare");
            }
            
        });

        buttonOttieni.addActionListener(e -> {
            String enteredPassword = String.valueOf(passField.getPassword());
            // Chiude la finestra corrente e apre la schermata di inserimento password.
            if(enteredPassword.equals(passAccessoProva)){
                updateRememberedPassword(cb, enteredPassword);
                frame.dispose();
                try {
                    ottieniDatiGUI = new OttieniDatiGUI();
                } catch (IOException ex) {
                    throw new RuntimeException("Errore durante l'avvio della GUI", ex);
                }
            }else if(enteredPassword.equals("")){
                labelMessaggio.setText("Inserire la password");
            }else{
                labelMessaggio.setText("Password errata, riprovare");
            }
        });

    }

    private void updateRememberedPassword(JCheckBox rememberCheck, String password){
        if(rememberCheck.isSelected()){
            prefs.put(KEY_SAVED_PASSWORD, password);
        }else{
            prefs.remove(KEY_SAVED_PASSWORD);
        }
    }
}
