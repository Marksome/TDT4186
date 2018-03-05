import java.util.LinkedList;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {
 	private int waitingAreaCapacity;
 	private static LinkedList<Customer> queue;

    /**
     * Creates a new waiting area.
     * areaSize decides how many people can be waiting at the same time (how large the shared buffer is)
     *
     * @param size The maximum number of Customers that can be waiting.
     */
    public WaitingArea(int size) {
        // TODO Implement required functionality
    	this.waitingAreaCapacity = size;
    	queue = new LinkedList<Customer>();
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) {
    	while (!roomInQueue()) { // Checks to see if there is no more room in the queue
    		try {
				wait(); // Make the door wait until there is more room in the queue
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	queue.add(customer);
    	SushiBar.write(Thread.currentThread().getName() + ": Customer" + customer.getCustomerID() + " is now waiting.");
    	notify(); // Notifies the waitresses that there is a customer in queue
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
    	while (emptyQueue()) {
    		try {
    	    	SushiBar.write(Thread.currentThread().getName() + " is now waiting.");
    			wait(); // Makes the waitress wait for a customer to enter the queue
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	notify(); // Notifies the door that there is more room in the queue
        return queue.poll(); // Returns the first element of the LinkedList
    }

    // Add more methods as you see fit
    
    public boolean roomInQueue() {
    	return queue == null || waitingAreaCapacity > queue.size() ? true : false; // Checks if there is room in the queue
    }
    
    public static boolean emptyQueue() {
    	return queue.size() == 0 ? true : false; // Checks if the queue is empty
    }
}
