package Bank;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by krikun on 23.07.2015.
 */
public class Bank implements Transactions {
    public static final int BUSINESS = 5000000;
    public static final int SAVING = 5000;
    public static final double BUSINESS_PERCENT = 0.01;
    public static final double SAVING_PERCENT = 0.05;

    public static double getAccountPercent(double balance) {
        if (balance >= Bank.BUSINESS) {
            return Bank.BUSINESS_PERCENT;
        }
        if (balance >= Bank.SAVING) {
            return Bank.SAVING_PERCENT;
        }
        return 0;
    }

    private ArrayList<Branch> branches = new ArrayList<>();

    public int getCountBranch() {
        return branches.size();
    }

    public Branch getBranch(int index) {
        if (index >= getCountBranch())
            return null;
        return branches.get(index);
    }

    public void addBranch(String name) {
        branches.add(new Branch(name));
    }

    public double deposit(Customer customer, double balance) {
        if (balance <= 0) {
            return 0;
        }
        customer.setBalance(customer.getBalance() + balance);
        Transaction transaction = new Transaction("deposit", customer, balance, Transaction.DEPOSIT);
        customer.getBranch().addTransaction(transaction);
        customer.addTransaction(transaction);
        return balance;
    }

    public double withdraw(Customer customer, double balance) {
        if ((balance <= 0)||(customer.getBalance() < balance)) {
            return 0;
        }
        customer.setBalance(customer.getBalance() - balance);
        Transaction transaction = new Transaction("withdraw", customer, balance, Transaction.WITHDRAW);
        customer.getBranch().addTransaction(transaction);
        customer.addTransaction(transaction);
        return balance;
    }

    public double transfer(Customer from, Customer to, double balance) {
        if (withdraw(from, balance) == 0) {
            return 0;
        }
        Transaction transaction = new Transaction("transfer", from, to, balance);
        // add transaction to branch
        if (!from.getBranch().equals(to.getBranch())) {
            from.getBranch().addTransaction(transaction);
            to.getBranch().addTransaction(transaction);
        } else {
            // if customers in same branch add just once
            from.getBranch().addTransaction(transaction);
        }
        from.addTransaction(transaction);
        to.addTransaction(transaction);
        deposit(to, balance);
        return balance;
    }

    public double payInterests(Customer customer) {
        double sum = customer.getBalance()*customer.getPercent();
        customer.setBalance(customer.getBalance() + sum);
        Transaction transaction = new Transaction("pay", customer, sum, Transaction.PAY_INTERESTS);
        customer.getBranch().addTransaction(transaction);
        customer.addTransaction(transaction);
        return sum;
    }

    public String getBranchesNames() {
        String result = "";
        ListIterator<Branch> iterator = branches.listIterator();
        while (iterator.hasNext()) {
            result += iterator.nextIndex() + " - [" + iterator.next().getName() + "]";
            result += (iterator.hasNext())? "\n" : "";
        }
        return result;
    }


    public String getTransactionsString() {
        ListIterator<Branch> iterator = branches.listIterator();
        String result = "";
        while (iterator.hasNext()) {
            Branch branch = iterator.next();
            result += "========== BRANCH [" + branch.getName() + "]\n";
            result += branch.getTransactionsString();
            result += (iterator.hasNext())? "\n" : "";
        }
        return result;

    }

    public static String getFormatedDouble(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }

}
