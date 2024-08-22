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

package org.app.windows;

import java.util.function.Supplier;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.app.windows.dependencies.components.WinHome;
import org.app.windows.dependencies.managers.WinManagerEncryption;
import org.app.windows.dependencies.managers.WinManagerPrimality;

import com.formdev.flatlaf.FlatLightLaf;
  

/**
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 * Classe principale per la gestione delle finestre dell'applicazione, che include la gestione delle finestre di test di primalità e di cifratura RSA.
 * 
 * Questa classe coordina le finestre di test di primalità e di cifratura RSA attraverso i rispettivi gestori e gestisce le interazioni tra di esse.
 */
public class WinManager {

    

    /**
     * Gestore per le finestre e le funzionalità relative ai test di primalità.
     * 
     * Questo attributo è utilizzato per accedere e gestire la finestra e le operazioni associate al test di primalità.
     */
    private WinManagerPrimality winManagerPrimality;

    /**
     * Gestore per le finestre e le funzionalità relative alla cifratura RSA.
     * 
     * Questo attributo è utilizzato per accedere e gestire la finestra e le operazioni associate alla cifratura RSA.
     */
    private WinManagerEncryption winManagerEncryption;


    /**
     * Finestra principale dell'applicazione che gestisce la visualizzazione e l'interazione tra le finestre di test di primalità e cifratura RSA.
     * 
     * Questo attributo rappresenta la finestra principale che ospita le finestre di test di primalità e cifratura RSA, e gestisce la loro disposizione e visibilità.
     */
    private WinHome windowHome;

 
    

    /**
     * Fornisce un'istanza di {@link JFrame} della finestra principale dell'applicazione.
     * 
     * Questo attributo è una funzione che restituisce la finestra principale dell'applicazione {@code JFrame}.
     * È utilizzato per ottenere una referenza alla finestra principale quando è necessario aggiornare o interagire con essa,
     * come nel caso di apertura di nuove finestre di errore o salvataggio. Viene utilizzato dai gestori di finestre secondarie
     * per avere accesso alla finestra principale per la gestione dell'interfaccia utente e delle operazioni correlate.
     */
    private final Supplier<JFrame> getFrameWindowHome = () -> windowHome.getFrame();


    /**
     * Costruttore della classe {@code WinManager}.
     * 
     * @param startCalcPrimality Runnable per avviare il calcolo dei numeri primi.
     * @param stopCalcPrimality Runnable per fermare il calcolo dei numeri primi.
     * @param startSavingPrimality Runnable per avviare il salvataggio dei numeri primi.
     * @param startCalcEncryption Runnable per avviare il calcolo della cifratura.
     * @param initAndCheckReadingFile Runnable per inizializzare e controllare il file di lettura.
     * @param checkFileOnchange Runnable per controllare le modifiche al file.
     */
    public WinManager(Runnable startCalcPrimality,Runnable stopCalcPrimality,Runnable startSavingPrimality,Runnable startCalcEncryption,Runnable initAndCheckReadingFile,Runnable checkFileOnchange){
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        winManagerPrimality = new WinManagerPrimality(getFrameWindowHome,stopCalcPrimality,startSavingPrimality,startCalcPrimality);
        winManagerEncryption = new WinManagerEncryption(getFrameWindowHome,startCalcEncryption,initAndCheckReadingFile,checkFileOnchange);

        



        windowHome = new WinHome(winManagerPrimality.getWindow(),winManagerEncryption.getWindow(),resizeFromWidth,winManagerPrimality.switchWindow);



        windowHome.setVisible();

    }   

    /**
     * Runnable per ridimensionare la finestra in base alla larghezza.
     */
    private final Runnable resizeFromWidth = () -> {

        if (windowHome != null){
            boolean selectedWindow = windowHome.getSelectedWindow();
            int frameWidth = windowHome.getFrameWidth();
            if (frameWidth < 965) {
                if(selectedWindow == true){
                    winManagerPrimality.setGridPanel(2,1);
                }else {
                    winManagerEncryption.setGridPanel(2, 1);
                }
            } else {
                if(selectedWindow == true){
                    winManagerPrimality.setGridPanel(1,2);      
                }else {
                    winManagerEncryption.setGridPanel(1,2);
                }
            }
        } 
    };
    
   

    /**
     * Restituisce il valore massimo per i numeri primi.
     * 
     * @return Il valore massimo come {@code int}.
     */
    public int getValMax(){
        return winManagerPrimality.getValMax();
    }

