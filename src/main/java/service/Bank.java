package service;

import dao.Dao;
import model.Account;
import model.CurrentAccount;
import model.SavingAccount;
import org.hibernate.HibernateException;

import java.math.BigDecimal;


public class Bank {
    private  Dao dao = new Dao();
    //Add account
    public boolean createAccount (String acNum,String acHolder,BigDecimal acbalance,int type) throws HibernateException
    {
        if(type==1 || type ==2){
            Account ac;
            if (type == 1) {
                 ac = new SavingAccount(acNum, acHolder, acbalance);
            } else {
                ac = new CurrentAccount(acNum, acHolder, acbalance);
            }
            dao.createAccount(ac);
            return true;
        }
        else return false;
    }

    //Delete account
    public boolean deleteAccount(String accNumber) throws HibernateException{
        return dao.deleteAccount(accNumber);

    }

    //Find account
    public Account findAccount(String accNumber) throws HibernateException
    {
        return dao.findAccount(accNumber);
    }

    //operations
   public String withdraw(String accN,BigDecimal amount)
   {
       Account ac = findAccount(accN);
       String str = ac.withdraw(amount);
        dao.updateBalance(ac);
        return str;
   }

   public void deposit(String accN,BigDecimal amount)
   {
       Account ac = findAccount(accN);
       ac.deposit(amount);
        dao.updateBalance(ac);

   }

   public boolean moneyTransfer(String sender,String receiver, BigDecimal amount)
   {
       try{
       Account ac1= findAccount(sender);
       Account ac2= findAccount(receiver);
       ac2.deposit(amount);
       ac1.withdraw(amount);
       dao.updateBalance(ac1);
       dao.updateBalance(ac2);
       return true;
       } catch (Exception e) {
           return false;
       }

   }


}