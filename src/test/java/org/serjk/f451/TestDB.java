package org.serjk.f451;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.serjk.f451.DAO.UserDAO;
import org.serjk.f451.model.User;
import org.serjk.f451.model.UserType;
import org.serjk.f451.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public class TestDB extends TestCase {

    @Autowired
    private UserDAO userDAO;

    public static void main(String[] args) {
//        userDAO.addUser();


    }
}
