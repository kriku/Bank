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
        assertEquals(" #0 test change amount: 10 involved customers (id): [0]", transaction.getTransactionString().substring(25));
    }
}