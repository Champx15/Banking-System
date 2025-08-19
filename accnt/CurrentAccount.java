package accnt;

import java.util.Scanner;

public class CurrentAccount extends Account {
    private float interestRate = 2f / 100 / 12;
    private double overDraftLim = 50_000;
    public String type = "Current Account";

    public CurrentAccount(String accNum, String accHolder, double balance) {
        super(accNum, accHolder, balance);
    }

    Scanner sc = new Scanner(System.in);

    @Override
    public String getType() {
        return type;}

    @Override
    public void withdraw() {
        double current = getBalance();
        System.out.println("Enter amount: ");
        double input = sc.nextDouble();
        if (current <= 0) {
            if (input <= overDraftLim) {
                overDraftLim -= input;
                System.out.println("Remaining overdraft amount is ₹" + (overDraftLim - input));
            } else
                System.out.println("Input amount is more than overdraft limit");

        } else {
            setBalance(current - input);
            System.out.println("Remaining amount is ₹" + (current - input));
        }

    }

    @Override
    public void calcInterest() {
        System.out.print("Enter no. of months: ");
        int time = sc.nextInt();
        double interest = interestRate * getBalance() * time;
        System.out.println("Interest after " + time + " months is ₹" + interest);
    }

}

