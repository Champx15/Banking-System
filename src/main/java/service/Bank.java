package service;

import dao.Dao;
import model.Account;
import model.CurrentAccount;
import model.SavingAccount;
import model.Transaction;
import org.hibernate.HibernateException;
import java.math.BigDecimal;
import java.util.List;


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

    //generate account numbers
    public String generateAcNum()
    {
        long min = 10000000000L;
        long max = 99999999999L;
        long accN = min + (long) (Math.random() *(max-min+1));
        return (String.valueOf(accN));
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
    //withdraw
   public String withdraw(Account ac,BigDecimal amount)
   {
       String str = ac.withdraw(amount);
        dao.updateBalance(ac);
       Transaction tran = new Transaction(ac,amount,"Debit",genTranId());
       dao.makeTransaction(tran);
        return str;
   }

   //deposit
   public void deposit(Account ac,BigDecimal amount)
   {
       ac.deposit(amount);
        dao.updateBalance(ac);
       Transaction tran = new Transaction(ac,amount,"Credit",genTranId());
       dao.makeTransaction(tran);
   }

   //money transfer
   public boolean moneyTransfer(String sender,String receiver, BigDecimal amount)
   {
       try{
       Account ac1= findAccount(sender);
       Account ac2= findAccount(receiver);
       ac2.deposit(amount);
       ac1.withdraw(amount);
       dao.updateBalance(ac1);
       dao.updateBalance(ac2);
        Transaction tran1 = new Transaction(ac1,amount,"Debit",genTranId());
        Transaction tran2 = new Transaction(ac2, amount, "Credit", genTranId());
        dao.makeTransaction(tran1);
        dao.makeTransaction(tran2);
        return true;
       } catch (Exception e) {
           return false;
       }

   }

   //generate transaction id
    public String genTranId()
    {
        long min = 10000000000L;
        long max = 99999999999L;
        long tid = min + (long) (Math.random() *(max-min+1));
        return ("T"+String.valueOf(tid));
    }

    //Get Transaction Log
    public List<Transaction> getTransaction(String accNum){
       return  dao.getTransaction(accNum);
    }


}