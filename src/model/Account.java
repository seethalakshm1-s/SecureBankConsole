package model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private int id;
    private String customerName;
    private double balance;

    private List<Transaction> transactions = new ArrayList<>();

    public Account(int id, String customerName, double balance) {
        this.id = id;
        this.customerName = customerName;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
   public void addTransaction(Transaction transaction) {
    transactions.add(transaction);
}
public Transaction removeLastTransaction() {

    if (transactions.isEmpty()) {
        return null;
    }

    return transactions.remove(transactions.size() - 1);
}
  

    @Override
    public String toString() {
        return "Account ID : " + id +
               "\nCustomer Name : " + customerName +
               "\nBalance : ₹" + balance;
    }
}