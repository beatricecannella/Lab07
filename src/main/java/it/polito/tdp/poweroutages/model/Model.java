package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	
	List<PowerOutage> soluzioneOttima;
	int totOre;
	int personeColpite;
	
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
		personeColpite = 0;
		totOre = 0;
		List<PowerOutage> parziale = new ArrayList<PowerOutage>();
		powerFiltrata = new ArrayList<>();
		
		
		for(PowerOutage p : this.getPowerOutagesList()) {
			if(p.getNerc_id() == nerc.getId()) {
				powerFiltrata.add(p);
			}
		}
	//	Collections.sort(powerFiltrata);
		cerca(parziale, numAnni, numOre);
		
		return soluzioneOttima;
	}
	
	
	private void cerca(List<PowerOutage> parziale, int numAnni, int numOre){
	
		// caso terminale	
	
			
			if(this.calcolaPersone(parziale) > personeColpite) {
				personeColpite = this.calcolaPersone(parziale);
				totOre = this.sommaOre(parziale);
				soluzioneOttima = new ArrayList<>(parziale);
				
			}

	
			for(PowerOutage p : this.powerFiltrata) {
				if(!parziale.contains(p)) {
					parziale.add(p);
				if(this.soluzioneAmmissibile(parziale, numAnni, numOre) == true) {
					cerca(parziale, numAnni, numOre);
						}
				 parziale.remove(p);
				}
			}	
		}


	private boolean soluzioneAmmissibile(List<PowerOutage> parziale, int numAnni, int numOre) {
		
		
		if(parziale.get(parziale.size()-1).getYear() - parziale.get(0).getYear() +1 > numAnni) {
			return false;
			}
		
		if(this.sommaOre(parziale) > numOre) {
			return false;
			}
		
		return true;
	
	}
	
	public int sommaOre(List<PowerOutage> parziale) {
		int contaOre = 0;
		for(PowerOutage pp : parziale) {
				contaOre += pp.getDuration();
			}
		return contaOre;
	}
	
	
	public int calcolaPersone(List<PowerOutage> parziale) {
	int contaPersone = 0;
	
	if(parziale != null) {
		for(PowerOutage p : parziale) {
			contaPersone += p.getCustomers_affected();
		}
		}
	return contaPersone;
	}

	public int getPersoneColpite() {
		return personeColpite;
	}

	public void setPersoneColpite(int personeColpite) {
		this.personeColpite = personeColpite;
	}

	public int getTotOre() {
		return totOre;
	}

	public void setTotOre(int totOre) {
		this.totOre = totOre;
	}
	
	
	
	
	
	}
