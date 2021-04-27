package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	
	public List<PowerOutage> getPowerOutagesList() {

		String sql = "SELECT id, event_type_id, tag_id, area_id, nerc_id, responsible_id, customers_affected, "
				+ "YEAR(date_event_began) AS annoI, MONTH(date_event_began) AS meseI, DAY(date_event_began) AS giorniI, HOUR(date_event_began) AS oreI, "
				+ "MINUTE(date_event_began) AS minI, YEAR(date_event_finished) AS annoF, MONTH(date_event_finished) AS meseF, "
				+ "DAY(date_event_finished) AS giorniF, HOUR(date_event_finished) AS oreF, "
				+ "MINUTE(date_event_finished) AS minF, demand_loss "
				+ "FROM poweroutages";
		List<PowerOutage> powerOutagesList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				int id = res.getInt("id");
				int event_type_id = res.getInt("event_type_id");
				int tag_id = res.getInt("id");
				int area_id = res.getInt("area_id");
				int nerc_id = res.getInt("nerc_id");
				int responsible_id = res.getInt("responsible_id");
				int customers_affected = res.getInt("customers_affected");
				int meseI = res.getInt("meseI");
				int giorniI = res.getInt("giorniI");
				int oreI = res.getInt("oreI");
				int minI = res.getInt("minI");
				int meseF = res.getInt("meseF");
				int giorniF= res.getInt("giorniF");
				int oreF = res.getInt("oreF");
				int minF = res.getInt("minF");
				int annoI = res.getInt("annoI");
				int annoF = res.getInt("annoF");
				//LocalDate date_event_began= res.getDate("date_event_began").toLocalDate();
				//LocalDate date_event_finished = res.getDate("date_event_finished").toLocalDate();
				int demand_loss = res.getInt("demand_loss");
				
				
				PowerOutage p = new PowerOutage(id, event_type_id, tag_id, area_id, nerc_id, responsible_id, customers_affected, annoI, meseI, giorniI, oreI, minI, annoF, meseF, giorniF, oreF, minF, demand_loss);
				powerOutagesList.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powerOutagesList;
	}
	
	

}
