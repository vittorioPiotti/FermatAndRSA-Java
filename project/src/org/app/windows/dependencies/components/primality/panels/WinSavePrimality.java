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

package org.app.windows.dependencies.components.primality.panels;

import java.awt.FileDialog;
import java.io.File;

import javax.swing.JDialog;


/**
 * Gestisce la finestra di salvataggio per i risultati del calcolo di primalità.
 * Permette all'utente di scegliere un percorso e un nome di file per salvare i dati.
 * Controlla anche se il percorso di salvataggio è all'interno di una directory bloccata
 * e gestisce gli errori associati.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinSavePrimality {

    /** 
     * La finestra di dialogo principale per la selezione del file. 
     */
    private final JDialog dialog = new JDialog();

    /** 
     * La finestra di dialogo per la selezione e il salvataggio del file. 
     */
    private final FileDialog fileDialog = new FileDialog(dialog, "Salva File", FileDialog.SAVE);

    /** 
     * Il percorso del file JAR in cui è eseguita l'applicazione. 
     */
    private final String jarFilePath = WinSavePrimality.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    
    /** 
     * Il percorso della cartella Desktop dell'utente. 
     */
    private final String desktopPath = System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop";
    
    /** 
     * Il percorso della directory da cui il salvataggio è bloccato, se applicabile. 
     */
    private String directoryToBlock = (jarFilePath.toLowerCase().endsWith(".jar") ? new File(jarFilePath).getParent() : System.getProperty("user.dir"));
    
    /** 
     * Il percorso completo del file selezionato per il salvataggio. 
     */
    private String fullPath;

    /** 
     * Il runnable da eseguire in caso di errore nel percorso di salvataggio. 
     */
    private Runnable openWindowErrorOutput;

    /** 
     * Indica se si è verificato un errore relativo al percorso di salvataggio. 
     */
    private Boolean errorType;


    /**
     * Costruisce una finestra di salvataggio con il nome di file predefinito e
     * il runnable per gestire gli errori di percorso di salvataggio.
     *
     * @param fileName Il nome del file predefinito per il salvataggio.
     * @param openWindowErrorOutput Il runnable da eseguire in caso di errore nel percorso di salvataggio.
     */
    public WinSavePrimality(String fileName,Runnable openWindowErrorOutput) {
        if (!directoryToBlock.equals(File.separator)) {
            directoryToBlock = new File(directoryToBlock).getParent();
        }
        this.openWindowErrorOutput = openWindowErrorOutput;
        fileDialog.setDirectory(desktopPath);
        fileDialog.setFile(fileName);
        fileDialog.setVisible(true);
    }

    /**
     * Inizializza la finestra di salvataggio e verifica il percorso selezionato.
     * Controlla se il percorso di salvataggio è all'interno della directory bloccata.
     * Esegue il runnable associato in caso di errore di percorso.
     */
    public void initWindow(){
        String selectedDirectory = fileDialog.getDirectory();
        String selectedFile = fileDialog.getFile();
        if (selectedDirectory != null && selectedFile != null) {
            if (!selectedFile.endsWith(".txt")) {
                selectedFile += ".txt";
            }
            fullPath = selectedDirectory + selectedFile;
            if (fullPath.startsWith(directoryToBlock)) {
                errorType = false;
            } else {      
                errorType = true;     
            }
            openWindowErrorOutput.run();
        }
    }

    /**
     * Restituisce il percorso completo del file selezionato per il salvataggio.
     *
     * @return Il percorso completo del file.
     */
    public String getPath(){
        return fullPath;
    }

    /**
     * Restituisce il tipo di errore associato al percorso di salvataggio.
     * 
     * @return {@code true} se il percorso di salvataggio è al di fuori della directory bloccata, {@code false} altrimenti.
     */
    public boolean getErrorType(){
        return errorType;
    }

    
}
