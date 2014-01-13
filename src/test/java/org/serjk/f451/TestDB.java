package org.serjk.f451;

import junit.framework.TestCase;
import org.serjk.f451.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

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
