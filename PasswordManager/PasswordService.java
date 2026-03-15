import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PasswordService {
    public ArrayList<PasswordEntry> credList;

    public PasswordService(){
        credList = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile(){
        // try-with-resources: apre il reader e lo chiude automaticamente a fine blocco.
        // Usiamo BufferedReader per leggere il file riga per riga in modo efficiente.
        try (BufferedReader br = new BufferedReader(new FileReader("passwords.txt"))) {
            // Contiene la riga corrente letta dal file.
            String line;
            // Variabili "temporanee" per ricostruire una credenziale completa.
            // Restano valorizzate fino a quando non abbiamo tutti e 3 i campi.
            String username = null;
            String website = null;
            String password = null;

            // Legge finché non raggiunge la fine del file (readLine() restituisce null).
            while ((line = br.readLine()) != null) {
                // Il file è salvato in questo formato:
                // Username: ...
                // Website: ...
                // Password: ...
                // (eventuali righe vuote)
                // Qui riconosciamo ogni tipo di riga e salviamo solo il valore dopo il prefisso.
                if (line.startsWith("Username: ")) {
                    username = line.substring("Username: ".length());
                } else if (line.startsWith("Website: ")) {
                    website = line.substring("Website: ".length());
                } else if (line.startsWith("Password: ")) {
                    password = line.substring("Password: ".length());
                }

                // Quando abbiamo tutti i campi, creiamo un PasswordEntry e lo aggiungiamo alla lista.
                // Poi resettiamo le variabili per poter leggere il blocco successivo nel file.
                if (username != null && website != null && password != null) {
                    credList.add(new PasswordEntry(username, website, password));
                    username = null;
                    website = null;
                    password = null;
                }
            }
        } catch (IOException e) {
            // Se il file non esiste o non è leggibile, non blocchiamo l'app:
            // segnaliamo il problema e lasciamo credList vuota.
            System.out.println("Impossibile leggere passwords.txt: " + e.getMessage());
        }
    }

    public ArrayList<PasswordEntry> searchData(String usern){
        int i = 0;
        ArrayList<PasswordEntry> data = new ArrayList<>();
        
        while(i < credList.size()){
            if(credList.get(i).username.equals(usern)){
                data.add(new PasswordEntry(
                    credList.get(i).username,
                    credList.get(i).website,
                    credList.get(i).password
                ));
            }
            i++;
        }
        return data;

    }

    public String deleteFromFile(String usern, String sito){
        int i = 0;
        boolean presente = false;

        while(i < credList.size()){
            if(credList.get(i).username.equals(usern) && credList.get(i).website.equals(sito)){
                credList.remove(i);
                presente = true;
                break;
            }
            i++;
        }

        if(!presente){
            return "Dati non trovati";
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("passwords.txt", false))) {
            for (PasswordEntry entry : credList) {
                bw.write("Username: " + entry.username);
                bw.newLine();
                bw.write("Website: " + entry.website);
                bw.newLine();
                bw.write("Password: " + entry.password);
                bw.newLine();
                bw.newLine();
                bw.newLine();
            }

            return "Utente eliminato";
        } catch (IOException e) {
            // Se il file non esiste o non è leggibile, non blocchiamo l'app:
            // segnaliamo il problema e manteniamo lo stato della lista in memoria.
            System.out.println("Impossibile scrivere passwords.txt: " + e.getMessage());
            return "Errore durante eliminazione";
        }
    }

    public void addPassword(String username, String website, String password){
        credList.add(new PasswordEntry(username, website, password));
        System.out.println("Utente: " + username + " aggiunto");
    }

}
