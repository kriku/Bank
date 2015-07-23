package bank;

import java.text.SimpleDateFormat;
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
    public static final int ERROR = -1;

    private static int idGererator=0;
    private int id;
    private String description;
    private int type;
    private double balance;
    private Date date;
    private ArrayList<Customer> customers = new ArrayList<>();

    /**
     * constructor for deposit, withdraw, payInterests transactions
     * (there are one customer in list)
     * @param description
     * @param customer
     * @param balance
     * @param type
     */
    public Transaction(String description, Customer customer, double balance, int type) {
        this.description = description;
        this.date = new Date();
        this.balance = balance;
        this.customers.add(customer);
        this.id = idGererator;
        idGererator++;
    }

    /**
     * constructor for transfer transaction
     * (in this transaction two customers: first - from, second - to
     * @param description
     * @param from
     * @param to
     * @param balance
     */
    public Transaction(String description, Customer from, Customer to, double balance) {
        this(description, from, balance, TRANSFER);
        this.customers.add(to);
    }

    /**
     * this method concat all parameters of transaction in one string
     * and customers list on another line, divided by comma
     * @return
     */
    public String getTransactionString() {
        String result;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SS");
        result = "[" + dateFormat.format(date) + "] " + "#" + id + " " +
                description + " change amount: " + Bank.getFormatedDouble(balance);
        result += " customers ids: [";

        ListIterator<Customer> iterator = customers.listIterator();
        while (iterator.hasNext()){
            Customer customer = iterator.next();
            result += customer.getId();
            if (iterator.hasNext()) result += ", ";
        }

        result += "]";

        return result;
    }
}
