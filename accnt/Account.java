package accnt;

public abstract class Account {
    private String accntNumber;
    private String accntHolderName;
    private double balance;

    public Account(String accntNumber, String accntHolderName, double balance) {
        this.accntNumber = accntNumber;
        this.accntHolderName = accntHolderName;
        this.balance = balance;
    }

    public abstract String getType();

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void accountDetails() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Account Number : " + accntNumber + "\n" + "Account Holder : " + accntHolderName + "\n"
                + "Balance : ₹" + balance);

    }

    public void deposit(double amount) {
        balance += amount;
    }

    public abstract void withdraw();

    public abstract void calcInterest();
}