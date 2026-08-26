import java.time.LocalDateTime;

public class TransactionKey implements Comparable<TransactionKey> {

    private LocalDateTime timestamp;
    private int transactionId;

    public TransactionKey(LocalDateTime timestamp, int transactionId){
        this.timestamp = timestamp;
        this.transactionId = transactionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getTransactionId() {
        return transactionId;
    }
    public TransactionKey(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        this.transactionId = 0;
    }

    @Override
    public int compareTo(TransactionKey other) {

        int timeCompare = this.timestamp.compareTo(other.timestamp);

        if (timeCompare != 0) {
            return timeCompare;
        }

        return Integer.compare(this.transactionId, other.transactionId);
    }
}