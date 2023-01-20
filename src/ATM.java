/**
 * This class represents an ATM machine
 *
 * @author Marie-Therese Acloque
 */
import java.io.*;
import java.util.Scanner;
public class ATM {
    private Customer customer;
    private Account savings;
    private Account checking;
    private int transactionID = 999;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_WHITE_BACK = "\u001B[47m";
    public static final String ANSI_RED = "\\u001B[31m";
    public static final String ANSI_GREEN_BACK = "\\u001B[42m";

    Scanner scan = new Scanner(System.in);

    public ATM(){}

    //Helper Methods

    private void welcome(){
        // Welcome Screen
        System.out.println(ANSI_GREEN + "__$$$___ $$$$$$_ $$___$$_\n" +
                "_$$_$$__ __$$___ $$$_$$$_\n" +
                "$$___$$_ __$$___ $$$$$$$_\n" +
                "$$$$$$$_ __$$___ $$_$_$$_\n" +
                "$$___$$_ __$$___ $$___$$_\n" +
                "$$___$$_ __$$___ $$___$$_" + ANSI_RESET);
        System.out.println("Welcome to the " + ANSI_GREEN + "A" + ANSI_RESET + "utomated " + ANSI_GREEN + "T" + ANSI_RESET + "eller " + ANSI_GREEN +"M" + ANSI_RESET + "achine");

        System.out.print(ANSI_WHITE_BACK + "Loading" + ANSI_RESET);
        System.out.print(ANSI_WHITE_BACK + "." + ANSI_RESET);
        System.out.print(ANSI_WHITE_BACK + "." + ANSI_RESET);
        System.out.println(ANSI_WHITE_BACK + "." + ANSI_RESET);

        // Create account screen
        System.out.println(ANSI_GREEN + "Create An Account" + ANSI_RESET);
        System.out.println("Enter your name: ");
        String name = scan.nextLine();
        System.out.println("Create a 4 digit PIN: ");
        int PIN = scan.nextInt();
        scan.nextLine();

        //Create Customer object
        customer = new Customer(name,PIN);
        savings = new Account("Savings Account", customer);
        checking = new Account("Checking Account", customer);

        System.out.println(ANSI_GREEN_BACK + "Account created!" + ANSI_RESET);

        System.out.print(ANSI_WHITE_BACK + "Loading");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.println(ANSI_WHITE_BACK + ". " + ANSI_RESET);

    }
    private boolean atm(){
        //Tracker varaible
        boolean choice = true;
        //PIN input
        System.out.println(ANSI_GREEN + "Sign in" + ANSI_RESET);
        boolean incorrect = true;
        while (incorrect) {
            System.out.println("Please enter your 4 digit PIN: ");
            int pin = scan.nextInt();
            scan.nextLine();
            if (customer.checkPIN(pin)){
                incorrect = false;
            }else {
                System.out.println("Your PIN is incorrect, Please try again.");

                System.out.println(ANSI_GREEN + "Sign in" + ANSI_RESET);
            }
        }


        //Main Menu
        System.out.println("Welcome " + ANSI_GREEN + customer.getName() + ANSI_RESET +  "!");
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("Please make a selection: ");
        boolean validInput = false;
        int selection = 0;
        while (!validInput){
            System.out.println("1. Withdraw money\n" +
                    "2. Deposit money\n" +
                    "3. Transfer money between accounts\n" +
                    "4. Get account balances\n" +
                    "5. Change PIN\n" +
                    "6. Exit\n");
            selection = scan.nextInt();
            scan.nextLine();


            if (selection > 6 || selection < 1){
                System.out.println(ANSI_RED + "Invalid input" + ANSI_RESET + "; Please make a valid selection");
                System.out.println("Welcome " + ANSI_GREEN + customer.getName() + ANSI_RESET +  "!");
                System.out.println();
                System.out.println("Main Menu");
                System.out.println("Please make a selection: ");
            }else {
                validInput = true;
            }
        }

        // Selection processing
        if (selection == 1){
            withdraw();
        }else if (selection == 2){
            deposit();
        }else if (selection == 3){
            transfer();
        }else if (selection == 4){
            getBalances();
        }else if (selection == 5){
            changePIN();
        }else {
            choice = false;
        }
        return choice;
    }

