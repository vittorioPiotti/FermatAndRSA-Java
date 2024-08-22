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

package org.app.windows.dependencies.components.encryption;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;


/**
 * La classe WinEncryption gestisce la finestra principale dell'applicazione con un layout diviso in due pannelli:
 * uno per l'input e uno per l'output.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinEncryption{
    
    /**
     * Pannello principale che contiene i due pannelli input e output disposti con un layout a griglia.
     */
    private final JPanel gridPanel = new JPanel(new GridLayout(1, 2));

    /**
     * Pannello per l'input, che contiene altri componenti per inserire i dati.
     */
    private final JPanel panelInput = new JPanel();

    /**
     * Pannello per l'output, che contiene i risultati elaborati.
     */
    private final JPanel panelOutput = new JPanel();

    /**
     * Pannello principale che utilizza un BorderLayout per gestire la disposizione dei pannelli.
     */
    private final JPanel mainPanel = new JPanel(new BorderLayout());

    /**
     * Oggetto GridBagConstraints usato per posizionare i componenti all'interno dei pannelli centrati.
     */
    private final GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Centra un pannello figlio all'interno del pannello genitore.
     *
     * @param parent Il pannello genitore in cui centrare il pannello figlio.
     * @param child  Il pannello figlio da centrare.
     */
    private void centerChildInParent(JPanel parent, JPanel child){
        parent.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        parent.add(child, gbc);
    }

    /**
     * Imposta il layout del pannello griglia con un numero specifico di righe e colonne.
     *
     * @param rows Numero di righe del layout a griglia.
     * @param cols Numero di colonne del layout a griglia.
     */
    public void setGridPanel(int rows, int cols){
        gridPanel.setLayout(new GridLayout(rows, cols));

    }

    /**
     * Restituisce il pannello principale che rappresenta la finestra dell'applicazione.
     *
     * @return Un oggetto JPanel che rappresenta la finestra principale.
     */
    public JPanel getWindow(){
        return mainPanel;
    }

    /**
     * Costruttore della classe WinEncryption.
     * Inizializza i pannelli input e output, li centra e li aggiunge al pannello principale.
     *
     * @param panelInput  Il pannello che contiene i componenti di input.
     * @param panelOutput Il pannello che contiene i componenti di output.
     */
    public WinEncryption(JPanel panelInput,JPanel panelOutput) {
        panelOutput.setPreferredSize(new Dimension(476, 214));
        panelInput.setPreferredSize(new Dimension(476, 214));
        
        centerChildInParent(this.panelInput,panelInput);
        centerChildInParent(this.panelOutput,panelOutput);
        gridPanel.add(this.panelInput);
        gridPanel.add(this.panelOutput);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
      
    }

    
}