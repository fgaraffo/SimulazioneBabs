package it.polito.tdp.babs.model;

public class SimulationResult {

	private int PICKmiss;
	private int DROPmiss;
	
	public SimulationResult(int pICKmiss, int dROPmiss) {
		super();
		PICKmiss = pICKmiss;
		DROPmiss = dROPmiss;
	}

	public int getPICKmiss() {
		return PICKmiss;
	}

	public void setPICKmiss(int pICKmiss) {
		PICKmiss = pICKmiss;
	}

	public int getDROPmiss() {
		return DROPmiss;
	}

	public void setDROPmiss(int dROPmiss) {
		DROPmiss = dROPmiss;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SimulationResult [PICKmiss=");
		builder.append(PICKmiss);
		builder.append(", DROPmiss=");
		builder.append(DROPmiss);
		builder.append("]");
		return builder.toString();
	}	
	
}