    private void withdraw(){
        System.out.print(ANSI_WHITE_BACK + "Loading");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.println(ANSI_WHITE_BACK + "." + ANSI_RESET);

        System.out.println(ANSI_GREEN + "Withdraw Money" + ANSI_RESET);
        System.out.println();

        //Choosing account
        System.out.println("Which account would you like to withdraw from? \n1. Savings \n2. Checking");
        int account = scan.nextInt();
        scan.nextLine();

        if (account == 1){
            System.out.println(ANSI_GREEN + savings.getName() + ANSI_RESET);
        }else {
            System.out.println(ANSI_GREEN + checking.getName() + ANSI_RESET);
        }
        System.out.println(ANSI_RED + "Machine can only dispense $20 and $5 bills" + ANSI_RESET);

        //Checking for valid input
        boolean validInput = false;
        int amount = 0;
        while (!validInput){
            System.out.println("How much would you like to withdraw?: ");
            amount = scan.nextInt();
            scan.nextLine();
            if (amount % 5 != 0 && amount != 0){
                System.out.println("Sorry :(, machine cannot dispense that amount; Please enter a valid amount");

                System.out.println(ANSI_GREEN + "Withdraw Money" + ANSI_RESET);
                System.out.println();
                System.out.println(ANSI_RED + "Machine can only dispense $20 and $5 bills" + ANSI_RESET);
            }else {
                if (account == 1){
                    if (savings.getBalance() < amount){
                        System.out.println(ANSI_RED + "Insufficient funds" + ANSI_RESET + "; Please choose a smaller amount");

                        System.out.print(ANSI_GREEN + "Withdraw Money :" + ANSI_RESET);
                        if (account == 1){
                            System.out.println(ANSI_GREEN + savings.getName() + ANSI_RESET);
                        }else {
                            System.out.println(ANSI_GREEN + checking.getName() + ANSI_RESET);
                        }
                        System.out.println();
                        System.out.println(ANSI_RED + "Machine can only dispense $20 and $5 bills" + ANSI_RESET);
                    }else {
                        validInput = true;
                    }
                }else {
                    if (checking.getBalance() < amount) {
                        System.out.println(ANSI_RED + "Insufficient funds" + ANSI_RESET + "; Please choose a smaller amount" + ANSI_RESET);

                        System.out.println(ANSI_GREEN + "Withdraw Money" + ANSI_RESET);
                        System.out.println();
                        if (account == 1){
                            System.out.println(ANSI_GREEN + savings.getName() + ANSI_RESET);
                        }else {
                            System.out.println(ANSI_GREEN + checking.getName() + ANSI_RESET);
                        }
                        System.out.println(ANSI_RED + "Machine can only dispense $20 and $5 bills" + ANSI_RESET);
                    }else {
                        validInput = true;
                    }
                }
            }
        }


        //Withdrawing amount
        if (account == 1){
            savings.withdraw(amount);
        }else {
            checking.withdraw(amount);
        }

        //Maximum # of bills that can be selected
        int[] max = maxDistributedBills(amount);
        validInput = false;

        //Checking if selection adds up
        while (!validInput) {
            System.out.println("Max $20 bills: " + ANSI_RED + max[0] + ANSI_RESET + "\nMax $5 bills: " + ANSI_RED + max[1]);
            System.out.println("How many $20 bills do you want?");
            int twentys = scan.nextInt();
            scan.nextLine();
            System.out.println("How many $5 bills do you want?");
            int fives = scan.nextInt();
            scan.nextLine();
            int total = (twentys * 20) + (fives * 5);

            if (total == amount && twentys <= max[0] && fives <= max[1]) {
                validInput = true;
            } else {
                if (total != amount) {
                    System.out.println(ANSI_RED + "Invalid input" + ANSI_RESET + ";Please enter an amount of bills that adds up to withdraw amount");
                }
//                }else if (twentys > max[0] || fives > max[1]){
//                    System.out.println(ANSI_RED + "Invalid input" + ANSI_RESET + ";Please enter an amount of bills less than or equal to the maximum amount of bills");
//                }

            }
        }
            System.out.print(ANSI_WHITE_BACK + "Dispensing cash");
            System.out.print(ANSI_WHITE_BACK + ". ");
            System.out.print(ANSI_WHITE_BACK + ". ");
            System.out.println(ANSI_WHITE_BACK + "." + ANSI_RESET);


            //Receipt
            System.out.println("-----------------------------" + ANSI_RESET);
            System.out.println(ANSI_WHITE_BACK + "Transaction #" + getTransactionID());
            System.out.println(ANSI_WHITE_BACK + "Withdraw");
            System.out.print(ANSI_WHITE_BACK + "From: ");
            if (account == 1){
                System.out.print("Savings");
            }else {
                System.out.print("Checking");
            }
            System.out.println();
            System.out.println(ANSI_WHITE_BACK + "Amount: $" + amount);
            System.out.println(ANSI_WHITE_BACK + "Transaction was successful");
            System.out.println(ANSI_WHITE_BACK + "-----------------------------");
            System.out.println(ANSI_WHITE_BACK + "Current Balances");
            System.out.println(ANSI_WHITE_BACK + "Savings: $" + savings.getBalance());
            System.out.println(ANSI_WHITE_BACK + "Checking: $" + checking.getBalance() + ANSI_RESET);
            System.out.println("⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄" + ANSI_RESET);

    }

