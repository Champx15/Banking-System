package accnt;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Bank {
    private DAO dao = new DAO();
    Scanner sc = new Scanner(System.in);

    public void createAccount(Account account) {
        double overdraft=0.00;
        if(account.getType().equals("CURRENT")) overdraft=50000.00;
        DAO.getConnection();
        dao.createAccount(account, overdraft);
        System.out.println("Account Created");
    }
    
    public boolean deleteAccount(String accNumber) {
        int status = dao.deleteAccount(accNumber);
        return (status==0)?false:true;
    }
    public void operations(Account account) {
        System.out.print("Enter(1: Deposit, 2: Withdraw, 3: Calculate Interest) : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double amount = sc.nextDouble();
                    account.deposit(amount);
                    dao.updateBalance(account.getBalance() ,account.getAccntNumber());
                    System.out.println("Amount Deposited");
                    System.out.println("Account Balance : "+account.getBalance());
                    break;
                case 2:
                    int status = account.withdraw(account.getBalance());
                    if(status==1) dao.updateBalance(account.getBalance(), account.getAccntNumber());
                    else if(status==0) System.out.println("Doesn't withdraw");
                    else System.out.println("OK hai darling");
                    break;
                case 3:
                    account.calcInterest(account.getBalance());
                default:
                    break;
            }
        }


    public boolean accountExists(String accNumber) 
    {
        return dao.accountExists(accNumber);
    }
    public Account findAccount(String accNumber) {
        Account result= dao.findAccount(accNumber);
        if(result==null) {System.out.println("Account doesn't exists"); return null;}
        System.out.println("----------------------xxxxxxx-------------------");
        System.out.println("Account Number : "+result.getAccntNumber()+"\n"+"Account Holder : "+result.getAccntHolderName()+"\n"+"Balance : "+result.getBalance()+"\n"+"Account Type : "+result.getType());
        if(result.getType().equals("CURRENT")){
            CurrentAccount account = (CurrentAccount) result;
            System.out.println("Over Draft Limit : "+ account.getOverDraftLim());
            System.out.println("----------------------xxxxxxx-------------------");
            return account;
        }
        System.out.println("----------------------xxxxxxx-------------------");
        return result;
    }

}    

