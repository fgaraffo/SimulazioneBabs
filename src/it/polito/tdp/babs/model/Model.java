package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.babs.db.BabsDAO;

public class Model {

	private BabsDAO bdao;
	private List <Station> stations;
	private StationIdMap stationIdMap;
	
	public Model () {
		this.bdao = new BabsDAO();
		this.stationIdMap = new StationIdMap();
		stations = bdao.getAllStations(stationIdMap);
	}
		
	public List <Trip> getTripsByDate (LocalDate date){
		return bdao.getAllTrips(date);
	}
	
	public List <CountResult> getTripCounts(LocalDate date) {
		if (getTripsByDate(date).size() == 0)
			return null;
		List <CountResult> results = new ArrayList <> ();
		for(Station station : stations) {
			CountResult cc = new CountResult(station, bdao.getDepartures(station, date), bdao.getArrivals(station, date));
			results.add(cc);
		}
		Collections.sort(results);
		return results;
	}

	public void simula(LocalDate date, Double k, Model model) {
		Simulazione sim = new Simulazione(date,k, model);
		sim.run();
		
	}

	
	
	
	
}
