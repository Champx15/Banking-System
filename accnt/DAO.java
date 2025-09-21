package accnt;

import java.sql.*;

public class DAO {
    // mysql connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "champ");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Create Account
    public void createAccount(Account account, double overdraft) {
        try (Connection conn = getConnection();
                PreparedStatement stmn = conn.prepareStatement(
                        "INSERT INTO accounts(account_num,account_holder,balance,account_type,overdraft_limit)VALUES (?,?,?,?,?)")) {
            stmn.setString(1, account.getAccntNumber());
            stmn.setString(2, account.getAccntHolderName());
            stmn.setDouble(3, account.getBalance());
            stmn.setString(4, account.getType());
            stmn.setDouble(5, overdraft);
            stmn.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error while creating account" + e.getMessage());
        }
    }

    // Delete Account
    public int deleteAccount(String accNum) {
        try (Connection conn = getConnection();
                PreparedStatement stmn = conn.prepareStatement("DELETE FROM accounts where account_num=?")) {
            stmn.setString(1, accNum);
            return stmn.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error while deleting account");
            e.printStackTrace();
            return 0;
        }

    }

    // account exists??
    public boolean accountExists(String accNum) {
        try (Connection conn = getConnection();
                PreparedStatement stmn = conn.prepareStatement("SELECT * FROM accounts where account_num=?")) {
            stmn.setString(1, accNum);
            return stmn.executeQuery().next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    // Update balance
    public  int updateBalance(double balance, String accNum){
        try (Connection conn = getConnection();
                PreparedStatement stmn = conn.prepareStatement("UPDATE accounts SET balance=? WHERE account_num=?")) {
                    stmn.setDouble(1, balance);
                    stmn.setString(2, accNum);
                    return stmn.executeUpdate();
    
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        
    }

    // Find Account
    public Account findAccount(String accNum){
        try (Connection conn = getConnection();
                PreparedStatement stmn = conn.prepareStatement("SELECT * FROM accounts WHERE account_num=?")) {
                    stmn.setString(1, accNum);
                    ResultSet rs = stmn.executeQuery();
                if (rs.next()) {
                    String type = rs.getString("account_type");
                    if ("SAVINGS".equalsIgnoreCase(type)) {
                        return new SavingAccount(
                            rs.getString("account_num"),
                            rs.getString("account_holder"),
                            rs.getDouble("balance")
                        );
                    } else {
                        return new CurrentAccount(
                            rs.getString("account_num"),
                            rs.getString("account_holder"),
                            rs.getDouble("balance"),
                            rs.getDouble("overdraft_limit")
                        );
    
                }}
            
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
            return  null;
        }
        return null;
    }



}
