/*
 * $Id: RobotMain.java 325 2010-05-19 01:53:12Z mitsuki $
 *
 * Copyright 2009-2010 Fujitsu Limited.
 * FUJITSU CONFIDENTIAL.
 * 
 * http://54.65.61.46/ServiceSample/contentsupload
 */
package rsnp.sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.BrickFinder;
import lejos.hardware.BrickInfo;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.internal.ev3.EV3LCDManager;
import lejos.internal.ev3.EV3LCDManager.LCDLayer;
import lejos.utility.Delay;

import org.robotservices.v02.IAsyncCallBack;
import org.robotservices.v02.exception.RSiException;
import org.robotservices.v02.profile.common.Ret_value;
import org.robotservices.v02.profile.invoker.IBasic_profile;
import org.robotservices.v02.profile.invoker.IContents_profile;
import org.robotservices.v02.profile.invoker.IMultimedia_profile;
import org.robotservices.v02.profile.invoker.InvokerProfileFactory;
import org.robotservices.v02.util.ConnectionInfo;

import com.fujitsu.rsi.helper.BasicProfileHelper;
import com.fujitsu.rsi.helper.ContentsProfileHelper;
import com.fujitsu.rsi.util.RESULT;

public class RobotMain {

	//private static String epn = "http://172.20.10.3:8080/ServiceSample/services";
	private static String epn = "http://54.65.61.46:8080/ServiceSample/services";
	private static String robot_id = "aaaa";
	private static String password = "bbbb";
	private static int port = 9001;
	//private static final EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S4);


	public static String getRobotId() {
		return robot_id;
	}

	public static String getRobotName() throws IOException{
		String robot_name = null;
		BrickInfo[] bricks = BrickFinder.discover();
		for(BrickInfo info: bricks) {
			robot_name = info.getName();
		}
		return robot_name;
	}

	public static String getId() throws IOException{
		String full = getRobotName();
		int index = full.indexOf("-");
		return full.substring(0, index);
	}

	public static String getPassword() throws IOException{
		String full = getRobotName();
		int index = full.indexOf("-");
		return full.substring(index+1);
	}

	public static void main(String[] args) throws IOException {
		for (LCDLayer layer : EV3LCDManager.getLocalLCDManager().getLayers()) {
			if (layer.getName() != "LCD") {
				layer.setVisible(false);
			}
		}

		if (args.length >= 3) {
			robot_id = args[0];
			password = args[1];
			port = Integer.parseInt(args[2]);
		}

		ServerSocket serversock = new ServerSocket(port);
		Socket sock = null;

		ConnectionInfo connectioninfo = new ConnectionInfo();
		connectioninfo.set_endpointname(epn);
		//		connectioninfo.set_proxy("localhost:8000");
		InvokerProfileFactory factory = null;
		try {
			for (LCDLayer layer : EV3LCDManager.getLocalLCDManager().getLayers()) {
				if (layer.getName() != "LCD") {
					layer.setVisible(false);
				}
			}
			// get factory
			factory = InvokerProfileFactory.newInstance(connectioninfo);
			// connect
			factory.connect();

			// communicate RSNP
			IBasic_profile bp = factory.getBasic_profile();
			Ret_value ret = bp.open(getId(), getPassword());
			BasicProfileHelper helper = new BasicProfileHelper(ret);

			for (LCDLayer layer : EV3LCDManager.getLocalLCDManager().getLayers()) {
				if (layer.getName() != "LCD") {
					layer.setVisible(false);
				}
			}


			if (helper.getResult() == RESULT.SUCCESS.getResult()) {
				long conv_id = helper.getConv_id();
				//LCD.drawString("conv_id = " + conv_id, 0, 0);
				//System.out.println("Certified success　conv_id:" + conv_id);

				// Contains profile(invoker)
				IContents_profile cp = factory.getContents_profile();
				IAsyncCallBack callback = new IAsyncCallBack() {
					JapanCobra japan = new JapanCobra();
					//ThreadTest test = new ThreadTest();
					//JapanCobra japan = new JapanCobra();
					//BruneiTest brunei = new BruneiTest();
					//NewzeaTest newzea = new NewzeaTest();
					public void doEvent(Ret_value ret, boolean isLast) {
						ContentsProfileHelper helper = new ContentsProfileHelper(ret);
						String message = helper.getDetail();
						System.out.println("get a message");
						LCD.getDisplay();
						LCD.clearDisplay();
						if(message.equals(" ")){
							LCD.drawString("Mission complete!", 0, 3);
							//Delay.msDelay(7000);
							//LCD.clearDisplay();
						} else if(message.equals("Ks9ga2Ni")){
							japan.start();
						} else if(message.equals("WfPgDjGL")){
							//newzea.start();
						} else if(message.equals("ttgeCxQL")){
							//brunei.start();
						} else{
							//LCD.drawString(message, 0, 0);
							int ca = 0;
							int x = 0;
							int y = 0;
							Sound.beep();
							while(y <= message.length() % 16 && ca <= message.length()){
								LCD.drawChar(message.charAt(ca), x, y+3);
								x++;
								ca++; 
								if(ca % 17 == 0) x = 0;  
								if(ca % 17 == 0 && ca != 0) y++; 
							}
							//LCD.drawString(message, 5, 0);
							//System.out.println(message);
						}
					}

					public void doException(Exception e) {
					}
				};
				cp.distribute_contents(conv_id, "", -1, 0, callback);

				// multi media profile（invoker）
				IMultimedia_profile mp = factory.getMultimedia_profile();
				mp.start_profile(conv_id);

				// stop until if connect to socket
				Button.LEDPattern(1);
				LCD.clearDisplay();
				System.out.println("connected");
				LCD.drawString("Connected.", 2, 3);
				Delay.msDelay(2000);
				LCD.clearDisplay();
				//System.out.println("port");
				sock = serversock.accept();

				// clean up
				mp.end_profile(conv_id);
				cp.stop_distribute_contents(conv_id, 0);
				bp.close(conv_id);
			} else {
				LCD.drawString("Certified faild　" + helper.getDetail(), 0, 0);
			}

		} catch (RSiException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (factory != null) {
				try {
					// disconnection
					factory.disconnect();
				} catch (RSiException e) {
					//e.printStackTrace();
				}
			}
			if (sock != null) {
				sock.close();
			}
			serversock.close();
		}
	}
}
