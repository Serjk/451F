package org.serjk.f451.DAO.Impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.DAO.UserDAO;
import org.serjk.f451.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
* @author Koyushev Sergey (mailto: serjk91@gmail.com)
*/

@Repository
public  class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void addUser(User user) {
        openSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listContact() {
        return openSession().createQuery("from USER")
                .list();

    }

    @Override
    public void removeUser(long id) {
        User user = (User) openSession().load(User.class, id);
        if (user!=null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
