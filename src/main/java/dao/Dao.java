package dao;

import model.Account;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.math.BigDecimal;

public class Dao {
    Transaction transaction = null;

    //Add account
    public void createAccount(Account account)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(account);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    //Delete account
    public boolean deleteAccount (String accNum) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Account ac = session.find(Account.class, accNum);
            if (ac != null) {
                session.remove(ac);
                transaction.commit();
            }
            return false;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    //Find Account
    public Account findAccount(String accNum)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return  session.find(Account.class,accNum);

        } catch (HibernateException e) {
            throw e;
        }
    }

    //Update Balance
    Session session;
    public void updateBalance(Account account)
    {
        try{
            session = HibernateUtil.getSessionFactory().openSession();
           transaction = session.beginTransaction();
           session.merge(account);
           transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            session.refresh(account);
            throw e;
       }
        finally {
            if(session!=null) session.close();
        }

    }

}

