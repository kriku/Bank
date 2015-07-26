package bank;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by krikun on 7/27/2015.
 */
public class BranchTest {

    Branch branch;

    @Before
    public void initBranch() {
        branch = new Branch("testBranch");
    }


    @Test
    public void testGetName() throws Exception {
        assertEquals("testBranch", branch.getName());
    }

    @Test
    public void testAddGetCustomerAndCount() throws Exception {
        Customer customer = new Customer(branch, 1000, "test", "test", new Date());
        branch.addCustomer(customer);
        assertEquals(customer, branch.getCustomer(0));
        assertEquals(1, branch.getCustomersCount());
    }
}