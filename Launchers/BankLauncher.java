package Launchers;

import Main.*;
import Accounts.*;
import Bank.*;

import java.util.*;

public class BankLauncher {
    // Private attributes
    private static final List<Bank> BANKS = new ArrayList<>();
    private static Bank loggedBank = null;
    private static final Scanner input = new Scanner(System.in);
    private static void print(String Value)
    {
        System.out.print(Value);
    }

    public static boolean isLogged() {
        return loggedBank == null;
    }

    public static void bankInit() {
        Bank bank = BankLauncher.getLoggedBank();
        while(true)
        {
            if (bank != null)
            {
                Main.showMenuHeader("My "+bank.getName()+" Accounts");
                Main.showMenu(31);
                Main.setOption();
                //Bank show accounts Menu
                switch (Main.getOption()) {
                    //Show Accounts
                    case 1 -> {
                        BankLauncher.showAccounts();
                    }
                    //Create New Account
                    case 2 -> {
                        Main.showMenuHeader("Select Account Type");
                        Main.showMenu(33);
                        Main.setOption();
                        if(Main.getOption()==1)
                        {
                            //New Credit
                            Bank Bank=BankLauncher.getLoggedBank();
                            String accountNumber;
                            String ownerFName;
                            String ownerLName;
                            String ownerEmail;
                            String pin;
                            print("Enter New Account Number: ");
                            accountNumber=input.nextLine();
                            print("Enter Pin: ");
                            pin = input.nextLine();
                            print("Enter Last Name: ");
                            ownerLName=input.nextLine();
                            print("Enter First Name: ");
                            ownerFName=input.nextLine();
                            print("Enter Email: ");
                            ownerEmail=input.nextLine();
                            CreditAccount newAccount = Bank.createNewCreditAccount(Bank, accountNumber, ownerFName, ownerLName, ownerEmail, pin);
                            Bank.addNewAccount(newAccount);
                            if(Bank.accountExists(BankLauncher.getLoggedBank(),newAccount.getAccountNumber()))
                            {
                                print("Account Creation Successful!\n");
                            }
                            else {
                                print("Error in creating account!");
                            }
                        }
                    }
                    //Logout
                    case 3 -> {
                        BankLauncher.logoutBank();
                        return;
                    }
                }
            }
        }
    }

    public static void showAccounts() {
        if (isLogged()) {
            System.out.println("No bank is currently logged in.");
            return;
        }
        List<Account> accounts = loggedBank.getBANKACCOUNTS();
        if (accounts == null || accounts.isEmpty()) {
            System.out.println(" No accounts found in " + loggedBank.getName());
        }
        else
        {
            Main.showMenuHeader("Savings Accounts");
//            for (Account account : accounts)
//            {
//                if (account instanceof SavingsAccount sa) {
//                    System.out.println("Account Number: " + account.getAccountNumber());
//                    System.out.println("Name: " + account.getOwnerFullName());
//                    System.out.println("Balance: $" + sa.getBalance());
//                    Main.showMenuHeader("End");
//                }
//            }
            loggedBank.showAccounts(SavingsAccount.class);

            Main.showMenuHeader("Credit Accounts");
            loggedBank.showAccounts(CreditAccount.class);
        }
    }


    public static void newAccounts(String accountType, Bank bank, String accNum, String ownerFName, String ownerLName, String ownerEmail, String pin, double balance) {
        if (isLogged()) {
            System.out.println("No bank is currently logged in.");
            return;
        }
        if ("savings".equalsIgnoreCase(accountType)) {
            loggedBank.createNewSavingsAccount( bank, accNum, ownerFName, ownerLName, ownerEmail, pin, balance);
            System.out.println("Created new SavingsAccount: " + accNum);
        } else if ("credit".equalsIgnoreCase(accountType)) {
            loggedBank.createNewCreditAccount( bank,  accNum, ownerFName, ownerLName, ownerEmail, pin);
            System.out.println("Created new CreditAccount: " + accNum);
        } else {
            System.out.println("Unknown account type: " + accountType);
        }
    }
    public static void bankLogin(String bankName)
    {
        Bank found = null;
        for (Bank b : BANKS)
        {
            if (b.getName().equalsIgnoreCase(bankName)) {
                found = b;
                break;
            }
        }
        if (found != null) {
            loggedBank = found;
            setLogSession(loggedBank);
            AccountLauncher.selectBank(loggedBank);
            System.out.println("Logged in to bank: " + found.getName());
        }
        else {
            System.out.println("Bank not found!");
            return;
        }
    }

    public static void setLogSession(Bank b) {
        loggedBank = b;
        if (b != null) {
            System.out.println("Session set to bank: " + b.getName());
        } else {
            System.out.println("No bank selected.");
        }
    }
    public static void logoutBank(){
        loggedBank=null;
        System.out.println("Logging out...\nSuccess");
    }

    public static void createNewBank() {
        int ID; String name; String password; double  DEPOSITLIMIT; double WITHDRAWLIMIT; double CREDITLIMIT; double processingFee;

        print("Enter Bank ID: ");
        ID=input.nextInt();
        print("Enter New Bank Name: ");
        name = input.next();
        print("Enter Bank Passcode: ");
        password= input.next();
        DEPOSITLIMIT=100000;
        WITHDRAWLIMIT= 10000;
        CREDITLIMIT = DEPOSITLIMIT;
        processingFee = 100;

        print("A processing fee of "+processingFee+" will be deducted from your account\nContinue?\n[1] Yes\n[2] No\nEnter Choice: ");
        int create = input.nextInt();
        if(create==1){
            Bank newBank = new Bank( ID, name, password,  DEPOSITLIMIT, WITHDRAWLIMIT, CREDITLIMIT, processingFee);

            //TODO: Implement Duplicate bank checker here

            BANKS.add(newBank);
            System.out.println("Created new bank: " + name + " (ID=" + ID + ")");
        }
        else if (create==2) {
            print("");
        }


    }

    public static void showBanksMenu() {
        if (BANKS.isEmpty()) {
            System.out.println("No banks available.");
            return;
        }
        for (int i = 0; i < BANKS.size(); i++) {
            Bank b = BANKS.get(i);
            System.out.printf("[%d] %s\n", i + 1, b.getName());
        }
    }
    public static void addBank(Bank bank)
    {
        if(bank != null)
        {
            BANKS.add(bank);
        }
        else{System.out.println("Invalid");}
    }

    public static Bank getLoggedBank() {
        return loggedBank;
    }

    public static Bank getBank(Comparator<Bank> comparator, Bank bank) {
        for (Bank b : BANKS) {
            if (comparator.compare(b, bank) == 0) {
                return b;
            }
        }
        return null;
    }

    public static Account findAccount(String num) {
        if (isLogged()) {
            System.out.println("No bank is currently logged in.");
            return null;
        }
        for (Account acc : loggedBank.getBANKACCOUNTS()) {
            if (acc.getAccountNumber().equals(num)) {
                    return acc;

            }
            return null;
        }
        return null;
    }
//    public static SavingsAccount showsavingAccounts()
//    {
//        for(Account accs: loggedBank.getBANKACCOUNTS())
//        {
//            if(accs.getClass().isInstance(SavingsAccount.class)){
//                return accs;
//            }
//        }
//    }

    public static int bankSize() {
        return BANKS.size();
    }
}