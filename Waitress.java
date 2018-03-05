/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {
	private WaitingArea waitingArea;
	private Customer customer;

    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */
    Waitress(WaitingArea waitingArea) {
    	this.waitingArea = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
    	while (SushiBar.isOpen || !WaitingArea.emptyQueue()) {
    		customer = waitingArea.next(); // Fetch the next customer
    		SushiBar.write(Thread.currentThread().getName() + ": Customer" + customer.getCustomerID() + " is now fetched.");
    		try {
				Thread.sleep(SushiBar.waitressWait); // Makes the waitress sleep a set time before taking the customer's order
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		customer.order(); // Waitress is now taking the customer's order
    	}
    }
}