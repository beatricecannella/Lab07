package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PowerOutage {
	int id;
	int event_type_id;
	int tag_id;
	int area_id;
	int nerc_id;
	int responsible_id;
	int customers_affected;
	int giorniI;
	int oreI;
	int minI;
	int giorniF;
	int oreF;
	int minF;
	int demand_loss;
	int meseI;
	int meseF;
	int anno1;
	int anno2;

	public PowerOutage(int id, int event_type_id, int tag_id, int area_id, int nerc_id, int responsible_id,
			int customers_affected, int anno1, int meseI, int giorniI, int oreI, int minI, int anno2, int meseF, int giorniF, int oreF, int minF, int demand_loss) {
		super();
		this.id = id;
		this.event_type_id = event_type_id;
		this.tag_id = tag_id;
		this.area_id = area_id;
		this.nerc_id = nerc_id;
		this.responsible_id = responsible_id;
		this.customers_affected = customers_affected;
		this.giorniI = giorniI;
		this.oreI = oreI;
		this.minI = minI;
		this.giorniF = giorniF;
		this.oreF = oreF;
		this.minF = minF;
		this.demand_loss = demand_loss;
		this.meseI = meseI;
		this.meseF = meseF;
		this.anno1=anno1;
		this.anno2=anno2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEvent_type_id() {
		return event_type_id;
	}

	public void setEvent_type_id(int event_type_id) {
		this.event_type_id = event_type_id;
	}

	public int getTag_id() {
		return tag_id;
	}

	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}

	public int getArea_id() {
		return area_id;
	}

	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}

	public int getNerc_id() {
		return nerc_id;
	}

	public void setNerc_id(int nerc_id) {
		this.nerc_id = nerc_id;
	}
	
	

	public int getMeseI() {
		return meseI;
	}

	public void setMeseI(int meseI) {
		this.meseI = meseI;
	}

	public int getMeseF() {
		return meseF;
	}

	public void setMeseF(int meseF) {
		this.meseF = meseF;
	}

	public int getAnno1() {
		return anno1;
	}

	public void setAnno1(int anno1) {
		this.anno1 = anno1;
	}

	public int getAnno2() {
		return anno2;
	}

	public void setAnno2(int anno2) {
		this.anno2 = anno2;
	}

	public int getResponsible_id() {
		return responsible_id;
	}

	public void setResponsible_id(int responsible_id) {
		this.responsible_id = responsible_id;
	}

	public int getCustomers_affected() {
		return customers_affected;
	}

	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}



	public int getGiorniI() {
		return giorniI;
	}

	public void setGiorniI(int giorniI) {
		this.giorniI = giorniI;
	}

	public int getOreI() {
		return oreI;
	}

	public void setOreI(int oreI) {
		this.oreI = oreI;
	}

	public int getMinI() {
		return minI;
	}

	public void setMinI(int minI) {
		this.minI = minI;
	}

	public int getGiorniF() {
		return giorniF;
	}

	public void setGiorniF(int giorniF) {
		this.giorniF = giorniF;
	}

	public int getOreF() {
		return oreF;
	}

	public void setOreF(int oreF) {
		this.oreF = oreF;
	}

	public int getMinF() {
		return minF;
	}

	public void setMinF(int minF) {
		this.minF = minF;
	}

	public int getDemand_loss() {
		return demand_loss;
	}

	public void setDemand_loss(int demand_loss) {
		this.demand_loss = demand_loss;
	}

public int calcolaDifferenzaInMinuti(int meseI, int giorniI, int oreI, int minI, int meseF, int giorniF, int oreF, int minF){
		int tot = 0;
		LocalDateTime t;
	
		if(meseI == meseF) {
			if(giorniI == giorniF) {
		//int giorni = (giorniF -giorniI);
		int ore = oreF - oreI;
		int min = ore*60+(minF - minI);
		tot =  ore+ min;
			}
			else {
				if(giorniI-giorniF == 1) { //sono 2 gg consecutivi
					int ore = (24-oreI) +oreF;
					int min = ore*60 + (60-minI); 
				}
				
				}
		}
		else {
			//considero tutti i mesi da 30 g
			int giorni = (30-giorniI) +(30-giorniF);
			int h = giorni*24 + (oreF-oreI);
			int m = h*60 + (minF-minI);
			tot = m;
		}
		return tot;
	
	}

public int getMinutiBlackOut(){
	return this.calcolaDifferenzaInMinuti(meseI, giorniI, oreI, minI, meseF, giorniF, oreF, minF);
}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
