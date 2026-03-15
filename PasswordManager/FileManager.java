
import java.io.*;

public class FileManager {
    private FileWriter fw;
    private BufferedWriter bw;
    

    public FileManager() throws IOException{
        fw = new FileWriter("passwords.txt", true);
        bw = new BufferedWriter(fw);
    }

    public void saveOnFile(PasswordEntry passEnt) throws IOException{
        bw.write("Username: " + passEnt.username);
        bw.newLine();
        bw.write("Website: " + passEnt.website);
        bw.newLine();
        bw.write("Password: " + passEnt.password);
        bw.newLine();
        bw.flush(); // scrive sul file senza chiudere il writer
    }

    

    
}
