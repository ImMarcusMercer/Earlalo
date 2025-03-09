package Launchers;

import Accounts.*;
import java.util.*;



public class SavingsAccountLauncher extends AccountLauncher{
    public static final Scanner input = new Scanner(System.in);
    public void savingsAccountInit(SavingsAccountLauncher launcher,int option) {
        // Implementation here. "Show Balance", "Deposit", "Withdraw", "Fund Transfer",
        //            "Show Transactions", "Logout"
        switch (option)
        {
            case 1->{

            }
            case 2->{
                System.out.print("Enter Amount: ");
                double amount = input.nextDouble();
                launcher.depositProcess(amount);
            }
            case 3->{
                System.out.print("Enter Amount: ");
                double amount = input.nextDouble();
                launcher.withdrawProcess(amount);
            }
            case 4->{
                System.out.print("Enter Account Number: ");
                String AccNum = input.nextLine();
                System.out.print("Enter Amount: ");
                double amount = input.nextDouble();
                launcher.fundTransferProcess(AccNum,amount);}

            case 5->{}
            case 6->launcher.logout();
        }
    }

    public void depositProcess(double amount) {
        if (loggedAccount == null) {
            System.out.println("No Savings Account logged in!");
            return;
        }
        if (!(loggedAccount instanceof SavingsAccount sa)) {
            System.out.println("Logged account is not a SavingsAccount!");
            return;
        }
        sa.cashDeposit(amount);
    }
    public void withdrawProcess(double amount) {
        if (loggedAccount == null) {
            System.out.println("No Savings Account logged in!");
            return;
        }
        if (!(loggedAccount instanceof SavingsAccount Acc)) {
            System.out.println("Logged account is not a SavingsAccount!");
            return;
        }
        Acc.withdrawal(amount);
    }

    public void fundTransferProcess(String targetAccNum, double amount) {
        if (loggedAccount == null) {
            System.out.println("No Savings Account logged in!");
            return;
        }
        if (!(loggedAccount instanceof SavingsAccount sa)) {
            System.out.println("Logged account is not a SavingsAccount!");
            return;
        }
        if (assocBank == null) {
            System.out.println("No bank selected!");
            return;
        }
        Account target = assocBank.getBankAccount(targetAccNum);
        if (target == null) {
            System.out.println("Recipient account not found!");
            return;
        }
        try {
            sa.transfer(target, amount);
        } catch (IllegalAccountType e) {
            System.out.println(e.getMessage());
        }
    }
    public SavingsAccount getLoggedAccount() {
        // Implementation here
        return null;
    }
}