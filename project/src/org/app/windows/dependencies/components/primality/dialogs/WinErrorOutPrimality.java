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
 * La classe WinErrorOutPrimality gestisce una finestra di dialogo che mostra un messaggio di errore o successo per un'operazione di primalità.
 * A seconda dello stato fornito, la finestra visualizza un'icona di avviso o di informazione, e un messaggio appropriato.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinErrorOutPrimality {

	/**
     * Finestra di dialogo principale.
     */
    private final JDialog dialog = new JDialog();

	/**
     * Etichetta che visualizza l'icona di errore o successo.
     */
    private final JLabel iconLabel = new JLabel();

	/**
     * Pannello che contiene il messaggio da visualizzare.
     */
    private final JPanel messagePanel = new JPanel();

	/**
     * Etichetta che visualizza il testo del messaggio.
     */
    private final JLabel messageLabel = new JLabel();

	/**
     * Pannello che contiene il bottone di conferma.
     */
    private final JPanel buttonPanel = new JPanel();

	/**
     * Bottone per chiudere la finestra di dialogo.
     */
    private final JButton button = new JButton("OK");

	/**
     * Icona per messaggi di successo.
     */
    private final Icon alertIcon = UIManager.getIcon("OptionPane.informationIcon");

	/**
     * Icona per messaggi di errore.
     */
    private final Icon errorIcon = UIManager.getIcon("OptionPane.warningIcon");


	/**
     * Costruttore della classe WinErrorOutPrimality.
     * Inizializza e configura la finestra di dialogo con icona e messaggio in base allo stato specificato.
     *
     * @param mainFrame Il frame principale su cui la finestra di dialogo è centrata.
     * @param state     Un booleano che determina il tipo di messaggio: 
     *                  {@code false} per errore (icona di avviso), 
     *                  {@code true} per successo (icona di informazione).
     */
    public WinErrorOutPrimality(JFrame mainFrame,boolean state){
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
		messageLabel.setText((state == false)?"Accesso cartella vietato":"File salvato correttamente");
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
	 * Chiude e libera le risorse della finestra di dialogo.
	 */
	public void dispose(){
		dialog.dispose();
	}
   
}

