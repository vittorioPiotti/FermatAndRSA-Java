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

package org.app.windows.dependencies.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;


/**
 * La classe `WinHome` rappresenta la finestra principale dell'applicazione che permette all'utente di 
 * selezionare tra due moduli: uno per il test di primalità e l'altro per la crittografia RSA.
 * 
 * La finestra include un'intestazione, una sezione di navigazione con pulsanti, e un piè di pagina. 
 * I pulsanti nella sezione di navigazione permettono di passare tra le due visualizzazioni principali.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class WinHome {

    /** 
     * La finestra principale dell'applicazione. 
     */
    private final JFrame frame = new JFrame("Test Primalità");

    /** 
     * Il pannello principale che utilizza un layout BorderLayout. 
     */
    private final JPanel mainPanel = new JPanel(new BorderLayout());

    /** 
     * Il pannello dell'intestazione della finestra. 
     */
    private final JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));

    /** 
     * L'etichetta che mostra il titolo nella parte superiore della finestra. 
     */
    private final JLabel labelHeader = new JLabel("Ricerca Numeri Primi");

    /** 
     * Il pannello del piè di pagina della finestra. 
     */
    private final JPanel footer = new JPanel(new GridBagLayout());

    /** 
     * L'etichetta che mostra il testo del piè di pagina. 
     */
    private final JLabel labelFooter = new JLabel("©VP - Sistemi e Reti - Test Primalità & Crittografia RSA");
    
    /** 
     * Il pannello di navigazione per i pulsanti di selezione. 
     */
    private final JPanel navPanel = new JPanel();

    /** 
     * Il pannello che contiene l'intestazione. 
     */
    private final JPanel containerLabelHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));

    /** 
     * Un pannello separatore utilizzato nella sezione di navigazione. 
     */
    private final JPanel separator = new JPanel();
    
    /** 
     * Il pulsante per il test di primalità. 
     */
    private final JButton buttonPrimalityTest = new JButton("Test Primalità");

    /** 
     * Il pulsante per la crittografia RSA. 
     */
    private final JButton buttonEncryptionRsa = new JButton("Crittografia RSA");


    /** 
     * Il pannello per la visualizzazione del test di primalità. 
     */
    private JPanel windowPrimalityTest;

    /** 
     * Il pannello per la visualizzazione della crittografia RSA. 
     */
    private JPanel windowEncryption;

    /** 
     * Indica quale finestra è attualmente selezionata. 
     */
    private boolean selectedWindow = false;

    /** 
     * Runnable per ridimensionare la finestra in base alla larghezza. 
     */
    private Runnable resizeFromWidth;

    /** 
     * Runnable per cambiare la finestra visualizzata. 
     */
    private Runnable switchWindow;


    /**
     * Restituisce lo stato della finestra attualmente selezionata.
     *
     * @return true se la finestra di test di primalità è selezionata, false se la finestra di crittografia RSA è selezionata.
     */
    public boolean getSelectedWindow(){
        return selectedWindow;
    }

    /**
     * Inizializza le impostazioni dei pulsanti.
     *
     * @param button Il pulsante da inizializzare.
     */
    private void initButton(JButton button){
        button.setPreferredSize(new Dimension(200, 35));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBackground(new Color(237,236,237));
        button.setForeground(Color.BLACK); 
        Font currentFont = button.getFont();
        button.setFont(new Font(currentFont.getName(), Font.PLAIN, 17));
    }


    /**
     * Attiva un pulsante e disattiva l'altro, cambiando il colore e il font per riflettere lo stato attivo.
     *
     * @param activeButton Il pulsante da attivare.
     * @param disactiveButton Il pulsante da disattivare.
     * @param currentWindow true se la finestra di test di primalità è selezionata, false se la finestra di crittografia RSA è selezionata.
     */
    private void activeButton(JButton activeButton, JButton disactiveButton, boolean currentWindow){
        if(selectedWindow != currentWindow){

            Font currentFont;
            activeButton.setBackground(Color.GRAY);
            activeButton.setForeground(Color.WHITE); 
            currentFont = activeButton.getFont();
            activeButton.setFont(new Font(currentFont.getName(), Font.PLAIN, 17));

            disactiveButton.setBackground(new Color(237,236,237));
            disactiveButton.setForeground(Color.BLACK); 
            currentFont = disactiveButton.getFont();
            disactiveButton.setFont(new Font(currentFont.getName(), Font.PLAIN, 17));
            selectedWindow = !selectedWindow;
            
            if (selectedWindow == false){
                switchWindow.run();

                mainPanel.add(windowEncryption);
                mainPanel.remove(windowPrimalityTest);
              
                labelHeader.setText("Crittografia RSA");
               
                
            } else {
                mainPanel.add(windowPrimalityTest);
                mainPanel.remove(windowEncryption);
               
                labelHeader.setText("Ricerca Numeri Primi");
                
            }
            mainPanel.revalidate();
            mainPanel.repaint();
            this.resizeFromWidth.run();
      
        }
        
    }

  

    /**
     * Costruisce una nuova istanza della finestra principale.
     * 
     * @param windowPrimalityTest Il pannello per il test di primalità.
     * @param windowEncryption Il pannello per la crittografia RSA.
     * @param resizeFromWidth Runnable per ridimensionare la finestra in base alla larghezza.
     * @param switchWindow Runnable per cambiare la finestra visualizzata.
     */
    public WinHome(JPanel windowPrimalityTest,JPanel windowEncryption,Runnable resizeFromWidth,Runnable switchWindow) {
        this.windowPrimalityTest = windowPrimalityTest;
        this.resizeFromWidth = resizeFromWidth;
        this.windowEncryption = windowEncryption;
        this.switchWindow = switchWindow;
        frame.setSize(500, 614);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        if(!System.getProperty("os.name").toLowerCase().contains("windows") ){
            frame.setMinimumSize(new Dimension(500, 614));
            this.resizeFromWidth.run();
        }
        
 
        labelFooter.setFont(new Font(labelFooter.getFont().getName(), Font.PLAIN, 15));
        footer.add(labelFooter, new GridBagConstraints());
        footer.setPreferredSize(new Dimension(1200, 40));
        labelFooter.setForeground(Color.WHITE);
		footer.setBackground(new Color(70, 130, 180));

        
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(new CompoundBorder(navPanel.getBorder(), BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)));
        navPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        initButton(buttonPrimalityTest);
        initButton(buttonEncryptionRsa);
        activeButton(buttonPrimalityTest,buttonEncryptionRsa,true);
     
        buttonPrimalityTest.addActionListener(e -> {
            activeButton(buttonPrimalityTest,buttonEncryptionRsa,true);
         
        });
        buttonEncryptionRsa.addActionListener(e -> {
            activeButton(buttonEncryptionRsa,buttonPrimalityTest,false);
        });
    
             
      
        
        separator.setBackground(Color.BLACK);
        separator.setPreferredSize(new Dimension(2, 35)); 

        navPanel.add(buttonPrimalityTest);
        navPanel.add(separator);
        navPanel.add(buttonEncryptionRsa);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));  
        header.add(navPanel);
        
        labelHeader.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        labelHeader.setFont(new Font(labelHeader.getFont().getName(), Font.BOLD, 23));

        containerLabelHeader.add(labelHeader);

        header.add(containerLabelHeader, new GridBagConstraints());
        header.setPreferredSize(new Dimension(1200, 90));
        frame.addComponentListener(new ComponentAdapter() {
            private Timer resizeTimer = new Timer(100, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (frame.getWidth() < 965) {
                        if(frame.getWidth() < 500){
                            frame.setSize(500,frame.getHeight());
                        }
                        if(frame.getHeight() < 614){
                            frame.setSize(frame.getWidth(),614);
                        }
                    }else{
                         if(frame.getHeight() < 361){
                            frame.setSize(frame.getWidth(),361);
                        }
                    }
                }
            });
            @Override
            public void componentResized(ComponentEvent e) {
                resizeFromWidth.run();
                if (!resizeTimer.isRunning()) {
                    resizeTimer.start();
                } else {
                    resizeTimer.restart();
                }
            }
        });
        frame.addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    return;
                }
                int newState = e.getNewState();
                if ((newState & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
                    resizeFromWidth.run();
                } else {
                    resizeFromWidth.run();
                }
            }
        });
        frame.add(mainPanel);
        mainPanel.add(footer, BorderLayout.SOUTH);
        mainPanel.add(windowPrimalityTest);

        mainPanel.add(header, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    /**
     * Restituisce la larghezza attuale della finestra.
     *
     * @return La larghezza della finestra in pixel.
     */
    public int getFrameWidth(){
        return frame.getWidth();
    }

    /**
     * Rende visibile la finestra principale e applica le modifiche di ridimensionamento.
     */
    public void setVisible(){
        resizeFromWidth.run();
        frame.setVisible(true);
    }

    /**
     * Restituisce l'istanza della finestra principale.
     *
     * @return L'istanza della finestra principale.
     */
    public JFrame getFrame(){
        return frame;
    }
    
}