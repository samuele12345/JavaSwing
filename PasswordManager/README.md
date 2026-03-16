# PasswordManager (Java Swing)

Applicazione desktop Java per la gestione di credenziali (username, sito, password) con interfaccia grafica Swing e persistenza su file di testo locale.

## Obiettivo del progetto

Il progetto permette di:
- accedere alle sezioni operative tramite password nella schermata iniziale
- inserire nuove credenziali
- cercare tutte le credenziali associate a uno username
- eliminare una credenziale specifica tramite coppia username + sito

I dati vengono salvati nel file locale `passwords.txt`.

## Tecnologie

- Java (JDK 21 consigliato)
- Swing/AWT per la GUI
- File I/O (`BufferedReader`, `BufferedWriter`, `FileWriter`, `FileReader`)

## Struttura del progetto

- `Main.java`: entry point dell'applicazione (avvia la GUI principale su EDT)
- `MainManagerGUI.java`: schermata menu iniziale con controllo password e opzione "Ricordami"
- `PasswordManagerGUI.java`: schermata di inserimento nuove credenziali
- `OttieniDatiGUI.java`: schermata di ricerca/eliminazione credenziali
- `PasswordService.java`: logica di caricamento, ricerca ed eliminazione dati
- `FileManager.java`: salvataggio delle credenziali su file
- `PasswordEntry.java`: modello dati (username, website, password)
- `passwords.txt`: archivio persistente dei dati
- `Appunti.txt`: note funzionali di progetto

## Accesso all'applicazione

All'avvio viene mostrata la schermata principale con:
- campo password (`JPasswordField`)
- pulsante `Sezione Aggiunta`
- pulsante `Ottieni Dati`
- checkbox `Ricordami`

Comportamento:
- Password corretta: apre la sezione richiesta.
- Campo vuoto: mostra il messaggio `Inserire la password`.
- Password errata: mostra il messaggio `Password errata, riprovare`.
- Checkbox `Ricordami` attiva: salva localmente la password nelle Java Preferences (`java.util.prefs.Preferences`) e la precompila al riavvio.
- Checkbox non attiva: rimuove l'eventuale password salvata.

Password attuale impostata nel codice:

```text
PasswordProva
```

## Regole funzionali (da specifica/Appunti)

### Inserimento credenziali
- Un inserimento viene bloccato se tutti i campi sono uguali a una credenziale gia presente.
- E permesso riutilizzare parzialmente i dati (ad esempio stesso username con sito diverso, oppure stessa password su siti diversi).
- Non e accettabile inserire una coppia `username + sito` gia esistente (anche con password diversa): in questo caso l'app suggerisce di eliminare il record precedente e reinserire.

### Eliminazione credenziali
- Per eliminare e necessario compilare tutti i campi richiesti nella schermata `OttieniDati` (username e sito).
- L'eliminazione rimuove il record sia dalla lista in memoria sia da `passwords.txt`.

### Ricerca credenziali
- La ricerca per username mostra tutti i record associati a quello username (piu siti/password possibili).

## Formato del file `passwords.txt`

Ogni record e scritto cosi:

```text
Username: <valore>
Website: <valore>
Password: <valore>

```

Il parser in `PasswordService.loadFromFile()` ricostruisce la lista leggendo questo formato riga per riga.

## Come avviare il progetto

Dal terminale nella cartella del progetto:

```bash
javac *.java
java Main
```

Su Windows PowerShell i comandi sono gli stessi.

## Note importanti

- La password di accesso alla GUI e hardcoded nel codice (`MainManagerGUI`) e viene usata solo come gate applicativo locale.
- Se si attiva `Ricordami`, la password viene salvata in chiaro nelle Java Preferences dell'utente.
- I dati sono in chiaro in `passwords.txt` (nessuna cifratura).
- Il progetto e didattico: non adatto a uso produzione o gestione password reali.
- Se `passwords.txt` non esiste, viene creato automaticamente al primo salvataggio.

## Possibili miglioramenti futuri

- cifratura delle password a riposo
- gestione sicura della password di accesso (hash/secret esterno, niente hardcode)
- validazioni piu forti lato GUI
- scroll nei risultati ricerca quando i record sono molti
- test automatici per `PasswordService`
- refactor con pattern MVC per separare meglio GUI e logica
