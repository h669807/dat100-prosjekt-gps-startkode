package no.hvl.dat100ptc.oppgave6;

import javax.swing.JOptionPane;

import easygraphics.*;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class CycleComputer extends EasyGraphics {

	private static int SPACE = 10;
	private static int MARGIN = 20;
	
	// FIXME: take into account number of measurements / gps points
	private static int ROUTEMAPXSIZE = 800; 
	private static int ROUTEMAPYSIZE = 400;
	private static int HEIGHTSIZE = 200;
	private static int TEXTWIDTH = 200;

	private GPSComputer gpscomp;
	private GPSPoint[] gpspoints;
	
	private int N = 0;

	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;

	public CycleComputer() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");

		gpscomp = new GPSComputer(filename);
		gpspoints = gpscomp.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		N = gpspoints.length; // number of gps points

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));

		xstep = xstep();
		ystep = ystep();

		makeWindow("Cycle Computer", 
				2 * MARGIN + ROUTEMAPXSIZE,
				2 * MARGIN + ROUTEMAPYSIZE + HEIGHTSIZE + SPACE);

		bikeRoute();

	}

	
	public void bikeRoute() {
		
				
		int ybase = 600;
		int ybase1 = 200;
		
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		int firstx = (int)(xstep()*(gpspoints[0].getLongitude()-minlon));
		int firsty = (int)(ystep()*(gpspoints[0].getLatitude()-minlat));
		
		setColor (0,0,255);
		int nodeId = fillCircle(MARGIN + firstx, ybase - firsty,5);
		
		int r = 3;
		int maxHeight = 0;
		int x = MARGIN,y;
		int tid = 0;
		int fart = 0;
	
		for (int i = 0; i < gpspoints.length-1; i++) {
			int fartid = drawString(GPSUtils.formatDouble(GPSUtils.speed(gpspoints[i], gpspoints[i+1])) + " km/t", 10 , 40);
			
			int time = gpspoints[i].getTime();
			setFont("Courier",18);
			
			int id = drawString(GPSUtils.formatTime(time), 10, 20);
			tid = id;
			fart = fartid;
			
			double timechange = 0;
			if (i < gpspoints.length - 1) {
				timechange = gpspoints[i+1].getTime()-gpspoints[i].getTime();
				
			}
			
			
			int height = 0;
			if (gpspoints[i].getElevation()>0) {
				height = (int)gpspoints[i].getElevation();
			}
			if (height > maxHeight) {
				maxHeight = height;
			}

			drawLine(x,ybase1,x,(int)(ybase1-height/2));
			if (3*N < ROUTEMAPXSIZE) {
				x += 3;
			} else {
				x += (int)ROUTEMAPXSIZE/N;
			}
			
			
			
						
			int elevationChange = (int)(gpspoints[i+1].getElevation()-gpspoints[i].getElevation());
			
			if(elevationChange > 0) {
				setColor(255,0,0);
			}else if (elevationChange == 0) {
				setColor(0,0,255);
			}else {
				setColor(0,255,0);
			}
			
			int x1 = (int)(xstep()*(gpspoints[i].getLongitude()-minlon));
			int y1 = (int)(ystep()*(gpspoints[i].getLatitude()-minlat));
			int x2 = (int)(xstep()*(gpspoints[i+1].getLongitude()-minlon));
			int y2 = (int)(ystep()*(gpspoints[i+1].getLatitude()-minlat));
			fillCircle(MARGIN+x1, ybase-y1,r);
			drawLine(MARGIN+x1, ybase-y1, MARGIN+x2, ybase-y2);
			
			
			int speed= 1;
			
			setColor(0,0,255); 
			setSpeed((int)speed);
			moveCircle(nodeId,MARGIN+x1, ybase-y1);
			
			setVisible(tid,false);	
			setVisible(fart,false);	
			}
	}

	
	public double xstep() {
		
		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = ROUTEMAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	public double ystep() {

		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = ROUTEMAPYSIZE / (Math.abs(maxlat - minlat)); 

		return ystep;	}

	
		
		
	}



