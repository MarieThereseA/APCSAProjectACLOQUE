/**
 * This class represents a bank account
 *
 * @author Marie-Therese Acloque
 */
public class Account {
    /** If the account is classified as a savings account */
    private boolean savingsAcc;

    /** If the account is classified as a checking account */
    private boolean checkingAcc;

    /** Balance of the account */
    private double balance;

    /** Owner of the account */
    private Customer owner;

    /** Name of the account */
    private String name;

    /**
     *
     * @param name Name of the account
     * @param owner Owner of the account
     */
    public Account(String name, Customer owner){
        this.name = name;
        this.owner = owner;
        if (name.indexOf("Savings") != -1){
            savingsAcc = true;
        }else {
            checkingAcc = true;
        }
    }

    // Setter + Getter Methods

    /**
     * Returns the account's balance
     * @return Balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns whether the account is a savings account
     * @return True or false
     */
    public boolean isSavingsAcc(){
        return savingsAcc;
    }

    /**
     * Returns whether the account is a checking account
     * @return True or false
     */
    public boolean isCheckingAcc(){
        return checkingAcc;
    }

    /**
     * Returns account's name
     * @return Name
     */
    public String getName(){
        return  name;
    }

    // Other methods

    /**
     * Subtracts the amount being withdrawn from the account's balance
     * @param amount Withdraw amount
     */
    public void withdraw(double amount){
        balance -= amount;
    }

    /**
     * Adds the amount being deposited to the account's balance
     * @param amount Deposit amount
     */
    public void deposit(double amount){
        balance += amount;
    }
}
