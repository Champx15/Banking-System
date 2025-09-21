import java.util.Scanner;
import java.lang.Math;

import accnt.*;

public class Sbi {
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        Bank SBI = new Bank();
        Account account;
        int choice;
        do {
            System.out.print("Enter (1: Create Account, 2: Remove Account, 3: Operations, 4: Find Account) : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter account Holder Name : ");
                    sc.nextLine();
                    String acHolder = sc.nextLine();
                    System.out.print("Enter balance : ");
                    double acbalance = sc.nextDouble();
                    System.out.print("Account type( 1 for Savings, 2 for Current) : ");
                    int type = sc.nextInt();
                    if(type==1) SBI.createAccount(new SavingAccount(String.valueOf((long)(Math.random()*(Math.pow(10,11)))), acHolder, acbalance));
                    else if (type==2) SBI.createAccount(new CurrentAccount(String.valueOf((long)(Math.random()*(Math.pow(10, 11)))).toString(), acHolder, acbalance,50_000));
                    else System.out.println("Enter a valid input");
                    break;
                case 2:
                    System.out.print("Enter Account Number: ");
                    sc.nextLine();
                    String accN = sc.nextLine();
                    boolean status = SBI.deleteAccount(accN);
                    if(status) System.out.println("Account Removed!!");
                    else System.out.println("Account doesn't exist");
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    sc.nextLine();
                    String accNumber = sc.nextLine();
                    status = SBI.accountExists(accNumber);
                    if(status){
                        account=SBI.findAccount(accNumber);
                        SBI.operations(account);
                    }
                    else System.out.println("Account doesn't exist");
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    sc.nextLine();
                    accN = sc.nextLine();
                    account = SBI.findAccount(accN);
                    break;
                default:
                    System.out.println("Enter a valid choice");
                    break;
            }
        } while (choice != 0);
    }

}
