package accnt;

import java.util.Scanner;

public class SavingAccount extends Account {
    private float interestRate = 7f / 100 / 12;
    public String type = "Savings Account";
    Scanner sc = new Scanner(System.in);

    public SavingAccount(String accntNumber, String accntHolderName, double balance) {
        super(accntNumber, accntHolderName, balance);
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void withdraw() {
        double current = getBalance();
        System.out.print("Enter amount: ");
        double input = sc.nextDouble();
        if (current < 0) {
            setBalance(current - input);
            System.out.print("Remaining amount is ₹" + (current - input));
        } else
            System.out.println("Not enough balance");
    }

    @Override
    public void calcInterest() {
        // simple interest
        System.out.print("Enter no. of months: ");
        int time = sc.nextInt();
        double interest = interestRate * getBalance() * time;
        System.out.println("Interest after " + time + " months is ₹" + interest);
    }
}