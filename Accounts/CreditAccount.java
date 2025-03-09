package Accounts;

import java.util.*;
import Bank.*;
public class CreditAccount extends Account{
    protected double Loan;

    public CreditAccount(Bank bank, String accountNumber, String ownerFName, String ownerLName, String ownerEmail, String pin)
    {
        super( bank,  accountNumber,  ownerFName,  ownerLName,  ownerEmail,  pin);
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.ownerFName = ownerFName;
        this.ownerLName = ownerLName;
        this.ownerEmail = ownerEmail;
        this.pin = pin;
        this.transactions = new ArrayList<>();
    }
    public String getLoanStatement()
    {
        return "";
    }
    protected boolean canCredit(double amountAdjustment)
    {
        //TO-DO: Complete this method
        return false;
    }
    protected void adjustLoanAmount(double amountAdjustment)
    {

    }
    public String toString()
    {
        return "";
    }

}
