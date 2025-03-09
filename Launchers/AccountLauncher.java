package Launchers;

import Accounts.*;
import Bank.*;

public abstract class AccountLauncher {
    protected Account loggedAccount; // Holds the currently logged-in account
    protected Bank assocBank;        // Associated bank

    // Method to check if a user is logged in
    public boolean isLoggedIn() {
        return loggedAccount != null;
    }

    public void login(String accountNum, String pin)
    {
        this.loggedAccount= assocBank.getBankAccount(accountNum);
    }

    public void selectBank(Bank bank) {
        this.assocBank = bank;
    }
    public void setLogSession(Bank bank) {
        assocBank = bank;
        if (bank != null)
        {
            System.out.println("New Session for: " + bank.getName());
        }
        else
        {
            System.out.println("No bank selected.");
        }
    }
    public void logout() {
        this.loggedAccount = null;
        System.out.println("Logged out successfully.");
    }

    public Account checkCredentials(Bank bank, String accountNumber, String pin) {
        if (assocBank != null) {
            Account account = bank.getBankAccount( accountNumber);
            if (account != null && account.getPin().equals(pin)) {
                return account;
            }
        }
        return null;
    }

    public Account getLoggedAccount() {
        return loggedAccount;
    }
}