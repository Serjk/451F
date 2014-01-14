package org.serjk.f451.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.dao.UserDAO;
import org.serjk.f451.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void addUser(User user) {
        openSession().save(user);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<User> listContact() {
        Query query = openSession().createQuery("from User");
        return query.list();

    }

    @Transactional
    public void removeUser(long id) {
        User user = (User) openSession().load(User.class, id);
        if (user!=null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
