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

import java.math.BigInteger;

/**
 * Classe che implementa la crittografia RSA semplice per cifrare e decifrare messaggi.
 * Fornisce un'opzione per calcolare la chiave privata (d) utilizzando il modulo inverso o
 * un metodo alternativo.
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */
public class TestEncryption {

    /** Primo numero primo utilizzato per generare la chiave RSA. */
    private BigInteger p;

    /** Secondo numero primo utilizzato per generare la chiave RSA. */
    private BigInteger q;

    /** Esponente pubblico utilizzato per la crittografia. */
    private BigInteger e;

    /** Modulo n utilizzato per la crittografia e la decrittografia. */
    private BigInteger n;

    /** Esponente privato utilizzato per la decrittografia. */
    private BigInteger d;

    /**
     * Costruttore per inizializzare i parametri RSA e calcolare la chiave privata (d).
     *
     * @param modInverse Se true, utilizza il metodo del modulo inverso per calcolare d, altrimenti utilizza un algoritmo alternativo.
     * @param p Primo numero primo come stringa.
     * @param q Secondo numero primo come stringa.
     * @param e Esponente pubblico come stringa.
     */
    public TestEncryption(boolean modInverse,String p, String q, String e) {
        this.p = new BigInteger(p);
        this.q = new BigInteger(q);
        this.e = new BigInteger(e);
        this.n = this.p.multiply(this.q);
        BigInteger phi_n = this.p.subtract(BigInteger.ONE).multiply(this.q.subtract(BigInteger.ONE));
        if(modInverse == true){
            this.d = this.e.modInverse(phi_n);
        }else{
            this.d = calculateD(this.e, phi_n);  

        }

    }

    /**
     * Metodo privato per calcolare la chiave privata (d) senza utilizzare il modulo inverso.
     *
     * @param e Esponente pubblico.
     * @param phi_n Funzione di Eulero (φ(n)).
     * @return La chiave privata (d) calcolata.
     */
    private BigInteger calculateD(BigInteger e, BigInteger phi_n) {
        BigInteger d = BigInteger.ZERO;
        BigInteger k = BigInteger.ONE;

        while (true) {
            BigInteger numerator = k.multiply(phi_n).add(BigInteger.ONE);
            if (numerator.mod(e).equals(BigInteger.ZERO)) {
                d = numerator.divide(e);
                break;
            }
            k = k.add(BigInteger.ONE);
        }

        return d;
    }

    /**
     * Cifra un messaggio di testo in chiaro utilizzando la chiave pubblica RSA.
     *
     * @param plaintext Il messaggio in chiaro da cifrare.
     * @return Il messaggio cifrato, rappresentato come una stringa di numeri separati da spazi.
     */
    public String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            BigInteger m = BigInteger.valueOf(c);
            BigInteger encrypted = m.modPow(e, n);
            ciphertext.append(encrypted).append(" ");
        }
        return ciphertext.toString().trim();
    }

    /**
     * Decifra un messaggio cifrato utilizzando la chiave privata RSA.
     *
     * @param ciphertext Il messaggio cifrato da decifrare.
     * @return Il messaggio in chiaro decifrato.
     */
    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (String token : ciphertext.split(" ")) {
            BigInteger encrypted = new BigInteger(token);
            BigInteger decrypted = encrypted.modPow(d, n);
            plaintext.append((char) decrypted.intValue());
        }
        return plaintext.toString();
    }
}
