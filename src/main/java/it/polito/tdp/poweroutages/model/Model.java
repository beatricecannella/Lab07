package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	
	List<PowerOutage> soluzioneOttima;
	int personeColpite = 0;
	
	
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}


	public List<PowerOutage> getPowerOutagesList() {
		return podao.getPowerOutagesList();
	}
	
	
	public List<PowerOutage> doRicorsione(int numAnni, int numOre){
		soluzioneOttima = null;
		List<PowerOutage> parziale = new ArrayList<PowerOutage>();
		
		
		cerca(parziale, 0, numAnni, numOre);
		
		return soluzioneOttima;
	}
	
	
	private void cerca(List<PowerOutage> parziale, int livello, int numAnni, int numOre){
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
			for(PowerOutage p : podao.getPowerOutagesList()) {
				
			}
		}
		
			
			
	}
	
	}
