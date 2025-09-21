package accnt;

import java.util.Scanner;

public class CurrentAccount extends Account {
    private float interestRate = 2f / 100 / 12;
    private double overDraftLim = 50_000;
    public String type = "CURRENT";

    public CurrentAccount(String accNum, String accHolder, double balance,double overDraftLim) {
        super(accNum, accHolder, balance);
        this.overDraftLim=overDraftLim;
    }

    Scanner sc = new Scanner(System.in);

    @Override
    public String getType() {
        return type;}

    @Override
    public int withdraw(double current) {
        System.out.println("Enter amount: ");
        double input = sc.nextDouble();
        if (current <= 0) {
            if (input <= overDraftLim) {
                overDraftLim -= input;
                System.out.println("Remaining overdraft amount is ₹" + (overDraftLim - input));
                return 2;
            } else
                {System.out.println("Input amount is more than overdraft limit"); return 0;}

        } else {
            setBalance(current - input);
            System.out.println("Remaining amount is ₹" + (current - input));
            return 1;
        }

    }

    @Override
    public void calcInterest(double current) {
        System.out.print("Enter no. of months: ");
        int time = sc.nextInt();
        double interest = interestRate * current * time;
        System.out.println("Interest after " + time + " months is ₹" + String.format("%.2f", interest));
    }

    public double getOverDraftLim() {
        return overDraftLim;
    }

    public void setOverDraftLim(double overDraftLim) {
        this.overDraftLim = overDraftLim;
    }

}

