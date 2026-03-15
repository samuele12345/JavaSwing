import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OttieniDatiGUI {
    private final JFrame frame;
    private final ArrayList<JLabel> resultLabels;
    public FileManager fileMan;
    public PasswordEntry passEn;
    public PasswordService passServ;
    @SuppressWarnings("unused")
    private MainManagerGUI mainManagerGUI;

    private void clearDynamicResults(){
        for (JLabel label : resultLabels) {
            frame.remove(label);
        }
        resultLabels.clear();
    }

    public OttieniDatiGUI() throws IOException{
        fileMan = new FileManager();
        frame = new JFrame("Search");
        // Imposta la chiusura completa dell'app quando premi la X della finestra.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Imposta la dimensione iniziale della finestra: larghezza 500, altezza 300.
        frame.setSize(260, 250);
        // Centra la finestra sullo schermo (null = centro del monitor).
        frame.setLocationRelativeTo(null);
        // Rende la finestra visibile all'utente.
        frame.setVisible(true);
        // Disattiva i layout manager per posizionare componenti manualmente
        frame.setLayout(null);
        resultLabels = new ArrayList<>();

        passServ = new PasswordService();

        //JPanel panel = new JPanel(); // pannello a cui impostare un particolare layout 
        //panel.setLayout(new GridLayout(2,2)); per layout grid, poca personalizzazione
        //panel.setLayout(new FlowLayout()); per layout su una riga, poca personalizzazione

        JLabel labelOttieni = new JLabel("Username: ");
        labelOttieni.setBounds(40, 20, 90, 25);
        JTextField textOttieni = new JTextField(1);
        textOttieni.setText("");
        textOttieni.setBounds(120, 20, 90, 25);
        frame.add(labelOttieni);
        frame.add(textOttieni);

        JLabel labelOttieniSito = new JLabel("Sito: ");
        labelOttieniSito.setBounds(40, 60, 90, 25);
        JTextField textOttieniSito = new JTextField(1);
        textOttieniSito.setText("");
        textOttieniSito.setBounds(120, 60, 90, 25);
        frame.add(labelOttieniSito);
        frame.add(textOttieniSito);

        JButton butCerca = new JButton("Cerca");
        butCerca.setBounds(40,120, 69, 25);
        frame.add(butCerca);

        JButton butCanc = new JButton("X");
        butCanc.setBounds(112, 120, 45, 25);
        frame.add(butCanc);

        JButton buttIndietro = new JButton("<-");
        buttIndietro.setBounds(160, 120, 45, 25);
        frame.add(buttIndietro);

        JLabel site = new JLabel("");
        site.setBounds(40, 150, 180, 25);
        frame.add(site);

        butCerca.addActionListener(e -> {
            String nUtente = textOttieni.getText().trim();
            ArrayList<PasswordEntry> ris;
            ris = passServ.searchData(nUtente);

            // Pulisce i risultati precedenti prima di mostrare la nuova ricerca.
            clearDynamicResults();

            if(ris.isEmpty()){
                site.setText("Utente non presente");
            }else{
                // Pulisce la label di stato quando ci sono risultati reali da mostrare.
                if(!site.getText().isEmpty()){
                    site.setText("");
                }
                //attenzione: | in regex è un carattere speciale, quindi va "escapato"
                // per questo si usa split("\\|", 2)
                // stringa divisa in due parti in corrispondenza del carattere |
                /*
                    String[] parti = ris.split("\\|", 2);
                    site.setText("Website: " + parti[0]);
                    password.setText("Password: " + parti[1]);
                */
                
                // Parte da sotto la label di stato per evitare sovrapposizioni visive.
                int y = 150;
                int n = 0;
                
                while(n < ris.size()){
                    JLabel siteLabel = new JLabel("Website: " + ris.get(n).website);
                    siteLabel.setBounds(40, y, 180, 20);
                    frame.add(siteLabel);
                    resultLabels.add(siteLabel);

                    JLabel passLabel = new JLabel("Password: " + ris.get(n).password);
                    passLabel.setBounds(40, y + 20, 180, 20);
                    frame.add(passLabel);
                    resultLabels.add(passLabel);

                    n++;
                    y += 45;
                }
            }

            // revalidate: ricalcola il layout dopo add/remove di componenti.
            frame.revalidate();
            // repaint: ridisegna subito la finestra con i cambiamenti grafici.
            frame.repaint();
        });

        butCanc.addActionListener(e ->{
            String nUtente = textOttieni.getText().trim();
            String nSito = textOttieniSito.getText().trim();
            boolean campiVuoti = true;

            // Prima di mostrare il messaggio di cancellazione, rimuove i risultati della ricerca.
            clearDynamicResults();

            if(nUtente.equals("") || nSito.equals("")){
                campiVuoti = false;
            }

            String ris;
            ris = passServ.deleteFromFile(nUtente, nSito);

            if(ris.equals("Dati non trovati") && campiVuoti){
                site.setText(ris);
            }else if(campiVuoti){
                site.setText(ris);
            }else{
                site.setText("Compilare tutti i campi");
            }

            // revalidate: ricalcola il layout dopo la rimozione dei risultati dinamici.
            frame.revalidate();
            // repaint: aggiorna a schermo il testo di stato e la rimozione delle label.
            frame.repaint();

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
