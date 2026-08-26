import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class StatementService {
    
public void displayTransactionStatement(
        Account account,
        LocalDateTime start,
        LocalDateTime end) {

    TreeMap<TransactionKey, Transaction> transactions =
        new TreeMap<>(account.getTransactions());

NavigableMap<TransactionKey, Transaction> result =
        transactions.subMap(
                new TransactionKey(start),
                true,
                new TransactionKey(end),
                false
        );
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

    List<Account> sortedAccounts =
            new ArrayList<>(accounts.values());

    sortedAccounts.sort(
            Comparator.comparingDouble(Account::getBalance)
                      .thenComparingInt(Account::getId)
    );

    System.out.println("\n===== Accounts Sorted By Balance =====");

    for (Account account : sortedAccounts) {
        System.out.println("----------------------------");
        System.out.println(account);
    }
}
public void demonstrateTreeMapNavigation(
        HashMap<Integer, Account> accounts) {

    TreeMap<Integer, Account> sortedAccounts =
            new TreeMap<>(accounts);

    System.out.println("\n===== TreeMap Navigation =====");

    System.out.println("Account IDs: " + sortedAccounts.keySet());

    System.out.println("ceilingKey(104) = "
            + sortedAccounts.ceilingKey(104));

    System.out.println("floorKey(104) = "
            + sortedAccounts.floorKey(104));

    System.out.println("headMap(105) = "
            + sortedAccounts.headMap(105).keySet());

    System.out.println("tailMap(105) = "
            + sortedAccounts.tailMap(105).keySet());

    System.out.println("subMap(100, 108) = "
            + sortedAccounts.subMap(100, 108).keySet());
}
}