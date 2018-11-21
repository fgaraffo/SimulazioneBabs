package it.polito.tdp.babs.model;

import java.util.HashMap;
import java.util.Map;

public class StationIdMap {

	private Map <Integer, Station> map = new HashMap();
	
	public Station get(int stationID) {
		return map.get(stationID);
	}
	
	public Station get(Station station) {
		Station old = map.get(station.getStationID());
		if (old!=null)
			return old;
		else
			map.put(station.getStationID(), station);
			return station;
	}
	
	// qui non metti il metodo put perchè in questo caso non verrebbe usato
	
}
