package no.hvl.dat100ptc.oppgave3;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {
		// Finner maxveri i liste.
		double max;
		max = da[0];
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		return max;
	}

	public static double findMin(double[] da) {
		// Finner minimumverdi i liste.
		double min;
		// TODO - START
		min = da[0];
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
		// TODO - SLUT     okkkkkkk ?
	}
	// Returnerer liste av Latitudes.
	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		// TODO - START
		// Oppretter ny liste med samme lengde som gpspoints.
		double[] latitudes = new double[gpspoints.length];
		// For hvert gpspoint i gpspoints henter vi latitude og setter inn i 
		// latitudes listen.
		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		return latitudes;
		// TODO - SLUTT
	}

	// Returnerer liste av Longitudes.
	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		// TODO - START
		// Oppretter ny liste med samme lengde som gpspoints.
		double[] longitude = new double[gpspoints.length];
		// For hvert gpspoint i gpspoints henter vi longitude og setter inn i 
		// longitudes listen.
		for (int i = 0; i < gpspoints.length; i++) {
			longitude[i] = gpspoints[i].getLongitude();
		}
		return longitude;
		// TODO - SLUTT
	}

	private static int R = 6371000; // jordens radius

	// Regner ut distanse mellom to gpspoint.
	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		// Deklarerer variabler vi trenger.
		double d;
		double latitude1, longitude1, latitude2, longitude2;
		double latdelta, longdelta, a, c;
		// TODO - START
		// Henter lat og long verdier fra gpspoint1.
		// Konverterer verdier til radianer.
		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		longitude1 = Math.toRadians(gpspoint1.getLongitude());

		// Henter lat og long verdier fra gpspoint2.
		// Konverterer verdier til radianer.
		latitude2 = Math.toRadians(gpspoint2.getLatitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());

		// Regner ut endring i lat og long verdi.
		latdelta = latitude2 - latitude1;
		longdelta = longitude2 - longitude1;
		
		// Much math.
		a = Math.pow(Math.sin(latdelta / 2), 2)
				+ Math.cos(latitude1) * Math.cos(latitude2) 
				* Math.pow(Math.sin(longdelta / 2), 2);
		c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		d = R * c;
		
		// Returnerer distansen.
		return d;
		// TODO - SLUTT
	}

	// Regner ut speed.
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START
		// Regner ut endring i tid.
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		// Regner ut distansen.
		speed = distance(gpspoint1, gpspoint2) / secs;
		// Kpnverterer til km/t og returnerer.
		return speed * 3.6;
		// TODO - SLUTT
	}

	// Formaterer tid i hh:mm:ss
	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START
		// Regner ut hele timer.
		int hr = secs / 3600;
		// Regner ut hele minutter.
		int min = (secs - hr * 3600) / 60;
		// Regner ut resterende sekunder.
		int sec = (secs - hr * 3600) - min * 60;
		// Konverterer til string og formaterer.
		timestr = String.format("%02d:%02d:%02d", hr, min, sec);
		// Setter den totale lengden på strengen til 10.
		timestr = String.format("%10s", timestr);
		return timestr;
		// TODO - SLUTT
	}

	private static int TEXTWIDTH = 10;

	// Formaterer double.
	public static String formatDouble(double d) {

		String str;

		// TODO - START
		// Gjør om til string og formaterer til to desimaler.
		str = String.format("%.02f", d);
		// Setter total lengde av string til 10.
		str = String.format("%10s", str);
		return str;
		// TODO - SLUTT
	}
}
