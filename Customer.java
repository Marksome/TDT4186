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
    	this.customerID = SushiBar.customerCounter.get(); // Gets the customerCounter and sets it as customerID
    	this.random = new SecureRandom();
    }
    
    /**
     * Here you should implement the functionality for ordering food as described in the assignment.
     * @throws InterruptedException 
     */
    public synchronized void order() {
    	customerOrders = random.nextInt(SushiBar.maxOrder) + 1; // SecureRandom from 1 to maxOrder
    	customerBarOrders = random.nextInt(customerOrders) + 1; // SecureRandom from 1 to customerOrders
    	customerTakeawayOrders = customerOrders - customerBarOrders; // Sets the rest of the orders as takeaway
    	SushiBar.servedOrders.add(customerBarOrders);
    	SushiBar.takeawayOrders.add(customerTakeawayOrders);
    	SushiBar.totalOrders.add(customerOrders);
    	SushiBar.write(Thread.currentThread().getName() + ": Customer" + this.getCustomerID() + " is now eating.");
		try {
			Thread.sleep(SushiBar.customerWait); // Makes the customer sleep for a set time
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	SushiBar.write(Thread.currentThread().getName() + ": Customer" + this.getCustomerID() + " is now leaving.");
    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
    	return this.customerID; // Returns the customerID
    }

    // Add more methods as you see fit
}
