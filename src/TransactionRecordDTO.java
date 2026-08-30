public class TransactionRecordDTO {

    private int accountId;
    private Transaction transaction;

    public TransactionRecordDTO() {
    }

    public TransactionRecordDTO(int accountId, Transaction transaction) {
        this.accountId = accountId;
        this.transaction = transaction;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}