package Bank;

import Accounts.*;
import Main.Field;

import java.util.*;

public class Bank {


    protected int ID;
    protected String name, password;
    protected double DEPOSITLIMIT, WITHDRAWLIMIT,CREDITLIMIT;
    protected double processingFee;
    protected ArrayList <Account> BANKACCOUNTS;




    public Bank(int ID, String name, String password) {
        this.ID = ID;
        this.name = name;
        this.password = password;
        this.BANKACCOUNTS = new ArrayList<>();
    }

    public Bank(int ID, String name, String password, double  DEPOSITLIMIT, double WITHDRAWLIMIT, double CREDITLIMIT, double processingFee)
    {
        this(ID, name, password);
        this.DEPOSITLIMIT=DEPOSITLIMIT;
        this.WITHDRAWLIMIT=WITHDRAWLIMIT;
        this.CREDITLIMIT=CREDITLIMIT;
        this.processingFee=processingFee;
    }

    public String getName() {
        return name;
    }
    public int getID() {
        return ID;
    }
    public ArrayList<Account> getBANKACCOUNTS() {
        return BANKACCOUNTS;
    }

    public <T> void showAccounts(Class<T> accountType) {
        for (Account account : BANKACCOUNTS) {
            if (accountType.isInstance(account)) {
                System.out.println(account);
            }
        }
    }
    public Account getBankAccount(String accountNum) {
        for (Account account : BANKACCOUNTS) {
            if (account.getAccountNumber().equals(accountNum)) {
                return account;
            }
        }
        return null;
    }
    public ArrayList<Field<String, ?>> createNewAccount() {
        return new ArrayList<>();
    }
    public CreditAccount createNewCreditAccount(Bank bank, String accountNumber, String ownerFName, String ownerLName, String ownerEmail, String pin) {
        return new CreditAccount( bank,  accountNumber,  ownerFName,  ownerLName,  ownerEmail,  pin);
    }

    public SavingsAccount createNewSavingsAccount(Bank bank, String accNum, String ownerFName, String ownerLName, String ownerEmail, String pin, double balance) {
        return new SavingsAccount(bank, accNum, ownerFName, ownerLName,ownerEmail, pin,balance);
    }

    public void addNewAccount(Account account) {
        BANKACCOUNTS.add(account);
    }

    public static boolean accountExists(Bank bank, String accountNum) {
        for(Account accs: bank.BANKACCOUNTS)
        {
            if(accs.getAccountNumber().equals(accountNum))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Bank ID: " + ID + ", Name: " + name;
    }


}
//Classes
class BankComparator implements Comparator<Bank> {
    @Override
    public int compare(Bank b1, Bank b2) {
        return b1.name.compareTo(b2.name);
    }
}

class BankIdComparator implements Comparator<Bank> {
    @Override
    public int compare(Bank b1, Bank b2) {
        return Integer.compare(b1.ID, b2.ID);
    }
}

class BankCredentialsComparator implements Comparator<Bank> {
    @Override
    public int compare(Bank b1, Bank b2) {
        return b1.password.compareTo(b2.password);
    }
}
