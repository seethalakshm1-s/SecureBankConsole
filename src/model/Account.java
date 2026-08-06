package model;

public class Account {

    private int id;
    private String customerName;
    private double balance;

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

    @Override
    public String toString() {
        return "Account ID : " + id +
               "\nCustomer Name : " + customerName +
               "\nBalance : ₹" + balance;
    }
}