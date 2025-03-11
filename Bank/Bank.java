package Bank;

import Accounts.Account;
import java.util.ArrayList;

public class Bank {

    int ID;
    String name, password;
    double DEPOSITLIMIT, WITHDRAWLIMIT,CREDITLIMIT;
    double processingFee;
    ArrayList <Account> BANKACCOUNTS;

    //Constructors
    public Bank(){}
    public Bank(int id, String name, String password){}

    public Bank(int id, String name, String password, double  DEPOSITLIMIT, double WITHDRAWLIMIT, double CREDITLIMIT, double processingFee)
    {
        this.ID=id;
        this.name=name;
        this.password=password;
        this.DEPOSITLIMIT=DEPOSITLIMIT;
        this.WITHDRAWLIMIT=WITHDRAWLIMIT;
        this.CREDITLIMIT=CREDITLIMIT;
        this.processingFee=processingFee;
    }

    //Methods
//    public void showAccounts(Class<T> accuntType){}

}
