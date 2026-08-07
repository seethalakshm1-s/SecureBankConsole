package model;

public class Transaction {

    private String type;
    private double amount;
    private int fromAccountId;
    private int toAccountId;

    public Transaction(String type, double amount,int fromAccountId, int toAccountId) {

        this.type = type;
        this.amount = amount;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    @Override
    public String toString() {
        return "Transaction{" +"type='" + type + '\'' + ", amount=" + amount + ", fromAccountId=" + fromAccountId +", toAccountId=" + toAccountId + '}';
    }
}