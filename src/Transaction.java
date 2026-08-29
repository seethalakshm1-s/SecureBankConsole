import java.time.LocalDateTime;

public class Transaction {
    private static int nextTransactionId = 1;
    private int transactionId;
    private String type;
    private double amount;
    private int fromAccountId;
    private int toAccountId;
    private LocalDateTime timestamp;

    public Transaction() {
    }

    public Transaction(String type, double amount,int fromAccountId, int toAccountId) {

        this.type = type;
        this.amount = amount;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.timestamp = LocalDateTime.now();
        this.transactionId = nextTransactionId++;
        

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
    public int getTransactionId() {
         return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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