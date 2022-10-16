package no.hvl.dat100ptc.oppgave5;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static final int MARGIN = 50;  // margin on the sides 
	
	private static int MAXBARHEIGHT = 500; // assume no height above 500 meters
	
	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		GPSComputer gpscomputer =  new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {

		int gain = Integer.parseInt(getText("Oppgi ønsket hastighet på avlesning"));
	
		int x = MARGIN,y;

		// TODO - START
		setColor(0, 0, 255);
		setFont("Courier", 10);
		
		int maxHeight = 0;
		
		for (int i = 0; i < gpspoints.length; i++) {
			
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
			drawLine(x,ybase,x,ybase-height);
			x += 3;
			
			pause((int)timechange*1000/gain);
		}
		
		for (int i = 0; i < maxHeight; i++) {
			if (i%10 == 0 && i >=0) {
				drawString(String.valueOf(i)+"m",MARGIN-40, ybase-i);
			}
		}
		
	}

}
