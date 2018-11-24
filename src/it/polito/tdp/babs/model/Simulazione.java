package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Simulazione {

	private LocalDate date;
	private double k;
	private PriorityQueue <Event> pq;
	private Model model;
	private int PICKmiss = 0;
	private int DROPmiss = 0;
	private Map<Station, Integer> stationCount;
	
	private enum EventType{
		PICK, DROP;
	}
		
	public Simulazione(LocalDate date, double k, Model model) {
		this.date = date;
		this.k = k;
		this.model = model;
		pq = new PriorityQueue <> ();
		stationCount = new HashMap<>();
	}
	
	public void run() {
		List <Trip> trips = model.getTripsByDate(date);
				
		// TO DO
		// aggiungere gli eventi alla queue
		for (Trip t : trips) {
			pq.add(new Event(EventType.PICK, t.getStartDate(), t));
		}
		
		// Inizializzo il numero di bici per ogni stazione --> mappa
		
		for (Station s : model.getStations()) {
			stationCount.put(s, (int)(s.getDockCount()*k));
		}
		
		// processare gli eventi
		while(!pq.isEmpty()) {
			Event e = pq.poll();
			
			switch (e.type) {
				case PICK:
					Station station = model.getStationById(e.trip.getStartStationID());
					int count = stationCount.get(station);
					
					if (count>0) {
						// bici disponibile
						count--;
						stationCount.put(station, count);
						
						pq.add(new Event(EventType.DROP, e.trip.getEndDate(),e.trip));
					}
					else {
						// bici NON disponibile
						PICKmiss++;
					}
					break;
			
				case DROP:
					station = model.getStationById(e.trip.getEndStationID());
					count = stationCount.get(station);
					
					if (count<station.getDockCount()) {
						// ci sono ancora posti disponibili
						count++;
						stationCount.put(station, count);
					}
					else {
						DROPmiss++;
					}
					break;
			}
		}
	}
	
	public SimulationResult getResults () {
		return new SimulationResult(PICKmiss, DROPmiss);
	}
	
	private class Event implements Comparable <Event> {
		EventType type;
		LocalDateTime date;
		Trip trip;
		
		public Event(EventType type, LocalDateTime date, Trip trip) {
			this.type = type;
			this.date = date;
			this.trip = trip;
		}

		@Override
		public int compareTo(Event other) {
			return this.date.compareTo(other.date);
		}		
	}
}
