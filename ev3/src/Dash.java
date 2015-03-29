import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;


public class Dash extends Thread{
	EV3LargeRegulatedMotor dash = new EV3LargeRegulatedMotor(MotorPort.C);
	public void run(){
		while(true){
			dash.forward();
		}
	}
}
