import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;


public class keyTouch extends Thread{
	public void run(){
	    	EV3 ev3 = (EV3) BrickFinder.getLocal();
	        Keys keys = ev3.getKeys();
	        keys.waitForAnyPress();
	        System.exit(0);
	}
}
