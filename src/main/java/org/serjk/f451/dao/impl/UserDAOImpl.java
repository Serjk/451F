package org.serjk.f451.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.dao.UserDAO;
import org.serjk.f451.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    @Transactional
    public User get(String login) {
        Query query = openSession().createQuery("FROM User  as u WHERE u.login='"+login+"'");

       if (query.list().isEmpty()) return null; else
           return (User) query.list().get(0);
    }

    @Transactional
    public long getUserIdByLogin(String login){
        Query query = openSession().createQuery("select u.id from User  u where  u.login=:login");
        query.setParameter("login", login);
        if (query.list().isEmpty()) return 0; else
        return   (Long)  query.uniqueResult();
    }

    @Transactional
    public void removeUser(long id) {
        User user = (User) openSession().load(User.class, id);
        if (user!=null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Transactional
    public List<User> listReportUser(String lastName, String firstName) {

        Query query;

        if(!lastName.isEmpty() && !lastName.isEmpty()){
            query = openSession()
            .createQuery("FROM User  as u WHERE u.lastName=:lastName and  u.firstName=:firstName");
            query.setParameter("lastName", lastName);
            query.setParameter("firstName", firstName);
            if (!query.list().isEmpty())
                return  query.list();
        }

        if(!lastName.isEmpty() && firstName.isEmpty()){
            query = openSession().createQuery("FROM User  as u WHERE u.lastName=:lastName");
            query.setParameter("lastName", lastName);
            if (!query.list().isEmpty())
                return  query.list();
        }

        if(lastName.isEmpty() && !firstName.isEmpty()){
            query = openSession().createQuery("FROM User  as u WHERE u.firstName=:firstName");
            query.setParameter("firstName", firstName);
            if (!query.list().isEmpty())
                return  query.list();
        }
        return null;
    }
}
