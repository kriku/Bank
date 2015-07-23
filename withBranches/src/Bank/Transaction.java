package Bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

/**
 * Created by krikun on 23.07.2015.
 */
public class Transaction {
    // transactions types
    public static final int DEPOSIT = 1;
    public static final int WITHDRAW = 2;
    public static final int PAY_INTERESTS = 3;
    public static final int TRANSFER = 4;

    private static int idGererator=0;
    private int id;
    private String description;
    private int type;
    private double balance;
    private Date date;
    private ArrayList<Customer> customers = new ArrayList<>();

    /**
     * constructor for default transaction
     * @param description
     * @param customer
     * @param type
     */
    public Transaction(String description, Customer customer, double balance, int type) {
        this.description = description;
        this.date = new Date();
        this.customers.add(customer);
        this.id = idGererator;
        idGererator++;
    }

    /**
     * constructor for transfer transaction
     * @param description
     * @param from
     * @param to
     */
    public Transaction(String description, Customer from, Customer to, double balance) {
        this(description, from, balance, TRANSFER);
        this.customers.add(to);
    }

    public String getTransaction() {
        String result;
        result = "#" + id + " " + description + " [" + date.toString() + "]\n";
        result += "customers: [";

        ListIterator<Customer> iterator = customers.listIterator();
        do {
            Customer customer = iterator.next();
            result += customer.getId();
            if (iterator.hasNext()) result += ", ";
        } while (iterator.hasNext());

        result += "]";

        return result;
    }
}
