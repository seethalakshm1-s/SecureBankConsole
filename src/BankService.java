import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDateTime;

public class BankService {

    private HashMap<Integer, Account> accounts = new HashMap<>();
    private HashMap<String, List<Integer>> customerIndex = new HashMap<>();

    private int nextAccountId = 1001;
    public void createAccount(Scanner sc) {
         sc.nextLine();
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();
        Account account = new Account(nextAccountId, name, 0);
        accounts.put(nextAccountId, account);
        if (!customerIndex.containsKey(name)) {
            customerIndex.put(name, new ArrayList<>());
        }
        customerIndex.get(name).add(nextAccountId);
        System.out.println("Account Created Successfully!");
        System.out.println("Account ID: " + nextAccountId);
        nextAccountId++;
    }
    public void deposit(Scanner sc) throws AccountNotFoundException {
        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        Account account = accounts.get(id);
        if (account == null) {
            throw new AccountNotFoundException("Account Not Found!");
        }
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }
        account.setBalance(account.getBalance() + amount);

        Transaction transaction =new Transaction("DEPOSIT", amount, id, id);

        account.addTransaction(transaction);

        System.out.println("Deposit Successful!");
        System.out.println("Current Balance: " + account.getBalance());
    }

    public void withdraw(Scanner sc) throws AccountNotFoundException, InsufficientFundsException {

        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        Account account = accounts.get(id);

        if (account == null) {
            throw new AccountNotFoundException("Account Not Found!");
        }
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }
        if (amount > account.getBalance()) {
            throw new InsufficientFundsException("Not Enough Balance!");
        }

        account.setBalance(account.getBalance() - amount);

        Transaction transaction = new Transaction("WITHDRAW", amount, id, id);

        account.addTransaction(transaction);

        System.out.println("Withdraw Successful!");
        System.out.println("Balance: " + account.getBalance());
    }
    public void checkBalance(Scanner sc)throws AccountNotFoundException {

        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();

        Account account = accounts.get(id);

        if (account == null) {
            throw new AccountNotFoundException("Account Not Found!");
        }
        System.out.println("Current Balance: "+ account.getBalance());
    }
    public void closeAccount(Scanner sc )throws AccountNotFoundException {
        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();
        Account account = accounts.get(id);
        if (account == null) {
            throw new AccountNotFoundException("Account Not Found!");
        }
        accounts.remove(id);
        List<Integer> accountIds =customerIndex.get(account.getCustomerName());
        if (accountIds != null) {
            accountIds.remove(Integer.valueOf(id));
            if (accountIds.isEmpty()) {
                customerIndex.remove(account.getCustomerName());
            }
        }

        System.out.println("Account Closed Successfully!");
    }
    public void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No Accounts Available.");
            return;
        }
        for (Map.Entry<Integer, Account> entry: accounts.entrySet()) {
            System.out.println("----------------------------");
            System.out.println(entry.getValue());
            System.out.println("----------------------------");
        }
    }
    public void transfer(Scanner sc)throws AccountNotFoundException,InsufficientFundsException {

        System.out.print("Enter Sender Account ID: ");
        int fromId = sc.nextInt();
        System.out.print("Enter Receiver Account ID: ");
        int toId = sc.nextInt();
        System.out.print("Enter Transfer Amount: ");
        double amount = sc.nextDouble();
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }
        Account sender = accounts.get(fromId);
        Account receiver = accounts.get(toId);
        if (sender == null) {
            throw new AccountNotFoundException("Sender Account Not Found!");
        }
        if (receiver == null) {
            throw new AccountNotFoundException("Receiver Account Not Found!");
        }

        if (amount > sender.getBalance()) {
            throw new InsufficientFundsException(  "Insufficient Funds!");
        }
        double oldSenderBalance = sender.getBalance();
        double oldReceiverBalance = receiver.getBalance();
        try {
            sender.setBalance(
                    sender.getBalance() - amount);

            receiver.setBalance(
                    receiver.getBalance() + amount);

            Transaction transaction =
                    new Transaction(
                            "TRANSFER",
                            amount,
                            fromId,
                            toId
                    );

            sender.addTransaction(transaction);
            receiver.addTransaction(transaction);
            System.out.println("Transfer Successful!");
            System.out.println( "Sender Balance : " + sender.getBalance());
            System.out.println( "Receiver Balance : "+ receiver.getBalance());
        } catch (Exception e) {
            sender.setBalance(oldSenderBalance);
            receiver.setBalance(oldReceiverBalance);

            throw e;
        }
    }
    public void reverseLastTransaction(Scanner sc)throws AccountNotFoundException {
        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();
        Account account = accounts.get(id);
        if (account == null) {
            throw new AccountNotFoundException(
                    "Account Not Found!");
        }
        Transaction transaction =account.removeLastTransaction();
        if (transaction == null) {
            System.out.println( "No Transaction To Reverse.");
            return;
        }
        String type = transaction.getType();
        double amount = transaction.getAmount();
        if (type.equals("DEPOSIT")) {
            account.setBalance(account.getBalance() - amount);

        } else if (type.equals("WITHDRAW")) {
            account.setBalance(account.getBalance() + amount);

        }else if (type.equals("TRANSFER")) {
            
             Account sender = accounts.get(transaction.getFromAccountId());

             Account receiver = accounts.get(transaction.getToAccountId());
             if (sender != null && receiver != null) {

                sender.setBalance(sender.getBalance() + amount);

                receiver.setBalance(receiver.getBalance() - amount);

            if (sender != account) {
                 sender.removeTransaction(transaction);
            }

            if (receiver != account) {
                 receiver.removeTransaction(transaction);
            }
        }
    }

        System.out.println( "Last Transaction Reversed Successfully!");

        System.out.println("Current Balance : "+ account.getBalance());
    }
    public void findAccountsByCustomer(Scanner sc) {
        sc.nextLine();
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();
        List<Integer> accountIds =customerIndex.get(name);

        if (accountIds == null || accountIds.isEmpty()) {
            System.out.println( "No accounts found for customer.");

            return;
        }

        System.out.println( "Accounts belonging to " + name + ":");

        for (Integer id : accountIds) {
            Account account = accounts.get(id);
            if (account != null) {
                System.out.println("Account ID : "+ account.getId());
                System.out.println("Balance : " + account.getBalance());
                System.out.println("-------------------");
            }
        }
    }
    public void displayTransactionHistory(Scanner sc)throws AccountNotFoundException {
        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();
        Account account = accounts.get(id);
        if (account == null) {
            throw new AccountNotFoundException( "Account Not Found!");
        }
        if (account.getTransactions().isEmpty()) {
            System.out.println( "No Transaction History Available.");
            return;
        }
        System.out.println("\n===== Transaction History =====");

        System.out.println("Account ID : "+ account.getId());
        System.out.println("Customer Name : "+ account.getCustomerName());
        System.out.println("Current Balance : ₹" + account.getBalance());
        for (Transaction transaction: account.getTransactions().values()) {
            System.out.println("----------------------------");
            System.out.println(transaction);
        }
        System.out.println("----------------------------");
    }
    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }
    public void displaySortedStatement(Scanner sc)throws AccountNotFoundException {
        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();

        Account account = accounts.get(id);

        if (account == null) {
            throw new AccountNotFoundException( "Account Not Found!");
        }

        if (account.getTransactions().isEmpty()) {
            System.out.println( "No Transactions Available.");
            return;
        }
        System.out.println("\n===== SORTED ACCOUNT STATEMENT =====");
        for (Transaction transaction : account.getTransactions().values()) {
            System.out.println("----------------------------");
            System.out.println(transaction);
        }

        System.out.println("----------------------------");
    }
    public void displayTransactionsByRange(Scanner sc)throws AccountNotFoundException {

        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();
        Account account = accounts.get(id);
        if (account == null) {
            throw new AccountNotFoundException( "Account Not Found!");
        }
        sc.nextLine();
        System.out.print("Enter Start Date & Time "+ "(yyyy-MM-ddTHH:mm): ");
        String startInput = sc.nextLine();
        System.out.print("Enter End Date & Time "+ "(yyyy-MM-ddTHH:mm): ");
        String endInput = sc.nextLine();
        LocalDateTime start =LocalDateTime.parse(startInput);
        LocalDateTime end = LocalDateTime.parse(endInput);

        if (start.isAfter(end)) {
            System.out.println("Start time must be before end time.");
            return;
        }

        boolean found = false;
        System.out.println("\n===== TRANSACTIONS IN RANGE =====");
        for (Transaction transaction : account.getTransactions().values()) {
            LocalDateTime time = transaction.getTimestamp();
            if (!time.isBefore(start) && !time.isAfter(end)) {

                System.out.println( "----------------------------");
                System.out.println(transaction);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Transactions Found In This Range.");
        }
        System.out.println("----------------------------");
    }
   
}