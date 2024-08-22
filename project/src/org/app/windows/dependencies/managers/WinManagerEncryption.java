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

package org.app.windows.dependencies.managers;

import java.util.function.Supplier;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.app.windows.dependencies.components.encryption.WinEncryption;
import org.app.windows.dependencies.components.encryption.dialogs.WinErrorInEncryption;
import org.app.windows.dependencies.components.encryption.dialogs.WinErrorOutEncryption;
import org.app.windows.dependencies.components.encryption.panels.WinInEncryption;
import org.app.windows.dependencies.components.encryption.panels.WinOutEncryption;
import org.app.windows.dependencies.components.encryption.panels.WinSaveEncryption;


/**
 * Gestisce l'interfaccia utente per le operazioni di crittografia, inclusi l'input dell'utente, l'output, e le finestre di errore.
 * Coordina le azioni tra le finestre di input, output e errore, e gestisce le operazioni di crittografia.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinManagerEncryption {


    /**
     * Messaggi di errore per la crittografia.
     */
    private final static String[] errorMessagesEncryption = {
        "Messaggio vuoto",
        "Nessun file selezionato",
    };
    
    /**
     * Finestra di errore per input di crittografia.
     */
    private WinErrorInEncryption windowErrorInputEncryption;

    /**
     * Finestra di errore per output di crittografia.
     */
    private WinErrorOutEncryption windowErrorOutputEncryption;

    /**
     * Finestra di input per la crittografia.
     */
    private WinInEncryption windowInputEncryption;

    /**
     * Finestra di output per la crittografia.
     */
    private WinOutEncryption windowOutputEncryption;
    
    /**
     * Finestra di salvataggio file per la crittografia.
     */
    private WinSaveEncryption windowReadingFileEncryption;

    /**
     * Finestra principale di crittografia.
     */
    private WinEncryption windowEncryption;

    /**
     * Fornisce il frame principale dell'applicazione.
     */
    private Supplier<JFrame> getFrameWindowHome;

    /**
     * Azione da eseguire per avviare la crittografia.
     */
    private Runnable startCalcEncryption;

    /**
     * Azione da eseguire per inizializzare e controllare la lettura del file.
     */
    private Runnable initAndCheckReadingFile;


    /**
     * Costruisce un'istanza di WinManagerEncryption.
     * 
     * @param getFrameWindowHome Fornisce il frame principale dell'applicazione.
     * @param startCalcEncryption Runnable per avviare la crittografia.
     * @param initAndCheckReadingFile Runnable per inizializzare e controllare la lettura del file.
     * @param checkFileOnchange Runnable per controllare i cambiamenti del file.
     */
    public WinManagerEncryption(Supplier<JFrame> getFrameWindowHome,Runnable startCalcEncryption,Runnable initAndCheckReadingFile,Runnable checkFileOnchange){
        this.getFrameWindowHome = getFrameWindowHome;
        this.startCalcEncryption = startCalcEncryption;
        this.initAndCheckReadingFile = initAndCheckReadingFile;
        windowInputEncryption = new WinInEncryption(openWindowErrorInputEncryption,openWindowReadingFile,checkFileOnchange);
        windowOutputEncryption = new WinOutEncryption();
        windowEncryption = new WinEncryption(windowInputEncryption.getWindow(),windowOutputEncryption.getWindow());
        
    }

    /**
     * Runnable per aprire la finestra di errore dell'input di crittografia.
     */
    private final Runnable openWindowErrorInputEncryption = () -> {
        if(windowErrorInputEncryption != null){
            windowErrorInputEncryption.dispose();
        }
       
        switch(windowInputEncryption.getErrorType() + ((windowReadingFileEncryption == null || windowReadingFileEncryption.getPath() == null)?"1":"0")){
            case "00":
                
                windowOutputEncryption.initWindow(windowInputEncryption.getMetodo());
                startCalcEncryption.run();
                
         
                break;
            case "10":
                windowErrorInputEncryption = new WinErrorInEncryption(getFrameWindowHome.get(), new String[]{errorMessagesEncryption[0]});
                break;
            case "01":
                windowErrorInputEncryption = new WinErrorInEncryption(getFrameWindowHome.get(), new String[]{errorMessagesEncryption[1]});
                break;
            default:
                windowErrorInputEncryption = new WinErrorInEncryption(getFrameWindowHome.get(), errorMessagesEncryption);
                break;
        }

    };

  
    /**
     * Runnable per aprire la finestra di errore dell'output di crittografia.
     */
    public final Runnable openWindowErrorOutputEncryption = () -> {
        if(windowReadingFileEncryption != null){
            if(windowErrorOutputEncryption != null)windowErrorOutputEncryption.dispose();
            initAndCheckReadingFile.run();
            windowErrorOutputEncryption = new WinErrorOutEncryption(getFrameWindowHome.get(), windowReadingFileEncryption.getErrorType());
        }
    };

    /**
     * Runnable per aprire la finestra di salvataggio del file di crittografia.
     */
    private final Runnable openWindowReadingFile = () ->{
    
            windowReadingFileEncryption = new WinSaveEncryption(openWindowErrorOutputEncryption);
            windowReadingFileEncryption.initWindow();
        
    };


    /**
     * Imposta il tipo di errore del file nella finestra di salvataggio.
     * 
     * @param state Indica se il file ha un errore (true) o meno (false).
     */
    public void setFileErrorType(boolean state){
        if(windowReadingFileEncryption != null){
            windowReadingFileEncryption.setErrorType(state);
            windowInputEncryption.setLabelFile(state);
            if(state == false){
                windowReadingFileEncryption.resetPath();
            }
        }

    }
   
    /**
     * Ottiene se è stato selezionato l'inverso mod.
     * 
     * @return true se l'inverso mod è selezionato, false altrimenti.
     */
    public boolean getModInverse(){
        return windowInputEncryption.getModInverse();
    }

    /**
     * Ottiene il percorso del file di lettura.
     * 
     * @return Il percorso del file di lettura, o una stringa vuota se non è stato selezionato un file.
     */
    public String getPathReading(){
        if(windowReadingFileEncryption != null){
            return windowReadingFileEncryption.getPath();
        } else return "";
    }

  
    /**
     * Imposta i risultati della crittografia nella finestra di output.
     * 
     * @param results I risultati della crittografia da visualizzare.
     */
    public void setResultsEncryption(String results){
        windowOutputEncryption.setResults(results);
    }

    /**
     * Ottiene il metodo di crittografia selezionato.
     * 
     * @return Il metodo di crittografia selezionato.
     */
    public int getEncryptionMetodo(){
        return windowInputEncryption.getMetodo();
    }

    /**
     * Ottiene il messaggio di crittografia dall'input dell'utente.
     * 
     * @return Il messaggio di crittografia.
     */
    public String getMessage(){
        return windowInputEncryption.getMessage();
    }


    /**
     * Ottiene la finestra principale di crittografia.
     * 
     * @return La finestra principale di crittografia.
     */
    public JPanel getWindow(){
        return windowEncryption.getWindow();
    }


    /**
     * Imposta il layout della griglia nella finestra principale di crittografia.
     * 
     * @param rows Il numero di righe nella griglia.
     * @param cols Il numero di colonne nella griglia.
     */
    public void setGridPanel(int rows, int cols){
        windowEncryption.setGridPanel(rows, cols);
    }
}
