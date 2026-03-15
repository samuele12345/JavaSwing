import java.io.IOException;
import javax.swing.*;

public class PasswordManagerGUI{
    private final JFrame frame;
    public FileManager fileMan;
    public PasswordEntry passEn;
    public PasswordService passServ;
    @SuppressWarnings("unused")
    private MainManagerGUI mainManagerGUI;

    public PasswordManagerGUI() throws IOException{
        fileMan = new FileManager();
        frame = new JFrame("Registration");
        // Imposta la chiusura completa dell'app quando premi la X della finestra.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Imposta la dimensione iniziale della finestra: larghezza 500, altezza 300.
        frame.setSize(340, 280);
        // Blocca il ridimensionamento da parte dell'utente.
        frame.setResizable(false);
        // Centra la finestra sullo schermo (null = centro del monitor).
        frame.setLocationRelativeTo(null);
        // Rende la finestra visibile all'utente.
        frame.setVisible(true);
        // Disattiva i layout manager per posizionare componenti manualmente
        frame.setLayout(null);

        //JPanel panel = new JPanel(); // pannello a cui impostare un particolare layout 
        //panel.setLayout(new GridLayout(2,2)); per layout grid, poca personalizzazione
        //panel.setLayout(new FlowLayout()); per layout su una riga, poca personalizzazione

        JLabel labelUser = new JLabel("Username: ");
        labelUser.setBounds(40, 20, 90, 25);
        JTextField textUser = new JTextField(1);
        textUser.setText("");
        textUser.setBounds(200, 20, 90, 25);
        frame.add(labelUser);
        frame.add(textUser);

        JLabel labelSite = new JLabel("Website: ");
        labelSite.setBounds(40, 60, 90, 25);
        JTextField textSite = new JTextField(1);
        textSite.setText("");
        textSite.setBounds(200, 60, 90, 25);
        frame.add(labelSite);
        frame.add(textSite);

        JLabel labelPass = new JLabel("Password: ");
        labelPass.setBounds(40, 100, 90, 25);
        JPasswordField passField = new JPasswordField(1);
        passField.setText("");
        passField.setBounds(200, 100, 90, 25);
        frame.add(labelPass);
        frame.add(passField);

        JButton buttonAdd = new JButton("Aggiungi");
        buttonAdd.setBounds(40, 140, 90, 25);
        JButton buttonRefresh = new JButton("Svuota");
        buttonRefresh.setBounds(140, 140, 90, 25);
        JButton buttIndietro = new JButton("<-");
        buttIndietro.setBounds(240, 140, 50, 25);
        frame.add(buttIndietro);
        frame.add(buttonAdd);
        frame.add(buttonRefresh);

        JLabel result = new JLabel("");
        result.setBounds(40, 180, 180, 25);
        frame.add(result);

        JLabel result2 = new JLabel("");
        result2.setBounds(40, 200, 220, 25);
        frame.add(result2);

        buttonAdd.addActionListener(e ->{
            boolean res = true;
            boolean campiVuoti = true;
            boolean campiDuplicati = true;
            boolean usSito = true;
            String username = textUser.getText().trim();
            String website = textSite.getText().trim();
            char[] passChars = passField.getPassword(); // leggi come char array
            String password = new String(passChars).trim(); // converte in String
            passServ = new PasswordService();
            int i=0;
            

            if(username.equals("") || website.equals("") || password.equals("")){
                campiVuoti = false;
                result.setText("Compilare tutti i campi");
                result2.setText("");
            }

            while(i < passServ.credList.size()){

                if(username.equals(passServ.credList.get(i).username) && website.equals(passServ.credList.get(i).website)){
                    if(password.equals(passServ.credList.get(i).password)){
                        campiDuplicati = false;
                        result.setText("Campi già salvati");
                        result2.setText("");
                        break;
                    }
                    usSito = false;
                    result.setText("Esiste già user + sito");
                    result2.setText("Elimini user in Ottieni per aggiornare");
                    break;
                }

                i++;
            }

            if(campiVuoti && campiDuplicati && usSito){
                passEn = new PasswordEntry(username, website, password);
                // Salva su file
                try {
                    fileMan.saveOnFile(passEn);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Fallimento nel salvataggio, riprovare");
                    res = false;
                }
            }

            if(res && campiVuoti && campiDuplicati && usSito){
                result.setText("Utente: " + username + " acquisito!");
            }
        });

        buttonRefresh.addActionListener(e ->{
            textUser.setText("");
            textSite.setText("");
            passField.setText("");
            result.setText("Campi svuotati");
        });

        buttIndietro.addActionListener(e ->{
            frame.dispose();
            try {
                mainManagerGUI = new MainManagerGUI();
            } catch (IOException ex) {
                throw new RuntimeException("Errore durante l'avvio della GUI", ex);
            }
        });

    }

    
}


