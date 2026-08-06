package service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Account;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;

public class BankService {

    private HashMap<Integer, Account> accounts = new HashMap<>();
    private int nextAccountId = 1001;

    public void createAccount(Scanner sc) {

        sc.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        Account account = new Account(nextAccountId, name, 0);

        accounts.put(nextAccountId, account);

        System.out.println("Account Created Successfully!");
        System.out.println("Account ID : " + nextAccountId);

        nextAccountId++;
    }

    public void deposit(Scanner sc) throws AccountNotFoundException {

        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        if (!accounts.containsKey(id)) {
            throw new AccountNotFoundException("Account Not Found!");
        }

        Account account = accounts.get(id);

        account.setBalance(account.getBalance() + amount);

        System.out.println("Deposit Successful!");
        System.out.println("Current Balance : " + account.getBalance());
    }

    public void withdraw(Scanner sc)
            throws AccountNotFoundException, InsufficientFundsException {

        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        if (!accounts.containsKey(id)) {
            throw new AccountNotFoundException("Account Not Found!");
        }

        Account account = accounts.get(id);

        if (amount > account.getBalance()) {
            throw new InsufficientFundsException("Insufficient Funds!");
        }

        account.setBalance(account.getBalance() - amount);

        System.out.println("Withdrawal Successful!");
        System.out.println("Current Balance : " + account.getBalance());
    }

    public void checkBalance(Scanner sc) throws AccountNotFoundException {

        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();

        if (!accounts.containsKey(id)) {
            throw new AccountNotFoundException("Account Not Found!");
        }

        System.out.println("Current Balance : " + accounts.get(id).getBalance());
    }

    public void closeAccount(Scanner sc) throws AccountNotFoundException {

        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();

        if (!accounts.containsKey(id)) {
            throw new AccountNotFoundException("Account Not Found!");
        }

        accounts.remove(id);

        System.out.println("Account Closed Successfully!");
    }

    public void displayAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No Accounts Available.");
            return;
        }

        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {

            System.out.println("----------------------------");
            System.out.println(entry.getValue());
            System.out.println("----------------------------");
        }
    }
}