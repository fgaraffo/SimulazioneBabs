package it.polito.tdp.babs.model;

public class CountResult implements Comparable<CountResult> {
	
	private Station station;
	private int numArrivals;
	private int numDepartures;
	
	public CountResult(Station station, int numArrivals, int numDepartures) {
		this.station = station;
		this.numArrivals = numArrivals;
		this.numDepartures = numDepartures;
	}
	
	@Override
	public String toString() {
		return String.format("%-50s %4d %4d\n", station.getName(), numArrivals, numDepartures);
	}
	
	@Override
	public int compareTo(CountResult o) {
		return Double.compare(station.getLat(), o.station.getLat());	
	}
}
