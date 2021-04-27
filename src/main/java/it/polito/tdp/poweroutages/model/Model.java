package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	
	List<PowerOutage> soluzioneOttima;
	int personeColpite = 0;
	int contaAnni = 0;
	int contaOre = 0;
	List<PowerOutage> powerFiltrata;
	
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}


	public List<PowerOutage> getPowerOutagesList() {
		return podao.getPowerOutagesList();
	}
	
	
	public List<PowerOutage> doRicorsione(int numAnni, int numOre, Nerc nerc){
		soluzioneOttima = null;
		LinkedList<PowerOutage> parziale = new LinkedList<PowerOutage>();
		powerFiltrata = new ArrayList<>();
		
		
		for(PowerOutage p : this.getPowerOutagesList()) {
			if(p.getNerc_id() == nerc.getId()) {
				powerFiltrata.add(p);
			}
		}
		cerca(parziale, 0, numAnni, numOre, nerc);
		
		return soluzioneOttima;
	}
	
	
	private void cerca(LinkedList<PowerOutage> parziale, int livello, int numAnni, int numOre, Nerc nerc){
		//livello per noi è il numero di anni inseriti nella parziale
		
		// caso terminale
		//livello == anno
		if(livello == numAnni ) {
		 personeColpite = this.calcolaPersone(parziale);
			
			if(parziale == null || personeColpite > this.calcolaPersone(soluzioneOttima)) {
				soluzioneOttima = new ArrayList<>(parziale);
			}
			return;
		}
		
		else {
			for(PowerOutage p : this.powerFiltrata) {
				if(this.soluzioneAmmissibile(p, parziale, numAnni, numOre) == true) {
					parziale.add(p);
					cerca(parziale, livello+1, numAnni, numOre, nerc);
					parziale.remove(p);
				}
			}
		}
		
			
			
	}

	private int calcolaPersone(List<PowerOutage> parziale) {
	int contaPersone = 0;
		for(PowerOutage p : parziale) {
			
			contaPersone += p.getCustomers_affected();
			
		}
		return contaPersone;
	}

	private boolean soluzioneAmmissibile(PowerOutage daInserire, LinkedList<PowerOutage> parziale, int numAnni, int numOre) {
		// TODO Auto-generated method stub
		
		
		if(parziale.size()==0) { //qualsiasi prima aggiunta va bene
			return true;
		}
			//salvare da qualche parte numAnni x capire se superi numAnni
	
		if(parziale.getLast().getAnno1() != daInserire.getAnno1()) {
			contaAnni++;
		}
			
		for(PowerOutage pp : parziale) {
			contaOre += pp.getMinutiBlackOut()/60;
		}
			
		if(contaAnni>numAnni || contaOre > numOre) {
			return false;
		}
		
		
		return true;
	}
	
	}
