import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class StatementService {
    
public void displayTransactionStatement(
        Account account,
        LocalDateTime start,
        LocalDateTime end) {

    TreeMap<LocalDateTime, Transaction> transactions =
            new TreeMap<>(account.getTransactions());

    NavigableMap<LocalDateTime, Transaction> result =
            transactions.subMap(start, true, end, true);

    System.out.println("\n===== Transaction Statement =====");
    System.out.println("Account ID : " + account.getId());
    System.out.println("Customer Name : " + account.getCustomerName());

    if (result.isEmpty()) {
        System.out.println("No transactions found in this date range.");
        return;
    }

    for (Transaction transaction : result.values()) {
        System.out.println("----------------------------");
        System.out.println(transaction);
    }
}



    public void displayAccountsSortedById(
            HashMap<Integer, Account> accounts) {

        TreeMap<Integer, Account> sortedAccounts =
                new TreeMap<>(accounts);

        System.out.println("\n===== Accounts Sorted By ID =====");

        for (Map.Entry<Integer, Account> entry : sortedAccounts.entrySet()) {
            System.out.println("----------------------------");
            System.out.println(entry.getValue());
        }
    }

    public void displayAccountsSortedByBalance(
            HashMap<Integer, Account> accounts) {

        TreeMap<Double, Account> sortedAccounts =
                new TreeMap<>();

        for (Account account : accounts.values()) {
            sortedAccounts.put(account.getBalance(), account);
        }

        System.out.println("\n===== Accounts Sorted By Balance =====");

        for (Map.Entry<Double, Account> entry : sortedAccounts.entrySet()) {
            System.out.println("----------------------------");
            System.out.println(entry.getValue());
        }
    }
    

}