    private void deposit(){
        System.out.print(ANSI_WHITE_BACK + "Loading");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.println(ANSI_WHITE_BACK + ". " + ANSI_RESET);

        System.out.println(ANSI_GREEN + "Deposit Money" + ANSI_RESET);
        System.out.println();

        //Choosing account
        System.out.println("Which account would you like to deposit to? \n1. Savings \n2. Checking");
        int account = scan.nextInt();
        scan.nextLine();

        if (account == 1){
            System.out.println(savings.getName());
        }else {
            System.out.println(checking.getName());
        }
        System.out.println("How much would you like to deposit?: ");
        int amount = scan.nextInt();
        scan.nextLine();
        if (account == 1){
            savings.deposit(amount);
        }else {
            checking.deposit(amount);
        }

        //Receipt
        System.out.println("-----------------------------" + ANSI_RESET);
        System.out.println(ANSI_WHITE_BACK + "Transaction #" + getTransactionID());
        System.out.println(ANSI_WHITE_BACK + "Deposit");
        System.out.print(ANSI_WHITE_BACK + "From: ");
        if (account == 1){
            System.out.print("Savings");
        }else {
            System.out.print("Checking");
        }
        System.out.println();
        System.out.println(ANSI_WHITE_BACK + "Amount: $" + amount);
        System.out.println(ANSI_WHITE_BACK + "Transaction was successful");
        System.out.println(ANSI_WHITE_BACK + "-----------------------------");
        System.out.println(ANSI_WHITE_BACK + "Current Balances");
        System.out.println(ANSI_WHITE_BACK + "Savings: $" + savings.getBalance());
        System.out.println(ANSI_WHITE_BACK + "Checking: $" + checking.getBalance() + ANSI_RESET);
        System.out.println("⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄" + ANSI_RESET);
    }

    private void transfer(){
        System.out.print(ANSI_WHITE_BACK + "Loading");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.println(ANSI_WHITE_BACK + ". " + ANSI_RESET);

        System.out.println(ANSI_GREEN + "Transfer Money" + ANSI_RESET);
        System.out.println();

        //Choosing accounts
        System.out.println("Which account would you like to transfer funds from?");
        System.out.println("1. Savings");
        System.out.println("2. Checking");
        int account = scan.nextInt();
        scan.nextLine();

        //Checking for valid input
        boolean validInput = false;
        int amount = 0;
        while (!validInput){
            System.out.println("How much would you like to transfer?");
            amount = scan.nextInt();
            scan.nextLine();
            if (account == 1){
                if (savings.getBalance() >= amount){
                    validInput = true;
                }else {
                    System.out.println(ANSI_RED + "Insufficient funds for transfer" + ANSI_RESET + "; Please enter an amount less than or equal to your accounts balance");

                    System.out.println(ANSI_GREEN + "Transfer Money" + ANSI_RESET);
                    System.out.println();
                }
            }else{
                if (checking.getBalance() >= amount){
                    validInput = true;
                }else {
                    System.out.println(ANSI_RED + "Insufficient funds for transfer" + ANSI_RESET + "; Please enter an amount less than or equal to your accounts balance");

                    System.out.println(ANSI_GREEN + "Transfer Money" + ANSI_RESET);
                    System.out.println();
                }
            }
        }

        //Transferring money
        if (account == 1){
            savings.withdraw(amount);
            checking.deposit(amount);
        }else {
            checking.withdraw(amount);
            savings.deposit(amount);
        }

        // Receipt
        System.out.println("-----------------------------" + ANSI_RESET);
        System.out.println(ANSI_WHITE_BACK + "Transaction #" + getTransactionID());
        System.out.println(ANSI_WHITE_BACK + "Transfer");
        System.out.print(ANSI_WHITE_BACK + "From: ");
        if (account == 1){
            System.out.print("Savings");
        }else {
            System.out.print("Checking");
        }
        System.out.println();
        System.out.print(ANSI_WHITE_BACK + "To: ");
        if (account == 1){
            System.out.print("Checking");
        }else {
            System.out.print("Savings");
        }
        System.out.println();
        System.out.println(ANSI_WHITE_BACK + "Amount: $" + amount);
        System.out.println(ANSI_WHITE_BACK + "Transaction was successful");
        System.out.println(ANSI_WHITE_BACK + "-----------------------------");
        System.out.println(ANSI_WHITE_BACK + "Current Balances");
        System.out.println(ANSI_WHITE_BACK + "Savings: $" + savings.getBalance());
        System.out.println(ANSI_WHITE_BACK + "Checking: $" + checking.getBalance() + ANSI_RESET);
        System.out.println("⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄" + ANSI_RESET);
    }

