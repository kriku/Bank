import bank.Bank;
import bank.Branch;
import bank.Customer;
import bank.Names;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by krikun on 23.07.2015.
 */
public class Main {

    private static Random random = new Random();

    public static void main(String[] args) {

        Bank bank = new Bank();

        for (int i=0; i<5; i++) {
            bank.addBranch("branch " + i);
        }

        for (int i=0; i<100; i++) {
            Branch randomBranch = getRandomBranch(bank);
            Customer customer = new Customer(randomBranch, random.nextDouble()*10000000,
                    Names.getRandomFirstName(), Names.getRandomLastName(), new Date());
            randomBranch.addCustomer(customer);
        }

        for (int i=0; i<100; i++) {
            Customer customer = getRandomCustomer(bank);
            doRandomAction(bank, customer);
        }

        showTransactionMenu(bank);
    }

    private static Branch getRandomBranch(Bank bank) {
        return bank.getBranch(random.nextInt(bank.getCountBranch()));
    }

    private static Customer getRandomCustomer(Bank bank) {
        Branch branch = getRandomBranch(bank);
        while (branch.getCustomersCount() == 0) {
            branch = getRandomBranch(bank);
        }
        return branch.getCustomer(random.nextInt(branch.getCustomersCount()));
    }

    private static void doRandomAction(Bank bank, Customer customer) {
        int action = random.nextInt(4);
        double amount = random.nextDouble()*1000000;
        switch (action) {
            case 0:
                bank.deposit(customer, amount);
                break;
            case 1:
                bank.withdraw(customer, amount);
                break;
            case 2:
                Customer randomCustomer;
                do {
                    randomCustomer = getRandomCustomer(bank);
                } while (randomCustomer.getId() == customer.getId());
                bank.transfer(customer, randomCustomer, amount);
                break;
            case 3:
                bank.payInterests(customer);
                break;
        }
    }

    private static void showTransactionMenu(Bank bank) {
        System.out.println("choice action:\n" +
                "1 - show all transactions\n" +
                "2 - show transaction in some branch\n" +
                "3 - show users in some branch\n" +
                "4 - show transaction for user from some branch\n" +
                "0 - exit");
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                break;
            case 1:
                System.out.println(bank.getTransactionsString());
                break;
            case 2:
                System.out.println(bank.getBranch(choiceBranch(bank, scanner)).getTransactionsString());
                break;
            case 3:
                System.out.println(bank.getBranch(choiceBranch(bank, scanner)).getCustomersString());
                break;
            case 4:
                Branch branchFrom = bank.getBranch(choiceBranch(bank, scanner));
                System.out.println(branchFrom.getCustomer(choiceUser(branchFrom, scanner)).getTransactionsString());
                break;
        }
        if (choice!=0) {
            showTransactionMenu(bank);
        }
    }

    private static int choiceBranch(Bank bank, Scanner scanner) {
        System.out.println(bank.getBranchesNames());
        System.out.println("select branch index:");
        System.out.print("> ");
        return scanner.nextInt();
    }

    private static int choiceUser(Branch branch, Scanner scanner) {
        System.out.println(branch.getCustomersString());
        System.out.println("select user number:");
        System.out.print("> ");
        return scanner.nextInt();
    }


}
