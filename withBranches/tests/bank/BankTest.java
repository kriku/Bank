package bank;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by krikun on 7/27/2015.
 */
public class BankTest {

    Bank bank;

    @Before
    public void addTestBranch() {
        bank = new Bank();
        bank.addBranch("testBranch");
    }

    @Test
    public void testGetAccountPercent() throws Exception {
        assertEquals(Bank.BUSINESS_PERCENT, Bank.getAccountPercent(Bank.BUSINESS), 0.0001);
        assertEquals(Bank.SAVING_PERCENT, Bank.getAccountPercent(Bank.SAVING), 0.0001);
        assertEquals(0, Bank.getAccountPercent(Bank.SAVING - 1), 0.0001);
    }
    
    @Test
    public void testAddAndGetCountBranch() throws Exception {
        assertEquals(1, bank.getCountBranch());
    }

    @Test
    public void testGetBranch() throws Exception {
        Branch branch = bank.getBranch(0);
        assertEquals("testBranch", branch.getName());
    }

    @Test
    public void testDeposit() throws Exception {
        Customer customer = new Customer(bank.getBranch(0), 1000, "test", "test", new Date());
        assertEquals(1000, bank.deposit(customer, 1000), 0.0001);
        assertEquals(2000, customer.getBalance(), 0.0001);
        assertEquals(1, bank.getBranch(0).getTransactions().size());
    }

    @Test
    public void testWithdraw() throws Exception {
        Customer customer = new Customer(bank.getBranch(0), 1000, "test", "test", new Date());
        assertEquals(1000, bank.withdraw(customer, 1000), 0.0001);
        assertEquals(0, customer.getBalance(), 0.0001);
        assertEquals(1, bank.getBranch(0).getTransactions().size());
    }

    @Test
    public void testTransfer() throws Exception {
        Customer customer1 = new Customer(bank.getBranch(0), 1000, "test", "test", new Date());
        Customer customer2 = new Customer(bank.getBranch(0), 0, "test", "test", new Date());
        assertEquals(1000, bank.transfer(customer1, customer2, 1000), 0.0001);
        assertEquals(0, customer1.getBalance(), 0.0001);
        assertEquals(1000, customer2.getBalance(), 0.0001);
        assertEquals(3, bank.getBranch(0).getTransactions().size());
    }

    @Test
    public void testPayInterests() throws Exception {
        Customer customer1 = new Customer(bank.getBranch(0), 1000, "test", "test", new Date());
        Customer customer2 = new Customer(bank.getBranch(0), Bank.SAVING, "test", "test", new Date());
        Customer customer3 = new Customer(bank.getBranch(0), Bank.BUSINESS, "test", "test", new Date());
        assertEquals(0, bank.payInterests(customer1), 0.0001);
        assertEquals(Bank.SAVING * Bank.SAVING_PERCENT, bank.payInterests(customer2), 0.0001);
        assertEquals(Bank.BUSINESS * Bank.BUSINESS_PERCENT, bank.payInterests(customer3), 0.0001);
        assertEquals(3, bank.getBranch(0).getTransactions().size());
    }

    @Test
    public void testGetBranchesNames() throws Exception {
        assertEquals("testBranch", bank.getBranch(0).getName());
    }

    @Test
    public void testGetFormatedDouble() throws Exception {
        assertEquals("1000.06", Bank.getFormatedDouble(1000.059));
    }
}