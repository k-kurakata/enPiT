package rsnp.sample;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class JapanCobra extends Thread{
	int centimeter;
	int sw = 0;
	boolean running = true;
	private static final EV3UltrasonicSensor ursensor = new EV3UltrasonicSensor(SensorPort.S4);
	EV3LargeRegulatedMotor bite = new EV3LargeRegulatedMotor(MotorPort.A);
	EV3MediumRegulatedMotor meandering = new EV3MediumRegulatedMotor(MotorPort.B);
	EV3LargeRegulatedMotor dash = new EV3LargeRegulatedMotor(MotorPort.C);
	
	public void run(){
		while(true){
			SampleProvider distanceMode = ursensor.getDistanceMode();
			float value[] = new float[distanceMode.sampleSize()];
			if(isPressed(SensorPort.S1) && sw == 0) {
				while(true){
					sw = 1;
					ursensor.enable();
					distanceMode.fetchSample(value, 0);
					centimeter = (int) (value[0] * 100);
					//under 30 -> bite
					//over 30 -> forward
					if(centimeter < 30) biteCobra();
					else forwardCobra();
					Delay.msDelay(100);
					if(isPressed(SensorPort.S1) && sw == 1){
						sw = 0;
						stopCobra();
						break;
					}
				}
			}
		}
	}

	//	private void executeIndividualOperation(int pattern, int centimeter) {	
	//		Button.LEDPattern(pattern);
	//		LCD.clearDisplay();
	//		LCD.drawString("Distance : " + centimeter, 0, 0);
	//	}

	boolean isPressed(Port port){
		EV3TouchSensor ev3TouchSensor = new EV3TouchSensor(port);
		SensorMode touchMode = ev3TouchSensor.getTouchMode();
		float[] sample = new float[touchMode.sampleSize()];
		touchMode.fetchSample(sample, 0);
		ev3TouchSensor.close();
		return 1.0F == sample[0] ? true:false;
	}

	void biteCobra(){
		dash.stop();
		meandering.stop();
		bite.setSpeed(500);
		bite.forward();
		Delay.msDelay(1500);
		bite.stop();
		Delay.msDelay(1000);
		bite.setSpeed(500);
		bite.backward();
		Delay.msDelay(1500);
		bite.stop();	
	}

	void forwardCobra(){
		dash.forward();
		meandering.rotate(40);
		Delay.msDelay(100);
		meandering.rotate(-40);	
	}

	void stopCobra(){
		bite.stop();
		dash.stop();
		meandering.stop();
		ursensor.disable();
	}
}