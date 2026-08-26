import java.util.TreeMap;
public class Account {

    private int id;
    private String customerName;
    private double balance;

    private TreeMap<TransactionKey, Transaction> transactions = new TreeMap<>();


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
   public TreeMap<TransactionKey, Transaction> getTransactions() {
    return transactions;
}
   public void addTransaction(Transaction transaction) {

    transactions.put(
            new TransactionKey(
                    transaction.getTimestamp(),
                    transaction.getTransactionId()
            ),
            transaction
    );
}
public Transaction removeLastTransaction() {

    if (transactions.isEmpty()) {
        return null;
    }
    TransactionKey lastKey = transactions.lastKey();
    return transactions.remove(lastKey);
  
}

    public String toString() {
        return "Account ID : " + id +
               "\nCustomer Name : " + customerName +
               "\nBalance : ₹" + balance;
    }
}
