package Launchers;

import Accounts.*;
import Bank.*;

import java.util.*;

public class BankLauncher {
    // Private attributes
    private static final List<Bank> BANKS = new ArrayList<>();
    private Bank loggedBank = null;

    public boolean isLogged() {
        return loggedBank != null;
    }

    public void bankInit() {
        // Example: create default banks
//        createNewBank(001, "Alpha Bank");
//        createNewBank(002, "Beta Bank");
    }

    public void showAccounts() {
        if (!isLogged()) {
            System.out.println("No bank is currently logged in.");
            return;
        }
        List<Account> accounts = loggedBank.getBANKACCOUNTS();
        if (accounts.isEmpty()) {
            System.out.println("No accounts in " + loggedBank.getName());
        } else {
            System.out.println("Accounts in " + loggedBank.getName() + ":");
            for (Account acc : accounts) {
                System.out.println(" - " + acc);
            }
        }
    }

    public void newAccounts(String accountType, Bank bank, String accNum, String ownerFName, String ownerLName, String ownerEmail, String pin, double balance) {
        if (!isLogged()) {
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
    public void bankLogin(String bankName) {
        Bank found = null;
        for (Bank b : BANKS) {
            if (b.getName().equalsIgnoreCase(bankName)) {
                found = b;
                break;
            }
        }
        if (found != null) {
            loggedBank = found;
            System.out.println("Logged in to bank: " + found.getName());
        } else {
            System.out.println("Bank not found with ID or name: " + bankName);
        }
    }

    public void setLogSession(Bank b) {
        this.loggedBank = b;
        if (b != null) {
            System.out.println("Session set to bank: " + b.getName());
        } else {
            System.out.println("No bank selected.");
        }
    }

    public void createNewBank(int ID, String name, String password, double  DEPOSITLIMIT, double WITHDRAWLIMIT, double CREDITLIMIT, double processingFee) {
        Bank newBank = new Bank( ID, name, password,  DEPOSITLIMIT, WITHDRAWLIMIT, CREDITLIMIT, processingFee);
        BANKS.add(newBank);
        System.out.println("Created new bank: " + name + " (ID=" + ID + ")");
    }

    public void showBanksMenu() {
        if (BANKS.isEmpty()) {
            System.out.println("No banks available.");
            return;
        }
        System.out.println("List of Banks:");
        for (int i = 0; i < BANKS.size(); i++) {
            Bank b = BANKS.get(i);
            System.out.printf("[%d] %s (ID=%s)\n", i + 1, b.getName(), b.getID());
        }
    }

    /**
     * Retrieves the currently logged-in bank.
     * This method fixes the "undefined for the type banklauncher" error.
     */
    public Bank getLoggedBank() {
        return loggedBank;
    }

    public Bank getBank(Comparator<Bank> comparator, Bank bank) {
        for (Bank b : BANKS) {
            if (comparator.compare(b, bank) == 0) {
                return b;
            }
        }
        return null;
    }

    public Accounts.Account findAccount(String num) {
        if (!isLogged()) {
            System.out.println("No bank is currently logged in.");
            return null;
        }
        for (Account acc : loggedBank.getBANKACCOUNTS()) {
            if (acc.getAccountNumber().equals(num)) {
                return acc;
            }
        }
        return null;
    }

    public int bankSize() {
        return BANKS.size();
    }
}