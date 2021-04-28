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
		List<PowerOutage> parziale = new ArrayList<PowerOutage>();
		powerFiltrata = new ArrayList<>();
		
		
		for(PowerOutage p : this.getPowerOutagesList()) {
			if(p.getNerc_id() == nerc.getId()) {
				powerFiltrata.add(p);
			}
		}
		
		
	//	System.out.println(powerFiltrata);
		cerca(parziale, 0, numAnni, numOre);
		
		return soluzioneOttima;
	}
	
	
	private void cerca(List<PowerOutage> parziale, int livello, int numAnni, int numOre){
		//livello per noi è il numero di anni inseriti nella parziale
		
		// caso terminale
		
		if(livello == powerFiltrata.size()) {
			return; //non ci sono piu powerOuages da aggiungere
			}
		
		/*if(this.contaOre(parziale)>numOre) {
			return;
		}else {*/
		 personeColpite = this.calcolaPersone(parziale);
			
			if(parziale == null || personeColpite > this.calcolaPersone(soluzioneOttima)) {
				soluzioneOttima = new ArrayList<>(parziale);
				
			
			}
	//	}
	
			for(PowerOutage p : this.powerFiltrata) {
				parziale.add(p);
				if(this.soluzioneAmmissibile( parziale, numAnni, numOre) == true) {
					
					cerca(parziale, livello+1, numAnni, numOre);
					parziale.remove(p);
				}
			
		
		
			}
			
	}

	private int calcolaPersone(List<PowerOutage> parziale) {
	int contaPersone = 0;
	
	if(parziale != null) {
		for(PowerOutage p : parziale) {
			contaPersone += p.getCustomers_affected();
		}
		}
		return contaPersone;
	}
	

	private boolean soluzioneAmmissibile(List<PowerOutage> parziale, int numAnni, int numOre) {
		// TODO Auto-generated method stub
		
		
		if(parziale.size()==0) { //qualsiasi prima aggiunta va bene
			return true;
		}
		
		
	
		
		for(PowerOutage pp : parziale) {
			
			contaOre += pp.calcolaOre();
		}
		
		if(parziale.get(parziale.size()-1).getEventBegan().getYear() - parziale.get(0).getEventBegan().getYear()> numAnni) {
			
			return false;
		}
			
		if(contaOre > numOre) {
			return false;
		}
		return true;
	}
	
	
	}
