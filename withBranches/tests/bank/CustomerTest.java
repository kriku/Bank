package bank;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by krikun on 7/26/2015.
 */
public class CustomerTest {

    static Calendar calendar = Calendar.getInstance();
    static Customer customer;


    @Before
    public void setCustomer() {
        calendar.set(1990, Calendar.JANUARY, 13);
        customer = new Customer(null, 100, "firstName", "lastName", calendar.getTime());
    }

    @Test
    public void testGetBalance() throws Exception {
        Customer customer = new Customer(null, 100, "firstName", "lastName", new Date());
        assertEquals(100, customer.getBalance(), 0.0001);
    }

    @Test
    public void testSetBalance() throws Exception {
        Customer customer = new Customer(null, 100, "firstName", "lastName", new Date());
        customer.setBalance(1000);
        assertEquals(1000, customer.getBalance(), 0.0001);
    }

    @Test
    public void testGetBalanceString() throws Exception {
        Customer customer = new Customer(null, 100, "firstName", "lastName", new Date());
        customer.setBalance(1000.059);
        assertEquals("1000.06", customer.getBalanceString());
    }

    @Test
    public void testGetFirstName() throws Exception {
        Customer customer = new Customer(null, 100, "firstName", "lastName", new Date());
        assertEquals("firstName", customer.getFirstName());
    }

    @Test
    public void testSetFirstName() throws Exception {
        Customer customer = new Customer(null, 100, "firstName", "lastName", new Date());
        customer.setFirstName("newFirstName");
        assertEquals("newFirstName", customer.getFirstName());
    }

    @Test
    public void testGetLastName() throws Exception {
        Customer customer = new Customer(null, 100, "firstName", "lastName", new Date());
        assertEquals("lastName", customer.getLastName());
    }

    @Test
    public void testSetLastName() throws Exception {
        Customer customer = new Customer(null, 100, "firstName", "lastName", new Date());
        customer.setLastName("newLastName");
        assertEquals("newLastName", customer.getLastName());
    }

    @Test
    public void testGetBirthDate() throws Exception {
        assertEquals(calendar.getTime(), customer.getBirthDate());
    }

    @Test
    public void testSetBirthDate() throws Exception {
        calendar.set(1966, Calendar.FEBRUARY, 4);
        customer.setBirthDate(calendar.getTime());
        assertEquals(calendar.getTime(), customer.getBirthDate());
    }

    @Test
    public void testGetBranch() throws Exception {
        assertEquals(null, customer.getBranch());
    }

    @Test
    public void testSetBranch() throws Exception {
        Branch testBranch = new Branch("testBranch");
        customer.setBranch(testBranch);
        assertEquals(testBranch, customer.getBranch());
    }

    @Test
    public void testGetPercent() throws Exception {
        assertEquals(0, customer.getPercent(), 0.0001);
    }

    @Test
    public void testSetPercent() throws Exception {
        customer.setPercent(0.01);
        assertEquals(0.01, customer.getPercent(), 0.0001);
    }

    @Test
    public void testGetFullName() throws Exception {
        assertEquals("firstName lastName", customer.getFullName());
    }

    @Test
    public void testGetInfoString() throws Exception {
        assertEquals("#id[" + customer.getId() + "] " + customer.getFullName() +
                " [current balance: " + customer.getBalanceString() + "]", customer.getInfoString());
    }

    @Test
    public void testAddTransaction() throws Exception {
        Transaction testTransaction = new Transaction("testTransaction", customer, 1000, Transaction.DEPOSIT);
        customer.addTransaction(testTransaction);
        assertEquals(1, customer.getTransactionCount());
    }
}