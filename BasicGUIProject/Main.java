import javax.swing.*; // Importa tutte le classi di Swing per creare interfacce grafiche

/*
Il programma permette di scrivere il proprio nome, successivamente compare un label
contenente "Hello Nome!"

JFrame is a Swing component that allows you to set up your GUI so it shows up when 
you run your program. Now, you can customize your GUI with other components to add 
functionality. Some popular Swing components include:
- JButton A clickable button
- JCheckBox A box that can be checked/unchecked
- JRadioButton A radio button (usually in groups)
- JToggleButton A button that stays pressed
- JComboBox A drop-down list (like a select box)
- JTextField A single-line text input/*
*/


public class Main {
    public static void main(String[] args) {
        // Crea una nuova finestra (JFrame) con il titolo "My First GUI"
        JFrame frame = new JFrame("My First GUI");
        frame.setSize(300, 150); // Imposta dimensioni della finestra: larghezza 300px, altezza 150px
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chiude il programma quando si chiude la finestra
        frame.setLayout(null); // Disattiva i layout manager per posizionare componenti manualmente

        // text field
        JTextField textField = new JTextField();
        // dimensioni e posizionamento della componente
        textField.setBounds(20, 20, 150, 25);

        // button
        JButton button = new JButton("Click Me");
        button.setBounds(180, 20, 90, 25);

        // label
        JLabel label = new JLabel("Insert your name!");
        label.setBounds(20, 60, 250, 25);

        // per aggiungere le componenti al frame
        frame.add(textField);
        frame.add(button);
        frame.add(label);

        button.addActionListener(e -> {
            String name = textField.getText();
            label.setText("Hello, " + name + "!");
        });

        frame.setVisible(true); // Rende la finestra visibile dopo aver aggiunto tutti i componenti
    }

    
}