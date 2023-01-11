/**
 * This class represents a customer
 *
 * @author Marie-Therese Acloque
 */
public class Customer {
    /** Customer's bank account's pin code */
    private int PIN;

    /** Customer's name */
    private String name;

    /**
     * @param name Customer's name
     * @param PIN Customer's bank account's pin code
     */
    public Customer(String name, int PIN){
        this.name = name;
        this. PIN = PIN;
    }

    //Getter + Setter Methods

    /**
     * Returns the pin of the customer's bank account
     *
     * @return The Customer's pin code
     */
    public int getPIN(){
        return PIN;
    }

    /**
     * Returns the customer's name
     *
     * @return Customer's name
     */
    public String getName(){
        return name;
    }

    /**
     * Updates the Customer's pin code
     *
     * @param newPIN new pin for customer's bank account
     */
    public void setPIN(int newPIN){
        PIN = newPIN;
    }

    // Other Methods

    public boolean checkPIN(int pin){
        if (pin == PIN){
            return true;
        }else {
            return false;
        }
    }


}
