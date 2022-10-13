package no.hvl.dat100ptc.oppgave3;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

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

		double min;

		// TODO - START

		min = da[0];

		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] latitudes = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		return latitudes;
		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] longitude = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			longitude[i] = gpspoints[i].getLongitude();
		}
		return longitude;
		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		double latdelta, longdelta, a, c;
		// TODO - START
		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		longitude1 = Math.toRadians(gpspoint1.getLongitude());

		latitude2 = Math.toRadians(gpspoint2.getLatitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());

		latdelta = latitude2 - latitude1;
		longdelta = longitude2 - longitude1;
		a = Math.pow(Math.sin(latdelta / 2), 2)
				+ Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(longdelta / 2), 2);
		c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		d = R * c;
		return d;
		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		speed = distance(gpspoint1, gpspoint2) / secs;
		return speed * 3.6;

		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START
		int hr = secs / 3600;
		int min = (secs - hr * 3600) / 60;
		int sec = (secs - hr * 3600) - min * 60;
		timestr = String.format("%02d:%02d:%02d", hr, min, sec);
		timestr = String.format("%10s", timestr);
		return timestr;
		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START
		str = String.format("%.02f", d);
		str = String.format("%10s", str);
		return str;
		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}
}
