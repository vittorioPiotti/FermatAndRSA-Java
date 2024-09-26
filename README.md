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

#### Support Me


[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/P5P012BC8U)

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




## Snippet Fermat Algorithm


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
| [Fermat And RSA](https://github.com/vittorioPiotti/FermatAndRSA-Java)     | [v1.0.0](https://github.com/vittorioPiotti/FermatAndRSA-Java/releases/tag/1.0.0)           | 2024 Vittorio Piotti Vittorio Piotti - [(GitHub page)](https://github.com/vittorioPiotti)                           | [GPL-3.0 License](https://github.com/vittorioPiotti/Fermat-And-RSA/blob/main/LICENSE.md)       |
| FlatLaf             | v3.2.5           | 2024 JFormDesigner GmbH                        | [Apache License 2.0](https://github.com/JFormDesigner/FlatLaf/blob/main/LICENSE)                   |