    private void getBalances(){
        System.out.print(ANSI_WHITE_BACK + "Loading");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.println(ANSI_WHITE_BACK + ". " + ANSI_RESET);


        // Receipt
        System.out.println("-----------------------------" + ANSI_RESET);
        System.out.println(ANSI_WHITE_BACK + "Transaction #" + getTransactionID());
        System.out.println(ANSI_WHITE_BACK + "Account Balances");
        System.out.println(ANSI_WHITE_BACK + "-----------------------------");
        System.out.println(ANSI_WHITE_BACK + "Savings: $" + savings.getBalance());
        System.out.println(ANSI_WHITE_BACK + "Checking: $" + checking.getBalance() + ANSI_RESET);
        System.out.println("⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄" + ANSI_RESET);


    }

    private void changePIN(){
        System.out.print(ANSI_WHITE_BACK + "Loading");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.println(ANSI_WHITE_BACK + ". " + ANSI_RESET);


        System.out.println(ANSI_GREEN + "Change PIN" + ANSI_RESET);
        System.out.println("Please enter a new 4 digit PIN");
        int newPIN = scan.nextInt();
        scan.nextLine();
        customer.setPIN(newPIN);

        // Receipt
        System.out.println("-----------------------------" + ANSI_RESET);
        System.out.println(ANSI_WHITE_BACK + "Transaction #" + getTransactionID());
        System.out.println(ANSI_WHITE_BACK + "Change PIN");
        System.out.println(ANSI_WHITE_BACK + "PIN successfully changed!");
        System.out.println(ANSI_WHITE_BACK + "-----------------------------");
        System.out.println(ANSI_WHITE_BACK + "Current Balances");
        System.out.println(ANSI_WHITE_BACK + "Savings: $" + savings.getBalance());
        System.out.println(ANSI_WHITE_BACK + "Checking: $" + checking.getBalance() + ANSI_RESET);
        System.out.println("⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄⌄" + ANSI_RESET);

    }

    private void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private int getTransactionID(){
        transactionID++;
        return transactionID;
    }

    private int[] maxDistributedBills(int amount){
        int[] bills = new int[2];
        int twentys = 0;
        int fives = 0;
        if (amount < 20){
            fives = amount / 5;
            bills[1] = fives;
        }else {
            twentys = amount / 20;
            fives = (amount - twentys * 20) / 5;
            bills[0] = twentys;
            bills[1] = fives;
        }
        return bills;
    }

    public void start(){
        welcome();
        boolean choice = true;
        while (choice){
            choice = atm();
            if (choice){
                System.out.println("Would you like to complete another action? Yes(1)/ No(2)");
                int answer = scan.nextInt();
                scan.nextLine();
                if (answer == 2){
                    choice = false;
                }
            }
        }
        System.out.println("Thanks for visiting!");
        System.out.print(ANSI_WHITE_BACK + "Signing out");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.print(ANSI_WHITE_BACK + ". ");
        System.out.print(ANSI_WHITE_BACK + ". ");

    }


}
