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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;


/**
 * La classe `WinOutPrimality` rappresenta un'interfaccia grafica per la visualizzazione
 * dei risultati di un calcolo di numeri primi. Offre funzionalità per avviare, monitorare
 * e interrompere il calcolo, nonché per salvare i risultati in un file.
 * 
 * La classe gestisce l'aggiornamento dei risultati calcolati in tempo reale e fornisce
 * strumenti per monitorare l'avanzamento del processo, come una barra di progresso e un timer.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinOutPrimality {


    /**
     * Pannello principale dell'interfaccia grafica.
     */
    private final JPanel panel = new JPanel();

    /**
     * Pannello che contiene i componenti principali della finestra.
     */
    private final JPanel panelContainer = new JPanel();

    /**
     * Pannello utilizzato per la visualizzazione dei messaggi di output predefiniti.
     */
    private final JPanel panelContainerDefault = new JPanel();

    /**
     * Pannello che contiene le informazioni di caricamento e progresso, configurato con un layout BorderLayout.
     */
    private final JPanel containerLoadingInfo = new JPanel(new BorderLayout());

    /**
     * Etichetta per visualizzare il valore minimo specificato.
     */
    private final JLabel labelMin = new JLabel("Minimo: ");

    /**
     * Etichetta per visualizzare il valore massimo specificato.
     */
    private final JLabel labelMax = new JLabel("Massimo: ");

    /**
     * Etichetta per mostrare il numero di numeri calcolati fino ad ora.
     */
    private final JLabel labelNum = new JLabel("Numeri calcolati: 0");

    /**
     * Etichetta per visualizzare il tempo trascorso dal momento dell'inizio del calcolo.
     */
    private final JLabel labelTime = new JLabel("Tempo di calcolo: 00:00");

    /**
     * Etichetta che indica lo stato del calcolo in corso, ad esempio, se è stato interrotto o completato.
     */
    private final JLabel stateCalculus = new JLabel("");

    /**
     * Etichetta per il titolo della finestra di output.
     */
    private final JLabel title = new JLabel("Output");

    /**
     * Etichetta per il titolo predefinito nella finestra di output.
     */
    private final JLabel titleDef = new JLabel("Output");

    /**
     * Pulsante per salvare i risultati del calcolo in un file.
     */
   	private final JButton buttonSalva = new JButton("Salva");

    /**
     * Oggetto GridBagConstraints utilizzato per gestire la disposizione dei pulsanti nel layout GridBag.
     */
    private final GridBagConstraints gbcButtons = new GridBagConstraints();

    /**
     * Pulsante per interrompere il calcolo in corso.
     */
   	private final JButton buttonStop = new JButton("Interrompi");

    /**
     * Barra di progresso utilizzata per indicare l'avanzamento del calcolo.
     */
    private final JProgressBar progressBar = new JProgressBar();

    /**
     * Oggetto GridBagConstraints utilizzato per gestire la disposizione dei componenti principali nel layout GridBag.
     */
    private final GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Oggetto GridBagConstraints utilizzato per gestire la disposizione dei componenti predefiniti nel layout GridBag.
     */
    private final GridBagConstraints gbcDef = new GridBagConstraints();

    /**
     * Area di testo utilizzata per visualizzare i risultati del calcolo.
     */
    private final JTextArea results = new JTextArea("");

    /**
     * Panello che contiene l'area di testo per i risultati.
     */
    private final JScrollPane scrollPaneResults = new JScrollPane(results);

    /**
     * Pannello che contiene l'area di testo dei risultati.
     */
    private final JPanel containerResults = new JPanel();

    /**
     * Pannello che contiene lo stato del calcolo.
     */
    private final JPanel containerStatoCalc = new JPanel();

    /**
     * Icona utilizzata per visualizzare notifiche e avvisi relativi allo stato del calcolo.
     */
    private final Icon alertIcon = UIManager.getIcon("OptionPane.informationIcon");

    /**
     * Variabile del tempo di inizio in funzione del tempo corrente
     */
    private long startTime = System.currentTimeMillis();

    /**
     * Etichetta per visualizzare un avviso quando non è possibile stimare il tempo di attesa.
     */
    private final JLabel loadingAdvertise = new JLabel("Impossibile stimare attesa");

    /**
     * Etichetta per visualizzare un messaggio che indica che i risultati saranno mostrati in questa area.
     */
    private final JLabel advertiseOutput = new JLabel("I risultati saranno visualizzati qui");

    /**
     * Barra di scorrimento verticale dell'area di testo dei risultati.
     */
    private final JScrollBar scrollBarResults = scrollPaneResults.getVerticalScrollBar();

    /**
     * Flag che indica se l'utente ha interagito con la barra di scorrimento dell'area di testo dei risultati.
     */
    private boolean scrolled = false;

    /**
     * Numero di numeri primi calcolati fino ad ora.
     */
    private int calculatedNumers = 0;
    /**
     * Funzione Runnable per interrompere il calcolo in corso.
     */
    private Runnable stopCalc;



    /**
     * Interrompe il calcolo in corso e aggiorna lo stato dell'interfaccia utente.
     * Imposta l'icona di stato e il testo dell'etichetta dello stato del calcolo.
     * Interrompe il timer e esegue il runnable associato per fermare il calcolo.
     */
    public void stopCalc(){
        stateCalculus.setIcon(alertIcon);
        if(!stateCalculus.getText().equals("Calcolo terminato"))stateCalculus.setText("Calcolo interrotto");
        stopCalc.run();
        timer.stop();
    }

    /**
     * Aggiorna il tempo trascorso dal momento dell'inizio del calcolo.
     * Calcola i minuti e i secondi trascorsi e aggiorna l'etichetta del tempo di calcolo.
     */
    private void updateTime() {
        final long currentTime = System.currentTimeMillis();
        final long elapsedTime = currentTime - startTime;
        final long minutes = (elapsedTime / 1000) / 60;
        final long seconds = (elapsedTime / 1000) % 60;
        final String elapsedTimeStr = String.format("%02d:%02d", minutes, seconds);
        labelTime.setText("Tempo di calcolo: " + elapsedTimeStr);
    }
   
    /**
     * Timer utilizzato per aggiornare il tempo trascorso ogni secondo.
     * La durata dell'intervallo è impostata su 1000 millisecondi.
     */
    private final javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
    });
   

    /**
     * Costruttore della classe `WinOutPrimality`.
     * Inizializza i componenti dell'interfaccia grafica e configura le azioni dei pulsanti.
     *
     * @param stopCalc Runnable che definisce l'azione da eseguire quando si interrompe il calcolo.
     * @param openWindowSaveInFile Runnable che definisce l'azione da eseguire per aprire la finestra di salvataggio del file.
     */
    public WinOutPrimality(Runnable stopCalc,Runnable openWindowSaveInFile){
        this.stopCalc = stopCalc;
        loadingAdvertise.setFont(loadingAdvertise.getFont().deriveFont(Font.ITALIC));
        gbc.insets = new Insets(0, 0, 10, 0);
        scrollBarResults.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (e.getValueIsAdjusting()) {
                    scrolled = true;
                } else {
                    scrolled = false;
                }
            }
        });
        buttonSalva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonStop.setEnabled(false); 

                stopCalc();
                openWindowSaveInFile.run();

            }
        });
        buttonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonStop.setEnabled(false); 
                stopCalc();
                


            }
        });
        results.setEditable(false);
        results.setCaretPosition(0);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        panelContainer.setLayout(new GridBagLayout());
        panelContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        newLabelRow(title, Font.BOLD,20,0,0);
        newLabelRow(labelMin,Font.PLAIN,13,1,0);
        newLabelRow(labelMax,Font.PLAIN,13,2,0);
        newLabelRow(labelNum,Font.PLAIN,13,3,0);
        newLabelRow(labelTime,Font.PLAIN,13,4,0);
        newLabelRow(labelTime,Font.PLAIN,13,5,0);
        gbc.insets = new Insets(0, 0, 0, 0);

        newLabelRow(containerLoadingInfo,Font.PLAIN,13,6,0);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonSalva.setFont(new Font(buttonSalva.getFont().getName(), Font.PLAIN, 13));
        buttonStop.setFont(new Font(buttonStop.getFont().getName(), Font.PLAIN, 13));

        buttonPanel.add(buttonSalva);
        buttonPanel.add(buttonStop);

        gbcButtons.anchor = GridBagConstraints.WEST;
        gbcButtons.gridx = 0;
        gbcButtons.gridy = 7;
        panelContainer.add(buttonPanel, gbcButtons);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 5;       
        containerResults.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        int width = 215;
        if(System.getProperty("os.name").toLowerCase().contains("windows"))width = 165;
        scrollPaneResults.setPreferredSize(new Dimension(width, 90));
        containerResults.add(scrollPaneResults);
        panelContainer.add(containerResults, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridheight = 3;         
        containerStatoCalc.setBorder(BorderFactory.createEmptyBorder(14, 20, 0, 0));
        containerStatoCalc.add(stateCalculus);
        panelContainer.add(containerStatoCalc, gbc);
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
     * Inizializza la finestra per la visualizzazione dei risultati.
     * Abilita il pulsante di stop, rimuove tutti i componenti esistenti e aggiunge il pannello principale.
     */
    public void initWindow(){
        buttonStop.setEnabled(true); 
        panel.removeAll();
        panel.add(panelContainer);
    }

    /**
     * Incrementa il numero di numeri primi calcolati e aggiorna l'etichetta che mostra questo numero.
     */
    public void incrementCalculatedPrimeNumbers(){
        calculatedNumers++;
        labelNum.setText("Numeri calcolati: " + calculatedNumers);
    }

    /**
     * Aggiunge un risultato calcolato all'area di testo dei risultati.
     * Scorre automaticamente verso il basso se l'utente non ha interagito con la barra di scorrimento.
     *
     * @param result Il risultato calcolato da aggiungere all'area di testo.
     */
    public void appendCalculatedNumber(String result){
        results.append(result);
        if(scrolled == false)results.setCaretPosition(results.getDocument().getLength());
    }    
    
    /**
     * Inizializza la barra di avanzamento e aggiorna l'interfaccia utente per il calcolo in corso.
     * Imposta il testo delle etichette, ripristina lo stato del calcolo e avvia il timer.
     * Se il valore massimo è lungo più di 10 caratteri, visualizza un'etichetta di avviso
     * invece della barra di avanzamento.
     *
     * @param max Il valore massimo del calcolo.
     * @param min Il valore minimo del calcolo.
     */
    public void initProgressBar(String max,String min){
        results.setText("");
        containerLoadingInfo.removeAll();
        stateCalculus.setIcon(null);
        stateCalculus.setText("");
        calculatedNumers = 0;
        labelMin.setText("Minimo: " +  ((getExp(min) != 0)?getNum(min) + " * 10 ^ "+getExp(min):min) +"\n");
        labelMax.setText("Massimo: " + ((getExp(max) != 0)?getNum(max) + " * 10 ^ "+getExp(max):max) +"\n");
        labelTime.setText("Tempo di calcolo: 00:00");
        labelNum.setText("Numeri calcolati: 0");
        startTime = System.currentTimeMillis();
        timer.start();
        if(max.length() <= 10){
            progressBar.setStringPainted(false);
            progressBar.setIndeterminate(false);
            progressBar.setValue(0);
            progressBar.setMinimum(0);
            progressBar.setMaximum(Integer.parseInt(max) - Integer.parseInt(min));
            containerLoadingInfo.add(progressBar, BorderLayout.WEST);
        }else{
            containerLoadingInfo.add(loadingAdvertise);
        }
        panel.revalidate(); // Aggiorna il layout
        panel.repaint(); 
    }

    /**
     * Aggiunge un nuovo componente all'interfaccia utente in una riga e colonna specifica
     * con il formato di font specificato.
     *
     * @param object Il componente da aggiungere.
     * @param fontSyle Lo stile del font (es. Font.PLAIN, Font.BOLD).
     * @param fontSize La dimensione del font.
     * @param row La riga nella quale posizionare il componente.
     * @param column La colonna nella quale posizionare il componente.
     */
    public void newLabelRow(JComponent object, int fontSyle, int fontSize, int row, int column) {
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = column;
        gbc.gridy = row;
        object.setFont(new Font(object.getFont().getName(), fontSyle, fontSize));
        if (!(object instanceof JButton)){
            object.setPreferredSize(new Dimension(200, 20));
        }
        panelContainer.add(object, gbc);
    }
    

    /**
     * Restituisce il pannello principale dell'interfaccia utente.
     *
     * @return Il pannello principale.
     */
    public JPanel getWindow(){
        return panel;
    }

    /**
     * Incrementa il valore della barra di avanzamento e aggiorna lo stato se il calcolo è completato.
     * Se il valore della barra di avanzamento raggiunge il massimo, disabilita il pulsante di stop,
     * ferma il timer e aggiorna lo stato del calcolo per indicare che il calcolo è terminato.
     */
    public void incrementProgressBar(){
        Component[] components = containerLoadingInfo.getComponents();
        if(components != null && components[0] instanceof JProgressBar){
            if (progressBar.getValue() != progressBar.getMaximum()) {
            progressBar.setValue(progressBar.getValue() + 1);
                if (progressBar.getValue() == progressBar.getMaximum()){
                    buttonStop.setEnabled(false); 
                    timer.stop();
                    stateCalculus.setIcon(alertIcon);
                    stateCalculus.setText("Calcolo terminato");
                }
            }
        }
    }


    /**
     * Calcola l'esponente della potenza di 10 da applicare a un valore numerico.
     * Se il valore termina con uno o più zeri, ritorna l'esponente della potenza di 10
     * altrimenti ritorna 0.
     *
     * @param val La stringa del valore numerico.
     * @return L'esponente della potenza di 10.
     */
    private  int getExp(String val){
		final int length = val.length();
		int exp = 1;
		if(length > 1 ){
			if(val.charAt(length - 1) == '0'){
				for(int i = length - 2; i > 0 ; i --){
					if(val.charAt(i) == '0')exp++;
				}
				return exp;//esponente potenza di 10 
			}else{
				return 0;//esponente potenza di 10 
			}
		}else{
			return 0;//esponente potenza di 10 
		}
	}

    /**
     * Estrae il valore numerico puro da una stringa che potrebbe contenere uno o più zeri finali.
     * Se la stringa termina con uno o più zeri, ritorna il valore numerico senza gli zeri finali.
     * Altrimenti ritorna il valore numerico come è.
     *
     * @param val La stringa del valore numerico.
     * @return Il valore numerico puro.
     */
    private  int getNum(String val){
		final int length = val.length();
		int exp = 1;
		int num = 0;
		if(length > 1 ){
			if(val.charAt(length - 1) == '0'){
				for(int i = length - 2; i > 0 ; i --){
					if(val.charAt(i) == '0')exp++;
				}
				num = Integer.parseInt(val.substring(0, length -exp));
				return num;//valore puro
			}else{
				return Integer.parseInt(val);//valore puro
			}
		}else{
			return Integer.parseInt(val);//valore puro
		}
	}
}
