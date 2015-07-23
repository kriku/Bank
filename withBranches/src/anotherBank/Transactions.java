package anotherBank;

/**
 * Created by krikun on 23.07.2015.
 */
public interface Transactions {

    /**
     * get money from account
     * @param sum
     */
    double withdraw(Customer customer, double sum);

    /**
     * put money to the account
     * @param sum
     */
    double deposit(Customer customer, double sum);

    /**
     * add percents of balance
     */
    double payInterests(Customer customer);

    /**
     * transfer money from transmitter to receiver
     * @param transmitter
     * @param receiver
     * @param sum
     */
    double transfer(Customer transmitter, Customer receiver, double sum);

}