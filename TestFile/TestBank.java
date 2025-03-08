package TestFile;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Launcher.BankLauncher;
import Bank.Bank;
import java.util.Comparator;
import java.lang.reflect.Field;

public class TestBank {
    private Bank testBank;
    private Bank anotherBank;

    @Before
    public void setUp() throws Exception {
        // Reset banks and ensure a clean state before each test
        clearBanks();

        // Create test banks
        testBank = new Bank(1, "TestBank", "1234");
        anotherBank = new Bank(2, "SecondBank", "5678");

        // Add banks
        BankLauncher.addBank(testBank);
        BankLauncher.addBank(anotherBank);
    }

    // Utility method to clear banks list using reflection (needed since banks list is private)
    private void clearBanks() throws Exception {
        Field banksField = BankLauncher.class.getDeclaredField("banks");
        banksField.setAccessible(true);
        ((java.util.ArrayList<?>) banksField.get(null)).clear();
    }

    @Test
    public void testSetLogSession() {
        BankLauncher.setLogSession(testBank);
        assertEquals("TestBank", testBank.getName());
    }

    @Test
    public void testBankLogout() {
        BankLauncher.setLogSession(testBank);
        BankLauncher.logout();

        // Bank should no longer be logged in
        Field loggedBankField;
        try {
            loggedBankField = BankLauncher.class.getDeclaredField("loggedBank");
            loggedBankField.setAccessible(true);
            assertNull(loggedBankField.get(null));  // Ensure no bank is logged in
        } catch (Exception e) {
            fail("Reflection error: " + e.getMessage());
        }
    }

    @Test
    public void testAddBank() {
        Bank newBank = new Bank(3, "NewBank", "9876");
        BankLauncher.addBank(newBank);

        assertEquals(3, BankLauncher.bankSize());  // Should have 3 banks now
    }

    @Test
    public void testDuplicateBankNotAllowed() {
        Bank duplicateBank = new Bank(1, "TestBank", "0000"); // Same ID as testBank
        BankLauncher.addBank(duplicateBank);

        assertEquals(2, BankLauncher.bankSize());  // Should remain 2 since duplicate ID isn't allowed
    }

    @Test
    public void testFindBankByName() {
        Bank foundBank = BankLauncher.getBank(Comparator.comparing(Bank::getName), new Bank(0, "TestBank", ""));
        assertNotNull(foundBank);
        assertEquals("TestBank", foundBank.getName());
    }

    @Test
    public void testFindBankById() {
        Bank foundBank = BankLauncher.getBank(Comparator.comparingInt(Bank::getId), new Bank(1, "", ""));
        assertNotNull(foundBank);
        assertEquals(1, foundBank.getId());
    }

    @Test
    public void testFindNonExistentBank() {
        Bank foundBank = BankLauncher.getBank(Comparator.comparingInt(Bank::getId), new Bank(99, "", ""));
        assertNull(foundBank);
    }
}
