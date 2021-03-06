/**
 * @author Gentian Gashi | 14/10/2020 | Total Lines: 570
 *
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	 
    // Global Variables
    static Shop event = new Shop();
    static volatile BlockingQueue<Customer> sharedQ = new LinkedBlockingQueue<Customer>(); 

    // Queue Information
    static int numOfOpenTills=0;
    static int maxQueueSize=50;

    // Simulation Clock
    static int simulationClock=10000;
    
    // Poison Pill
    public static Customer POISON_PILL = new Customer(-1, null);

	// Main Method
	public static void main(String[] args) throws InterruptedException, IOException {		 
		Shop shop = new Shop();
		Producer producer = new Producer(sharedQ);
		Consumer consumer = new Consumer(shop,sharedQ);	
   
		// (Duplicate Stream) Output in console && writes to file
		FileOutputStream file = new FileOutputStream("output.txt");
		OutputPrintStream output = new OutputPrintStream(file, System.out);
		System.setOut(output);
        
		System.out.println("=============== Simulation Started ===============");
		producer.start();
		consumer.start();
		try{Thread.sleep(simulationClock);
		producer.interrupt();
		consumer.interrupt();
		Thread.sleep(1);}
		catch (InterruptedException e) {e.printStackTrace();} 
		System.out.println("=============== Simulation Stopped ===============");
	}	
}