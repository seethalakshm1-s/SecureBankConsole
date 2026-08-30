import java.util.TreeMap;
public class Account {

    private int id;
    private String customerName;
    private double balance;

    private TreeMap<TransactionKey, Transaction> transactions = new TreeMap<>();

    public Account() {
    }

    public Account(int id, String customerName, double balance) {
        this.id = id;
        this.customerName = customerName;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }
     public void setId(int id) {
        this.id = id;
    }


    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
    public void setTransactions(TreeMap<TransactionKey, Transaction> transactions) {
        this.transactions = transactions;
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
public void removeTransaction(Transaction transaction) { 
    TransactionKey key = new TransactionKey( transaction.getTimestamp(), transaction.getTransactionId() ); transactions.remove(key); 
}

    public String toString() {
        return "Account ID : " + id +
               "\nCustomer Name : " + customerName +
               "\nBalance : ₹" + balance;
    }
}
