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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * La classe WinInPrimality gestisce la finestra di input per l'algoritmo di verifica della primalità. 
 * L'utente può inserire un intervallo di valori (minimo e massimo) e scegliere un metodo per il calcolo.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinInPrimality {
    
    /**
     * Indice del metodo scelto dall'utente.
     */
    private int sceltaMetodo = 0;

    /**
     * Array di stringhe contenente i nomi dei metodi disponibili per il calcolo della primalità.
     */
    private final String[] metodi = {
		"Divisori fino al numero anche pari no break",
		"Divisori fino al numero anche pari con break",
		"Divisori fino alla meta anche pari no break",
		"Divisori fino alla meta solo dispari no break",
		"Divisori fino alla radice solo dispari con break",
		"Algoritmo di Fermat"
	};

    /**
     * Pannello principale che contiene il layout della finestra.
     */
    private final JPanel panel = new JPanel();

    /**
     * Contenitore del form di input.
     */
    private final JPanel panelContainer = new JPanel();

    /**
     * Etichetta del titolo della finestra.
     */
    private final JLabel title = new JLabel("Input");

    /**
     * Etichetta per campo di input del valore minimo.
     */
    private final JLabel labelValMin = new JLabel("Minimo: ");
    /**
     * Casella di input del valore minimo.
     */
    private final JTextField inputValMin = new JTextField(6);

    /**
     * Etichetta per campo di input del valore esponente minimo.
     */

    private final JLabel labelExpMin = new JLabel(" * 10 ^ ");

    /**
     * Casella di input del valore esponente minimo.
     */
    private final JTextField inputExpMin = new JTextField(3);

    /**
     * Etichetta per campo di input del valore massimo.
     */
    private final JLabel labelValMax = new JLabel("Massimo: ");

    /**
     * Casella di input del valore massimo.
     */
    private final JTextField inputValMax = new JTextField(6);

    /**
     * Casella di input del valore esponente massimo.
     */
    private final JLabel labelExpMax = new JLabel(" * 10 ^ ");

    /**
     * Casella di input del valore massimo.
     */
    private final JTextField inputExpMax = new JTextField(3);

    /**
     * Etichetta per la selezione del metodo.
     */
    private final JLabel labelMetodo = new JLabel("Metodo: ");

    /**
     * ComboBox per la selezione del metodo.
     */
    private final JComboBox < String > inputMetodo = new JComboBox <>(metodi);

    /**
     * Impostazioni del layout per il GridBagLayout.
     */
    private final GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Bottone per eseguire l'algoritmo di verifica della primalità.
     */
   	private final JButton buttonEsegui = new JButton("Esegui");
 
    /**
     * Stringa che memorizza l'errore corrente.
     */
    private String errorType;

    /**
     * Runnable per aprire una finestra di errore in caso di input non valido.
     */
    private Runnable openWindowErrorInput;

    /**
     * Valori e risultati delle operazioni di input.
     */
    String numMin;
    String numMax;
    int valMin;
    int valMax;
    int expMin;
    int expMax;


    /**
     * Costruttore della classe WinInPrimality. Inizializza l'interfaccia utente e imposta i listener per i campi di input.
     *
     * @param openWindowErrorInput Runnable che gestisce l'apertura della finestra di errore in caso di input non valido.
     */
    public WinInPrimality(Runnable openWindowErrorInput) {
        this.openWindowErrorInput = openWindowErrorInput;
        setInputKeyListener(inputValMin,7);
        setInputKeyListener(inputValMax,7);
        setInputKeyListener(inputExpMax,3);
        setInputKeyListener(inputExpMin,3);
        gbc.insets = new Insets(0, 0, 10, 0);
        inputMetodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sceltaMetodo = inputMetodo.getSelectedIndex();
            }
        });
        buttonEsegui.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                errorType = findError();
                openWindowErrorInput.run();

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
        labelMetodo.setPreferredSize(new Dimension(100, 20));
        labelMetodo.setFont(new Font(labelMetodo.getFont().getName(), Font.PLAIN, 13));
        inputMetodo.setFont(new Font(labelMetodo.getFont().getName(), Font.PLAIN, 13));
        buttonEsegui.setFont(new Font(labelMetodo.getFont().getName(), Font.PLAIN, 13));
        panelContainer.add(labelMetodo, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 4; 
        panelContainer.add(inputMetodo, gbc);
        setInputRow(labelValMin, labelExpMin, inputValMin, inputExpMin);
        setInputRow(labelValMax, labelExpMax, inputValMax, inputExpMax);
        addInputRow(gbc, panelContainer, labelValMin, labelExpMin, inputValMin, inputExpMin, 2);
        addInputRow(gbc, panelContainer, labelValMax, labelExpMax, inputValMax, inputExpMax, 3);
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridwidth = 4; 
        panelContainer.add(buttonEsegui,gbc);
        panel.add(panelContainer);
    }
    
    /**
     * Restituisce il pannello principale della finestra.
     *
     * @return Il pannello principale.
     */
    public JPanel getWindow() {
        return panel;
    }
  
    /**
     * Verifica la presenza di errori negli input forniti.
     * 
     * @return Una stringa rappresentante l'errore trovato, o una stringa vuota se non ci sono errori.
     */
    private String findError(){
        String error = "";
        if(inputValMin.getText().equals(""))error += "1";
        else error += "0";
        if(inputValMax.getText().equals(""))error += "1";
        else error += "0";
        if(inputExpMin.getText().equals(""))inputExpMin.setText("0");
        if(inputExpMax.getText().equals(""))inputExpMax.setText("0");
        if(error.equals("00")){
            valMin = Integer.parseInt(inputValMin.getText());
            valMax = Integer.parseInt(inputValMax.getText());
            expMin = Integer.parseInt(inputExpMin.getText());
            expMax = Integer.parseInt(inputExpMax.getText());
            numMin = setNum(inputValMin.getText(),inputExpMin.getText());
            numMax = setNum(inputValMax.getText(),inputExpMax.getText());
            if(valMin == valMax && expMin == expMax)return "33";
            if(inputValMin.getText().length() + expMin > inputValMax.getText().length() + expMax || inputValMin.getText().length() + expMin == inputValMax.getText().length() + expMax && valMin>valMax)return "22";
        }
        return error;
    }

    /**
     * Crea una rappresentazione numerica basata sui valori ed esponenti inseriti.
     *
     * @param val Il valore base.
     * @param exp L'esponente.
     * @return Il numero finale come stringa.
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
     * Restituisce il numero massimo calcolato.
     *
     * @return Il numero massimo.
     */
    public String getMax(){
        return numMax;
    }

    /**
     * Restituisce il numero minimo calcolato.
     *
     * @return Il numero minimo.
     */
    public String getMin(){
        return numMin;
    }
 
    /**
     * Restituisce il valore massimo inserito.
     *
     * @return Il valore massimo.
     */
    public int getValMax(){
        return valMax;
    }

    /**
     * Restituisce il valore minimo inserito.
     *
     * @return Il valore minimo.
     */
    public int getValMin(){
        return valMin;
    }

    /**
     * Restituisce l'esponente massimo inserito.
     *
     * @return L'esponente massimo.
     */
    public int getExpMax(){
        return expMax;
    }

    /**
     * Restituisce l'esponente minimo inserito.
     *
     * @return L'esponente minimo.
     */
    public int getExpMin(){
        return expMin;
    }

    /**
     * Restituisce l'indice del metodo selezionato.
     *
     * @return L'indice del metodo selezionato.
     */
    public int getMetodo(){
        return sceltaMetodo;
    }

    /**
     * Restituisce il nome del metodo selezionato in formato stringa.
     *
     * @return Il nome del metodo selezionato.
     */
    public String toStringMetodo(){
         return metodi[sceltaMetodo];
    }

    /**
     * Imposta le proprietà del layout per una riga di input.
     * 
     * @param labelVal  Etichetta per il valore.
     * @param labelExp  Etichetta per l'esponente.
     * @param inputVal  Campo di input per il valore.
     * @param inputExp  Campo di input per l'esponente.
     */
    private static void setInputRow(JLabel labelVal,JLabel labelExp,JTextField inputVal,JTextField inputExp){
        labelVal.setFont(new Font(labelVal.getFont().getName(), Font.PLAIN, 13));
        labelExp.setFont(new Font(labelExp.getFont().getName(), Font.PLAIN, 13));
        inputVal.setFont(new Font(inputVal.getFont().getName(), Font.PLAIN, 13));
        inputExp.setFont(new Font(inputExp.getFont().getName(), Font.PLAIN, 13));
        inputVal.setHorizontalAlignment(SwingConstants.RIGHT);
        inputExp.setHorizontalAlignment(SwingConstants.RIGHT);
        int width = 100;
        if(System.getProperty("os.name").toLowerCase().contains("windows") && (labelVal.getText().equals("Minimo: ")||labelVal.getText().equals("Massimo: ") ))width = 200;
        labelVal.setPreferredSize(new Dimension(width, 20));
        labelVal.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
    }

    /**
     * Aggiunge una riga di input al pannello del form.
     *
     * @param gbc            Configurazione del layout.
     * @param formContainer  Pannello contenitore.
     * @param labelVal       Etichetta per il valore.
     * @param labelExp       Etichetta per l'esponente.
     * @param inputVal       Campo di input per il valore.
     * @param inputExp       Campo di input per l'esponente.
     * @param row            Numero di riga in cui inserire gli elementi.
     */
    private static void addInputRow(GridBagConstraints gbc, JPanel formContainer, JLabel labelVal, JLabel labelExp, JTextField inputVal, JTextField inputExp, int row) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 3; 
        formContainer.add(labelVal, gbc);
        JPanel containerInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        containerInput.add(inputVal);
        containerInput.add(labelExp);
        containerInput.add(inputExp);
        gbc.gridx = 3;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.EAST;
        formContainer.add(containerInput, gbc);
    }

    /**
     * Imposta un KeyListener per validare l'input e limitare il numero di caratteri.
     *
     * @param inputField Campo di input da validare.
     * @param max        Numero massimo di caratteri consentiti.
     */
    private void setInputKeyListener(JTextField inputField,int max){
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE) {
                    String currentText = inputField.getText();
                    int selectionStart = inputField.getSelectionStart();
                    int selectionEnd = inputField.getSelectionEnd();
                    if (selectionStart != selectionEnd) {
                            e.consume();
                            String startStr = currentText.substring(0, selectionStart);
                            String endStr = currentText.substring(selectionEnd, currentText.length());	
                            inputField.setText("");
                            inputField.setText(startStr + c + endStr);
                            inputField.setCaretPosition(selectionStart + 1); 
                    } else if (currentText.length() >= max && c != KeyEvent.VK_BACK_SPACE) {
                        errorType = "44";
                        openWindowErrorInput.run();
                        e.consume();
                    }
                } else {  
                    errorType = "55";
                    openWindowErrorInput.run();
                    e.consume();
                }
            }
        });
    }

    /**
     * Restituisce l'errore corrente in formato stringa.
     *
     * @return La stringa dell'errore.
     */
    public String getErrorType(){
        return errorType;
    }
    
}

