package TestFile;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Accounts.SavingsAccount;
import Bank.Bank;

public class TestAccount {
    private Bank testBank;
    private SavingsAccount testAccount;

    @Before
    public void setUp() {
        // Create a test bank
        testBank = new Bank(123, "BDO", "1234");

        // Create a savings account with an initial balance of 1000
        testAccount = new SavingsAccount(testBank, "SA123", "John", "Doe", "john.doe@example.com", "4321", 1000.00);
    }

    @Test
    public void testInitialBalance() {
        assertEquals(1000.00, testAccount.getBalance(), 0.01);  // ✅ Changed method to getBalance()
    }

    @Test
    public void testValidDeposit() {
        testAccount.cashDeposit(500);
        assertEquals(1500.00, testAccount.getBalance(), 0.01);  // ✅ Changed method to getBalance()
    }

    @Test
    public void testNegativeDeposit() {
        testAccount.cashDeposit(-100);
        assertEquals(1000.00, testAccount.getBalance(), 0.01); // ✅ Changed method to getBalance()
    }

    @Test
    public void testValidWithdrawal() {
        boolean success = testAccount.withdrawal(300);
        assertTrue(success);
        assertEquals(700.00, testAccount.getBalance(), 0.01);  // ✅ Changed method to getBalance()
    }

    @Test
    public void testInsufficientFunds() {
        boolean success = testAccount.withdrawal(2000);
        assertFalse(success);
        assertEquals(1000.00, testAccount.getBalance(), 0.01); // ✅ Changed method to getBalance()
    }

    @Test
    public void testExactBalanceWithdrawal() {
        boolean success = testAccount.withdrawal(1000);
        assertTrue(success);
        assertEquals(0.00, testAccount.getBalance(), 0.01);  // ✅ Changed method to getBalance()
    }

    @Test
    public void testNegativeWithdrawal() {
        boolean success = testAccount.withdrawal(-100);
        assertFalse(success);
        assertEquals(1000.00, testAccount.getBalance(), 0.01); // ✅ Changed method to getBalance()
    }
}
