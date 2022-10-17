package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	// Objektvariabler.
	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {

		// TODO - START
		// Oppretter liste med lengde n
		this.gpspoints = new GPSPoint[n];
		this.antall = 0;
		// TODO - SLUTT
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	// Tar ett gpspunkt og setter inn i gpspoints listen.
	protected boolean insertGPS(GPSPoint gpspoint) {
		boolean inserted = false;
		// TODO - START
		// Skjekker om det er plass til nytt punkt i gpspoints.
		if (this.antall < this.gpspoints.length) {
			// Setter inserted til true om vi har plass til det nye punktet.
			inserted = true;
			// Settet punktet inn i gpspoints, med index antall.
			this.gpspoints[this.antall] = gpspoint;
			// Inkrementerer antall så neste punkt kommer på neste posisjon.
			antall++;
		}
		return inserted;
		// TODO - SLUTT
	}

	// Samme som InsertGPS men vi tar strings som argumenter i stedet for ett gpspoint.
	public boolean insert(String time, String latitude, String longitude, String elevation) {
		GPSPoint gpspoint;
		// TODO - START
		// Opprettet nytt gpspoint med verdiene vi blir gitt.
		gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
		// Setter gpspoint inn i gpspoints.
		if (insertGPS(gpspoint)) {
			// Returnerer true om dette er suksessfult.
			return true;
		}
		// Returnerer false om det ikke er suksessfult.
		return false;
		// TODO - SLUTT
	}

	public void print() {
		System.out.println("====== Konvertert GPS Data - START ======");
		// TODO - START
		// For hvert gpspoint i gpspoints.
		for (GPSPoint gpspoint : this.gpspoints) {
			// Printer vi ut strengversjonen av gpspoint.
			System.out.print(gpspoint.toString());
		}
		// TODO - SLUTT
		System.out.println("====== Konvertert GPS Data - SLUTT ======");
	}
}
