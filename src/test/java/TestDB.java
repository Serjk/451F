
import junit.framework.TestCase;
import org.serjk.f451.model.User;
import org.serjk.f451.util.HibernateUtil;

import javax.persistence.EntityManager;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public class TestDB extends TestCase {
    public static void main(String[] args) {
        EntityManager em = HibernateUtil.getEjb3Configuration()
                .buildEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        User usr = new User();
        usr.setFirstName("Ivan");
        usr.setLastName("Ivanov");
        em.persist(usr);
        em.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }
}
