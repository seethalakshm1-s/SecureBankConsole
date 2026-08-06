package main;

import java.util.Scanner;

import service.BankService;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;

public class BankConsoleApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService bankService = new BankService();

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("      Secure Bank System");
            System.out.println("==================================");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Close Account");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            try {

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
                        System.out.println("\nThank you for using Secure Bank!");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }

            } catch (AccountNotFoundException | InsufficientFundsException e) {
                System.out.println(e.getMessage());
            }

        } while (choice != 7);

        sc.close();
    }
}