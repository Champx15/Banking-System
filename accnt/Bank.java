package accnt;

import java.util.HashMap;
import java.util.Scanner;

public class Bank {
    HashMap<String, Account> accounts = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public void createAccount() {
        System.out.print("Enter account Number : ");
        String acNum = sc.nextLine();
        System.out.print("Enter account Holder Name : ");
        String acHolder = sc.nextLine();
        System.out.print("Enter balance : ");
        double acbalance = sc.nextDouble();
        System.out.print("Account type( 1 for Savings, 2 for Current) : ");
        int choice = sc.nextInt();
        if (choice == 1) {
            SavingAccount ac = new SavingAccount(acNum, acHolder, acbalance);
            accounts.put(acNum, ac);
            System.out.println("Accoun Created");
        } else if (choice == 2) {
            CurrentAccount ac = new CurrentAccount(acNum, acHolder, acbalance);
            accounts.put(acNum, ac);
            System.out.println("Accoun Created");
        } else {
            System.out.println("Wrong Input");
        }

    }

    public void operations() {
        System.out.print("Enter Account Number: ");
        String accNumber = sc.nextLine();
        Account find = accounts.get(accNumber);
        if (find == null) {
            System.out.println("Account doesn't exist");
            return;
        }
        System.out.print("Enter(1: Deposit, 2: Withdraw, 3: Calculate Interest) : ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter amount to deposit: ");
                double amount = sc.nextDouble();
                find.deposit(amount);
                break;
            case 2:
                find.withdraw();
                break;
            case 3:
                find.calcInterest();
            default:
                break;
        }
    }

    public void deleteAccount(String accNumber) {
        accounts.remove(accNumber);
        System.out.println("Account removed");
    }

    public void findAccount(String accNumber) {
        Account find = accounts.get(accNumber);
        if (find == null)
            System.out.println("Account not found");
        else {
            find.accountDetails();
            System.out.println("Account type : " + find.getType());
            System.out.println("--------------------------------------------------------");
        }
    }
}