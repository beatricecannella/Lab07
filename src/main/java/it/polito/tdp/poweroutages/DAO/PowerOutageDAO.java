package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

		String sql = "SELECT * "
				+ "FROM poweroutages "
				+ "ORDER BY date_event_began ASC";
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
				LocalDateTime date_event_began = res.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime date_event_finished = res.getTimestamp("date_event_finished").toLocalDateTime();
				//LocalDate date_event_began= res.getDate("date_event_began").toLocalDate();
				//LocalDate date_event_finished = res.getDate("date_event_finished").toLocalDate();
				//LocalTime hour_event_began= res.getTime("date_event_began").toLocalTime();
				//LocalTime hour_event_finished = res.getTime("date_event_finished").toLocalTime();
				int demand_loss = res.getInt("demand_loss");
				
				
				PowerOutage p = new PowerOutage(id, event_type_id, tag_id, area_id, nerc_id, responsible_id, customers_affected, date_event_began, date_event_finished, demand_loss);
				powerOutagesList.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powerOutagesList;
	}
	
	

}
