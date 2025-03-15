package Launchers;

import Accounts.*;
import java.util.*;



public class SavingsAccountLauncher extends AccountLauncher{
    private static SavingsAccount loggedAccount = null;
    private static final Scanner input = new Scanner(System.in);
    private static void print(String Value)
    {
        System.out.print(Value);
    }

    public static void  login(SavingsAccount acc)
    {
        loggedAccount=acc;
        print("Login to "+loggedAccount.getAccountNumber()+" success...\n");
    }
    public static void logout()
    {
        loggedAccount=null;
        print("Logout success...\n");
    }

    public static void savingsAccountInit() {
        // Implementation here. "Show Balance", "Deposit", "Withdraw", "Fund Transfer",
        //            "Show Transactions", "Logout"
        print("[1] Show Balance\n[2] Deposit\n[3] Withdraw\n[4] Fund Transfer\n[5]Show Transactions\n[6] Logout");
        int option=input.nextInt();
        switch (option)
        {
            case 1->{
                System.out.println(loggedAccount.getBalance());
            }
            case 2->{
                System.out.print("Enter Amount: ");
                double amount = input.nextDouble();
                depositProcess(amount);
            }
            case 3->{
                System.out.print("Enter Amount: ");
                double amount = input.nextDouble();
                withdrawProcess(amount);
            }
            case 4->{
                //Complete functionality
                System.out.print("Enter Account Number: ");
                String AccNum = input.nextLine();
                if(BankLauncher.findAccount(AccNum)!=null)
                {
                    System.out.print("Enter Amount: ");
                    double amount = input.nextDouble();
                    fundTransferProcess(AccNum,amount);
                }
            }

            case 5->{
                getLoggedAccount().getTransactionsInfo();
            }
            case 6->logout();
        }
    }

    public static void depositProcess(double amount) {
        if (loggedAccount == null) {
            System.out.println("No Savings Account logged in!");
            return;
        }
        SavingsAccount sa = loggedAccount;
        loggedAccount.cashDeposit(amount);
    }
    public static void withdrawProcess(double amount) {
        if (loggedAccount == null) {
            System.out.println("No Savings Account logged in!");
            return;
        }
        SavingsAccount Acc = loggedAccount;
        Acc.withdrawal(amount);
    }

    public static void fundTransferProcess(String targetAccNum, double amount)
    {
        if(getLoggedAccount()!=null)
        {
            Account recepient = BankLauncher.findAccount(targetAccNum);
            if(recepient!=null&&recepient.getClass().isInstance(SavingsAccount.class))
            {
                while(true)
                {
                    print("Send P"+amount+" to "+recepient.getAccountNumber()+"\nY or N?\nEnter Choice: ");
                    String choice = input.nextLine();
                    if(choice.equals("Y")||choice.equals("y"))
                    {
                        String currAccount=loggedAccount.getAccountNumber();
                        loggedAccount.adjustAccountBalance(-amount);
                        setLogSession(BankLauncher.findAccount(recepient.getAccountNumber()));
                        loggedAccount.adjustAccountBalance(+amount);
                        logout();
                        setLogSession(BankLauncher.findAccount(currAccount));

                        print("Successfully sent P"+amount+" to "+recepient.getAccountNumber());
                        break;
                    }
                }
            }
            else
            {
                print("Account not found!\n");
            }
        }
    }
    public static Account getLoggedAccount() {
        return AccountLauncher.getLoggedAccount();
    }
}