import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class MainManagerGUI {
    private final JFrame frame;
    @SuppressWarnings("unused")
    private PasswordManagerGUI passwordManagerGUI;
    private OttieniDatiGUI ottieniDatiGUI;

    public MainManagerGUI() throws IOException{
        frame = new JFrame("Main");
        // Imposta la chiusura completa dell'app quando premi la X della finestra.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Imposta la dimensione iniziale della finestra: larghezza 500, altezza 300.
        frame.setSize(400, 80);
        // Blocca il ridimensionamento da parte dell'utente.
        frame.setResizable(false);
        // Centra la finestra sullo schermo (null = centro del monitor).
        frame.setLocationRelativeTo(null);
        // Rende la finestra visibile all'utente.
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton butAggiungi = new JButton("Sezione Aggiunta");
        butAggiungi.setBounds(40, 10, 90, 25);
        JButton buttonOttieni = new JButton("Ottieni Dati");
        buttonOttieni.setBounds(200, 10, 90, 25);

        panel.add(butAggiungi);
        panel.add(buttonOttieni);
        frame.add(panel);

        butAggiungi.addActionListener(e -> {
            // Chiude la finestra corrente e apre la schermata di inserimento password.
            frame.dispose();
            try {
                passwordManagerGUI = new PasswordManagerGUI();
            } catch (IOException ex) {
                throw new RuntimeException("Errore durante l'avvio della GUI", ex);
            }
        });

        buttonOttieni.addActionListener(e -> {
            // Chiude la finestra corrente e apre la schermata di inserimento password.
            frame.dispose();
            try {
                ottieniDatiGUI = new OttieniDatiGUI();
            } catch (IOException ex) {
                throw new RuntimeException("Errore durante l'avvio della GUI", ex);
            }
        });

    }
}
