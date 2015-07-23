import Bank.Bank;
import Bank.Branch;
import Bank.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by krikun on 23.07.2015.
 */
public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();
        // add branches
        for (int i=0; i<3; i++) {
            bank.addBranch("branch" + i);
        }

        Random rnd = new Random();

        ArrayList<Customer> customers = new ArrayList<>();
        //add customers
        for (int i=0; i<100; i++) {
            Branch randomBranch = bank.getBranch(rnd.nextInt(bank.getCountBranch()));
            Customer customer = new Customer(randomBranch, rnd.nextDouble()*10000000, "customer" + i, "last", new Date());
            randomBranch.addCustomer(customer);
            customers.add(customer);
        }

        for (int i=0; i<100; i++) {
            Branch randomBranch = bank.getBranch(rnd.nextInt(bank.getCountBranch()));

            Customer randomCustomer = randomBranch.getCustomer(rnd.nextInt(randomBranch.getCustomersCount()));
            if (randomCustomer!=null) {
                int method = rnd.nextInt(4);
                switch (method) {
                    case 0:
                        randomCustomer.deposit(rnd.nextDouble()*1000);
                        break;
                    case 1:
                        randomCustomer.withdraw(rnd.nextDouble()*1000);
                        break;
                    case 2:
                        Branch randomBranch2 = bank.getBranch(rnd.nextInt(bank.getCountBranch()));

                        Customer randomReceiverCustomer;
                        do {
                            randomReceiverCustomer = randomBranch2.getCustomer(rnd.nextInt(randomBranch2.getCustomersCount()));
                        } while (randomCustomer.getId() == randomReceiverCustomer.getId());
                        if (randomReceiverCustomer!=null) {
                            randomCustomer.transfer(randomReceiverCustomer, rnd.nextDouble()*1000);
                        }
                        break;
                    case 3:
                        randomCustomer.payInterests();
                        break;
                }

            }
        }
        bank.getBranch(0).printTransactions();
        System.out.println(bank.getBranch(0).getCustomer(0).getLog());
        int end = 0;

//        Scanner scanner = new Scanner(System.in);
//        int choise=0;
//
//        do {
//            System.out.println("choice log: \n1: customer\n");
//            choise = scanner.nextInt();
//            switch (choise) {
//                case 1:
//
//            }
//
//        } while (choise!=0);

    }
}
