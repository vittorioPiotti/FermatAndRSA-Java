/*
    Fermat And RSA v1.0.0 (https://github.com/vittorioPiotti/Fermat-And-RSA/releases/tag/1.0.0)
    Copyright 2024 Vittorio Piotti
    Licensed under GPL-3.0 (https://github.com/vittorioPiotti/Fermat-And-RSA/blob/main/LICENSE.md)
*/

/*
    FlatLaf v3.2.5 (https://github.com/JFormDesigner/FlatLaf/releases/tag/3.2.5)
    Copyright 2024 JFormDesigner GmbH
    Licensed under Apache License 2.0 (https://github.com/JFormDesigner/FlatLaf/blob/main/LICENSE)
*/

package org.app;
import org.app.applications.encryption.ReadFile;
import org.app.applications.encryption.TestEncryption;
import org.app.applications.primality.SavePrimality;
import org.app.applications.primality.TestPrimality;
import org.app.windows.WinManager;

/**
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 * La classe {@code App} rappresenta il punto di ingresso principale dell'applicazione e gestisce l'inizializzazione e il controllo
 * dei vari componenti dell'interfaccia utente e delle operazioni di calcolo.
 * <p>
 * Questa classe si occupa di:
 * <ul>
 *     <li>Gestire la creazione e la configurazione di {@link WinManager} per la gestione delle finestre dell'applicazione.</li>
 *     <li>Definire e gestire i {@link Runnable} per le operazioni di calcolo e gestione dei risultati.</li>
 *     <li>Gestire i test di primalità e le operazioni di crittografia.</li>
 * </ul>
 * </p>
 */
public class App {

	/**
     * Gestore delle finestre per la gestione delle operazioni di primalità e crittografia.
     * <p>
     * Questo attributo è responsabile della creazione e del controllo delle finestre relative ai calcoli di primalità e crittografia.
     * </p>
     */
    private static WinManager windowManager;

	/**
     * Oggetto per eseguire i test di primalità.
     * <p>
     * Questo attributo contiene l'istanza di {@link TestPrimality} che viene utilizzata per eseguire i test di primalità sui numeri.
     * </p>
     */
	private static TestPrimality primalityTest;

	/**
     * Oggetto per eseguire le operazioni di crittografia e decrittografia.
     * <p>
     * Questo attributo contiene l'istanza di {@link TestEncryption} che viene utilizzata per gestire le operazioni di crittografia e decrittografia.
     * </p>
     */
	private static TestEncryption encryption;


	/**
     * Oggetto per gestire il salvataggio dei risultati dei test di primalità in un file.
     * <p>
     * Questo attributo contiene l'istanza di {@link SavePrimality} che è responsabile del salvataggio dei risultati dei test di primalità in un file.
     * </p>
     */
	private static SavePrimality saveInFilePrimality;

	/**
     * Oggetto per leggere i file necessari alle operazioni di crittografia.
     * <p>
     * Questo attributo contiene l'istanza di {@link ReadFile} che viene utilizzata per leggere i file e verificare i dati necessari per le operazioni di crittografia.
     * </p>
     */
	private static ReadFile readFileEncryption;

	/**
     * Thread utilizzato per eseguire i test di primalità.
     * <p>
     * Questo attributo contiene il {@link Thread} che esegue il test di primalità, permettendo l'esecuzione in background.
     * </p>
     */
	private static Thread taskPrimalityTest;

    /**
     * {@link Runnable} per stampare i risultati del test di primalità e aggiornare l'interfaccia utente.
     * <p>
     * Questo {@link Runnable} è responsabile dell'aggiornamento dell'interfaccia utente con i risultati calcolati dal test di primalità.
     * </p>
     */
	public static Runnable printResultPrimality = () -> {
		windowManager.incrementCalculatedPrimeNumbers();
		if(!primalityTest.getResult().equals("1") && !primalityTest.getResult().equals("0")  ){
			windowManager.appendCalculatedNumber( "   " + primalityTest.getResult() +"\n");
		}
	};

	/**
     * {@link Runnable} per incrementare la barra di progresso nell'interfaccia utente.
     * <p>
     * Questo {@link Runnable} è responsabile dell'incremento della barra di progresso durante l'esecuzione del calcolo di primalità.
     * </p>
     */
	private static Runnable incrementProgressBar = () -> {
        windowManager.incrementProgressBar();
    };
	
	/**
     * {@link Runnable} per fermare il calcolo di primalità.
     * <p>
     * Questo {@link Runnable} interrompe il thread di calcolo della primalità se è in esecuzione.
     * </p>
     */
	public static Runnable stopCalcPrimality = () -> {
		if (taskPrimalityTest != null) {
            taskPrimalityTest.interrupt();
        }
	};
	
