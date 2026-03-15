
import java.io.IOException;
import javax.swing.SwingUtilities;

/** Punto di ingresso dell'applicazione Password Manager. */
public class Main {
    // Riferimento mantenuto per evitare che l'istanza GUI venga considerata inutilizzata.
    @SuppressWarnings("unused")
    private static MainManagerGUI app;

    public static void main(String[] args){
        // Avvia la GUI nel thread grafico di Swing (EDT), come richiesto da Swing.
        SwingUtilities.invokeLater(() -> {
            try {
                // Crea la finestra principale dell'applicazione.
                app = new MainManagerGUI();
            } catch (IOException e) {
                // Trasforma l'errore checked in unchecked per bloccare l'avvio con un messaggio chiaro.
                throw new RuntimeException("Errore durante l'avvio della GUI", e);
            }
        });
    }
}
