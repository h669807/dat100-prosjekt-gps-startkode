package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		
		// TODO - START
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 

		return ystep;
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		int r = 3;
		
		for (int i = 0; i < gpspoints.length-1; i++) {
			setColor(0,0,255);
			int elevationChange = (int)(gpspoints[i+1].getElevation()-gpspoints[i].getElevation());
			
			if(elevationChange > 0) {
				setColor(0,255,0);
			}else if (elevationChange == 0) {
				setColor(0,0,255);
			}else {
				setColor(255,0,0);
			}
			
			int x1 = (int)(xstep()*(gpspoints[i].getLongitude()-minlon));
			int y1 = (int)(ystep()*(gpspoints[i].getLatitude()-minlat));
			int x2 = (int)(xstep()*(gpspoints[i+1].getLongitude()-minlon));
			int y2 = (int)(ystep()*(gpspoints[i+1].getLatitude()-minlat));
			fillCircle(MARGIN+x1, ybase-y1,r);
			drawLine(MARGIN+x1, ybase-y1, MARGIN+x2, ybase-y2);
		}
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		String[] stats = gpscomputer.getStatistics();
		for(int i = 0; i < stats.length; i++) {
			drawString(stats[i], 10, 20 + TEXTDISTANCE*i);
		}
		//hrow new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT;
	}

}
