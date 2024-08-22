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

import org.app.windows.dependencies.components.primality.WinPrimality;
import org.app.windows.dependencies.components.primality.dialogs.WinErrorInPrimality;
import org.app.windows.dependencies.components.primality.dialogs.WinErrorOutPrimality;
import org.app.windows.dependencies.components.primality.panels.WinInPrimality;
import org.app.windows.dependencies.components.primality.panels.WinOutPrimality;
import org.app.windows.dependencies.components.primality.panels.WinSavePrimality;

/**
 * Gestisce l'interfaccia utente per il test di primalità, inclusi l'input dell'utente, l'output, e le finestre di errore e salvataggio.
 * Coordina le azioni tra le finestre di input, output e errore e gestisce le operazioni di calcolo dei numeri primi.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinManagerPrimality {

    /**
     * Messaggi di errore per il test di primalità.
     */
    private final static String[] errorMessages = {
        "Numero minimo",
        "Numero massimo",
        "Minimo maggiore di massimo",
        "Minimo uguale a massimo",
        "Overflow caratteri",
        "Ammessi solo numeri",
    };

    /**
     * Finestra di errore per input di primalità.
     */
    private WinErrorInPrimality windowErrorInput;

    /**
     * Finestra di errore per output di primalità.
     */
    private WinErrorOutPrimality windowErrorOutput;

    /**
     * Finestra di input per il test di primalità.
     */
    private WinInPrimality windowInput;

    /**
     * Finestra di output per il test di primalità.
     */
    private WinOutPrimality windowOutput;

    /**
     * Finestra di salvataggio file per il test di primalità.
     */
    private WinSavePrimality windowSaveInFile;

    /**
     * Finestra principale del test di primalità.
     */
    private WinPrimality windowPrimalityTest;

    /**
     * Fornisce il frame principale dell'applicazione.
     */
    private Supplier<JFrame> getFrameWindowHome;

    /**
     * Azione da eseguire per fermare il calcolo della primalità.
     */
    private Runnable stopCalcPrimality;

    /**
     * Azione da eseguire per avviare il salvataggio dei risultati della primalità.
     */
    private Runnable startSavingPrimality;

    /**
     * Azione da eseguire per avviare il calcolo della primalità.
     */
    private Runnable startCalcPrimality;


    /**
     * Costruisce un'istanza di WinManagerPrimality.
     * 
     * @param getFrameWindowHome Fornisce il frame principale dell'applicazione.
     * @param stopCalcPrimality Runnable per fermare il calcolo della primalità.
     * @param startSavingPrimality Runnable per avviare il salvataggio dei risultati della primalità.
     * @param startCalcPrimality Runnable per avviare il calcolo della primalità.
     */
    public WinManagerPrimality(Supplier<JFrame> getFrameWindowHome,Runnable stopCalcPrimality,Runnable startSavingPrimality, Runnable startCalcPrimality  ){
        this.getFrameWindowHome = getFrameWindowHome;
        this.stopCalcPrimality = stopCalcPrimality;
        this.startSavingPrimality = startSavingPrimality;
        this.startCalcPrimality = startCalcPrimality;
        windowInput = new WinInPrimality(openWindowErrorInput);
        windowOutput = new WinOutPrimality(this.stopCalcPrimality,openWindowSaveInFile );
        windowPrimalityTest = new WinPrimality(windowInput.getWindow(),windowOutput.getWindow());
    }

    /**
     * Runnable per cambiare la finestra di output e fermare il calcolo della primalità.
     */
    public final Runnable switchWindow = () ->{
        windowOutput.stopCalc();
    };

    /**
     * Runnable per aprire la finestra di errore dell'output di primalità.
     */
    public final Runnable openWindowErrorOutput = () -> {
        if(windowErrorOutput != null)windowErrorOutput.dispose();
        windowErrorOutput = new WinErrorOutPrimality(getFrameWindowHome.get(), windowSaveInFile.getErrorType());
        if(windowSaveInFile.getErrorType())startSavingPrimality.run();
    };
  
    /**
     * Runnable per aprire la finestra di salvataggio dei risultati della primalità.
     */
    private final Runnable openWindowSaveInFile = () ->{
        windowSaveInFile = new WinSavePrimality("numeri-primi",openWindowErrorOutput);
        windowSaveInFile.initWindow();
    };

    /**
     * Runnable per aprire la finestra di errore dell'input di primalità.
     */
    private final Runnable openWindowErrorInput = () -> {
        if(windowErrorInput != null)windowErrorInput.dispose();
        switch(windowInput.getErrorType()){
            case "00":
                stopCalcPrimality.run();
                windowOutput.initWindow();
                windowOutput.initProgressBar(getMax(), getMin());
                startCalcPrimality.run();
                break;
            case "22":
                windowErrorInput = new WinErrorInPrimality(getFrameWindowHome.get(),new String[]{errorMessages[2]});
                break;
            case "33":
                windowErrorInput = new WinErrorInPrimality(getFrameWindowHome.get(),new String[]{errorMessages[3]});
                break;
            case "44":
                windowErrorInput = new WinErrorInPrimality(getFrameWindowHome.get(),new String[]{errorMessages[4]});
                break;
            case "55":
                windowErrorInput = new WinErrorInPrimality(getFrameWindowHome.get(),new String[]{errorMessages[5]});
                break;
            default:
                String [] newMessage;
                int c = 0;
                for(int i = 0; i < 2; i ++){
                    if(windowInput.getErrorType().charAt(i) == '1')c++;
                }
                newMessage = new String [c];
                c = 0;
                for(int i = 0; i < 2; i ++){
                    if(windowInput.getErrorType().charAt(i) == '1'){
                        newMessage[c] = errorMessages[i];
                        c++;
                    }
                }
                windowErrorInput = new WinErrorInPrimality(getFrameWindowHome.get(),newMessage);
                break;
        }
    };


    /**
     * Ottiene il valore massimo per il test di primalità.
     * 
     * @return Il valore massimo.
     */
    public int getValMax(){
        return windowInput.getValMax();
    }

    /**
     * Ottiene il valore minimo per il test di primalità.
     * 
     * @return Il valore minimo.
     */
    public int getValMin(){
        return windowInput.getValMin();
    }

    /**
     * Ottiene l'esponente massimo per il test di primalità.
     * 
     * @return L'esponente massimo.
     */
    public int getExpMax(){
        return windowInput.getExpMax();
    }

    /**
     * Ottiene l'esponente minimo per il test di primalità.
     * 
     * @return L'esponente minimo.
     */
    public int getExpMin(){
        return windowInput.getExpMax();
    }

    /**
     * Ottiene il valore minimo come stringa per il test di primalità.
     * 
     * @return Il valore minimo come stringa.
     */
    public String getMin(){
        return windowInput.getMin();
    }

    /**
     * Ottiene il valore massimo come stringa per il test di primalità.
     * 
     * @return Il valore massimo come stringa.
     */
    public String getMax(){
        return windowInput.getMax();
    }

    /**
     * Ottiene il metodo di test di primalità selezionato.
     * 
     * @return Il metodo di test di primalità selezionato.
     */
    public int getMetodo(){
        return windowInput.getMetodo();
    }

    /**
     * Ottiene una rappresentazione del metodo di test di primalità come stringa.
     * 
     * @return La rappresentazione del metodo come stringa.
     */
    public String toStringMetodo(){
        return windowInput.toStringMetodo();
    }

    /**
     * Incrementa la barra di progresso nella finestra di output di primalità.
     */
    public void incrementProgressBar(){
        windowOutput.incrementProgressBar();
    }

    /**
     * Incrementa il conteggio dei numeri primi calcolati nella finestra di output di primalità.
     */
    public void incrementCalculatedPrimeNumbers(){
        windowOutput.incrementCalculatedPrimeNumbers();
    }

    /**
     * Aggiunge un numero calcolato ai risultati nella finestra di output di primalità.
     * 
     * @param result Il numero calcolato da aggiungere ai risultati.
     */
    public void appendCalculatedNumber(String result){
        windowOutput.appendCalculatedNumber(result);
    }

    /**
     * Verifica se esiste una finestra di salvataggio dei risultati di primalità e, se necessario, la apre.
     * 
     * @return true se la finestra di salvataggio esiste, false altrimenti.
     */
    public boolean existWindowSaveInFile(){
        if(windowSaveInFile == null)openWindowSaveInFile.run();
        return windowSaveInFile != null;
    }

    /**
     * Ottiene il percorso del file di salvataggio.
     * 
     * @return Il percorso del file di salvataggio.
     */
    public String getPath(){
        return windowSaveInFile.getPath();
    }

    /**
     * Imposta il pannello della griglia nella finestra di test di primalità.
     * 
     * @param rows Il numero di righe da impostare nella griglia.
     * @param cols Il numero di colonne da impostare nella griglia.
     */
    public void setGridPanel(int rows, int cols){
        windowPrimalityTest.setGridPanel(rows, cols);
    }

    /**
     * Ottiene il pannello della finestra di test di primalità.
     * 
     * @return Il pannello della finestra di test di primalità come {@link JPanel}.
     */
    public JPanel getWindow(){
        return  windowPrimalityTest.getWindow();
    }



}
