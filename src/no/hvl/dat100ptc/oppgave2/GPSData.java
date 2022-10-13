package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {

		// TODO - START
		this.gpspoints = new GPSPoint[n];
		this.antall = 0;
		// throw new UnsupportedOperationException(TODO.construtor("GPSData"));

		// TODO - SLUTT
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		// TODO - START
		if (this.antall < this.gpspoints.length) {
			inserted = true;
			this.gpspoints[this.antall] = gpspoint;
			antall++;

		}
		return inserted;
		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		// TODO - START
		gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);

		if (insertGPS(gpspoint)) {
			return true;
		}
		return false;
		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		// TODO - START
		for (GPSPoint gpspoint : this.gpspoints) {
			System.out.print(gpspoint.toString());
		}
		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
