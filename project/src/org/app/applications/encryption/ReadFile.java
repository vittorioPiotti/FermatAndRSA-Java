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

package org.app.applications.encryption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Classe che gestisce la lettura di un file di testo contenente dati numerici.
 * Filtra le righe secondo specifiche condizioni e offre funzionalità per estrarre
 * o verificare i dati numerici contenuti nel file.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class ReadFile {
    
    /** Percorso del file da leggere. */
    private String path;

    /** Indica se il modulo inverso è attivo per il filtraggio dei dati. */
    private boolean modInverse;

    /**
     * Costruttore della classe ReadFile.
     *
     * @param path Percorso del file da leggere.
     * @param modInverse Se true, applica un filtro meno restrittivo sui dati numerici.
     */
    public ReadFile(String path,boolean modInverse) {
        this.path = path;
        this.modInverse = modInverse;
    }

    /**
     * Legge il file e restituisce i dati numerici filtrati.
     *
     * @return Una stringa con i dati numerici filtrati, o null se il file non esiste o si verifica un errore.
     */
    public String readFile() {
        StringBuilder numericData = new StringBuilder();

        if (!fileExists(path)) {
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.matches("^\\d+$")  && line.length() >= 2) {
                    if(modInverse == false){
                        if(line.length() <= String.valueOf(Integer.MAX_VALUE).length() - 1) {
                            numericData.append(line);
                            numericData.append("\n");
                        }
                    }else{
                    numericData.append(line);
                    numericData.append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return numericData.toString();
    }

    /**
     * Verifica se il file rispetta i criteri specificati (almeno 3 righe valide).
     *
     * @return true se il file contiene almeno 3 righe numeriche valide, altrimenti false.
     */
    public boolean checkFile() {
        if (this.path == null || !this.path.endsWith(".txt")) {
            return false;
        }
    
        int count = 0;
    
        if (!fileExists(path)) {
            return false;
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;
    
            while ((line = br.readLine()) != null) {
                if (line.matches("^\\d+$") && line.length() >= 2) {
                    if (modInverse == false) {
                        if (line.length() <= String.valueOf(Integer.MAX_VALUE).length() - 1) {
                            count++;
                        }
                    } else {
                        count++;
                    }
                    if (count >= 3) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    
        return false;
    }
    
    /**
     * Estrae casualmente 3 righe numeriche dal file.
     *
     * @return Un array di 3 stringhe contenenti le righe numeriche estratte, o null se non ci sono abbastanza righe valide.
     */
    public String[] getRows() {
        // Verifica se il file esiste
        if (!fileExists(path)) {
            return null;
        }

        List<String> numericRows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.matches("^\\d+$")) {
                    numericRows.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (numericRows.size() < 3) {
            return null;
        }

        String[] selectedRows = new String[3];
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(numericRows.size());
            selectedRows[i] = numericRows.get(randomIndex);
        }

        return selectedRows;
    }

    /**
     * Verifica se il file esiste e se il percorso è valido.
     *
     * @param path Percorso del file da verificare.
     * @return true se il file esiste ed è valido, altrimenti false.
     */
    private boolean fileExists(String path) {
        if (this.path == null) {
            return false;
        }
        File file = new File(path);
        return file.exists() && file.isFile();
    }
}
