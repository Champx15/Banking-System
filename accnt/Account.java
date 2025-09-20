package accnt;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;

public abstract class Account {
    private String accntNumber;
    private String accntHolderName;
    private double balance;

    public Account(String accntNumber, String accntHolderName, double balance) {
        this.accntNumber = accntNumber;
        this.accntHolderName = accntHolderName;
        this.balance = balance;
    }
        //mysql connection
         public static Connection getConnection()
        {
            try {
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "champ");
            } catch (SQLException e) {
                 e.printStackTrace();
                 return null;
            }
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


    public static void deposit(double amount,String accNum) {
                    try(Connection conn = getConnection();
                        PreparedStatement stmn= conn.prepareStatement("UPDATE accounts SET balance =?+balance where account_num=? "))
                        {   stmn.setDouble(1, amount);
                            stmn.setString(2, accNum);
                            int result = stmn.executeUpdate();
                            System.out.println("Amount deposited");
                        }
                        catch(SQLException e) 
                        { 
                            System.out.println(e.getMessage());
                            System.out.println("Error depositing amount");
                        }
    }

    public abstract void withdraw();

    public abstract void calcInterest();
}