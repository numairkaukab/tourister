package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;



import Recommendation.CollaborativeFiltering;

public class Dispatcher {
	
	public static final String JAVABRIDGE_PORT="8087";
	  static final php.java.bridge.JavaBridgeRunner runner = 
	    php.java.bridge.JavaBridgeRunner.getInstance(JAVABRIDGE_PORT);
	

	public static void main(String[] args) throws InterruptedException {
		
       System.out.println("Dispatcher Running, Waiting for PHP");
		runner.waitFor();
		
		
	}
	
	public List<Long> recommend(){
		
		List<Long> returnArray = new ArrayList<Long>();
		CollaborativeFiltering cf = new CollaborativeFiltering();
		try {
			returnArray = cf.filter();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (TasteException e) {
			
			e.printStackTrace();
		}
		
		return returnArray;
		
		
	}
	
	

}
