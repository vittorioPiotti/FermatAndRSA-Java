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


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe sviluppa e garantisce logica dei metodi di calcolo per il test di primalità<br>La classe utilizza BigInteger per gestire numeri di centinaia di cifre<br>La classe implementa Runnable in quanto le operazioni di calcolo impiegano un dispendio di tempo tale per cui il thread deve essere gestito in modo che non interferisca con altri thread impedendone la corretta esecuzione<br>I metodi di calcolo non sono ottimizzati per un tempo di esecuzione minore in quanto presentano due colback in App di cui la prima per aggiornare la UI e di cui la seconda per salvare o dati con Log
 * @author Vittorio Piotti
 * @version 1.0
 * @since 16-10-2023
 */

public class TestPrimality implements Runnable {

	/**
     * Rappresenta la scelta del metodo di calcolo utilizzato per la ricerca dei numeri primi.
     * Questo valore viene usato per determinare quale algoritmo di calcolo dei numeri primi utilizzare.
     */
	private int choice; //corrisponde alla scelta del metodo di calcolo

	/**
     * Il numero minimo dell'intervallo da cui inizia la ricerca dei numeri primi.
     * Questo valore rappresenta il limite inferiore dell'intervallo di ricerca per trovare numeri primi.
     */
	private BigInteger min; //corrisponde al numero minimo di partenza della ricerca dei numeri primi

	/**
     * Il numero massimo dell'intervallo fino al quale viene effettuata la ricerca dei numeri primi.
     * Questo valore rappresenta il limite superiore dell'intervallo di ricerca per trovare numeri primi.
     */
	private BigInteger max; //corrisponde al numero massimo di termine della ricerca dei numeri primi
	
	/**
     * Il tempo impiegato per completare la ricerca dei numeri primi, espresso in secondi.
     * Questo valore viene aggiornato durante l'esecuzione della ricerca e utilizzato per misurare le prestazioni del calcolo.
     */
	private double time; //corrisponde al tempo impiegato per la ricerca dei numeri primi

	/**
     * Una lista di stringhe che contiene tutti i numeri primi trovati all'interno dell'intervallo specificato.
     * Ogni stringa rappresenta un numero primo trovato durante la ricerca.
     */
	private List < String > results = new ArrayList < >(); //corrisponde a tutti i numeri primi trovati nell'intervallo dato

	/**
     * Callback invocata alla fine dell'esecuzione del metodo {@link #run()}.
     * Questo callback fa riferimento a {@link Log} per salvare i risultati della ricerca e a {@link UI} per visualizzare i risultati all'utente.
     */
	private Runnable stopCalc;

	/**
     * Callback utilizzato per aggiungere un risultato alla visualizzazione dell'interfaccia utente.
     * Questo callback è invocato ogni volta che viene trovato un numero primo e serve per aggiornare la visualizzazione dei risultati.
     */
	private Runnable appendResult;

	/**
     * Callback invocato ad ogni iterazione della ricerca per aggiornare la barra di progresso nell'interfaccia utente.
     * Questo callback è utilizzato per incrementare la barra di caricamento e fornire un feedback visivo sul progresso della ricerca.
     */
	private Runnable incrementProgressBar;

	/**
     * Questo costruttore definisce l'istanza degli attributi min e max rispettivamente i valori dell'intervallo entro cui ricercare numeri primi.
	 * @param min     				Stringa rappresentante il valore minimo inserito in input per l'intervallo di ricerca dei numeri primi
	 * @param max     				Stringa rappresentante il valore massimo inserito in input per l'intervallo di ricerca dei numeri primi
	 * @param choice  				Rappresenta il numero del metodo scelto per il calcolo della ricerca dei numeri primi
	 * @param stopCalc  	Callback definita nel  fa riferimento a SaveInFile per salvare i risultati e  App per visualizzare i risultati
	 * @param incrementProgressBar 	Callback definita nel  ed invocata ad ogni numero edll'intervallo di ricerca fa riferimento a App per incrementare la barra di caricamento
	 * @param appendResult 	Callback definita nel  ed invocata ad ogni numero edll'intervallo di ricerca fa riferimento a App per incrementare la barra di caricamento

	*/
	public TestPrimality(String min, String max, int choice, Runnable stopCalc, Runnable incrementProgressBar,Runnable appendResult) {
        this.min = new BigInteger(min);
		this.max = new BigInteger(max);
		this.choice = choice;
		this.stopCalc = stopCalc;
		this.appendResult = appendResult;
		this.incrementProgressBar = incrementProgressBar;

	}

