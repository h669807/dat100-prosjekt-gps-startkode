package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START
		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
				
		}
		return distance;
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double elevationChange = gpspoints[i+1].getElevation()-gpspoints[i].getElevation();
			if (elevationChange > 0) {
				elevation += elevationChange;
			}
		}
		return elevation;
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		return gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
		//throw new UnsupportedOperationException(TODO.method());
		
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		double[] speeds = new double[gpspoints.length-1];
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
		
		}
		return speeds;
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}
	
	public double maxSpeed() {
		
		return GPSUtils.findMax(speeds());
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		return (totalDistance()/totalTime())*3.6;
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO - START
		if (speedmph < 10) {
			met = 4;
		} else if (speedmph >=10 && speedmph < 12) {
			met = 6;
		} else if (speedmph >=12 && speedmph < 14) {
			met = 8;
		} else if (speedmph >=14 && speedmph < 16) {
			met = 10;
		} else if (speedmph >=16 && speedmph < 20) {
			met = 12; 
		} else {
			met = 16; 
		}
		return met * weight * ((double) secs/3600);
				 
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
		for (int i = 0; i < gpspoints.length-1; i++) {
			 double speed = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
			 int time = gpspoints[i+1].getTime() - gpspoints[i].getTime();
			 totalkcal += kcal(weight, time, speed);
			 
				}
		return totalkcal;
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");
		System.out.printf("%-22s: %-10s%n", "Total time", GPSUtils.formatTime(totalTime()));
		System.out.printf("%-22s: %-3.2f %-5s%n", "Total distance", totalDistance()/1000, "km");
		System.out.printf("%-22s: %-3.2f %-5s%n", "Total elevation", totalElevation(), "m");
		System.out.printf("%-22s: %-3.2f %-5s%n", "Max speed", maxSpeed(), "km/t");
		System.out.printf("%-22s: %-3.2f %-5s%n", "Average speed", averageSpeed(), "km/t");
		System.out.printf("%-22s: %-3.2f %-5s%n", "Energy", totalKcal(WEIGHT), "Kcal");
		
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		System.out.println("==============================================");
	}
 
	public String[] getStatistics() {
		String [] output = new String [6];
		
		output[0]=String.format("%-22s: %-10s%n", "Total time", GPSUtils.formatTime(totalTime()));
		output[1]=String.format("%-22s: %-3.2f %-5s%n", "Total distance", totalDistance()/1000, "km");
		output[2]=String.format("%-22s: %-3.2f %-5s%n", "Total elevation", totalElevation(), "m");
		output[3]=String.format("%-22s: %-3.2f %-5s%n", "Max speed", maxSpeed(), "km/t");
		output[4]=String.format("%-22s: %-3.2f %-5s%n", "Average speed", averageSpeed(), "km/t");
		output[5]=String.format("%-22s: %-3.2f %-5s%n", "Energy", totalKcal(WEIGHT), "Kcal");
		
		return output;
		// TODO - START
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		
	}
	public double[] climbs() {
				
		double[] climbs = new double[gpspoints.length-1];
			for (int i = 0; i < gpspoints.length - 1; i++) {
			
			double elevationchange = gpspoints[i].getElevation()-gpspoints[i+1].getElevation();
			double distancechange = GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
			
			climbs[i] = (elevationchange/distancechange)*100;	
	}
			return climbs;
 }
 
 	public double maxclimb() {
				
				return GPSUtils.findMax(climbs());		
}
}