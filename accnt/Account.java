package accnt;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.math.BigDecimal;
import java.sql.*;

public abstract class Account {
    private String accntNumber;
    private String accntHolderName;
    private BigDecimal balance;

    public Account(String accntNumber, String accntHolderName, double balance) {
        this.accntNumber = accntNumber;
        this.accntHolderName = accntHolderName;
        this.balance = balance;
    }

    public abstract String getType();

    public double getBalance() {
        return balance;
    }

    public String getAccntNumber() {
        return accntNumber;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getAccntHolderName() {
        return accntHolderName;
    }


    public void deposit(double amount) {
        balance+=amount;
    }

    public abstract int withdraw(double amount);

    public abstract void calcInterest(double current);
}