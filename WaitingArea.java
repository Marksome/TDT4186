import java.util.LinkedList;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {
 	private int waitingAreaCapacity;
 	private LinkedList<Customer> queue;

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
        // TODO Implement required functionality
    	// SushiBar.customerCounter.increment(); // SyncronizedInteger increments customerCounter
    	queue.add(customer);
		// notify(); // notify consumer threads.
    	SushiBar.write("Customer" + customer.getCustomerID() + " is now waiting.");
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
		// SushiBar.customerCounter.decrement(); // SyncronizedInteger decrements customerCounter
        return queue.poll();
    }
    
    public boolean roomInQueue() {
    	return queue == null || waitingAreaCapacity > queue.size() ? true : false; // Checks if there is room in queue
    }
    
    public boolean emptyQueue() {
    	return queue.size() == 0 ? true : false; // Checks if the queue is empty
    }

    // Add more methods as you see fit
}