	/**
     * Questo metodo assegna il valore all'attributo privato choice che è relativo all'algoritmo di calcolo scelto
	 * @param choice numero del metodo di calcolo scelto
    */
	public void setChoice(int choice) {
		this.choice = choice;
	}

	/**
     * Questo metodo restituisce il valore dell'attributo privato choice che è relativo all'algoritmo di calcolo scelto
	 * @return numero del metodo scelto
    */
	public int getChoice() {
		return choice;
	}

	/**
     * Questo metodo ripristina la lista dei risultati rimuovendo ogni elemento da essa
    */
	public void setResults() {
		results.clear();
	}
	
	/**
     * Questo metodo ritorna la lista dei risultati
	 * @return risultati dei numeri primi derivati dai calcoli
    */
	public List < String > getResults() {
		return results;
	}

	/**
     * Questo metodo ritorna il valore massimo di arrivo per la ricerca dei numeri primi.
	 *  @return numero massimo inserito in input per l'intervallo di ricerca dei numeri primi
    */
	public String getMax() {
		return max.toString();
	}

	/**
     * Questo metodo ritorna il valore minimo di partenza per la ricerca dei numeri primi.
	 * @return numero minimo inserito in input per l'intervallo di ricerca dei numeri primi

    */
	public String getMin() {
		return min.toString();
	}

	/**
     * Questo metodo ritorna il tempo impiegato per la ricerca dei numeri primi.
	 * @return tempo trascorso dall'inizio al termine della ricerca dei numeri primi
    */
	public double getTime() {
		return time;
	}

	/**
     * Questo metodo imposta un nuovo valore al numero massimo di arrivo per la ricerca dei numeri primi.
	 * @param max numero massimo inserito in input per l'intervallo di ricerca dei numeri primi
    */
	public void setMax(String max) {
		this.max = new BigInteger(max);
	}

	/**
     * Questo metodo imposta un nuovo valore al numero minimo di partenza per la ricerca dei numeri primi.
	 * @param min numero massimo inserito in input per l'intervallo di ricerca dei numeri primi
    */
	public void setMin(String min) {
		this.min = new BigInteger(min);
	}

	/**
     * Questo metodo imposta un nuovo valore al tempo impiegato per la ricerca dei numeri primi.
	 * @param time tempo trascorso dall'inizio al termine della ricerca dei numeri primi
    */
	public void setTime(double time) {
		this.time = time;
	}

