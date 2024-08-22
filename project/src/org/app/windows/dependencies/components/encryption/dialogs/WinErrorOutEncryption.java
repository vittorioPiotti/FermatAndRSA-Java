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

package org.app.windows.dependencies.components.encryption.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 * Classe che rappresenta una finestra di dialogo per visualizzare un messaggio di successo o errore
 * durante l'operazione di crittografia o decrittografia. La finestra può mostrare un'icona e un
 * messaggio personalizzati a seconda dello stato passato.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinErrorOutEncryption {

    /** Finestra di dialogo per visualizzare l'errore o il successo. */
    private final JDialog dialog = new JDialog();

	/** Etichetta per mostrare l'icona (di errore o informazione). */
    private final JLabel iconLabel = new JLabel();

    /** Pannello per contenere il messaggio da visualizzare. */
    private final JPanel messagePanel = new JPanel();

	/** Etichetta per mostrare il messaggio principale. */
    private final JLabel messageLabel = new JLabel();

	/** Pannello per contenere il pulsante di chiusura. */
    private final JPanel buttonPanel = new JPanel();

	/** Pulsante per chiudere la finestra di dialogo. */
    private final JButton button = new JButton("OK");

	/** Icona di avviso (per segnalare un errore). */
    private final Icon alertIcon = UIManager.getIcon("OptionPane.informationIcon");

	/** Icona di errore (per segnalare un errore grave). */
    private final Icon errorIcon = UIManager.getIcon("OptionPane.warningIcon");

	/**
     * Costruttore per creare e visualizzare una finestra di dialogo con un messaggio di errore o successo.
     *
     * @param mainFrame Il frame principale a cui la finestra di dialogo è relativa.
     * @param state Se false, la finestra mostra un messaggio di errore; se true, mostra un messaggio di successo.
     */
    public WinErrorOutEncryption(JFrame mainFrame,boolean state){
			dialog.setTitle((state == false)?"Errore":"Successo");
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dialog.setSize(300, 180);
			dialog.setResizable(false); 
			dialog.setLayout(new BorderLayout());
            dialog.setLocationRelativeTo(mainFrame);
			iconLabel.setIcon((state == false)?errorIcon:alertIcon);
			iconLabel.setBorder(new EmptyBorder(20, 20, 0, 20)); 
			dialog.add(iconLabel, BorderLayout.WEST);
			messagePanel.setBorder(new EmptyBorder(20, 0, 0,20)); 
			messagePanel.setLayout(new BorderLayout());
			messageLabel.setText((state == false)?"Formato file non valido":"File selezionato");
			messagePanel.add(messageLabel, BorderLayout.CENTER);
			dialog.add(messagePanel, BorderLayout.CENTER);
			buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPanel.setBorder(new EmptyBorder(0, 0, 20,20)); 
			buttonPanel.add(Box.createVerticalGlue()); 
			buttonPanel.add(button);
			buttonPanel.add(Box.createVerticalGlue()); 
			dialog.add(buttonPanel, BorderLayout.SOUTH);
			button.addActionListener(e -> {
				dialog.dispose();
			});
            dialog.setVisible(true);
		}

	/**
     * Chiude la finestra di dialogo.
     */
	public void dispose(){
		dialog.dispose();
	}
   
}

