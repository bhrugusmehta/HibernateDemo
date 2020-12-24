package dao;

import model.Sales;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SalesDao {
    private Session session;

    public SalesDao(Session session) {
        this.session = session;
    }

    public void saveSales(Sales sales) {
        Transaction transaction = null;
        try  {
            transaction = session.beginTransaction();
            session.save(sales);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Boolean deleteSales(Long id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("Delete Sales where id = :id");
            query.setParameter("id", id);
            int res = query.executeUpdate();
            if (res == 0) {
                return false;
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public Boolean updateSales(int amount, Long id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Sales sales = session.get(Sales.class, id);
            sales.setAmount(amount);
            session.update(sales);
            transaction.commit();
            return true;
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
    public List<Sales> getSales() {
        return session.createQuery("from Sales", Sales.class).list();
    }

}
