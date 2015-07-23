package Bank;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by krikun on 23.07.2015.
 */
public class Branch {
    private String name;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private ArrayList<String> log = new ArrayList<>();

    public Branch(String name) {
        this.name = name;
    }

    public Customer getCustomer(int index) {
        return customers.get(index);
    }

    public int getCustomersCount() {
        return customers.size();
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.setBranch(this);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void printCustomers() {
        ListIterator<Customer> iterator = customers.listIterator();
        do {
            Customer customer = iterator.next();
            System.out.println(customer.getListString());
        } while (iterator.hasNext());
    }

    public void printLog() {
        System.out.println("BRANCH " + name + " LOG:\n");
        ListIterator<String> iterator = log.listIterator();
        do {
            String message = iterator.next();
            System.out.println(message);
        } while (iterator.hasNext());
    }

    public void printTransactions() {
        ListIterator<Transaction> iterator = transactions.listIterator();
        do {
            System.out.println(iterator.next().getTransaction());
        } while (iterator.hasNext());
    }
}
