import java.util.Scanner;

import accnt.*;

public class Sbi {
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        Bank SBI = new Bank();
        int choice;
        do {
            System.out.print("Enter (1: Create Account, 2: Remove Account, 3: Operations, 4: Find Account) : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    SBI.createAccount();
                    break;
                case 2:
                    System.out.print("Enter Account Number: ");
                    sc.nextLine();
                    String accN = sc.nextLine();
                    SBI.deleteAccount(accN);
                    break;
                case 3:
                    SBI.operations();
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    sc.nextLine();
                    accN = sc.nextLine();
                    boolean accountExist=SBI.findAccount(accN);
                    break;
                default:
                    System.out.println("Enter a valid choice");
                    break;
            }
        } while (choice != 0);
    }

}
