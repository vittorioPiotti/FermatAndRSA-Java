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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Classe che rappresenta la finestra di output per la cifratura/decifratura.
 * Consente di visualizzare i risultati del processo e di copiarli negli appunti.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinOutEncryption {
    
    /**
     * Il pannello principale della finestra di output.
     */
    private final JPanel panel = new JPanel();

    /**
     * Il pannello contenitore che organizza i componenti della finestra di output.
     */
    private final JPanel panelContainer = new JPanel();

    /**
     * Il pannello contenitore predefinito per la finestra di output.
     */
    private final JPanel panelContainerDefault = new JPanel();

    /**
     * L'etichetta del titolo principale della finestra di output.
     */
    private final JLabel title = new JLabel("Output");

    /**
     * L'etichetta del titolo predefinito della finestra di output.
     */
    private final JLabel titleDef = new JLabel("Output");

    /**
     * Il pulsante per copiare tutto il testo dei risultati negli appunti.
     */
    private final JButton buttonCopy = new JButton("Copia tutto");

    /**
     * Array di stringhe che contiene i messaggi descrittivi per il tipo di output.
     * Usato per cambiare dinamicamente l'etichetta in base al metodo selezionato.
     */
    private final String[] typeMessages = {
        "<html>Messaggio<br>cifrato:</html>",
        "<html>Messaggio<br>in chiaro:</html>",
    };

    /**
     * L'etichetta che descrive il tipo di messaggio visualizzato (cifrato o in chiaro).
     */
    private final JLabel labelMessage = new JLabel(typeMessages[0]);

    /**
     * Etichetta che visualizza un messaggio predefinito quando non sono presenti risultati.
     */
    private final JLabel advertiseOutput = new JLabel("I risultati saranno visualizzati qui");

    /**
     * Area di testo per visualizzare i risultati dell'operazione di cifratura o decifratura.
     */
    private final JTextArea results = new JTextArea("");

    /**
     * Scroll pane per l'area di testo dei risultati.
     */
    private final JScrollPane scrollPaneResults = new JScrollPane(results);

    /**
     * Pannello che contiene l'area di testo dei risultati.
     */
    private final JPanel containerResults = new JPanel();

    /**
     * Codice che rappresenta lo stato di errore dell'output.
     * Valore predefinito "1" indica che non ci sono risultati visibili.
     */
    private String errorType = "1";

    /**
     * Oggetto GridBagConstraints per gestire il layout dei componenti nel pannello.
     */
    private final GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Oggetto GridBagConstraints per gestire il layout dei componenti nel pannello predefinito.
     */
    private final GridBagConstraints gbcDef = new GridBagConstraints();


    /**
     * Costruttore della classe WinOutEncryption.
     * Inizializza la finestra con il layout predefinito e configura i componenti dell'interfaccia.
     * Gestisce l'azione del pulsante di copia e prepara il layout per la visualizzazione dei risultati.
     */
    public WinOutEncryption() {
      
        gbc.insets = new Insets(0, 0, 10, 0);
        
        results.setEditable(false);

        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelContainer.setLayout(new GridBagLayout());
        panelContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 20));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; 
        panelContainer.add(title, gbc);
        gbc.gridy = 1; 
        gbc.gridx = 0;
        gbc.gridwidth = 1;

        labelMessage.setFont(new Font(labelMessage.getFont().getName(), Font.PLAIN, 13));

        buttonCopy.setFont(new Font(buttonCopy.getFont().getName(), Font.PLAIN, 13));
        labelMessage.setPreferredSize(new Dimension(100, 30));

        buttonCopy.addActionListener(e -> {
            String copiedText = results.getText();
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection = new StringSelection(copiedText);
            clipboard.setContents(stringSelection, null);
        });
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 2; 
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panelContainer.add(labelMessage, gbc);
        gbc.gridy = 2; 
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        int width = 334;
        int height = 90;
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
            width = 300;
            height = 80;
        }
        scrollPaneResults.setPreferredSize(new Dimension(width, height));
        containerResults.add(scrollPaneResults);
        panelContainer.add(containerResults, gbc);
        gbc.anchor = GridBagConstraints.EAST; 
        gbc.gridy = 3;
        gbc.gridx = 3;
        gbc.gridwidth = 3;
        panelContainer.add(buttonCopy, gbc);

    
        


    

        panelContainerDefault.setLayout(new GridBagLayout());
        panelContainerDefault.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        gbcDef.anchor = GridBagConstraints.WEST;
        gbcDef.gridx = 0;
        gbcDef.gridy = 0;
        panelContainerDefault.add(titleDef,gbcDef);
        titleDef.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        titleDef.setFont(new Font( titleDef.getFont().getName(), Font.BOLD, 20));
        gbcDef.gridy = 1;
        panelContainerDefault.add(advertiseOutput,gbcDef);
        panel.add(panelContainerDefault);
    }

    /**
     * Inizializza la finestra di output con l'interfaccia specifica per il metodo selezionato.
     *
     * @param metodo L'indice del metodo selezionato, dove 0 corrisponde alla cifratura
     *               e 1 alla decifratura. Questo indice determina il messaggio visualizzato.
     */
    public void initWindow(int metodo){
        panel.removeAll();
        panel.add(panelContainer);
        panel.revalidate(); 
        panel.repaint(); 
        labelMessage.setText(typeMessages[metodo]);

    }

    /**
     * Restituisce il pannello principale della finestra, contenente tutti i componenti.
     *
     * @return Un oggetto JPanel rappresentante la finestra principale.
     */
    public JPanel getWindow() {
        return panel;
    }
  
   

    /**
     * Imposta il testo nei risultati di output.
     *
     * @param results Una stringa che contiene il testo da visualizzare nei risultati.
     */
    public void setResults(String results){
        this.results.setText(results);
    }
   
    
  
    /**
     * Restituisce il codice di errore associato all'output corrente.
     *
     * @return Una stringa rappresentante il tipo di errore:
     *         "1" se non ci sono risultati visibili (stato iniziale),
     *         "0" se i risultati sono presenti.
     */
    public String getErrorType(){
        return errorType;
    }
    
}