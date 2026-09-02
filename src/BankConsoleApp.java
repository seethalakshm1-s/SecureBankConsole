import java.util.Scanner;

public class BankConsoleApp {
   public static void main(String[] args) throws Exception{
    Scanner sc = new Scanner(System.in);

    BankService bankService = new BankService();
    StatementService statementService = new StatementService();

    int choice;

    while (true) {

        System.out.println("\n==================================");
        System.out.println("      Secure Bank System");
        System.out.println("==================================");

        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Check Balance");
        System.out.println("5. Close Account");
        System.out.println("6. Display All Accounts");
        System.out.println("7. Transfer Money");
        System.out.println("8. Reverse Last Transaction");
        System.out.println("9. Find Accounts By Customer");
        System.out.println("10. Display Transaction History");
        System.out.println("11. Display Accounts Sorted By ID");
        System.out.println("12. Display Accounts Sorted By Balance");
        System.out.println("13. Display Transactions By Date Range");
        System.out.println("14. TreeMap Navigation Demo");
        System.out.println("15. Export Transaction to JSON");
        System.out.println("16. Exit");

        System.out.print("Enter your choice: ");
        choice = sc.nextInt();
        switch (choice) {

    case 1:
        bankService.createAccount(sc);
        break;

    case 2:
        bankService.deposit(sc);
        break;

    case 3:
        bankService.withdraw(sc);
        break;

    case 4:
        bankService.checkBalance(sc);
        break;

    case 5:
        bankService.closeAccount(sc);
        break;

    case 6:
        bankService.displayAccounts();
        break;

    case 7:
        bankService.transfer(sc);
        break;

    case 8:
        bankService.reverseLastTransaction(sc);
        break;

    case 9:
        bankService.findAccountsByCustomer(sc);
        break;

    case 10:
        bankService.displayTransactionHistory(sc);
        break;

    case 11:
        statementService.displayAccountsSortedById( bankService.getAccounts());
        break;

    case 12:
        statementService.displayAccountsSortedByBalance(bankService.getAccounts());
        break;
    case 13:
        bankService.displayTransactionsByRange(sc);
        break;
    case 14:
        statementService.demonstrateTreeMapNavigation( bankService.getAccounts() );
        break;

    case 15:
        bankService.exportLastTransactionToJson(sc);
        break;
    case 16:
        System.out.println("Thank You!");
        sc.close();
        return;
    default:
        System.out.println("Invalid Choice!");
        break;
        }
    }
}
}
