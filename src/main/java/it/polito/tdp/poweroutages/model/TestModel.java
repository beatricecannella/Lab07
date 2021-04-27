package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getNercList());
		
		for(Nerc n : model.getNercList()) {
			if(n.getValue().equals("MAAC")) {
			System.out.println(model.doRicorsione(4, 200, n));
		}}
	}

}
