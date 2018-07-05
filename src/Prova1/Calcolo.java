 package Prova1;

import java.util.ArrayList;



public class Calcolo {
	static ArrayList<Integer> Problemi = new ArrayList<Integer>();
	static ArrayList<Integer> DimensioniGrafi = new ArrayList<Integer>();
	static ArrayList<Integer> Nodi = new ArrayList<Integer>();
	static ArrayList<Integer> Collegamenti = new ArrayList<Integer>();
	static ArrayList<Integer> Quantita = new ArrayList<Integer>();
	static ArrayList<Integer> grafoSP = new ArrayList<Integer>();
	static ArrayList<Integer> LinkSP = new ArrayList<Integer>();
	static ArrayList<Integer> quantitaSP = new ArrayList<Integer>();
	static ArrayList<Integer> soluzioni = new ArrayList<Integer>();
	static int contaLink=0;
	static int dimensioneGrafo=0;
	static int puntatore=0;
	static int posizioneIniziale=0;

	public Calcolo() {
	}

	public static void scegli(String nome, String valore) {


		if(nome.equals("link"))
		{
			contaLink++;
		}
		else {
			if(contaLink!=0)
				Quantita.add(contaLink);
			contaLink=0;
		}
		switch(nome) {
		case "root":
			break;
		case "problem":
			Problemi.add(Integer.parseInt(valore));
			break;
		case "graph":
			DimensioniGrafi.add(Integer.parseInt(valore));
			break;
		case "link":
			Collegamenti.add(Integer.parseInt(valore));
			break;
		case "node":
			Nodi.add(Integer.parseInt(valore));
			break;
		}
	}
	public static void Iterazioni() {
		for(int i=0; i<Problemi.size();i++) {
			int posizionePartenza=dimensioneGrafo;
			grafoSP.clear();
			LinkSP.clear();
			quantitaSP.clear();
			dimensioneGrafo=dimensioneGrafo + DimensioniGrafi.get(i);
			for(int j=posizionePartenza;j<dimensioneGrafo;j++) {
				int puntatorePartenza = posizioneIniziale;
				posizioneIniziale= posizioneIniziale + Quantita.get(j);
				grafoSP.add(Nodi.get(j));
				quantitaSP.add(Quantita.get(j));
				for(int k=puntatorePartenza; k<posizioneIniziale;k++) {
					LinkSP.add(Collegamenti.get(k));
				}
			}
        soluzioni.add(Flooding(grafoSP,quantitaSP,LinkSP));
		}
	}

	public static void risolviProblemaUltimoLink() {
		Quantita.add(contaLink);
	}

	public static int Flooding(ArrayList<Integer> nodi,ArrayList<Integer> quantitaLink,ArrayList<Integer> link) {
		int contaDa=0;
		int contaA=0;
		int contatore=1;
		int contatoreSecondario=1;
		ArrayList<ArrayList<Integer>> indirizzi = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<nodi.size();i++) {
			ArrayList<Integer> linkDelNodo = new ArrayList<Integer>();
			contaA=contaDa + quantitaLink.get(i);
			for(int j=contaDa;j<contaA;j++) {
				linkDelNodo.add(link.get(j));
			}
			indirizzi.add(linkDelNodo);
			contaDa = contaDa +quantitaLink.get(i);
		}
		for(int i=0;i<nodi.size();i++) {
			ArrayList<Integer> copia = new ArrayList<Integer>(nodi);
			copia.remove(i);
			ArrayList<Integer> linkDaPassare = new ArrayList<Integer>(indirizzi.get(i));
			ArrayList<Integer> linkAggiornati = new ArrayList<Integer>();
			do {
				linkAggiornati.clear();
				for(int puntatore1 : linkDaPassare) {
					for(int k=0;k<copia.size();k++) {
						if(puntatore1==copia.get(k)) {
							copia.remove(k);
						}
					}
				}
				contatore++;
				for(int puntatore1 : linkDaPassare) {
					for(int puntatore2:indirizzi.get(puntatore1)) {
						for(int j=0;j<copia.size();j++) {
							if(puntatore2==copia.get(j)) {
								linkAggiornati.add(puntatore2);
							}
						}
					}
				}
				linkDaPassare.clear();
				for(int puntatore3 : linkAggiornati) 
				{
					linkDaPassare.add(puntatore3);
				}
			}
			while(!copia.isEmpty());
			if(i==0) {
				contatoreSecondario=contatore;
			}
			if(contatoreSecondario>contatore) {
				contatoreSecondario=contatore;
			}
			contatore=1;
		}
		return contatoreSecondario;
	}
}