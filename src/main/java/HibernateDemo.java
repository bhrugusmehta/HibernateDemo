import configure.HibernateConfig;
import dao.SalesDao;
import org.hibernate.Session;

public class HibernateDemo {
    public static void main(String[] args) {
       Session session = HibernateConfig.openSession();
       SalesDao salesDao = new SalesDao(session);

       Menu menu = new Menu(salesDao);
       menu.startMenu();

       HibernateConfig.shutdown();
    }
}
