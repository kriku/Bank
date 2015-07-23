package anotherBank;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by krikun on 23.07.2015.
 */
public class Branch {
    private String name;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Branch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Customer getCustomer(int index) {
        return customers.get(index);
    }

    public int getCustomersCount() {
        return customers.size();
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.setBranch(this);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public String getCustomersString() {
        ListIterator<Customer> iterator = customers.listIterator();
        String result = "";
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            result += iterator.nextIndex() + " - " + customer.getListString();
            result += (iterator.hasNext())? "\n" : "";
        }
        return result;
    }

    public String getTransactionsString() {
        ListIterator<Transaction> iterator = transactions.listIterator();
        String result = "";
        while (iterator.hasNext()) {
            result += iterator.next().getTransactionString();
            result += (iterator.hasNext())? "\n" : "";
        }
        return result;
    }
}
