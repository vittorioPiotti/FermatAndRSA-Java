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

package org.app.windows.dependencies.components.primality.dialogs;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * La classe WinErrorInPrimality rappresenta una finestra di dialogo che mostra un errore di inserimento relativo alla primalità.
 * Viene visualizzato un messaggio di errore con la possibilità di chiudere la finestra.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinErrorInPrimality {

    /**
     * Dialogo principale che ospita la finestra di errore.
     */
    private final JDialog dialog = new JDialog();

    /**
     * Pannello principale che contiene tutti i componenti della finestra.
     */
    private final JPanel mainPanel = new JPanel(new GridBagLayout());

    /**
     * Gestisce la disposizione dei componenti nel pannello con le relative restrizioni.
     */
    private final GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Etichetta che mostra il titolo del messaggio di errore.
     */
    private final JLabel label = new JLabel("Errore inserimento");

    /**
     * Pannello orizzontale che funge da separatore.
     */
    private final JPanel hr = new JPanel(new BorderLayout());

    /**
     * Pannello orizzontale più piccolo per un effetto di separazione sottile.
     */
    private final JPanel hr_small = new JPanel(new BorderLayout());

    /**
     * Bottone per chiudere la finestra di dialogo.
     */
    private final JButton closeButton = new JButton("Chiudi");

    /**
     * Array di etichette che mostra i messaggi di errore.
     */
    private  JLabel[] errorMessages;


    /**
     * Costruttore della classe WinErrorInPrimality.
     * Inizializza e configura la finestra di dialogo per mostrare i messaggi di errore specificati.
     *
     * @param mainFrame     Il frame principale su cui la finestra di dialogo è centrata.
     * @param errorMessages Un array di stringhe contenente i messaggi di errore da visualizzare.
     */
    public WinErrorInPrimality(JFrame mainFrame,String[] errorMessages){
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);
        gbc.insets = new Insets(10, 10, 10, 10);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        label.setFont(new Font(label.getFont().getFontName(), Font.BOLD, label.getFont().getSize()));
        gbc.gridy++;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.CENTER; 
        mainPanel.add(label, gbc);
        gbc.gridy++;
        hr_small.setBackground(Color.BLACK);
        hr_small.setPreferredSize(new Dimension(15, 7));
        mainPanel.add(hr_small, gbc);

        this.errorMessages = new JLabel[errorMessages.length];
        for (int i = 0; i < errorMessages.length;i++) {
            this.errorMessages[i] = new JLabel(errorMessages[i]);
            gbc.gridy++;
            gbc.gridwidth = 0;
            gbc.anchor = GridBagConstraints.WEST; 
            mainPanel.add(this.errorMessages[i], gbc);
        }
        
        hr.setBackground(Color.GRAY);
        hr.setPreferredSize(new Dimension(230, 1));
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER; 
        mainPanel.add(hr, gbc); 
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(closeButton, gbc);
        dialog.add(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setVisible(true);
    }

    /**
     * Chiude e libera le risorse della finestra di dialogo.
     */
    public void dispose(){
        dialog.dispose();
    }
}
