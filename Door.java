/**
 * This class implements the Door component of the sushi bar assignment
 * The Door corresponds to the Producer in the producer/consumer problem
 */
public class Door implements Runnable {
	private WaitingArea waitingArea;
	private Customer customer;
	/**
     * Creates a new Door. Make sure to save the
     * @param waitingArea   The customer queue waiting for a seat
     */
    public Door(WaitingArea waitingArea) {
    	this.waitingArea = waitingArea;
    }

    /**
     * This method will run when the door thread is created (and started)
     * The method should create customers at random intervals and try to put them in the customerQueue
     */
    @Override
    public void run() {
        // TODO Implement required functionality    	
    	while (SushiBar.isOpen) { // Checks to see if the shop is open
    		SushiBar.customerCounter.increment(); // SyncronizedInteger increments customerCounter
			customer = new Customer();	// Creates a new customer
	    	SushiBar.write(Thread.currentThread().getName() + ": Customer" + customer.getCustomerID() + " is now created.");
	    	waitingArea.enter(customer); // Sends the customer to the waiting area
	    	try {
				Thread.sleep(SushiBar.doorWait); // Makes the door sleep for set time
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	waitingArea.close();
    	SushiBar.write("***** NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW. *****");
    	SushiBar.write("Total served orders " + SushiBar.servedOrders.get());
    	SushiBar.write("Total takeway orders " + SushiBar.takeawayOrders.get());
    	SushiBar.write("Total orders " + SushiBar.totalOrders.get());
    }

    // Add more methods as you see fit
}
