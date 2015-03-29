import java.util.ArrayList;

import org.jfree.util.WaitingImageObserver;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.port.TachoMotorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;


public class KingCobra {
	public static void main(String[] args) {
//		System.out.println("hoge");
//		EV3TouchSensor ev3TouchSensor = new EV3TouchSensor(SensorPort.S4);
//		SensorMode touchMode = ev3TouchSensor.getTouchMode();
//		float[] sample = new float[touchMode.sampleSize()];
//		touchMode.fetchSample(sample, 0);
//		System.out.println(sample[0]);
//		
//		LCD.drawString(String.valueOf(sample[0]),0,4);
//		Delay.msDelay(5000);
		
		 if(isPressed(SensorPort.S4)){
			 goEV3(MotorPort.C);
			 LCD.drawString("dash",0,4);
			 Delay.msDelay(5000);
		 }else{
			 LCD.drawString("stop",0,4);
			 Delay.msDelay(5000);
		 }
	}
	
	private static boolean isPressed(Port port){
		EV3TouchSensor ev3TouchSensor = new EV3TouchSensor(port);
		SensorMode touchMode = ev3TouchSensor.getTouchMode();
		float[] sample = new float[touchMode.sampleSize()];
		touchMode.fetchSample(sample, 0);
		ev3TouchSensor.close();
		return 1.0F == sample[0] ? true:false;
	}
	
	private static void goEV3(Port motorPort){
		EV3LargeRegulatedMotor ev3LargeRegulatedMotor = new EV3LargeRegulatedMotor(motorPort);
		
		ev3LargeRegulatedMotor.forward();
		long currentTimeMillis = System.currentTimeMillis();
		while (true) {
			long currentTimeMillis2 = System.currentTimeMillis();
			long result = currentTimeMillis2 - currentTimeMillis;
			if(result > 5000){
				break;
			}
		}
		ev3LargeRegulatedMotor.close();
	}
}