import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
public class Account {

    private int id;
    private String customerName;
    private double balance;

     private TreeMap<LocalDateTime, Transaction> transactions = new TreeMap<>();


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
    public Map<LocalDateTime, Transaction> getTransactions() {
        return transactions;
    }
    public void addTransaction(Transaction transaction) {
      transactions.put(transaction.getTimestamp(), transaction);
    }

    public Transaction removeLastTransaction() {

    if (transactions.isEmpty()) {
        return null;
    }
     LocalDateTime lastTime = transactions.lastKey();

        return transactions.remove(lastTime);
    }
  


    public String toString() {
        return "Account ID : " + id +
               "\nCustomer Name : " + customerName +
               "\nBalance : ₹" + balance;
    }
}