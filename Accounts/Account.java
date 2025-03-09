package Accounts;

import java.util.ArrayList;
import Bank.*;

public class Account {
    protected Bank bank;
    protected String accountNumber;
    protected String ownerFName,ownerLName,ownerEmail;
    protected String pin;
    protected ArrayList<Transaction> transactions;
    public Account(Bank bank, String accountNumber, String ownerFName, String ownerLName, String ownerEmail, String pin) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.ownerFName = ownerFName;
        this.ownerLName = ownerLName;
        this.ownerEmail = ownerEmail;
        this.pin = pin;
        this.transactions = new ArrayList<>();
    }
    public Bank getBank()
    {
        return this.bank;
    }
    public String getPin() {
        return pin;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerFullName() {
        return ownerFName + " " + ownerLName;
    }

    public void addNewTransaction(String accountNum, Transaction.Transactions type, String description) {
        transactions.add(new Transaction(accountNum, type, description));
    }

    public String getTransactionsInfo() {
        StringBuilder info = new StringBuilder();
        info.append("=====Transactions=====");
        for (Transaction transaction : transactions) {
            info.append(transaction.toString()).append("\n");
        }
        return info.toString();
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Owner: " + getOwnerFullName();
    }
}

