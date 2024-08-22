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

package org.app.applications.primality;


import java.io.FileWriter;
import java.io.IOException;

/**
 * La classe {@code SavePrimality} gestisce la scrittura di una lista di stringhe formattate su un file TSV (Tab-Separated Values).
 * <p>
 * Ogni volta che viene chiamato il metodo {@link #newFile()}, il file viene ricreato, ovvero viene eliminato il file esistente e ne viene creato uno nuovo con il contenuto fornito.
 * </p>
 * 
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class SavePrimality {

	/**
     * Contenuto formattato da scrivere nel file. 
     * Questo campo contiene le stringhe che verranno scritte nel file TSV.
     */
	private String content;

	/**
     * Percorso del file in cui salvare il contenuto.
     * Questo campo specifica il percorso completo del file in cui il contenuto sarà scritto.
     */
	private String path;

	/**
     * Costruttore della classe {@code SavePrimality}.
     * <p>
     * Inizializza il contenuto e il percorso del file in cui il contenuto verrà salvato.
     * </p>
     *
     * @param content Il contenuto formattato da scrivere nel file.
     * @param path Il percorso completo del file in cui salvare il contenuto.
     */
	public SavePrimality(String content,String path) {
		this.content = content;
		this.path = path;
	}

	/**
     * Scrive il contenuto nel file specificato dal percorso.
     * <p>
     * Questo metodo elimina il file esistente e ne crea uno nuovo con il contenuto fornito. Se si verifica un'eccezione durante la scrittura, il metodo la gestisce silenziosamente senza interrompere l'esecuzione.
     * </p>
     */
	public void newFile() {
		
		try (FileWriter fileWriter = new FileWriter(path, false)) {
			fileWriter.write(content);
		} catch (IOException e) {
			return;
		}
	}

}