	/**
	 * Genera una stringa di risultati della ricerca sui numeri primi.
	 * <p>
	 * Questo metodo costruisce una stringa che include informazioni dettagliate sui risultati della ricerca di numeri primi. 
	 * La stringa contiene:
	 * <ul>
	 *     <li>Il titolo della sezione ("Ricerca Numeri Primi").</li>
	 *     <li>Il valore minimo e massimo dell'intervallo di ricerca, formattato con un esponente se l'esponente non è zero.</li>
	 *     <li>Il metodo di calcolo utilizzato per la ricerca dei numeri primi.</li>
	 *     <li>Il tempo totale impiegato per il calcolo.</li>
	 *     <li>Il numero di numeri primi calcolati, o 0 se il risultato è vuoto.</li>
	 *     <li>Una lista dei numeri primi calcolati, ognuno su una nuova riga.</li>
	 * </ul>
	 * </p>
	 * 
	 * @return Una stringa che rappresenta i risultati della ricerca di numeri primi, formattata per la visualizzazione o la stampa.
	 */
	private static String getResults(){
		StringBuilder stringResults = new StringBuilder();
		stringResults.append("Ricerca Numeri Primi\n");
		stringResults.append("Input minimo: " + ((windowManager.getExpMin() != 0)?windowManager.getValMin() + " * 10 ^ "+windowManager.getExpMin():windowManager.getMin()) +"\n");
		stringResults.append("Input massimo: " + ((windowManager.getExpMax() != 0)?windowManager.getValMax() + " * 10 ^ "+windowManager.getExpMax():windowManager.getMax()) +"\n");
		stringResults.append("Metodo calcolo: " + windowManager.toStringMetodo()+"\n");
		stringResults.append("Tempo calcolo: " + primalityTest.getTime() + "s\n");
		stringResults.append("Numeri calcolati: " + (primalityTest.getResults().size() == 1 && primalityTest.getResults().get(0).equals("") ? 0 : primalityTest.getResults().size()) + "\n");
		for (String result : primalityTest.getResults()){
			stringResults.append(result).append("\n");
		}
		return stringResults.toString();
	}

	/**
     * {@link Runnable} per avviare il salvataggio dei risultati dei test di primalità in un file.
     * <p>
     * Questo {@link Runnable} crea un nuovo file e salva i risultati dei test di primalità se la finestra di salvataggio è visibile.
     * </p>
     */
	public static Runnable startSavingPrimality = () -> {
			if(windowManager.existWindowSaveInFile() == true){
			saveInFilePrimality = new SavePrimality(getResults(), windowManager.getPath());
			saveInFilePrimality.newFile();
		}
	};

	/**
     * {@link Runnable} per inizializzare e controllare la lettura del file di crittografia.
     * <p>
     * Questo {@link Runnable} legge il file di crittografia, verifica la validità e crea un'istanza di {@link TestEncryption} se il file è valido.
     * </p>
     */
	public static Runnable initAndCheckReadingFile = () -> {
		
		readFileEncryption = new ReadFile(windowManager.getPathReading(),windowManager.getModInverse());
		windowManager.setFileErrorType(readFileEncryption.checkFile());
		readFileEncryption = new ReadFile(windowManager.getPathReading(),windowManager.getModInverse());
			String[] selectedRows = readFileEncryption.getRows();
			if (readFileEncryption.checkFile() == true) {
				encryption = new TestEncryption(windowManager.getModInverse(),selectedRows[0],selectedRows[1],selectedRows[2]);
			}

	};



    /**
     * {@link Runnable} per avviare il calcolo dei numeri primi.
     * <p>
     * Questo {@link Runnable} crea un'istanza di {@link TestPrimality}, avvia un nuovo thread per eseguire il calcolo e aggiorna l'interfaccia utente.
     * </p>
     */
	public static Runnable startCalcPrimality = () -> {
		primalityTest = new TestPrimality(windowManager.getMin(), windowManager.getMax(), windowManager.getMetodo(), stopCalcPrimality, incrementProgressBar,printResultPrimality);
        taskPrimalityTest = new Thread(primalityTest, "Thread-TestPrimalita");
        taskPrimalityTest.start();
	};

	/**
     * {@link Runnable} per avviare il calcolo della crittografia.
     * <p>
     * Questo {@link Runnable} legge il file di crittografia e, in base al metodo di crittografia, esegue l'operazione di crittografia o decrittografia.
     * </p>
     */
	public static Runnable startCalcEncryption = () -> {
		readFileEncryption = new ReadFile(windowManager.getPathReading(),windowManager.getModInverse());
		if (readFileEncryption.checkFile() == true) {
			
				if(windowManager.getEncryptionMetodo() == 0){	
					windowManager.setResultsEncryption(encryption.encrypt(windowManager.getMessage()));
				}else{
					windowManager.setResultsEncryption(encryption.decrypt(windowManager.getMessage()));
				}
			

		
		}

		
	};

	/**
     * {@link Runnable} per controllare eventuali modifiche al file di crittografia.
     * <p>
     * Questo {@link Runnable} verifica se il file di crittografia è stato modificato e, se necessario, aggiorna l'interfaccia utente di conseguenza.
     * </p>
     */
	public static Runnable checkFileOnchange = () -> {
		if (readFileEncryption != null){
			if(readFileEncryption.checkFile()){
				windowManager.setFileErrorType(false);
				windowManager.openWindowErrorOutput();
			

			}

		}

	};
	
	
	/**
     * Costruttore della classe {@code App}.
     * <p>
     * Inizializza {@link WinManager} con i vari {@link Runnable} per gestire le operazioni di calcolo e le interazioni dell'interfaccia utente.
     * </p>
     */
	public App(){
		windowManager = new WinManager(startCalcPrimality,stopCalcPrimality,startSavingPrimality,startCalcEncryption,initAndCheckReadingFile,checkFileOnchange);
	}
	

}
