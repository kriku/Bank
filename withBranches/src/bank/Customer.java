package bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

/**
 * Created by krikun on 23.07.2015.
 */
public class Customer {
    private static int idGenerator=0;
    private int id;
    private double balance;
    private double percent;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private Branch branch;

    public Customer(Branch branch, double balance, String firstName, String lastName, Date birthDate) {
        this.branch = branch;
        this.balance = balance;
        this.percent = Bank.getAccountPercent(balance);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.id = idGenerator;
        idGenerator++;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBalanceString() {
        return Bank.getFormatedDouble(balance);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getInfoString() {
        return "#id[" + id + "] " + getFullName() + " [current balance: " + Bank.getFormatedDouble(balance) + "]";
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public String getTransactionsString() {
        ListIterator<Transaction> iterator = transactions.listIterator();
        String result = "";

        while (iterator.hasNext()){
            result += iterator.next().getTransactionString();
            result += (iterator.hasNext())? "\n" : "";
        }

        if (transactions.isEmpty()) {
            return "customer has no transactions";
        }
        return result;
    }

    public int getTransactionCount() {
        return transactions.size();
    }

}