	/**
     * Questo metodo effettua il test di verifica della primalita con la logica della ricerca dei divisori fino al numero<br>Presenta un controllo isInterrupted() che ad ogni iterazione del ciclo annidato controlla se processo terminato ed in caso interrompe i calcoli sviluppando i dati ricavati per essere gestiti nel main
    */
	public void calcPrimeUntilNum() {
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
			j.compareTo(i) < 0; //j compareTo fino al numero
			j = j.add(BigInteger.valueOf(1)))
			//verifica se j divisore di i ed in caso aggiorna check = false
			{
				if (Thread.currentThread().isInterrupted()) {
					end = System.currentTimeMillis(); //tempo fine calcolo numeri primi
					time = Math.abs(start - end) / 1000.0; //tempo durata calcolo numeri primi
					return;
				}
				if (i.mod(j).equals(BigInteger.valueOf(0))) checkPrimo = false;
			}
			if (checkPrimo){
				results.add(i.toString());
				appendResult.run();
			}
		
		}
		end = System.currentTimeMillis(); //tempo fine calcolo numeri primi
		time = Math.abs(start - end) / 1000.0; //tempo durata calcolo numeri primi
	}

	/**
     * Questo metodo effettua il test di verifica della primalita con la logica della ricerca dei divisori fino al numero uscendo dal ciclo appena possibile<br>Presenta un controllo isInterrupted() che ad ogni iterazione del ciclo annidato controlla se processo terminato ed in caso interrompe i calcoli sviluppando i dati ricavati per essere gestiti nel main
    */
	public void calcPrimeUntilNumWithBreak() {
		long start; //tempo inizio calcolo numeri primi
		long end; //tempo fine calcolo numeri primi
		boolean checkPrimo; //verifica se un numero è primo
		//check = true:     primo
		//check = false:    composto
		start = System.currentTimeMillis(); //tempo inizio calcolo numeri primi                     
		//ciclo for di i che scorre da min a max
		for (
		BigInteger i = min; //è il numero per cui si verifica la primalità
		i.compareTo(max) <= 0;
		i = i.add(BigInteger.valueOf(1))) {
			incrementProgressBar.run();
			checkPrimo = true;
			//ciclo for annidato di j che scorre da 2 al divisore massimo
			for (
			BigInteger j = BigInteger.valueOf(2); //è il dvisore del numero per la verifica della primalità
			j.compareTo(i) < 0; //j compareTo fino al numero
			j = j.add(BigInteger.valueOf(1)))
			//verifica se j divisore di i ed in caso aggiorna check = false
			{
				if (Thread.currentThread().isInterrupted()) {
					end = System.currentTimeMillis(); //tempo fine calcolo numeri primi
					time = Math.abs(start - end) / 1000.0; //tempo durata calcolo numeri primi
					return;
				}
				if (i.mod(j).equals(BigInteger.valueOf(0))) {
					checkPrimo = false;
					break; //fine ricerca primalita quindi interrompe il ciclo di j perche i è composto
				}

			}
			if (checkPrimo){
				results.add(i.toString());
				appendResult.run();
			}
		}
		end = System.currentTimeMillis(); //tempo fine calcolo numeri primi
		time = Math.abs(start - end) / 1000.0; //tempo durata calcolo numeri primi
	}

	/**
     * Questo metodo effettua il test di verifica della primalita con la logica della ricerca dei divisori fino alla meta del numero <br>Presenta un controllo isInterrupted() che ad ogni iterazione del ciclo annidato controlla se processo terminato ed in caso interrompe i calcoli sviluppando i dati ricavati per essere gestiti nel main
    */
	public void calcPrimeUntilHalfNum() {
		long start; //tempo inizio calcolo numeri primi
		long end; //tempo fine calcolo numeri primi
		boolean checkPrimo; //verifica se un numero è primo
		//check = true:     primo
		//check = false:    composto
		start = System.currentTimeMillis(); //tempo inizio calcolo numeri primi                     
		//ciclo for di i che scorre da min a max
		for (
		BigInteger i = min; //è il numero per cui si verifica la primalità
		i.compareTo(max) <= 0;
		i = i.add(BigInteger.valueOf(1))) {
			incrementProgressBar.run();
			checkPrimo = true;
			//ciclo for annidato di j che scorre da 2 al divisore massimo
			for (
			BigInteger j = BigInteger.valueOf(2); //è il dvisore del numero per la verifica della primalità
			j.compareTo(i.divide(BigInteger.valueOf(2))) <= 0; //j compareTo fino alla meta del numero
			j = j.add(BigInteger.valueOf(1)))
			//verifica se j divisore di i ed in caso aggiorna check = false
			{
				if (Thread.currentThread().isInterrupted()) {
					end = System.currentTimeMillis(); //tempo fine calcolo numeri primi
					time = Math.abs(start - end) / 1000.0; //tempo durata calcolo numeri primi
					return;
				}
				if (i.mod(j).equals(BigInteger.valueOf(0))) checkPrimo = false;
			}
			if (checkPrimo){
				results.add(i.toString());
				appendResult.run();
			}
		}
		end = System.currentTimeMillis(); //tempo fine calcolo numeri primi
		time = Math.abs(start - end) / 1000.0; //tempo durata calcolo numeri primi
	}

	/**
     * Questo metodo effettua il test di verifica della primalita con la logica della ricerca dei divisori fino alla meta del numero considerando solo i numeri dispari<br>Presenta un controllo isInterrupted() che ad ogni iterazione del ciclo annidato controlla se processo terminato ed in caso interrompe i calcoli sviluppando i dati ricavati per essere gestiti nel main
    */
	public void calcPrimeUntilHalfNumOnlyOdd() {
		long start; //tempo inizio calcolo numeri primi
		long end; //tempo fine calcolo numeri primi
		boolean checkPrimo; //verifica se un numero è primo
		//check = true:     primo
		//check = false:    composto
		start = System.currentTimeMillis(); //tempo inizio calcolo numeri primi                     
		//ciclo for di i che scorre da min a max
		for (
		BigInteger i = min; //è il numero per cui si verifica la primalità
		i.compareTo(max) <= 0;
		i = i.add(BigInteger.valueOf(1))) {
			incrementProgressBar.run();
			checkPrimo = true;
			//ciclo for annidato di j che scorre da 2 al divisore massimo
			for (
			BigInteger j = BigInteger.valueOf(2); //è il dvisore del numero per la verifica della primalità
			j.compareTo(i.divide(BigInteger.valueOf(2))) <= 0; //j compareTo fino alla meta del numero
			j = j.add(BigInteger.valueOf(1)))
			//verifica se i è pari o se j divisore di i ed in caso aggiorna check = false
			{
				if (Thread.currentThread().isInterrupted()) {
					end = System.currentTimeMillis(); //tempo fine calcolo numeri primi
					time = Math.abs(start - end) / 1000.0; //tempo durata calcolo numeri primi
					return;
				}
				if (i.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(0)) //se i è pari
				|| i.mod(j).equals(BigInteger.valueOf(0)) // se i è divisivbile 
				) checkPrimo = false; //allora i è composto
			}
			if (checkPrimo){
				results.add(i.toString());
				appendResult.run();
			}
		}
		end = System.currentTimeMillis(); //tempo fine calcolo numeri primi
		time = Math.abs(start - end) / 1000.0; //tempo durata calcolo numeri primi
	}

	/**
     * Questo metodo effettua il test di verifica della primalita con la logica della ricerca dei divisori fino alla radice del numero considerando solo i numeri dispari ed uscendo dal ciclo appena possibile<br>Presenta un controllo isInterrupted() che ad ogni iterazione del ciclo annidato controlla se processo terminato ed in caso interrompe i calcoli sviluppando i dati ricavati per essere gestiti nel main
    */
	public void calcPrimeUntilRadixNumOnlyOddWithBreak() {
		long start; //tempo inizio calcolo numeri primi
		long end; //tempo fine calcolo numeri primi
		boolean checkPrimo; //verifica se un numero è primo
		//check = true:     primo
		//check = false:    composto
		start = System.currentTimeMillis(); //tempo inizio calcolo numeri primi                     
		//ciclo for di i che scorre da min a max
		for (
		BigInteger i = min; //è il numero per cui si verifica la primalità
		i.compareTo(max) <= 0;
		i = i.add(BigInteger.valueOf(1))) {
			incrementProgressBar.run();
			checkPrimo = true;
			//ciclo for annidato di j che scorre da 2 al divisore massimo
			for (
			BigInteger j = BigInteger.valueOf(2); //è il dvisore del numero per la verifica della primalità
			j.compareTo(i.sqrt().add(BigInteger.valueOf(1))) < 0; //j compareTo fino alla radice del numero
			j = j.add(BigInteger.valueOf(1)))
			//verifica se i è pari o se j divisore di i ed in caso aggiorna check = false
			{
				if (Thread.currentThread().isInterrupted()) {
					end = System.currentTimeMillis(); //tempo fine calcolo numeri primi
					time = Math.abs(start - end) / 1000.0; //tempo durata calcolo numeri primi
					return;
				}
				if (i.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(0)) //i è pari
				|| i.mod(j).equals(BigInteger.valueOf(0)) //i è divisivbile 
				) {
					checkPrimo = false; //i è composto
					break; //fine ricerca primalita quindi interrompe il ciclo di j perche i è composto
				}
			}
			if (checkPrimo){
				results.add(i.toString());
				appendResult.run();
			}
		}
		end = System.currentTimeMillis(); //tempo fine calcolo numeri primi                                        
		time = Math.abs(start - end) / 1000.0; //tempo durata calcolo numeri primi
	}

	/**
     * Questo metodo effettua il test di verifica della primalita con la logica dell'algoritmo di Fermat<br>Presenta un controllo isInterrupted() che ad ogni iterazione del ciclo annidato controlla se processo terminato ed in caso interrompe i calcoli sviluppando i dati ricavati per essere gestiti nel main
    */
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

	/**
     * Questo metodo sviluppa il metodo di calcolo sulla base dell valore dell'attributo privato relativo <br>Questo metodo è l'implementazione dell'interfaccia 'Runnable' ed è utilizzo per creare il thread del calcolo in modo che la classe possa essere eseguita in modo concorrente rispetto alla classe UI<br>Nello specifico è stato necessario ciò in quanto il tempo di calcolo è tale per cui senza l'implementazione di runnable nella UI all'avvio del calcolo nella relativa finestra di dialogo questa o rimane in attesa fino al termine del calcolo o il calcolo viene avviato solo alla chiusura della finestra di dialogo <br> Il comportamento corretto reso possibile dall'implementazione prevede quindi che il calcolo non interferisca con la UI
    */
	public void run() {
		//questa sleep è usato rispetto alla 'UI'
		//La 'UI' gestisce il calcolo con le schermata di richiesta, attesa e risultato
		//Ciò in modo che la 'UI' abbia il tempo di svilupparsi correttamente senza interferenze tra le schermate.
		try {
			Thread.sleep(500);
		} catch(InterruptedException e) {
			return;
		}
		switch (choice) {
		case 0:
			//senza break,  anche dispari,  tutti    
			calcPrimeUntilNum();
			break;
		case 1:
			//con break,    anche dispari,  tutti
			calcPrimeUntilNumWithBreak();
			break;
		case 2:
			//senza break,  anche dispari,  fino meta
			calcPrimeUntilHalfNum();
			break;
		case 3:
			//senza break,  solo dispari,   fino meta
			calcPrimeUntilHalfNumOnlyOdd();
			break;
		case 4:
			//con break,    solo dispari,   fino radice
			calcPrimeUntilRadixNumOnlyOddWithBreak();
			break;
		case 5:
			// Algoritmo di Fermat
			if(max.compareTo(BigInteger.valueOf(10)) > 0){
				algoritmoFermat();
			}
			break;
		}
		if(results.size() > 0){
			if(results.get(0).equals("0"))results.remove(0);
			if(results.get(0).equals("1"))results.remove(0);
		}
		if(choice == 5){
				final int [] primi = {2,3,5,7};
				for(int i = 0; i < primi.length; i ++){
					if(min.compareTo(BigInteger.valueOf(primi[i])) <= 0 && max.compareTo(BigInteger.valueOf(primi[i])) >= 0){
						results.add(i,String.valueOf(primi[i]));
					}
				}
			}
		if(results.size() == 0){
			results.add("");
		}
	
		stopCalc.run();
	}

	/**
     *@return ritorna i numeri risultati della ricerca dei numeri primi
    */
    public String getResult(){   
        return ((results.size() == 0)?"":results.get(results.size() - 1));  
    }
	
}