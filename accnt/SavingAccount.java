package accnt;

import java.util.Scanner;

public class SavingAccount extends Account {
    private float interestRate = 7f / 100 / 12;
    public String type = "SAVINGS";
    Scanner sc = new Scanner(System.in);

    public SavingAccount(String accntNumber, String accntHolderName, double balance) {
        super(accntNumber, accntHolderName, balance);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int withdraw(double current) {
        System.out.print("Enter amount: ");
        double input = sc.nextDouble();
        if (current > 0) {
            if(input>current) { System.out.println("Not enough balance"); return 0;}
            else {
                setBalance(current - input);
                System.out.println("Remaining amount is ₹" + (current - input));
                return 1;
            }
                
        } else
           { System.out.println("Not enough balance"); return 0;}
    }

    @Override
    public void calcInterest(double current) {
        // simple interest
        System.out.print("Enter no. of months: ");
        int time = sc.nextInt();
        double interest = interestRate * current * time;
        System.out.println("Interest after " + time + " months is ₹" +  String.format("%.2f", interest));
    }
}