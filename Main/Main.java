package Main;

import java.util.ArrayList;
import java.util.Scanner;

import Accounts.Account;
import Bank.*;
import Launchers.*;

public class Main
{

    private static final Scanner input = new Scanner(System.in);
    /**
     * Option field used when selection options during menu prompts. Do not create a different
     * option variable in menus. Just use this instead. <br>
     * As to how to utilize Field objects properly, refer to the following:
     * 
     * @see #prompt(String, boolean)
     * @see #setOption() How Field objects are used.
     */
    public static Field<Integer, Integer> option = new Field<Integer, Integer>("Option",
            Integer.class, -1, new Field.IntegerFieldValidator());
    public static void main(String[] args)
    {
        BankLauncher BLauncher = new BankLauncher();
        ArrayList<Bank> Banks= new ArrayList<>();
        Bank CheckBank = null;
        Bank Bank1 = new Bank(1,"BDO","123",10000.0,10000.0,1000.0,100.0);
//        Account acc1=new Account("BDO","1234","Janmarc","Pulmones","example@gmail.com","12345");
//        Bank1.addNewAccount(acc1);
        while (true)
        {
            showMenuHeader("Main Menu");
            showMenu(1);
            setOption();
            // Account Option
            if (getOption() == 1)
            {
                // READ ME: Refer to this code block on how one should properly utilize
                // showMenuHeader(), showMenu(),
                // setOption(), and getOption() methods...
                showMenuHeader("Account Login Menu");
                showMenu(2, 1);
                setOption();
//                showMenu(getOption(), 1);
                // TODO: Complete this portion
                if(getOption()==1)
                {
                    showMenu(33);
                    setOption();
                    if(getOption()==1)
                    {
                        showMenuHeader("Credit Account");
                        CreditAccountLauncher caLauncher = new CreditAccountLauncher();
                    }
                    else if(getOption()==2)
                    {
                        showMenuHeader("Savings Account");
                        SavingsAccountLauncher SALauncher = new SavingsAccountLauncher();
                        System.out.print("Enter Savings Account Number: ");
                        String accNumber = input.nextLine();
                        Account checkAccount =BLauncher.findAccount(accNumber);
                        if(checkAccount!=null)
                        {
                            System.out.print("Enter PIN: ");
                            String Pin = input.nextLine();
                            if(checkAccount.getPin().equals(Pin))
                            {
                                SALauncher.selectBank(checkAccount.getBank());
                                SALauncher.login(accNumber,Pin);
                                if(SALauncher.isLoggedIn())
                                {
                                    //show SALauncher menu
                                    showMenuHeader("Savings Account");
                                    showMenu(51);
                                    setOption();
                                    SALauncher.savingsAccountInit(SALauncher,getOption());
                                }
                            }
                        }
                        else{System.out.println("Account does not exist!");}

                    }
                }
                else if(getOption()==0)
                {
                    break;
                }


            }
            // Bank Option
            else if (getOption() == 2)
            {
                // TODO: Complete Bank option
                showMenuHeader("Banks");
                showMenuHeader("Bank Login");
                showMenu(3);
                setOption();
                //Assuming successful login
                //if(isLogged == true){}
                showMenuHeader("My Accounts");
                showMenu(31);
                setOption();
            }
            // Create New Bank
            else if (getOption() == 3)
            {
                // TODO: Complete this portion...
                showMenuHeader("Creating New Bank");
            }
            else if (getOption() == 4)
            {
                System.out.println("Exiting. Thank you for banking!");
                break;
            }
            else
            {
                System.out.println("Invalid option!");
            }
        }
    }

    /**
     * Show menu based on index given. <br>
     * Refer to Menu enum for more info about menu indexes. <br>
     * Use this method if you want a single menu option every line.
     * 
     * @param menuIdx Main.Menu index to be shown
     */
    public static void showMenu(int menuIdx)
    {
        showMenu(menuIdx, 1);
    }

    /**
     * Show menu based on index given. <br>
     * Refer to Menu enum for more info about menu indexes.
     * 
     * @param menuIdx Main.Menu index to be shown
     * @param inlineTexts Number of menu options in a single line. Set to 1 if you only want a
     *        single menu option every line.
     * @see Menu
     */
    public static void showMenu(int menuIdx, int inlineTexts)
    {
        String[] menu = Menu.getMenuOptions(menuIdx);
        if (menu == null)
        {
            System.out.println("Invalid menu index given!");
        }
        else
        {
            String space = inlineTexts == 0 ? "" : "%-20s";
            String fmt = "[%d] " + space;
            int count = 0;
            for (String s : menu)
            {
                count++;
                System.out.printf(fmt, count, s);
                if (count % inlineTexts == 0)
                {
                    System.out.println();
                }
            }
        }
    }

    /**
     * Prompt some input to the user. Only receives on non-space containing String. This string can
     * then be parsed into targeted data type using DataTypeWrapper.parse() method.
     * 
     * @param phrase Prompt to the user.
     * @param inlineInput A flag to ask if the input is just one entire String or receive an entire
     *        line input. <br>
     *        Set to <b>true</b> if receiving only one String input without spaces. <br>
     *        Set to <b>false</b> if receiving an entire line of String input.
     * @return Value of the user's input.
     * @see Field#setFieldValue(String, boolean) How prompt is utilized in Field.
     */
    public static String prompt(String phrase, boolean inlineInput)
    {
        System.out.print(phrase);
        if (inlineInput)
        {
            String val = input.next();
            input.nextLine();
            return val;
        }
        return input.nextLine();
    }

    /**
     * Prompts user to set an option based on menu outputted.
     * 
     * @throws NumberFormatException May happen if the user attempts to input something other than
     *         numbers.
     */
    public static void setOption() throws NumberFormatException
    {
        option.setFieldValue("Select an option: ");
    }

    /**
     * @return Recently inputted option by the user.
     */
    public static int getOption()
    {
        return Main.option.getFieldValue();
    }

    /**
     * Used for printing the header whenever a new menu is accessed.
     * 
     * @param menuTitle Title of the menu to be outputted.
     */
    public static void showMenuHeader(String menuTitle)
    {
        System.out.printf("<---- %s ----->\n", menuTitle);
    }
}