    /**
     * Restituisce il valore minimo per i numeri primi.
     * 
     * @return Il valore minimo come {@code int}.
     */
    public int getValMin(){
        return winManagerPrimality.getValMin();
    }

    /**
     * Restituisce l'esponente massimo per i numeri primi.
     * 
     * @return L'esponente massimo come {@code int}.
     */
    public int getExpMax(){
        return winManagerPrimality.getExpMax();
    }

    /**
     * Restituisce l'esponente minimo per i numeri primi.
     * 
     * @return L'esponente minimo come {@code int}.
     */
    public int getExpMin(){
        return winManagerPrimality.getExpMax();
    }

    /**
     * Restituisce il valore minimo come stringa.
     * 
     * @return Il valore minimo come {@code String}.
     */
    public String getMin(){
        return winManagerPrimality.getMin();
    }

    /**
     * Restituisce il valore massimo come stringa.
     * 
     * @return Il valore massimo come {@code String}.
     */
    public String getMax(){
        return winManagerPrimality.getMax();
    }

    /**
     * Restituisce il metodo di calcolo selezionato.
     * 
     * @return Il metodo come {@code int}.
     */
    public int getMetodo(){
        return winManagerPrimality.getMetodo();
    }

    /**
     * Restituisce una rappresentazione del metodo di calcolo come stringa.
     * 
     * @return Il metodo come {@code String}.
     */
    public String toStringMetodo(){
        return winManagerPrimality.toStringMetodo();
    }

    /**
     * Incrementa la barra di progresso nella finestra di test di primalità.
     */
    public boolean existWindowSaveInFile(){
        return winManagerPrimality.existWindowSaveInFile();
    }

    /**
     * Incrementa il conteggio dei numeri primi calcolati nella finestra di test di primalità.
     */
    public void incrementProgressBar(){
        winManagerPrimality.incrementProgressBar();
    }

    /**
     * Aggiunge un numero primo calcolato alla finestra di test di primalità.
     * 
     * @param result Il numero primo calcolato come {@code String}.
     */
    public void incrementCalculatedPrimeNumbers(){
        winManagerPrimality.incrementCalculatedPrimeNumbers();
    }

    /**
     * Verifica se esiste una finestra di salvataggio dei numeri primi. Se non esiste, viene aperta.
     * 
     * @return {@code true} se la finestra di salvataggio esiste, {@code false} altrimenti.
     */
    public void appendCalculatedNumber(String result){
        winManagerPrimality.appendCalculatedNumber(result);
    }

    /**
     * Restituisce il percorso del file di salvataggio.
     * 
     * @return Il percorso del file come {@code String}.
     */
    public String getPath(){
        return winManagerPrimality.getPath();
    }

    /**
     * Restituisce il percorso del file di lettura per la cifratura RSA.
     * 
     * @return Il percorso del file di lettura come {@code String}.
     */
    public String getPathReading(){
        return winManagerEncryption.getPathReading();
    }

    /**
     * Imposta lo stato di errore del file nella finestra di cifratura RSA.
     * 
     * @param state {@code true} se c'è un errore nel file, {@code false} altrimenti.
     */
    public void setFileErrorType(boolean state){
        winManagerEncryption.setFileErrorType(state);
    }

    /**
     * Restituisce il metodo di cifratura selezionato.
     * 
     * @return Il metodo di cifratura come {@code int}.
     */
    public int getEncryptionMetodo(){
        return winManagerEncryption.getEncryptionMetodo();
    }

    /**
     * Imposta i risultati della cifratura nella finestra di output della cifratura RSA.
     * 
     * @param results I risultati della cifratura come {@code String}.
     */
    public void setResultsEncryption(String results){
        winManagerEncryption.setResultsEncryption(results);
    }

    /**
     * Restituisce lo stato della scelta dell'inverso modulare nella finestra di cifratura RSA.
     * 
     * @return {@code true} se è selezionato l'inverso modulare, {@code false} altrimenti.
     */
    public boolean getModInverse(){
        return winManagerEncryption.getModInverse();
    }

    /**
     * Apre la finestra di errore dell'output della cifratura RSA.
     */
    public void openWindowErrorOutput(){
        winManagerEncryption.openWindowErrorOutputEncryption.run();
    }

    /**
     * Restituisce il messaggio da cifrare nella finestra di cifratura RSA.
     * 
     * @return Il messaggio come {@code String}.
     */
    public String getMessage(){
        return winManagerEncryption.getMessage();
    }

  
}

