# BasicGUIProject

Piccolo progetto Java Swing per ripassare le basi della GUI.

## Descrizione
L'applicazione apre una finestra con:
- un campo di testo (`JTextField`)
- un pulsante (`JButton`)
- un'etichetta (`JLabel`)

Quando premi il pulsante, il testo inserito viene letto e mostrato come saluto nel formato:

`Hello, Nome!`

## Tecnologie
- Java
- Swing (`javax.swing`)

## Requisiti
- JDK installato (versione 8 o superiore)
- Terminale (PowerShell, CMD o simili)

## Come eseguire
Dalla cartella del progetto:

1. Compila il file:
   ```bash
   javac Main.java
   ```
2. Esegui il programma:
   ```bash
   java Main
   ```

## Struttura del progetto
- `Main.java`: contiene tutta la logica della finestra e degli eventi

## Obiettivo didattico
Esercitarsi con:
- creazione di una finestra Swing (`JFrame`)
- inserimento e posizionamento manuale di componenti (`setBounds`, `setLayout(null)`)
- gestione di eventi con `ActionListener`

## Possibili miglioramenti
- usare un layout manager (es. `BorderLayout`, `FlowLayout`) invece di posizionamento assoluto
- validare l'input quando il campo è vuoto
- separare la logica in più classi
