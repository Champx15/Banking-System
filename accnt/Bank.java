package accnt;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class Bank {
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

    Scanner sc = new Scanner(System.in);

    public void createAccount() {
        System.out.print("Enter account Number : ");
        String acNum = sc.nextLine();
        System.out.print("Enter account Holder Name : ");
        String acHolder = sc.nextLine();
        System.out.print("Enter balance : ");
        double acbalance = sc.nextDouble();
        System.out.print("Account type( 1 for Savings, 2 for Current) : ");
        int choice = sc.nextInt();
        if (choice == 1) {
            try(Connection conn = getConnection();
                PreparedStatement stmn= conn.prepareStatement("INSERT INTO accounts(account_num,account_holder,balance,account_type) VALUES(?,?,?,?)"))
                {
                    stmn.setString(1,acNum);
                    stmn.setString(2,acHolder);
                    stmn.setDouble(3, acbalance);
                    stmn.setString(4,"SAVINGS");
                    int result = stmn.executeUpdate();
                }
                catch(SQLException e) 
                { 
                    System.out.println("Error creating account");
                }

            System.out.println("Account Created");
        } else if (choice == 2) {
            try(Connection conn = getConnection();
                PreparedStatement stmn= conn.prepareStatement("INSERT INTO accounts(account_num,account_holder,balance,account_type) VALUES(?,?,?,?)"))
                {
                    stmn.setString(1,acNum);
                    stmn.setString(2,acHolder);
                    stmn.setDouble(3, acbalance);
                    stmn.setString(4,"CURRENT");
                    int result = stmn.executeUpdate();
                }
                catch(SQLException e) 
                { 
                    System.out.println("Error creating account");
                }
            System.out.println("Account Created");
        } else {
            System.out.println("Wrong Input");
        }
        sc.nextLine();

    }

    public void operations() {
        System.out.print("Enter Account Number: ");
        String accNumber = sc.nextLine();
        boolean accountExists = findAccount(accNumber);
        if(accountExists==true){
        System.out.print("Enter(1: Deposit, 2: Withdraw, 3: Calculate Interest) : ");
            try(Connection conn = getConnection())
        {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double amount = sc.nextDouble();
                    Account.deposit(amount,accNumber);
                    break;
                case 2:
                    // find.withdraw();
                    break;
                case 3:
                    // find.calcInterest();
                default:
                    break;
            }
        }
        
        catch(SQLException e) 
        { 
            System.out.println("Error creating account");
        }}
        else System.out.println("Account doesn't exist");
    }

    public void deleteAccount(String accNumber) {
            try(Connection conn = getConnection();
                PreparedStatement stmn= conn.prepareStatement("DELETE FROM accounts WHERE account_num=?"))
                {
                    stmn.setString(1, accNumber);
                    int result = stmn.executeUpdate();
                    if(result==0) System.out.println("Account doesn't exist");
                    else System.out.println("Account Removed!!!");

                }
                catch(SQLException e) 
                { 
                    System.out.println("Error deleting account");
                }        
    }

    public boolean findAccount(String accNumber) {
            try(Connection conn = getConnection();
                PreparedStatement stmn= conn.prepareStatement("SELECT * FROM accounts WHERE account_num=?"))
                {   
                    stmn.setString(1, accNumber);
                    ResultSet result = stmn.executeQuery();
                    if(result.next()){

                    
                    System.out.println("----------------------xxxxxxx-------------------");
                    System.out.println("Account Number : "+result.getString("account_num")+"\n"+"Account Holder : "+result.getString("account_holder")+"\n"+"Balance : "+result.getDouble("balance")+"\n"+"Account Type : "+result.getString("account_type")+"\n"+"Created At : "+ result.getTimestamp("created_at"));
                    System.out.println("----------------------xxxxxxx-------------------"); return true;}
                    else {System.out.println("Account doesn't exist"); return false;}
                
            }
                catch(SQLException e) 
                { 
                    System.out.println("Error finding account");
                    return false;
                }        
    }

}