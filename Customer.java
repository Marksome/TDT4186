import java.security.SecureRandom;

/**
 * This class implements a customer, which is used for holding data and update the statistics
 *
 */
public class Customer {
	private int customerID;
	private int customerOrders;
	private int customerBarOrders;
	private int customerTakeawayOrders;
	private SecureRandom random;
    /**
     *  Creates a new Customer.
     *  Each customer should be given a unique ID
     */
    public Customer() {
        // TODO Implement required functionality
    	this.customerID = SushiBar.customerCounter.get(); // Gets the customerCounter and sets it as customerID
    	this.customerOrders = customerOrders;
    	this.customerBarOrders = customerBarOrders;
    	this.customerTakeawayOrders = customerTakeawayOrders;
    	this.random = new SecureRandom();
    }
    
    /**
     * Here you should implement the functionality for ordering food as described in the assignment.
     * @throws InterruptedException 
     */
    public synchronized void order(){
        // TODO Implement required functionality
    	customerOrders = random.nextInt(SushiBar.maxOrder) + 1; // SecureRandom from 1 to maxOrder
    	customerBarOrders = random.nextInt(customerOrders) + 1; // SecureRandom from 1 to customerOrders
    	customerTakeawayOrders = customerOrders - customerBarOrders; // Sets the rest of the orders as takeaway
    	SushiBar.servedOrders.add(customerBarOrders);
    	SushiBar.takeawayOrders.add(customerTakeawayOrders);
    	SushiBar.totalOrders.add(customerOrders);
    	SushiBar.write("Customer" + this.getCustomerID() + " is now eating.");
    	try {
    		Thread.sleep(SushiBar.customerWait)); // Makes the customer sleep for a set time
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	// Thread.currentThread().notify(); // Notify producer/consumer threads.
    	SushiBar.write("Customer" + this.getCustomerID() + " is now leaving.");
    	

//        totalOrders = new SynchronizedInteger(0);
//        servedOrders = new SynchronizedInteger(0);
//        takeawayOrders = new SynchronizedInteger(0);
    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
        // TODO Implement required functionality
    	return this.customerID;
    }

    // Add more methods as you see fit
}
