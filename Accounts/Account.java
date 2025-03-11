package Accounts;

import java.util.ArrayList;
import Bank.Bank;

public class Account
{
    String ACCOUNTNUMBER, OWNERFNAME, OWNERLNAME, OWNEREMAIL;
    String pin;
    ArrayList <Transaction> TRANSACTIONS= new ArrayList<>();

    public Account(){}
    public Account(Bank Bank,String ACCOUNTNUMBER, String OWNERFNAME, String OWNERLNAME, String OWNEREMAIL, String pin)
    {
        this.ACCOUNTNUMBER=ACCOUNTNUMBER;
        this.OWNERFNAME=OWNERFNAME;
        this.OWNERLNAME=OWNERLNAME;
        this.OWNEREMAIL=OWNEREMAIL;
        this.pin=pin;
    }
    /**Return fullname
     */
    public String getOwnerFullName()
    {
        return this.OWNERLNAME+", "+this.OWNERFNAME;
    }

    /**Add Transaction+description to Database/JSON
     */
    public void addNewTransaction(String Accnum, Transaction.Transactions transaction, String Description)
    {
        //TO-DO: Complete this method
        //Date/Timestamp: Activity
    }

    /**
     * Return Transaction Logs
     */
    public String getTransactions()
    {
        return "";
    }

    /**Account Number:
     *Account Name:
     *Email:
     */
    public String toString()
    {

        return "";
    }

}
