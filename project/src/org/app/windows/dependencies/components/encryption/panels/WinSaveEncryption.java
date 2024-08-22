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

package org.app.windows.dependencies.components.encryption.panels;

import java.awt.FileDialog;

import javax.swing.JDialog;


/**
 * La classe WinSaveEncryption gestisce una finestra di dialogo per salvare file, con funzionalità
 * aggiuntive per rilevare eventuali errori e richiamare una finestra di avviso in caso di problemi.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinSaveEncryption {

    /**
     * Dialog per visualizzare la finestra di selezione file.
     */
    private final JDialog dialog = new JDialog();

    /**
     * Finestra di dialogo nativa per l'apertura di file.
     */
    private final FileDialog fileDialog = new FileDialog(dialog, "Apri File", FileDialog.LOAD);

    /**
     * Percorso completo del file selezionato dall'utente.
     */
    private String fullPath;

    /**
     * Runnable che gestisce l'apertura della finestra di errore se si verifica un problema.
     */
    private Runnable openWindowErrorOutput;
   

    /**
     * Indica se si è verificato un errore nel processo di salvataggio o apertura file.
     */
    private Boolean errorType;

    /**
     * Costruttore della classe WinSaveEncryption.
     * Inizializza la finestra di dialogo e la mostra all'utente.
     *
     * @param openWindowErrorOutput Runnable per eseguire l'azione quando si verifica un errore.
     */
    public WinSaveEncryption(Runnable openWindowErrorOutput) {
        this.openWindowErrorOutput = openWindowErrorOutput;
        fileDialog.setVisible(true);
    }


    /**
     * Inizializza la finestra di selezione file e gestisce il percorso selezionato dall'utente.
     * Se il percorso è valido, richiama la finestra di errore.
     */
    public void initWindow() {
        String selectedDirectory = fileDialog.getDirectory();
        String selectedFile = fileDialog.getFile();
        if (selectedDirectory != null && selectedFile != null) {
            fullPath = selectedDirectory + selectedFile;
            openWindowErrorOutput.run();
        }
    }

    /**
     * Resetta il percorso del file selezionato, impostandolo a null.
     */
    public void resetPath( ) {
        fullPath = null;
    }

    /**
     * Restituisce il percorso completo del file selezionato.
     *
     * @return Una stringa rappresentante il percorso completo del file.
     */
    public String getPath() {
        return fullPath;
    }

    /**
     * Imposta lo stato di errore dell'operazione di salvataggio.
     *
     * @param state Un booleano che indica se c'è stato un errore (true) o meno (false).
     */
    public void setErrorType(boolean state) {
        errorType = state;
    }

    /**
     * Restituisce lo stato di errore corrente.
     *
     * @return Un booleano che indica se c'è un errore (true) o meno (false).
     */
    public boolean getErrorType() {
        return errorType;
    }
}
