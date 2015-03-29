import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


public class Bite extends Thread{
	EV3LargeRegulatedMotor bite = new EV3LargeRegulatedMotor(MotorPort.A);

	public void run(){
		bite.rotate(10);
		Delay.msDelay(1000);
		bite.backward();
	}
}
