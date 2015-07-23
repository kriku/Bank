package Bank;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by krikun on 23.07.2015.
 */
public class Customer {
    private static int idGenerator=0;
    private int id;
    private double balance;
    private double percent = 0;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private ArrayList<String> log = new ArrayList<>();
    private Branch branch;

    public Customer(Branch branch, double balance, String firstName, String lastName, Date birthDate) {
        this.branch = branch;
        this.balance = balance;
        calcPercent();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.id = idGenerator;
        idGenerator++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    public ArrayList<String> getLog() {
        return log;
    }

    public void setLog(ArrayList<String> log) {
        this.log = log;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void addLogMessage(String message) {
        this.log.add(message);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getListString() {
        return "#" + id + " " + firstName + " " + lastName + " [balance: " + balance + "]";
    }

    public void calcPercent() {
        double percent = 0;
        if (balance > 5000000) {
            this.percent = 0.01;
        }
        if (balance > 5000) {
            this.percent = 0.05;
        }
    }

    public double deposit(double balance) {
        if (balance < 0) {
            return 0;
        }
        this.balance += balance;
        log.add("deposit: " + balance);
        Transaction transaction = new Transaction("deposit", this, balance, Transaction.DEPOSIT);
        this.branch.addTransaction(transaction);
        return balance;
    }

    public double withdraw(double balance) {
        if ((balance < 0)||(this.balance < balance)) {
            return 0;
        }
        this.balance -= balance;
        log.add("withdraw: " + balance);
        Transaction transaction = new Transaction("withdraw", this, balance, Transaction.WITHDRAW);
        this.branch.addTransaction(transaction);
        return balance;
    }

    public double transfer(Customer customer, double balance) {
        if (this.withdraw(balance) == 0) {
            return 0;
        }
        customer.deposit(balance);
        log.add("transfer: " + balance + " to: #" + customer.getId() + " " + customer.getFullName());
        Transaction transaction = new Transaction("transfer", this, customer, balance);
        this.branch.addTransaction(transaction);
        return balance;
    }

    public double payInterests() {
        double sum = this.balance * this.percent;
        this.balance += sum;
        log.add("pay interests: " + sum);
        Transaction transaction = new Transaction("pay", this, balance, Transaction.PAY_INTERESTS);
        this.branch.addTransaction(transaction);
        return sum;
    }

//    TODO: add print full log of transactions

}
