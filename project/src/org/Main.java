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


package org;

import org.app.App;



/**
 * Punto di ingresso per l'applicazione Java.
 * <p>
 * Questa classe contiene il metodo {@code main} che avvia l'applicazione. L'applicazione implementa due principali funzionalità:
 * <ul>
 *     <li><strong>Generazione di Numeri Primi:</strong> Utilizza l'algoritmo di Fermat per generare numeri primi all'interno di un intervallo specificato dall'utente. I numeri primi calcolati vengono visualizzati e possono essere salvati su file.</li>
 *     <li><strong>Crittografia RSA:</strong> Implementa la crittografia RSA per cifrare e decifrare messaggi. L'algoritmo RSA utilizza chiavi pubbliche e private per garantire la sicurezza dei dati.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * La classe {@link App} è responsabile della gestione dell'interfaccia utente e della logica dell'applicazione, coordinando le operazioni di generazione dei numeri primi e di crittografia. Quando viene eseguita, crea una nuova istanza di {@link App}, avviando così l'intera applicazione.
 * </p>
 * 
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class Main {
	/**
     * Metodo principale che avvia l'applicazione.
     * <p>
     * Questo metodo è il punto di ingresso dell'applicazione. Crea una nuova istanza della classe {@link App}, che avvia il processo di generazione dei numeri primi e di crittografia RSA.
     * </p>
     * 
     * @param args Argomenti della riga di comando, non utilizzati in questa applicazione.
     */
    public static void main(String[] args) {
		new App();
	}
}
