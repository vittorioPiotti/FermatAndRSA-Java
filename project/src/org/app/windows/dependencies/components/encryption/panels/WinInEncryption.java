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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 * Classe che rappresenta l'interfaccia grafica per la gestione dell'input e delle opzioni 
 * per la crittografia e decrittografia RSA. La finestra include campi per selezionare il metodo,
 * caricare file, gestire l'opzione modInverse e mostrare i risultati.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinInEncryption {
    
    /** Indice del metodo selezionato (0 = Crittografia, 1 = Decrittografia). */
    private int sceltaMetodo = 0;

    /** Opzioni di metodi di crittografia e decrittografia disponibili. */
    private final String[] metodi = {
        "Cripta messaggio con RSA",
        "Decripta messaggio con RSA",
    };

    /** Etichette per i messaggi a seconda del metodo selezionato. */
    private final String[] typeMessages = {
        "<html>Messaggio<br>in chiaro:</html>",
        "<html>Messaggio<br>cifrato:</html>"
    };

    /** Etichette per l'opzione modInverse. */
    private final String[] typeModInverse = {
        "On modInverse",
        "Off modInverse",
    };

    /** Pannello principale per contenere tutti i componenti. */
    private final JPanel panel = new JPanel();

    /** Contenitore per la disposizione dei componenti nel pannello. */
    private final JPanel panelContainer = new JPanel();

    /** Etichetta del titolo della finestra. */
    private final JLabel title = new JLabel("Input");

    /** Etichetta per il metodo selezionato. */
    private final JLabel labelMetodo = new JLabel("Metodo: ");

    /** Etichetta per il tipo di messaggio (chiaro o cifrato) visualizzato. */
    private final JLabel labelMessage = new JLabel(typeMessages[0]);

    /** ComboBox per selezionare il metodo di crittografia o decrittografia. */
    private final JComboBox < String > inputMetodo = new JComboBox <>(metodi);

    /** Constraints per la gestione del layout GridBag. */
    private final GridBagConstraints gbc = new GridBagConstraints();

    /** Pulsante per eseguire l'operazione selezionata. */
    private final JButton buttonEsegui = new JButton("Esegui");

    /** Pulsante per caricare un file di input. */
    private final JButton buttonFile = new JButton("Carica file");

    /** Pulsante per attivare o disattivare l'opzione modInverse. */
    private final JButton buttonModInverse = new JButton(typeModInverse[0]);

    /** Etichetta che visualizza lo stato del file caricato. */
    private final JLabel labelFile = new JLabel("Nessun file");

    /** Pannello per separatori grafici nel layout. */
    private final JPanel separator = new JPanel();

    /** Contenitore per separatori grafici. */
    private final JPanel containerSeparator = new JPanel();

    /** Pannello per gestire l'opzione modInverse e il separatore. */
    private final JPanel modInversePanel = new JPanel(new BorderLayout());

    /** Etichetta per il separatore visivo. */
    private final JLabel separatorLabel = new JLabel(" "); 

    /** Area di testo per visualizzare i risultati. */
    private final JTextArea results = new JTextArea("");

    /** Pannello di scorrimento per l'area di testo dei risultati. */
    private final JScrollPane scrollPaneResults = new JScrollPane(results);

    /** Contenitore per il pannello di scorrimento dei risultati. */
    private final JPanel containerResults = new JPanel();

    /** Stato attuale dell'opzione modInverse (true = attivo, false = disattivato). */
    private boolean modInverse = true;

    /** Codice di errore per la gestione dello stato dell'input. */
    private String errorType = "1";

  
    /**
     * Costruttore della finestra di input per crittografia/decrittografia.
     * Configura i listener per i pulsanti e i componenti UI.
     *
     * @param openWindowErrorInput Runnable per aprire la finestra di errore in caso di input non valido.
     * @param openWindowReadingFile Runnable per aprire la finestra di lettura file.
     * @param checkFileOnchange Runnable per eseguire controlli sul file caricato quando cambia l'opzione modInverse.
     */
    public WinInEncryption(Runnable openWindowErrorInput,Runnable openWindowReadingFile,Runnable checkFileOnchange) {
        setInputTextAreaListener(results);
        
        gbc.insets = new Insets(0, 0, 10, 0);
        inputMetodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sceltaMetodo = inputMetodo.getSelectedIndex();
                labelMessage.setText(typeMessages[sceltaMetodo]);

            }
        });
        buttonEsegui.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWindowErrorInput.run();

            }
        });
        buttonFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openWindowReadingFile.run();
            }
        });
        buttonModInverse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(buttonModInverse.getText().equals(typeModInverse[0]) ){
                    buttonModInverse.setText(typeModInverse[1]);
                    modInverse = false;
                }else {
                    buttonModInverse.setText(typeModInverse[0]);
                    modInverse = true;

                }
                checkFileOnchange.run();
            }
        });
        inputMetodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sceltaMetodo = inputMetodo.getSelectedIndex();
            }
        });
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
        labelMetodo.setPreferredSize(new Dimension(100, 25));
        labelMetodo.setFont(new Font(labelMetodo.getFont().getName(), Font.PLAIN, 13));
        labelMessage.setFont(new Font(labelMessage.getFont().getName(), Font.PLAIN, 13));
        labelFile.setFont(new Font(labelFile.getFont().getName(), Font.PLAIN, 13));

        inputMetodo.setFont(new Font(labelMetodo.getFont().getName(), Font.PLAIN, 13));
        buttonEsegui.setFont(new Font(labelMetodo.getFont().getName(), Font.PLAIN, 13));
        buttonFile.setFont(new Font(labelMetodo.getFont().getName(), Font.PLAIN, 13));
        buttonModInverse.setFont(new Font(labelMetodo.getFont().getName(), Font.PLAIN, 13));

        panelContainer.add(labelMetodo, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 4; 
        gbc.anchor = GridBagConstraints.EAST;
        panelContainer.add(inputMetodo, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 2; 
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panelContainer.add(labelMessage, gbc);
        
        gbc.gridy = 2; 
        gbc.gridx = 1;
        gbc.gridwidth = 4;
        int width = 334;
        int height = 90;
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
            width = 300;
            height = 80;
        }
        scrollPaneResults.setPreferredSize(new Dimension(width, height));
        containerResults.add(scrollPaneResults);
        panelContainer.add(containerResults, gbc);

        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridwidth = 1; 
        
        panelContainer.add(buttonEsegui,gbc);


        modInversePanel.add(buttonModInverse, BorderLayout.CENTER);


        int widthSeparator = 5;
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
                    widthSeparator = 10;
            
                }
        separatorLabel.setPreferredSize(new Dimension(widthSeparator, 1));

        modInversePanel.add(separatorLabel, BorderLayout.EAST);

        gbc.gridx = 1;
        panelContainer.add(modInversePanel, gbc);

        gbc.gridx = 2;
        
        panelContainer.add(buttonFile,gbc);
        
        gbc.gridx = 3;

        int heightSeparator = 20;
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
            heightSeparator = 25;
        }
        containerSeparator.setPreferredSize(new Dimension(20, heightSeparator )); 
        separator.setBackground(Color.GRAY);
        separator.setPreferredSize(new Dimension(2, heightSeparator)); 
        containerSeparator.add(separator);

        panelContainer.add(containerSeparator,gbc);
        gbc.gridx = 4;
   
        panelContainer.add(labelFile,gbc);
        
        
      
        panel.add(panelContainer);
    }

    /**
     * Restituisce il pannello principale della finestra.
     *
     * @return Il pannello principale che contiene l'interfaccia grafica.
     */
    public JPanel getWindow() {
        return panel;
    }

    /**
     * Restituisce lo stato dell'opzione modInverse.
     *
     * @return true se modInverse è attivo, false altrimenti.
     */
    public boolean getModInverse(){
        return modInverse;
    }
  
   
    /**
     * Imposta il contenuto del campo numerico aggiungendo zeri.
     *
     * @param val Il valore numerico iniziale.
     * @param exp Il numero di zeri da aggiungere.
     * @return La stringa risultante con il valore numerico e gli zeri aggiunti.
     */
    public String setNum(String val,String exp){
        String num = "";
        num += val;
        for(int i = 0; i<  Integer.parseInt(exp); i ++){
            num += "0";
        }
        return num;
    }
    
  
    /**
     * Restituisce l'indice del metodo selezionato.
     *
     * @return L'indice del metodo selezionato (0 = Crittografia, 1 = Decrittografia).
     */
    public int getMetodo(){
        return sceltaMetodo;
    }

    /**
     * Restituisce il nome del metodo selezionato.
     *
     * @return Una stringa che rappresenta il metodo selezionato.
     */
    public String toStringMetodo(){
         return metodi[sceltaMetodo];
    }

    /**
     * Imposta un listener per l'area di testo e aggiorna il codice di errore in base al contenuto.
     *
     * @param textArea L'area di testo da monitorare.
     */
    private void setInputTextAreaListener(JTextArea textArea) {
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange(textArea.getText());
            }
    
            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTextChange(textArea.getText());
            }
    
            @Override
            public void changedUpdate(DocumentEvent e) {
                // Non applicabile ai componenti JTextArea
            }
    
            private void handleTextChange(String text) {
                if (text.isEmpty()) {
                    errorType = "1"; // Testo vuoto
                } else {
                    errorType = "0"; // Testo non vuoto
                }
            }
        });
    }
    /**
     * Aggiorna l'etichetta dello stato del file in base al parametro passato.
     *
     * @param state Se true, imposta l'etichetta su "File caricato", altrimenti "Nessun file".
     */
    public void setLabelFile(boolean state){
        labelFile.setText((state == false)?"Nessun file":"File caricato");

    }
    /**
     * Restituisce il codice di errore associato all'input corrente.
     * 
     * @return Una stringa che rappresenta il tipo di errore:
     *         "1" se il campo di testo è vuoto (errore),
     *         "0" se è presente del testo valido (nessun errore).
     */
    public String getErrorType(){
        return errorType;
    }
  
    /**
     * Restituisce il contenuto dell'area di testo (messaggio di input).
     * 
     * @return Una stringa contenente il messaggio digitato dall'utente.
     */
    public String getMessage(){
        return results.getText();
    }
}