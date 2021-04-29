package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class PowerOutage { // implements Comparable <PowerOutage>
	int id;
	int event_type_id;
	int tag_id;
	int area_id;
	int nerc_id;
	int responsible_id;
	int customers_affected;
	LocalDateTime eventBegan;
	LocalDateTime eventFinished;
	//LocalTime eventHourBegan;
	//LocalTime eventHourFinished;
	int demand_loss;
	private int year;
	private long duration;
	


	
	
public PowerOutage(int id, int event_type_id, int tag_id, int area_id, int nerc_id, int responsible_id,
			int customers_affected, LocalDateTime eventBegan, LocalDateTime eventFinished, int demand_loss) {
		super();
		this.id = id;
		this.event_type_id = event_type_id;
		this.tag_id = tag_id;
		this.area_id = area_id;
		this.nerc_id = nerc_id;
		this.responsible_id = responsible_id;
		this.customers_affected = customers_affected;
		this.eventBegan = eventBegan;
		this.eventFinished = eventFinished;
		LocalDateTime temp = LocalDateTime.from(eventBegan);
		this.duration =  temp.until(eventFinished, ChronoUnit.HOURS); 
		//this.eventHourBegan = eventHourBegan;
		//this.eventHourFinished = eventHourFinished;
		this.year = this.eventBegan.getYear();
		this.demand_loss = demand_loss;
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








public LocalDateTime getEventBegan() {
	return eventBegan;
}





public void setEventBegan(LocalDateTime eventBegan) {
	this.eventBegan = eventBegan;
}





public LocalDateTime getEventFinished() {
	return eventFinished;
}





public void setEventFinished(LocalDateTime eventFinished) {
	this.eventFinished = eventFinished;
}





public int getDemand_loss() {
	return demand_loss;
}



public void setDemand_loss(int demand_loss) {
	this.demand_loss = demand_loss;
}

public int calcolaOre() {
	long f = this.eventBegan.toEpochSecond(ZoneOffset.UTC);
	long b = this.eventFinished.toEpochSecond(ZoneOffset.UTC);
	int diff = (int) (f - b);
	return diff/3600;
}


public int getOreTotali() {
	return calcolaOre();
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
	

	
	public String toString() {
		return this.year + " " + eventBegan + " " + eventFinished + " " + duration +" " + customers_affected+"\n";
	}





	public int getYear() {
		return year;
	}





	public void setYear(int year) {
		this.year = year;
	}





	public long getDuration() {
		return duration;
	}





	public void setDuration(long duration) {
		this.duration = duration;
	}





	/*@Override
	public int compareTo(PowerOutage o) {
		// TODO Auto-generated method stub
		return this.getEventBegan().compareTo(o.getEventBegan());
	}*/
	
}
