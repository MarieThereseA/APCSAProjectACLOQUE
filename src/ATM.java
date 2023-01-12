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
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String WHITE_BACK = "\u001B[47m";
    public static final String RED = "\\u001B[31m";
    Scanner scan = new Scanner(System.in);

    public ATM(){}

    private void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //Helper Methods
    private void welcome(){
        // Welcome Screen
        System.out.println(GREEN + "  ▒▒░░░░  ▒▒▒▒▒▒░░▒▒▒▒▒▒░░        ░░▒▒░░          ▒▒░░  ▒▒▒▒░░      ░░      ░░▒▒░░▒▒░░▒▒▒▒░░  ▒▒░░▒▒  ▒▒▒▒░░▒▒▒▒  ░░▒▒  ▒▒▓▓▓▓░░  ░░▒▒▒▒▒▒░░▒▒▒▒░░░░  ████▓▓▓▓▒▒  ░░░░  ▒▒▒▒  ░░░░\n" +
                "░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒░░▒▒▓▓▒▒▒▒░░      ░░        ▒▒░░▒▒░░▒▒▒▒░░    ░░░░  ▒▒▒▒░░  ░░▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒  ▒▒▓▓░░░░  ░░░░        ░░░░  ░░░░    ░░░░▒▒░░  ▓▓▓▓▓▓▓▓▒▒  ░░░░  ▒▒░░  ░░▒▒\n" +
                "  ░░▒▒░░░░▒▒▒▒░░  ░░░░▒▒░░▒▒▒▒▒▒▒▒▒▒░░░░░░      ░░▒▒▒▒▒▒  ▒▒▒▒░░░░  ░░  ▒▒▒▒  ░░▒▒░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░  ▓▓▒▒░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒  ░░░░  ░░░░  ▒▒░░░░▓▓▓▓▓▓▓▓░░  ░░░░  ▒▒▒▒░░░░░░\n" +
                "░░░░▒▒  ▒▒▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒  ▒▒▒▒▒▒░░▒▒░░▒▒▒▒░░  ░░░░▒▒  ░░░░░░      ░░░░░░▒▒░░▒▒░░▒▒░░  ██▓▓▒▒▒▒▒▒░░▒▒▒▒░░▒▒▒▒░░░░▒▒▓▓▓▓▒▒  ▒▒░░░░▓▓▓▓▓▓▓▓░░  ░░░░░░▒▒▒▒  ▒▒▒▒\n" +
                "░░░░▒▒  ▒▒▒▒░░▒▒░░░░▒▒▒▒▓▓▒▒░░░░▒▒▒▒▒▒▒▒░░▒▒▒▒  ▒▒▒▒▒▒░░    ░░░░  ░░░░░░  ▒▒░░░░      ░░░░    ░░░░░░▒▒▒▒▒▒  ░░▓▓░░  ▒▒▒▒░░▒▒░░▒▒▒▒░░░░▓▓▓▓▓▓▒▒  ▒▒░░░░▓▓▓▓▓▓▓▓░░  ░░░░░░░░░░  ▒▒▒▒\n" +
                "░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░  ▒▒░░░░░░░░░░░░░░          ░░░░░░  ░░░░░░░░░░▒▒░░    ░░░░    ░░      ░░▒▒▒▒░░▒▒  ░░▓▓▒▒░░▒▒▒▒▒▒░░▒▒░░░░  ▓▓▓▓▓▓▒▒░░▒▒░░░░▓▓▓▓▓▓▓▓░░  ░░  ░░░░░░  ░░▒▒\n" +
                "░░░░  ▒▒▒▒░░▒▒░░▒▒░░░░              ░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░  ▒▒░░░░░░▒▒▒▒░░  ░░░░        ░░        ░░▒▒▒▒▒▒▒▒  ▒▒▒▒░░░░▒▒▒▒▒▒▒▒▒▒▒▒  ▓▓▓▓██▒▒░░▒▒░░░░▓▓▓▓▓▓▓▓░░      ░░░░░░  ░░▒▒\n" +
                "░░    ▒▒░░░░░░        ░░░░        ▒▒▒▒▒▒▒▒▒▒▒▒░░░░    ░░    ▒▒░░  ░░░░░░            ░░  ░░            ░░▒▒▒▒▒▒░░  ▒▒▒▒  ▒▒▒▒░░░░▒▒▒▒  ▓▓▓▓▓▓▒▒  ░░░░░░▓▓▓▓▓▓▓▓░░░░░░░░  ░░░░░░▒▒▒▒\n" +
                "  ▒▒  ▒▒  ░░░░    ░░    ░░        ▒▒░░▒▒▒▒▒▒▒▒░░  ▒▒▒▒░░░░░░░░  ▒▒░░░░      ░░    ░░░░░░                ▒▒▒▒▒▒▒▒░░  ▓▓▒▒░░▒▒▒▒▒▒▒▒▒▒  ██▓▓▓▓░░░░░░░░░░████▓▓▓▓░░░░░░░░  ░░░░  ▒▒▓▓\n" +
                "▒▒░░░░▒▒  ▒▒░░  ░░░░              ░░▒▒▒▒░░▒▒▒▒▒▒  ░░  ░░▒▒░░  ░░▒▒░░    ░░    ░░░░░░▒▒▒▒░░░░      ░░    ▒▒  ▒▒▒▒▒▒  ░░▓▓░░░░▒▒▒▒▒▒▒▒  ██▓▓▓▓▒▒  ▒▒  ░░▓▓▓▓▓▓▓▓░░  ░░░░  ░░▓▓  ▒▒▒▒\n" +
                "░░  ░░▒▒░░▒▒░░  ░░  ░░            ░░░░▒▒  ▒▒░░▒▒░░  ░░░░░░░░▒▒░░░░    ░░    ░░░░░░▒▒░░░░▒▒░░░░          ▒▒▒▒░░▒▒▒▒▒▒  ▓▓▓▓░░▒▒▒▒░░░░  ████▓▓▒▒  ░░  ▒▒▓▓▓▓▓▓▓▓▓▓░░  ░░░░░░░░  ▒▒▒▒\n" +
                "░░  ▒▒░░░░▒▒        ░░░░░░░░░░    ░░░░▒▒░░▒▒▒▒░░  ░░▒▒  ▒▒░░░░  ░░░░          ░░▒▒░░░░▒▒▒▒░░  ░░    ░░  ░░▒▒▒▒░░▒▒  ▓▓▓▓▓▓▒▒░░░░▒▒▒▒  ▒▒████▓▓░░▒▒  ▒▒▓▓▓▓▓▓▓▓▒▒▒▒▒▒    ░░▒▒  ▒▒░░\n" +
                "  ░░░░  ░░░░    ░░  ░░▒▒░░░░▒▒░░░░  ░░▒▒  ░░  ░░░░░░  ▒▒░░░░  ░░▒▒░░░░        ░░░░▒▒░░░░▒▒                ░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓░░▒▒▒▒░░▒▒████▓▓░░░░░░▒▒▓▓▓▓▓▓▓▓▒▒░░▒▒▒▒░░    ░░▒▒▒▒\n" +
                "░░░░░░▒▒▒▒░░  ░░░░    ░░░░░░░░      ▒▒▒▒▒▒  ░░░░░░░░░░▒▒░░  ░░▒▒▒▒▒▒░░          ░░░░▒▒▒▒░░    ░░░░    ░░░░▒▒░░▒▒██▓▓▓▓▓▓▓▓▓▓░░▒▒▒▒▒▒░░░░██████░░░░  ▒▒▓▓▓▓▓▓▓▓▓▓▒▒▒▒░░▒▒▒▒    ░░▒▒\n" +
                "  ░░░░▒▒▒▒        ░░          ░░▒▒  ▒▒░░  ░░░░░░░░▓▓▒▒    ▓▓▒▒░░▒▒▓▓▓▓▒▒░░      ░░░░        ░░░░  ░░░░▒▒▒▒░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░  ░░▒▒▒▒▒▒  ▓▓████▒▒░░░░▒▒▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒░░░░▒▒░░    \n" +
                "  ░░░░░░▒▒    ░░  ░░  ░░▒▒▒▒▒▒░░▒▒  ░░  ░░░░  ░░░░░░  ░░▓▓░░░░░░░░░░▒▒▓▓▒▒░░              ░░░░  ░░░░▒▒▒▒  ▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░      ▒▒▒▒▒▒  ▓▓████▓▓▒▒░░▒▒▓▓▓▓▓▓▓▓▒▒  ░░▒▒▒▒▒▒▒▒▒▒▒▒  \n" +
                "░░▒▒  ▒▒░░          ░░░░▒▒░░▒▒▒▒▒▒  ░░░░░░  ░░▒▒░░  ░░▒▒▒▒░░░░░░░░░░▒▒▒▒▓▓▒▒░░          ░░░░  ░░░░▒▒▒▒░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒        ░░░░▒▒▒▒  ▓▓▓▓██▓▓▒▒░░▒▒▓▓▓▓▓▓▓▓▒▒▒▒░░  ▒▒▒▒░░▒▒▒▒▒▒\n" +
                "░░▒▒░░░░    ░░░░        ▒▒▒▒▒▒    ░░▒▒░░░░▒▒▒▒░░  ▒▒▒▒▒▒░░░░▒▒▒▒░░░░░░▓▓▓▓▓▓░░      ░░░░  ░░░░▒▒▒▒░░░░▓▓▓▓▓▓▓▓▓▓▓▓░░    ▒▒  ▒▒▒▒░░▒▒▒▒  ██▓▓▓▓▓▓▓▓░░▒▒▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░  ▒▒░░▒▒▒▒░░\n" +
                "▒▒░░░░▒▒                ▒▒▒▒▒▒  ░░▒▒░░░░▒▒▒▒░░    ░░░░▒▒▒▒▒▒░░▒▒░░░░░░▒▒▓▓▓▓▓▓▒▒░░  ░░  ░░▒▒▒▒▒▒░░▒▒▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░  ░░  ░░▒▒▒▒▒▒▒▒  ▓▓▓▓▓▓▓▓▓▓▒▒▒▒▓▓▓▓▓▓▓▓▒▒▒▒░░▒▒▒▒▓▓░░▒▒░░  \n" +
                "░░░░▒▒░░                ▒▒░░  ▒▒▒▒░░░░░░░░░░░░░░      ▒▒▓▓▒▒▒▒▒▒▒▒░░░░▒▒▓▓▓▓▓▓░░▒▒▒▒▒▒▒▒░░▒▒▒▒  ▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░  ░░        ░░▒▒░░▒▒▒▒  ▓▓▓▓▓▓▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒  ▒▒\n" +
                "░░░░░░  ░░░░░░        ░░    ▒▒▒▒  ░░▒▒░░  ░░░░        ░░▓▓▒▒▓▓▒▒▓▓▒▒▓▓  ▓▓▒▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒░░██▓▓▓▓▓▓██▓▓▒▒░░░░░░░░▒▒▒▒▒▒▒▒▒▒░░░░░░▒▒  ▒▒▓▓▓▓▒▒▓▓▓▓██▓▓▓▓▓▓▓▓░░░░░░▒▒▒▒▒▒▒▒  ▒▒▓▓\n" +
                "░░░░▒▒░░        ░░▒▒▒▒░░░░▒▒░░  ░░░░░░  ░░░░            ▓▓▓▓▓▓▓▓▒▒▒▒▓▓░░░░░░▒▒▒▒▒▒▓▓░░░░░░░░██▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░▒▒▓▓▓▓▓▓▓▓  ▓▓██▓▓▓▓▓▓▓▓    ░░▒▒▒▒▒▒░░  ▓▓▓▓\n" +
                "░░░░    ░░░░▒▒▒▒▒▒▒▒  ░░▒▒░░░░▒▒▒▒░░░░░░                ░░▓▓▓▓▓▓▒▒▒▒▒▒░░░░░░░░▒▒▒▒▒▒▒▒░░▒▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░░░    ░░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓  ░░████▓▓▓▓▒▒░░░░░░░░▒▒▒▒  ▓▓▓▓▓▓\n" +
                "  ░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒  ▒▒▒▒░░░░░░░░░░░░░░                    ▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░  ██████▓▓▓▓▒▒░░  ▒▒▒▒  ▒▒▓▓▓▓▓▓\n" +
                "▒▒░░▒▒▒▒░░▒▒▒▒░░  ▒▒▒▒▒▒░░░░      ░░    ▒▒▒▒▒▒▒▒            ▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒  ████████████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░▒▒▒▒▓▓░░░░▒▒░░  ████▓▓▓▓░░░░░░▒▒░░░░██▓▓▓▓▓▓\n" +
                "▒▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒░░▒▒░░    ░░▒▒░░░░▒▒░░          ░░░░▒▒▒▒▒▒▒▒▒▒░░░░▒▒░░██████████████████▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░░░░░░░    ░░      ░░▒▒▒▒  ▒▒▒▒  ▒▒████▓▓░░░░▒▒▒▒  ██▓▓▓▓▓▓▓▓\n" +
                "▒▒░░▒▒▒▒▒▒  ░░▒▒▒▒░░▒▒░░▒▒░░▒▒▒▒      ░░▒▒▒▒░░░░▒▒      ░░  ░░▒▒▒▒▒▒▒▒▒▒░░▒▒░░▒▒██████████▓▓▓▓▓▓▓▓▒▒▒▒░░            ░░░░    ░░    ░░      ░░▒▒░░░░▒▒░░  ████▓▓░░▒▒▒▒  ▒▒████▓▓▓▓▓▓\n" +
                "░░░░▒▒▒▒▒▒    ░░▒▒░░▒▒  ▒▒▒▒▒▒░░        ▒▒░░░░▒▒░░  ░░░░░░    ▒▒▒▒▒▒▒▒▒▒▒▒  ▓▓████████▓▓▓▓▒▒▒▒▒▒▒▒▒▒░░          ░░░░░░░░░░  ░░░░  ░░░░    ░░▒▒░░  ▒▒▒▒  ░░██▓▓▒▒▒▒░░░░██████▓▓▓▓▓▓\n" +
                "▒▒▒▒▒▒░░        ▒▒░░▓▓▒▒░░▒▒▒▒    ░░    ░░▓▓▒▒▒▒  ░░░░        ░░▒▒▒▒  ▒▒  ▓▓██████████▒▒░░▓▓▒▒░░░░▒▒  ░░      ░░  ░░░░▒▒▒▒░░░░░░      ░░    ░░░░░░░░▒▒▒▒  ▓▓██▓▓░░  ▓▓▓▓████▓▓▓▓▓▓\n" +
                "▒▒▒▒░░            ▒▒░░▒▒▒▒▒▒▒▒    ░░                        ░░▒▒░░░░▒▒░░▓▓▓▓████████▓▓    ░░░░▒▒▓▓░░░░░░        ░░▒▒▒▒░░░░▒▒░░  ░░    ░░    ░░░░░░  ░░▒▒░░  ██▓▓▒▒▒▒▓▓▓▓██████▓▓▒▒\n" +
                "▒▒▒▒░░░░░░░░        ▒▒▒▒▒▒▒▒▒▒░░                ░░  ░░░░░░░░▒▒▒▒▒▒░░▒▒▓▓██▓▓████████▒▒░░    ▒▒░░░░▒▒  ░░░░      ▒▒▒▒░░░░▒▒░░▒▒░░░░    ░░░░    ░░  ░░░░▒▒▒▒  ▒▒▓▓▓▓██▓▓▓▓██████▒▒▒▒\n" +
                "░░░░░░░░            ░░▒▒░░░░░░▒▒          ░░░░    ░░░░░░░░▒▒▒▒▒▒░░▒▒▓▓▓▓▓▓██████████▒▒░░░░░░░░▒▒  ▒▒░░          ░░▒▒░░▒▒░░░░░░░░  ░░  ░░      ░░░░▒▒▒▒▒▒▒▒░░  ▓▓██████▓▓▓▓████▓▓▒▒\n" +
                "░░  ░░    ░░░░  ░░░░  ░░▒▒▒▒▒▒▒▒░░      ░░  ░░  ░░    ░░░░▒▒▒▒░░▓▓▓▓██▓▓██▓▓▓▓██████░░░░░░░░  ░░▒▒▒▒▒▒░░░░░░    ░░░░▒▒▒▒▒▒░░▒▒░░    ░░  ░░░░  ▒▒▒▒░░░░▒▒▒▒▓▓  ░░████▓▓▓▓▓▓██▓▓▓▓▓▓\n" +
                "▒▒  ░░    ░░░░░░░░  ░░  ░░▒▒▒▒▒▒░░            ░░░░  ░░░░▒▒░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░████▓▓░░░░░░░░░░  ▒▒▒▒░░    ░░░░    ░░▒▒░░▒▒▒▒    ░░          ░░▒▒▒▒▒▒▒▒░░░░▒▒▒▒  ████████▓▓▒▒▓▓▓▓▓▓\n" +
                "░░    ░░      ░░  ░░▒▒░░  ▒▒▒▒▒▒░░        ░░░░    ░░▒▒▒▒░░░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒  ▓▓  ▓▓██▒▒  ▒▒░░  ░░░░▒▒░░░░    ░░      ░░░░  ░░        ░░░░░░░░▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒░░  ██████▒▒  ▓▓▓▓▓▓\n" +
                "▒▒░░  ░░    ░░  ░░░░░░▒▒    ▒▒▒▒░░▒▒░░░░░░░░░░  ░░▒▒▒▒░░▒▒▓▓▓▓██▓▓▓▓▓▓▒▒▒▒░░░░░░  ░░▒▒▒▒    ░░▒▒  ░░▒▒░░░░  ░░░░    ░░              ░░░░  ░░▒▒▒▒▒▒▒▒▒▒  ▒▒▒▒▒▒▒▒  ▒▒████▒▒  ▒▒▓▓██\n" +
                "▒▒░░  ░░░░  ░░  ░░▒▒▒▒▒▒▒▒  ░░▒▒▒▒▒▒▒▒▒▒▒▒░░  ░░▒▒▒▒  ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒░░▒▒▒▒░░  ░░░░░░  ▒▒▒▒  ▒▒▒▒░░░░  ░░░░    ░░                  ░░▒▒▒▒▒▒░░  ░░▒▒▒▒▒▒▒▒░░  ██▓▓▓▓░░  ▓▓██\n" +
                "▒▒░░    ░░  ░░    ░░▒▒▒▒▒▒▒▒  ░░▒▒▒▒▒▒░░▒▒▒▒░░▒▒▒▒  ▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░░░░░▒▒▒▒░░    ░░▒▒░░░░▒▒  ▒▒▒▒░░░░▒▒░░                              ░░░░▒▒▒▒    ▒▒▒▒▒▒░░▒▒▒▒░░  ░░▓▓▓▓▒▒  ▓▓▓▓\n" +
                "░░░░░░  ░░    ░░░░  ░░▒▒▒▒▒▒░░  ░░▒▒▒▒▒▒░░░░▒▒░░░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒    ░░░░░░    ░░▒▒▒▒░░  ▒▒▒▒  ▒▒▒▒░░  ▒▒▒▒░░░░  ░░                      ▓▓▓▓▒▒▒▒░░▒▒▒▒░░▒▒▒▒░░    ░░▓▓▓▓▓▓▓▓░░░░██\n" +
                "  ▒▒▒▒    ░░  ░░      ░░▒▒▒▒▒▒░░  ▒▒▒▒▒▒▒▒░░░░▒▒▓▓▓▓▓▓▓▓▓▓▓▓░░  ░░  ░░  ░░▒▒▒▒▒▒▒▒▒▒░░░░▒▒░░  ▒▒▒▒░░  ░░▒▒▒▒░░                        ░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░    ▓▓▓▓▓▓▓▓▓▓▓▓▒▒  ██\n" +
                "░░▒▒▒▒    ░░            ░░▒▒▒▒      ▒▒▒▒▒▒░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░        ░░▒▒▒▒▒▒░░░░░░░░░░▒▒▒▒░░  ▒▒▒▒░░    ▒▒▒▒░░    ░░▒▒░░          ░░  ░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░    ▒▒████▓▓▓▓▓▓▓▓▓▓▓▓░░░░\n" +
                "░░░░░░░░    ░░        ░░░░░░▒▒▒▒      ▒▒  ▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░░░  ░░▒▒▒▒▒▒░░░░░░░░░░    ▒▒▒▒░░░░  ▒▒▒▒░░  ░░  ▒▒▒▒░░    ░░            ▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒░░    ░░██████▓▓▓▓▓▓▓▓██▓▓▓▓░░  \n" +
                "▒▒  ▒▒░░              ░░▒▒    ▒▒▒▒    ░░██▓▓▓▓▓▓▓▓▓▓▒▒    ░░▒▒▒▒▒▒░░░░░░░░        ▒▒▒▒░░░░    ▒▒▒▒░░  ░░  ░░▒▒░░      ░░░░      ░░▒▒▒▒▒▒░░░░░░▒▒      ██████████▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒  \n" +
                "░░░░░░░░          ░░  ▒▒░░▒▒░░  ▒▒  ░░▒▒▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░▒▒░░░░        ░░░░  ░░▒▒░░        ▒▒▒▒░░  ░░    ▒▒▒▒▒▒░░▒▒░░▒▒▒▒░░▒▒▒▒▒▒▒▒░░▒▒▒▒░░    ▒▒████████▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒░░\n" +
                "▒▒░░░░▒▒░░░░░░░░░░▒▒░░▒▒▒▒░░░░░░    ▒▒░░▒▒▓▓▓▓▒▒░░▒▒░░░░▒▒▒▒░░        ░░░░░░    ▒▒▒▒░░░░  ░░  ▓▓▒▒    ░░    ░░▒▒▒▒░░▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░    ░░▓▓▓▓████████▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░  ░░\n" +
                "░░▒▒░░░░░░  ▒▒▒▒▓▓░░░░▒▒░░▒▒▒▒▒▒░░  ▒▒▒▒▒▒░░▓▓░░  ▒▒▒▒▒▒  ▒▒░░░░░░░░          ▒▒▒▒░░░░  ░░░░  ▒▒▒▒            ▒▒░░▒▒▒▒  ░░▒▒░░▒▒▒▒▒▒▒▒      ▓▓▓▓▓▓▓▓████████▓▓▓▓▒▒▒▒▒▒░░    ▒▒▓▓▓▓\n" +
                "  ░░░░░░░░  ▒▒▒▒▓▓  ▒▒▒▒▒▒▒▒▒▒▒▒░░  ▒▒▒▒▒▒▒▒▒▒    ▒▒▒▒▒▒░░▒▒▒▒░░  ▒▒        ░░▒▒░░          ░░▒▒▒▒    ░░░░    ░░░░▒▒░░▒▒▒▒░░▒▒▒▒▒▒░░    ▒▒▓▓▓▓██▓▓▓▓████████▓▓▒▒▒▒░░    ▒▒▓▓▓▓▓▓▓▓\n" +
                "  ░░░░░░▒▒░░░░▒▒░░░░░░░░▒▒    ░░░░░░▒▒▒▒▒▒▒▒░░░░    ▒▒▒▒▒▒░░▒▒░░            ▒▒░░░░    ░░    ░░▒▒░░    ░░░░      ░░▒▒▒▒▒▒▒▒▒▒▒▒░░    ░░▓▓▓▓████▓▓▓▓▓▓████████▒▒░░  ░░▓▓████████████\n" +
                "░░░░░░  ░░░░░░▓▓  ▒▒▒▒▒▒▒▒▒▒▒▒▒▒  ░░░░▒▒░░▒▒▒▒░░░░  ▒▒▓▓▒▒▒▒░░            ▒▒▒▒░░    ░░        ▒▒░░  ░░            ▒▒░░░░▒▒▒▒░░    ▓▓▓▓▓▓▓▓▓▓▓▓██▓▓██████▓▓▓▓░░▒▒██████████████████\n" +
                "░░░░░░░░▒▒░░  ▓▓  ░░▒▒▒▒▒▒░░░░▒▒  ▒▒▒▒▒▒  ▒▒▒▒▒▒░░  ░░▒▒▒▒▒▒░░          ░░░░░░▒▒░░  ░░░░    ░░▒▒░░░░░░▒▒░░        ░░▒▒▒▒░░    ▒▒▓▓▓▓▓▓▓▓▓▓▓▓██▓▓▓▓██▓▓▓▓▓▓████████████████████████\n" +
                "▒▒  ░░░░▒▒▒▒░░░░░░      ░░▒▒▒▒░░  ▒▒▒▒▒▒  ▒▒░░▒▒░░    ▒▒▒▒▒▒░░        ░░░░▒▒▒▒▒▒▒▒░░        ░░░░▒▒▒▒▒▒▒▒░░░░        ░░    ░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░▒▒▓▓██████████████████▓▓▓▓▓▓▓▓▓▓\n" +
                "    ▒▒░░░░▒▒░░░░▓▓▒▒░░▒▒░░    ░░  ▒▒▒▒▒▒  ▒▒░░▒▒░░░░  ░░▒▒▒▒▒▒░░      ▒▒░░▒▒  ▒▒▒▒▒▒    ░░  ░░░░▒▒      ▒▒░░░░░░▒▒▒▒    ▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██▓▓▒▒  ▓▓████████████████▓▓▓▓▒▒  ▒▒▓▓▓▓\n" +
                "▓▓░░░░▒▒  ▒▒▒▒  ▒▒▒▒▓▓▓▓▓▓██▒▒░░  ▒▒▒▒▒▒░░▒▒▒▒▒▒░░░░  ░░▒▒▒▒▒▒▒▒▒▒  ░░▓▓░░▒▒▒▒░░▒▒░░        ░░░░▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▒▒▒▒  ▓▓██████████████▒▒░░░░░░  ▓▓▓▓▓▓\n" +
                "████  ░░░░░░▒▒░░▒▒▒▒▒▒▒▒▒▒▓▓▓▓  ░░▒▒░░▒▒▓▓▒▒░░▒▒▒▒  ▒▒  ▒▒▒▒▒▒▒▒░░    ▒▒▒▒▒▒▒▒▒▒▒▒▒▒        ░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒  ▓▓████████████▓▓░░▒▒░░  ▓▓▓▓▓▓▓▓\n" +
                "▓▓▓▓  ▒▒░░░░░░  ░░░░░░░░        ░░▒▒▒▒▒▒▒▒░░▒▒▒▒░░  ░░  ░░▒▒░░▒▒░░░░    ░░▒▒▒▒▒▒░░▒▒▒▒░░░░  ░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░▒▒▓▓▓▓▓▓▓▓▒▒▒▒░░▒▒▒▒▒▒  ██████████▓▓▓▓▒▒░░    ░░▓▓▓▓▓▓▓▓\n" +
                "▒▒▒▒░░░░▒▒  ▒▒░░                  ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░  ▒▒▒▒░░▒▒▒▒░░      ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒        ░░░░░░░░  ░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒  ▓▓▓▓▓▓██████▓▓▒▒▒▒░░  ▓▓▓▓▓▓▓▓▓▓\n" +
                "▒▒▒▒▒▒  ░░░░▒▒░░                              ░░░░▒▒▒▒▒▒  ░░░░▒▒▒▒▒▒▒▒░░░░    ░░▒▒▒▒░░▒▒▓▓░░    ░░    ░░                                          ▓▓▓▓▓▓██████▓▓▓▓▒▒  ▒▒▓▓▓▓▓▓▓▓▓▓\n" +
                "░░░░▒▒  ░░░░░░░░░░░░░░                ▒▒▓▓▓▓▒▒░░░░          ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒    ░░▒▒▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▓▓██▓▓▓▓████████████▓▓▓▓▒▒▓▓▓▓██▓▓▓▓████▓▓▓▓▒▒░░░░▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                "▒▒▒▒░░░░░░▒▒  ░░░░░░░░                ▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒  ▒▒░░▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒    ░░▒▒▒▒░░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓██▓▓▓▓▓▓▓▓▓▓██████████████▓▓▓▓██▓▓▓▓▓▓██████▓▓▓▓▒▒  ▓▓▓▓▓▓▓▓▓▓▓▓▒▒\n" +
                "▒▒░░░░▒▒  ░░  ░░░░  ░░      ░░▒▒▓▓▓▓░░░░░░░░▒▒▓▓▓▓▓▓▓▓▓▓▓▓  ░░▒▒▒▒▒▒  ▒▒▒▒▒▒▒▒░░░░    ░░▒▒░░▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██████████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓██████▓▓▓▓  ░░██▓▓▓▓▓▓▓▓▓▓▒▒\n" +
                "░░▒▒░░░░  ░░░░░░░░░░░░░░    ▒▒▒▒  ░░▒▒░░░░░░  ▒▒▒▒▒▒▒▒▒▒▓▓▒▒  ▒▒▒▒▒▒░░░░▒▒▒▒▒▒▒▒░░░░░░    ▒▒▒▒▒▒▒▒▒▒░░  ░░▒▒▒▒▒▒▒▒▒▒▒▒░░████████▓▓▓▓▓▓▒▒▒▒░░▒▒▓▓▒▒▒▒▒▒████████▒▒  ▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░\n" +
                "▒▒░░░░░░░░░░▒▒  ░░░░░░░░  ░░▒▒░░░░▒▒▒▒░░░░░░  ▒▒▒▒▒▒▒▒▒▒▒▒▒▒  ░░▒▒▒▒▒▒  ▒▒▒▒░░▒▒░░░░▒▒▒▒░░  ░░▒▒▒▒░░▒▒▒▒    ▒▒▒▒▒▒▒▒  ▒▒██▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒  ░░▓▓▓▓▓▓▓▓░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░  \n" +
                "░░▒▒░░░░░░░░░░░░░░░░  ░░    ▒▒░░░░░░▒▒░░░░    ░░▒▒▒▒▒▒▒▒░░      ▒▒░░▒▒  ▒▒░░▒▒▒▒▒▒▒▒▒▒░░░░      ▒▒▒▒░░▒▒▒▒░░▒▒▒▒▒▒░░  ██▓▓██▓▓▓▓▓▓▓▓▒▒▒▒░░▒▒▒▒▒▒▒▒  ░░██▓▓██▓▓░░▓▓▓▓▓▓▓▓▓▓▓▓░░░░▓▓");
        System.out.println();
        System.out.println("Welcome to the " + GREEN + "A" + RESET + "utomated " + GREEN + "T" + RESET + "eller " + GREEN +"M" + RESET + "achine");
        clear();

        System.out.println(WHITE_BACK + "Loading");
        System.out.print(WHITE_BACK + ".");
        System.out.print(WHITE_BACK + ".");
        System.out.print(WHITE_BACK + ".");
        clear();

        // Create account screen
        System.out.println(GREEN + "Create An Account");
        System.out.println("Enter your name: ");
        String name = scan.nextLine();
        System.out.println("Create a 4 digit PIN: ");
        int PIN = scan.nextInt();
        scan.nextLine();
        clear();

        //Create Customer object
        customer = new Customer(name,PIN);
        savings = new Account("Savings Account", customer);
        checking = new Account("Checking Account", customer);

        System.out.println(WHITE_BACK + "Loading");
        System.out.print(WHITE_BACK + ". ");
        System.out.print(WHITE_BACK + ". ");
        System.out.print(WHITE_BACK + ". ");
        clear();

    }

    private void atm(){
        //PIN input
        System.out.println(GREEN + "Sign in");
        boolean incorrect = true;
        while (incorrect) {
            System.out.println("Please enter your 4 digit PIN: ");
            int pin = scan.nextInt();
            scan.nextLine();
            if (customer.checkPIN(pin)){
                incorrect = false;
            }else {
                System.out.println("Your PIN is incorrect, Please try again.");
                clear();
                System.out.println(GREEN + "Sign in");
            }
        }
        clear();

        //Main Menu
        System.out.println("Welcome " + GREEN + customer.getName() + RESET +  "!");
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
            System.out.println();


            if (selection > 6 || selection < 1){
                System.out.println(RED + "Invalid input; Please make a valid selection");
                clear();
                System.out.println("Welcome " + GREEN + customer.getName() + RESET +  "!");
                System.out.println();
                System.out.println("Main Menu");
                System.out.println("Please make a selection: ");
            }else {
                validInput = true;
            }
            clear();
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
            System.out.println("Thanks for visiting!");
            System.out.println(WHITE_BACK + "Signing out");
            System.out.print(WHITE_BACK + ". ");
            System.out.print(WHITE_BACK + ". ");
            System.out.print(WHITE_BACK + ". ");
            clear();
        }
        clear();
    }

    private void withdraw(){
        System.out.println(WHITE_BACK + "Loading");
        System.out.print(WHITE_BACK + ". ");
        System.out.print(WHITE_BACK + ". ");
        System.out.print(WHITE_BACK + ".");
        clear();
        System.out.println(GREEN + "Withdraw Money");
        System.out.println();

        //Choosing account
        System.out.println("Which account would you like to withdraw from? \n1. Savings \n2. Checking");
        int account = scan.nextInt();
        scan.nextLine();
        clear();
        if (account == 1){
            System.out.println(GREEN + savings.getName());
        }else {
            System.out.println(GREEN + checking.getName());
        }
        System.out.println(RED + "Machine can only dispense $20 and $5 bills");

        //Checking for valid input
        boolean validInput = false;
        int amount = 0;
        while (!validInput){
            System.out.println("How much would you like to withdraw?: ");
            amount = scan.nextInt();
            scan.nextLine();
            if (amount % 5 != 0){
                System.out.println("Sorry :(, machine cannot dispense that amount; Please enter a valid amount");
                clear();
                System.out.println(GREEN + "Withdraw Money");
                System.out.println();
                System.out.println(RED + "Machine can only dispense $20 and $5 bills");
            }else {
                if (account == 1){
                    if (savings.getBalance() < amount){
                        System.out.println(RED + "Insufficient funds" + RESET + "; Please choose a smaller amount");
                        clear();
                        System.out.println(GREEN + "Withdraw Money");
                        System.out.println();
                        if (account == 1){
                            System.out.println(GREEN + savings.getName());
                        }else {
                            System.out.println(GREEN + checking.getName());
                        }
                        System.out.println(RED + "Machine can only dispense $20 and $5 bills");
                    }else {
                        validInput = true;
                    }
                }else {
                    if (checking.getBalance() < amount) {
                        System.out.println(RED + "Insufficient funds" + RESET + "; Please choose a smaller amount");
                        clear();
                        System.out.println(GREEN + "Withdraw Money");
                        System.out.println();
                        if (account == 1){
                            System.out.println(GREEN + savings.getName());
                        }else {
                            System.out.println(GREEN + checking.getName());
                        }
                        System.out.println(RED + "Machine can only dispense $20 and $5 bills");
                    }else {
                        validInput = true;
                    }
                }
            }
        }
        clear();

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
        while (!validInput){
            System.out.println("Max $20 bills: " + RED + max[0] + RESET + "\nMax $5 bills: " + RED + max[1]);
            System.out.println("How many $20 bills do you want?");
            int twentys = scan.nextInt();
            scan.nextLine();
            System.out.println("How many $5 bills do you want?");
            int fives = scan.nextInt();
            scan.nextLine();
            int total = (twentys * 20) + (fives * 5);

            if (total == amount && twentys <= max[0] && fives <= max[1]){
                validInput = true;
            }else{
                if (total != amount){
                    System.out.println(RED + "Invalid input" + RESET + ";Please enter an amount of bills that adds up to withdraw amount");
                }else if (twentys > max[0] || fives > max[1]){
                    System.out.println(RED + "Invalid input" + RESET + ";Please enter an amount of bills less than or equal to the maximum amount of bills");
                }
                clear();
            }
            System.out.println(WHITE_BACK + "Dispensing cash");
            System.out.print(WHITE_BACK + ". ");
            System.out.print(WHITE_BACK + ". ");
            System.out.print(WHITE_BACK + ".");
            clear();
            System.out.println("Cash Dispensed!");
            clear();
        }
    }

    private int[] maxDistributedBills(int amount){
        int[] bills = new int[2];
        int twentys = 0;
        int fives = 0;
        if (amount < 20){
            fives = amount / 5;
            bills[0] = fives;
        }else {
            twentys = amount / 20;
            fives = (amount - twentys * 20) / 5;
            bills[0] = twentys;
            bills[1] = fives;
        }
        return bills;
    }

    private void deposit(){
        System.out.println(WHITE_BACK + "Loading");
        System.out.print(WHITE_BACK + ". ");
        System.out.print(WHITE_BACK + ". ");
        System.out.print(WHITE_BACK + ". ");
        clear();
        System.out.println(GREEN + "Deposit Money");
        System.out.println();

        //Choosing account
        System.out.println("Which account would you like to withdraw from? \n1. Savings \n2. Checking");
        int account = scan.nextInt();
        scan.nextLine();
        clear();
        if (account == 1){
            System.out.println(savings.getName());
        }else {
            System.out.println(checking.getName());
        }
        System.out.println("How much would you like to deposit?: ");
        int amount = scan.nextInt();
        scan.nextLine();


    }

    private void transfer(){

    }

    private void getBalances(){

    }

    private void changePIN(){

    }


}
