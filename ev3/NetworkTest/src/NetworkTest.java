
public class NetworkTest {
	public static void main(String args[]){
		Register register = new Register();
		Finish finish = new Finish();
		keyTouch key = new keyTouch();
		
		register.start();
		finish.start();
		key.start();
	}
}