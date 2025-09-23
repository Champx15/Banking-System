package ui;

import model.Account;
import model.CurrentAccount;
import model.SavingAccount;
import service.Bank;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.math.BigDecimal;
import java.util.Scanner;

public class Sbi {

    public static void main(String[] arg) {

        Scanner sc = new Scanner(System.in);
        Bank SBI = new Bank();
        int choice;
        do {
            System.out.print("Enter (1: Create Account, 2: Remove Account, 3: Find Account, 4: Operation, 0:Exit) : ");
            choice = sc.nextInt();
            switch (choice) {

                case 1:
                    System.out.print("Enter account Number : ");
                    sc.nextLine();
                    String acNum = sc.nextLine();
                    System.out.print("Enter account Holder Name : ");
                    String acHolder = sc.nextLine();
                    System.out.print("Enter balance : ");
                    BigDecimal acbalance = sc.nextBigDecimal();
                    System.out.print("Account type( 1 for Savings, 2 for Current) : ");
                    int type = sc.nextInt();
                    try {
                        boolean status = SBI.createAccount(acNum, acHolder, acbalance, type);
                        if (status) System.out.println("Account created successfully!");
                        else System.err.println("Invalid input");
                    } catch (HibernateException e) {
                        System.err.println("Failed to create account: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    sc.nextLine();
                    String accN = sc.nextLine();
                    try {
                        boolean status = SBI.deleteAccount(accN);
                        if (status) System.out.println("Account removed successfully!!");
                        else System.err.println("Account doesn't exist");
                    } catch (HibernateException e1) {
                        System.err.println("Failed to delete account: " + e1.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    sc.nextLine();
                    accN = sc.nextLine();
                    try {
                        Account ac = SBI.findAccount(accN);
                        if (ac != null) {
                            System.out.println("--------------------------------------------------------");
                            System.out.println("Account Number : " + ac.getAccntNumber() + "\n" + "Account Holder : " + ac.getAccntHolderName() + "\n"
                                    + "Balance : ₹" + ac.getBalance() + "\n" + "Account Type : " + ac.getAccntType() );
                            if(ac instanceof  CurrentAccount ) {
                                System.out.println("Over Draft Amount : " + ((CurrentAccount) ac).getOverDraftLim());
                            }
                            System.out.println("Created At : " + ac.getTimestamp());
                            System.out.println("--------------------------------------------------------");
                        } else System.err.println("Account doesn't exist");
                    } catch (HibernateException e2) {
                        System.err.println("Failed to search account : " + e2.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter Your Account Number: ");
                    sc.nextLine();
                    String accNumber = sc.nextLine();
                    Account ac = SBI.findAccount(accNumber);
                    if (ac != null) {
                        System.out.print("Enter(1: Deposit, 2: Withdraw, 3: Calculate Interest 4: Money Transfer, 0: Exit) : ");
                        int op = sc.nextInt();
                        switch (op) {
                            case 1:
                                System.out.print("Enter amount to deposit: ");
                                BigDecimal amount = sc.nextBigDecimal();
                                SBI.deposit(accNumber,amount);
                                System.out.println("Amount deopsited");
                                System.out.println("Total balance is ₹" +ac.getBalance());
                                break;
                            case 2:
                                System.out.print("Enter amount to withdraw: ");
                                amount = sc.nextBigDecimal();
                                String message = SBI.withdraw(accNumber,amount);
                                System.out.println(message);
                                break;
                            case 3:
                                System.out.println("Enter no of months : ");
                                int months = sc.nextInt();
                                System.out.println("Simple Interest after " + months + " is " + "₹" + ac.calcInterest(months));
                                break;
                            case 4:
                                System.out.println("Enter Reciever's account number : ");
                                sc.nextLine();
                                String receiver = sc.nextLine();
                                System.out.println("Enter Amount : ");
                                 amount = sc.nextBigDecimal();
                                boolean status = SBI.moneyTransfer(ac.getAccntNumber(),receiver,amount);
                                if(status) System.out.println("₹ "+amount+" transferred successfully");
                                else System.out.println("Money Transfer fail");
                                break;
                            default:
                                break;
                        }
                    } else System.err.println("Account doesn't exist");

            break;
            default:
                System.out.println("Enter a valid choice");
                break;
            }
        }while (choice != 0);
    }
}



