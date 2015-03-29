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
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


public class CobraMain {
	public static void main(String[] args) {
		EV3UltrasonicSensor ursensor = new EV3UltrasonicSensor(SensorPort.S4);
		SampleProvider distanceMode = ursensor.getDistanceMode();
		float value[] = new float[distanceMode.sampleSize()];

		Dash dash = new Dash();
		Bite bite = new Bite();
		while(true){
			distanceMode.fetchSample(value, 0);
			int centimeter = (int) (value[0] * 100);
			dash.start();
			LCD.drawInt(centimeter, 0, 3);
			if(centimeter < 30){
				bite.start();
			}
		}
	}










	//
	//		int old_sw = 0;
	//		int new_sw = 1;
	//		int i = 0;
	//		EV3LargeRegulatedMotor ev3LargeRegulatedMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	//		EV3LargeRegulatedMotor kamu = new EV3LargeRegulatedMotor(MotorPort.C);
	//		EV3MediumRegulatedMotor dash = new EV3MediumRegulatedMotor(MotorPort.A);
	//		EV3UltrasonicSensor ursensor = new EV3UltrasonicSensor(SensorPort.S3);
	//		SampleProvider distanceMode = ursensor.getDistanceMode();
	//		float value[] = new float[distanceMode.sampleSize()];
	//		//		System.out.println("hoge");
	//		//		EV3TouchSensor ev3TouchSensor = new EV3TouchSensor(SensorPort.S4);
	//		//		SensorMode touchMode = ev3TouchSensor.getTouchMode();
	//		//		float[] sample = new float[touchMode.sampleSize()];
	//		//		touchMode.fetchSample(sample, 0);
	//		//		System.out.println(sample[0]);
	//		//		
	//		//		LCD.drawString(String.valueOf(sample[0]),0,4);
	//		//		Delay.msDelay(5000);		
	//
	//		while(true){
	//			distanceMode.fetchSample(value, 0);
	//			int centimeter = (int) (value[0] * 100);
	//			LCD.drawInt(centimeter, 0, 3);
	//			if(isPressed(SensorPort.S4) && new_sw == 1){
	//				if(centimeter < 30){
	//					kamu.stop();
	//					dash.stop();
	//					kamu.forward();
	//					Delay.msDelay(1000);
	//					kamu.stop();
	//					Delay.msDelay(500);
	//				} else{
	//					kamu.backward();
	//					Delay.msDelay(1000);
	//					kamu.stop();
	//					Delay.msDelay(1000);
	//					ev3LargeRegulatedMotor.forward();
	//					dash.rotate(-50);
	//					Delay.msDelay(500);
	//					dash.rotate(90);;
	//					Delay.msDelay(500);
	//				}
	//				new_sw = 0;
	//				Delay.msDelay(500);
	//			}
	//			if(isPressed(SensorPort.S4) && new_sw == 0){
	//				kamu.stop();
	//				dash.stop();
	//				ev3LargeRegulatedMotor.stop();
	//				new_sw = 1;
	//				Delay.msDelay(500);
	//			}
	//		}
	//	}
	//
	//	private static boolean isPressed(Port port){
	//		EV3TouchSensor ev3TouchSensor = new EV3TouchSensor(port);
	//		SensorMode touchMode = ev3TouchSensor.getTouchMode();
	//		float[] sample = new float[touchMode.sampleSize()];
	//		touchMode.fetchSample(sample, 0);
	//		ev3TouchSensor.close();
	//		return 1.0F == sample[0] ? true:false;
	//	}
	//
	//	private static void goEV3(Port motorPort){
	//		EV3LargeRegulatedMotor ev3LargeRegulatedMotor = new EV3LargeRegulatedMotor(motorPort);
	//		ev3LargeRegulatedMotor.forward();
	//		//		long currentTimeMillis = System.currentTimeMillis();
	//		//		while (true) {
	//		//			long currentTimeMillis2 = System.currentTimeMillis();
	//		//			long result = currentTimeMillis2 - currentTimeMillis;
	//		//			if(result > 5000){
	//		//				break;
	//		//			}
	//		//		}
	//		//		ev3LargeRegulatedMotor.close();
	//	}
}