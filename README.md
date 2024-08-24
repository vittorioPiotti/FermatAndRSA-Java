# Fermat-And-RSA
Soluzione software in Java che genera e salva su file i numeri primi con algoritmo di Fermat e algoritmo di crittografia RSA


> [!NOTE]
> Il progetto è stato sviluppato a scuola per fini scolastici in funzione della consegna assegnata dal docente come lavoro individuale



> [!TIP]
> Software responsive con schermata Small e Large

> [!TIP]
> Corretto funzionamento garantito per algoritmo di Fermat e RSA

> [!Warning]
> Presenza di BUG nell'UI



## Javadoc

Link al javadoc: [(link)](https://vittoriopiotti.altervista.org/FermatAndRsaJava/index.html)


## Preview

Video di Test [(link)](https://drive.google.com/file/d/1IVb3ctowyLbrHMg7zlFN-Zv7If_51uzH/view?usp=sharing)


---

<img src="https://github.com/vittorioPiotti/Fermat-And-RSA/blob/main/images/preview.png" />



## Responsive

|<img src="https://github.com/vittorioPiotti/Fermat-And-RSA/blob/main/images/small2.png" />|<img src="https://github.com/vittorioPiotti/Fermat-And-RSA/blob/main/images/large2.png" />|          
|-|-|
|Small|Large|




## Utilizzo

 1. Seleziona schermata di "Test Primalità"
 2. Genera i numeri primi con algoritmo di Fermat
 3. Salva su file i numeri primi
 4. Seleziona schermata di "Algoritmo RSA"
 5. Carica il file con i numeri primi
 6. Cifra o decifra un messaggio


## Snippets

### Snippet Fermat Algorithm


```java
public void algoritmoFermat(){
	long start; //tempo inizio calcolo numeri primi
	long end; //tempo fine calcolo numeri primi
	boolean checkPrimo; //verifica se un numero è primo
	//check = true:     primo
	//check = false:    composto
	start = System.currentTimeMillis(); //tempo inizio calcolo numeri primi                     
	//ciclo for di i che scorre da min a max
	for (
		BigInteger i = min; //è il numero di cui si verifica la primalità
		i.compareTo(max) <= 0;
		i = i.add(BigInteger.valueOf(1))
	) {
		incrementProgressBar.run();
		checkPrimo = true;
		//ciclo for annidato di j che scorre da 2 al divisore massimo
		for (
			BigInteger j = BigInteger.valueOf(2); //è il dvisore del numero per la verifica della primalità
			j.compareTo(BigInteger.valueOf(10)) <= 0; //j compareTo fino al numero
			j = j.add(BigInteger.valueOf(1)))
		//verifica se j divisore di i ed in caso aggiorna check = false
		{
			if (Thread.currentThread().isInterrupted()) {
				end = System.currentTimeMillis(); //tempo fine calcolo numeri primi
				time = Math.abs(start - end) / 1000.0; //tempo durata calcolo numeri primi
				return;
			}
			if (!j.modPow(i.subtract(BigInteger.valueOf(1)), i).equals(BigInteger.valueOf(1))) checkPrimo = false;
		}
		if (checkPrimo){
			results.add(i.toString());
			appendResult.run();
		}
	}
	end = System.currentTimeMillis(); //tempo fine calcolo numeri primi
	time = Math.abs(start - end) / 1000.0; //tempo durata calcolo numeri primi
}

```
### Snippet RSA Algorithm

```java  
public class TestEncryption {
    //Classe che implementa la crittografia RSA semplice per cifrare e decifrare messaggi.
    private BigInteger p;	//Primo numero primo utilizzato per generare la chiave RSA
    private BigInteger q;	//Secondo numero primo utilizzato per generare la chiave RSA
    private BigInteger e;	//Esponente pubblico utilizzato per la crittografia
    private BigInteger n;	//Modulo n utilizzato per la crittografia e la decrittografia
    private BigInteger d;	//Esponente privato utilizzato per la decrittografia.
    public TestEncryption(boolean modInverse,String p, String q, String e) { // Costruttore per inizializzare i parametri RSA e calcolare la chiave privata (d).
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
    private BigInteger calculateD(BigInteger e, BigInteger phi_n) { // Metodo privato per calcolare la chiave privata (d) senza utilizzare il modulo inverso.
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
    public String encrypt(String plaintext) { // Cifra un messaggio di testo in chiaro utilizzando la chiave pubblica RSA.
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            BigInteger m = BigInteger.valueOf(c);
            BigInteger encrypted = m.modPow(e, n);
            ciphertext.append(encrypted).append(" ");
        }
        return ciphertext.toString().trim();
    }
    public String decrypt(String ciphertext) { // Decifra un messaggio cifrato utilizzando la chiave privata RSA.		
        StringBuilder plaintext = new StringBuilder();
        for (String token : ciphertext.split(" ")) {
            BigInteger encrypted = new BigInteger(token);
            BigInteger decrypted = encrypted.modPow(d, n);
            plaintext.append((char) decrypted.intValue());
        }
        return plaintext.toString();
    }
}
```

## Albero di Path

```bash
$ tree
.
├──lib
│   └── flatlaf-3.2.5.jar
└──src
    └── org
        └── app
            ├── applications
            │   ├── encryption
            │   │   ├── ReadFile.java
            │   │   └── TestEncryption.java
            │   └── primality
            │       ├── SavePrimality.java
            │       └── TestPrimality.java
            ├── windows
            │   ├── dependencies
            │   │   ├── components
            │   │   │   ├── encryption
            │   │   │   │   ├── dialogs
            │   │   │   │   │   ├── WinErrorInEncryption.java
            │   │   │   │   │   └── WinErrorOutEncryption.java
            │   │   │   │   ├── panels
            │   │   │   │   │   ├── WinInEncryption.java
            │   │   │   │   │   ├── WinOutEncryption.java
            │   │   │   │   │   └── WinSaveEncryption.java
            │   │   │   │   └── WinEncryption.java
            │   │   │   ├── primality
            │   │   │   │   ├── dialogs
            │   │   │   │   │   ├── WinErrorInPrimality.java
            │   │   │   │   │   └── WinErrorOutPrimality.java
            │   │   │   │   ├── panels
            │   │   │   │   │   ├── WinInPrimality.java
            │   │   │   │   │   ├── WinOutPrimality.java
            │   │   │   │   │   └── WinSavePrimality.java
            │   │   │   │   └── WinPrimality.java
            │   │   │   └── WinHome.java
            │   │   └── managers
            │   │       ├── WinManagerEncryption.java
            │   │       └── WinManagerPrimality.java
            │   └── WinManager.java
            ├── App.java
            └── Main.java

```

## Licenze

| Componente          | Versione         | Copyright                                      | Licenza                                                                                            |
|---------------------|------------------|------------------------------------------------|----------------------------------------------------------------------------------------------------|
| Fermat And RSA     | v1.0.0           | 2024 Vittorio Piotti                           | [GPL-3.0 License](https://github.com/vittorioPiotti/Fermat-And-RSA/blob/main/LICENSE.md)       |
| FlatLaf             | v3.2.5           | 2024 JFormDesigner GmbH                        | [Apache License 2.0](https://github.com/JFormDesigner/FlatLaf/blob/main/LICENSE)                   |
