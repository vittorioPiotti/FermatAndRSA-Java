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

package org.app.windows.dependencies.components.primality;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;


/**
 * Classe che gestisce la finestra principale dell'applicazione per la visualizzazione e l'interazione
 * con i pannelli di input e output.
 * 
 * La classe dispone di un layout a griglia per disporre i pannelli di input e output e un layout 
 * principale per gestire l'interfaccia utente dell'applicazione.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinPrimality {
    
    /** 
     * Il pannello che contiene i pannelli di input e output disposti in griglia. 
     */
    private final JPanel gridPanel = new JPanel(new GridLayout(1, 2));

    /** 
     * Il pannello per l'input dell'utente. 
     */
    private final JPanel panelInput = new JPanel();

    /** 
     * Il pannello per l'output dei risultati. 
     */
    private final JPanel panelOutput = new JPanel();

    /** 
     * Il pannello principale che contiene il pannello a griglia. 
     */
    private final JPanel mainPanel = new JPanel(new BorderLayout());

    /** 
     * I vincoli di layout per la disposizione dei componenti nel pannello.
     */
    private final GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Allinea un pannello figlio al centro di un pannello genitore utilizzando un layout GridBagLayout.
     *
     * @param parent Il pannello genitore in cui il pannello figlio deve essere centrato.
     * @param child Il pannello figlio da centrare nel pannello genitore.
     */
    private void centerChildInParent(JPanel parent, JPanel child){
        parent.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        parent.add(child, gbc);
    }

    /**
     * Imposta il layout a griglia per il pannello principale che contiene i pannelli di input e output.
     *
     * @param rows Il numero di righe nella griglia.
     * @param cols Il numero di colonne nella griglia.
     */
    public void setGridPanel(int rows, int cols){
        gridPanel.setLayout(new GridLayout(rows, cols));

    }
    /**
     * Restituisce il pannello principale della finestra.
     *
     * @return Il pannello principale.
     */
    public JPanel getWindow(){
        return mainPanel;
    }

    /**
     * Costruisce una nuova istanza della finestra principale.
     * Inizializza i pannelli di input e output, li posiziona nella griglia e aggiunge la griglia al pannello principale.
     *
     * @param panelInput Il pannello per l'input dell'utente.
     * @param panelOutput Il pannello per l'output dei risultati.
     */
    public WinPrimality(JPanel panelInput,JPanel panelOutput) {
        panelOutput.setPreferredSize(new Dimension(476, 214));
        panelInput.setPreferredSize(new Dimension(476, 214));
        
        centerChildInParent(this.panelInput,panelInput);
        centerChildInParent(this.panelOutput,panelOutput);
        gridPanel.add(this.panelInput);
        gridPanel.add(this.panelOutput);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
      
    }

    
}