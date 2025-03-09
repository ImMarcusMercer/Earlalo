package Accounts;

import Bank.*;
import Launchers.*;
import Accounts.Deposit;
import Accounts.FundTransfer;
import Accounts.IllegalAccountType;
import Accounts.Withdrawal;

public class SavingsAccount extends Account implements Withdrawal, Deposit, FundTransfer {
    private double balance;

    public SavingsAccount(Bank bank, String accNum, String ownerFName, String ownerLName, String ownerEmail, String pin, double balance) {
        super(bank, accNum, ownerFName,ownerLName, ownerEmail, pin);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountBalance() {
        return "Balance: " + balance;
    }

    // @Override
    public void insufficientBalance() {
        System.out.println("Insufficient balance!");
    }

    // @Override
    public void adjustAccountBalance(double amount) {
        this.balance += amount;
    }

    // @Override
    public boolean hasEnoughBalance(double amount) {
        return this.balance >= amount;
    }

    @Override
    public boolean transfer(Bank bank, Account account, double amount) throws IllegalAccountType {
        return transfer(account, amount);
    }

    @Override
    public boolean transfer(Account account, double amount) throws IllegalAccountType {

        if (amount > this.balance) {
            System.out.println("Insufficient Funds!");
            return false;
        }
        balance -= amount;
        addNewTransaction(accountNumber, Transaction.Transactions.FundTransfer,
                String.format("Transferred %.2f to %s", amount, account.getAccountNumber()));
        ((SavingsAccount) account).cashDeposit(amount);
        return true;
    }

    @Override
    public boolean cashDeposit(double amount)
    {
        this.balance+=amount;
        return true;
    }

    @Override
    public boolean withdrawal(double amount)
    {
        if (hasEnoughBalance(amount)) {
            adjustAccountBalance(- amount);
            System.out.println("Withdrawal successful. New balance: " + balance);
            return true;
        }
        insufficientBalance();
        return false;
    }
}
