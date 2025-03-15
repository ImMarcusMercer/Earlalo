package Launchers;

import Accounts.*;
import Main.*;

import java.util.Scanner;

public class CreditAccountLauncher extends AccountLauncher {
    private static CreditAccount loggedAccount = null;
    private static final Scanner input = new Scanner(System.in);
    private static void print(String Value)
    {
        System.out.print(Value);
    }


    public static void creditAccountInit()
    {
        if(loggedAccount==null){print("No account logged in.\n");}
        while(true)
        {
            Main.showMenuHeader("Credit Account");
            Main.showMenu(41);
            print("Enter choice: ");
            int choice = input.nextInt();
            //"Show Credits", "Pay", "Recompense", "Show Transactions", "Logout"
            switch (choice) {
                case 1:
                    System.out.println(loggedAccount.getLoanStatement());
                    break;
                case 2:
                    creditPaymentProcess();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    logout();
                    return; // Exit loop on logout
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }

    public static void login(CreditAccount acc)
    {
        loggedAccount=acc;
    }
    public static void logout()
    {
        loggedAccount= null;
        print("Logout success...\n");
    }

    public static void createAccount() {
        // Logic to create a CreditAccount
    }

    public static void creditPaymentProcess() {
        CreditAccount acc = getLoggedAccount();

        if(acc == null){print("No account\n");return;}
        while(true)
        {
            if(input.hasNextDouble())
            {
                print(loggedAccount.getLoanStatement());
                print("Enter amount: ");
                double amount = input.nextDouble();
                loggedAccount.adjustLoanAmount(amount);
                break;
            }
            else
            {
                print("Invalid input!\n");
            }

        }
    }

    public static void setTransactionProcess() {
        // Logic to handle credit transactions
    }

    // Method to get the logged-in Credit Account
    public static CreditAccount getLoggedAccount() {
        return loggedAccount;
    }

}