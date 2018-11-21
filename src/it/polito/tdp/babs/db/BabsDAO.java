package it.polito.tdp.babs.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.babs.model.Station;
import it.polito.tdp.babs.model.Trip;

public class BabsDAO {

	public List<Station> getAllStations() {
		List<Station> result = new ArrayList<Station>();
		Connection conn = ConnectDB.getConnection();
		
		String sql = "SELECT * FROM station";

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Station station = new Station(rs.getInt("station_id"),
										rs.getString("name"),
										rs.getDouble("lat"),
										rs.getDouble("long"),
										rs.getInt("dockcount"));
				result.add(station);
			}
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error in database query", e);
		}

		return result;
	}

	public List<Trip> getAllTrips() {
		List<Trip> result = new LinkedList<Trip>();
		Connection conn = ConnectDB.getConnection();

		String sql = "SELECT * FROM trip";

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Trip trip = new Trip(rs.getInt("tripid"), 
									rs.getInt("duration"),
									rs.getTimestamp("startdate").toLocalDateTime(),
									rs.getInt("startterminal"),
									rs.getTimestamp("enddate").toLocalDateTime(),
									rs.getInt("endterminal"));
				result.add(trip);
			}
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error in database query", e);
		}

		return result;
	}
}