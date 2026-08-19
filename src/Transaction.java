import java.time.LocalDateTime;

public class Transaction {

    private String type;
    private double amount;
    private int fromAccountId;
    private int toAccountId;
    private LocalDateTime timestamp;

    public Transaction(String type, double amount,int fromAccountId, int toAccountId) {

        this.type = type;
        this.amount = amount;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.timestamp = LocalDateTime.now();
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
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
    return "Transaction Type : " + type +
           "\nAmount : ₹" + amount +
           "\nFrom Account : " + fromAccountId +
           "\nTo Account : " + toAccountId+
           "\nDate & Time : " + timestamp;
}

}