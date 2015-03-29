package rsnp.sample;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class ThreadTest extends Thread{
	public void run(){
		while(true){
			for(int i = 0; i <10; i++){
				Button.LEDPattern(i);
				Delay.msDelay(100);
				if(i == 9){
					i = 0;
				}
			}
		}
	}
}
