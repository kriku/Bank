package bank;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by krikun on 7/27/2015.
 */
public class TransactionTest {

    @Test
    public void testGetTransactionString() throws Exception {
        Customer customer = new Customer(null, 0, "", "", null);
        Transaction transaction = new Transaction("test", customer, 10, 0);
        assertEquals(" #" + transaction.getId() + " test change amount: 10 involved customers (id): [" +
                customer.getId() + "]", transaction.getTransactionString().substring(25));
    }
}