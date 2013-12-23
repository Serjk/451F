package org.serjk.f451.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.ejb.Ejb3Configuration;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    private static final Ejb3Configuration ejb3Configuration;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            ejb3Configuration = new Ejb3Configuration().configure("hibernate.cfg.xml");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Ejb3Configuration getEjb3Configuration() {
        return ejb3Configuration;
    }
}