/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {
	private WaitingArea waitingArea;

    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */
    Waitress(WaitingArea waitingArea) {
        // TODO Implement required functionality
    	this.waitingArea = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
        // TODO Implement required functionality
    	while (SushiBar.isOpen || !waitingArea.emptyQueue()) {
    		if (waitingArea.emptyQueue()) {
    			try {
					Thread.currentThread().wait(); // Makes the waitress wait for a notification
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    		Customer customer = waitingArea.next(); // Fetch the next customer
    		SushiBar.write("Customer" + customer.getCustomerID() + " is now fetched.");
    		// notify(); // notify producer thread.
    		try {
				Thread.sleep(SushiBar.waitressWait); // Makes the waitress sleep a set time before taking the customer's order.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		customer.order(); // Waitress is now taking the customers order.
    	}
    }
}