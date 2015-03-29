import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.lcd.LCD;


public class Register extends Thread{

	static int port = 10007; 
	public void run(){
		try {
			ServerSocket server = new ServerSocket(port);
			Socket sock = null;
			while(true) {
				try {
					sock = server.accept(); // クライアントからの接続を待つ

					BufferedReader in = new BufferedReader(
							new InputStreamReader(sock.getInputStream()));
					String s;
					while((s = in.readLine()) != null) { // 一行受信
						LCD.drawString(s, 0, 0);
					}
					sock.close(); // クライアントからの接続を切断
				} catch (IOException e) {
					System.err.println(e);
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
