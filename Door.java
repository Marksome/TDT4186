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
    	while (SushiBar.isOpen) {
    		if (waitingArea.roomInQueue()) { // If there is room for a new customer.
    			SushiBar.customerCounter.increment(); // SyncronizedInteger increments customerCounter
    			customer = new Customer();	// Creates a new customer
    	    	SushiBar.write(Thread.currentThread().getName() + ": Customer" + customer.getCustomerID() + " is now created.");
    	    	waitingArea.enter(customer); // Sends the customer to the waiting area
    	    	try {
					Thread.sleep(SushiBar.doorWait); // Makes the door sleep for set time
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else {

    		}	    			
    	}
    }

    // Add more methods as you see fit
}
