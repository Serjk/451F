package org.serjk.f451.dao.impl;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.dao.UserDAO;
import org.serjk.f451.model.User;
import org.serjk.f451.model.SimpleUser;
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
    public void addUser(User user) throws HibernateException {
        openSession().saveOrUpdate(user);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<User> listContact() throws HibernateException{
        Query query = openSession().createQuery("from User");
        return query.list();
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) throws HibernateException {
        Query query = openSession().createQuery("FROM User  as u WHERE u.login='"+login+"'");

       if (query.list().isEmpty()) return null; else
           return (User) query.list().get(0);
    }

    @Override
    @Transactional
    public User getUserById(long userId) throws HibernateException {
        Query query = openSession().createQuery("FROM User as  u where  u.id=:userId");
        query.setParameter("userId", userId);
        if (query.list().isEmpty()) return null; else
            return   (User)  query.uniqueResult();

    }

    @Override
    @Transactional
    public SimpleUser getSimpleUserById (long userId) throws HibernateException {
        Query query = openSession().createQuery("FROM User as  u where  u.id=:userId");
        query.setParameter("userId", userId);
        SimpleUser simpleUser =new SimpleUser();

        if (query.list().isEmpty()) return null;

        User user =  (User)  query.uniqueResult();
        simpleUser.setFirstName(user.getFirstName());
        simpleUser.setLastName(user.getLastName());
        simpleUser.setAddress(user.getAddress());
        simpleUser.setRole(user.getRole());
        simpleUser.setId(user.getId());
        return simpleUser;
    }

    @Transactional
    public long getUserIdByLogin(String login) {
        Query query = openSession().createQuery("select u.id from User  u where  u.login=:login");
        query.setParameter("login", login);
        if (query.list().isEmpty()) return 0; else
        return   (Long)  query.uniqueResult();
    }

    @Transactional
    public void removeUser(long id) throws HibernateException {
        User user = (User) openSession().load(User.class, id);
        if (user!=null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Transactional
    public List<User> listReportUser(String lastName, String firstName) throws HibernateException {

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

    @Transactional
    public List<User> getUsersByRole(String role) throws HibernateException {
        Query query = openSession().createQuery("FROM User as user WHERE user.role=:role");
        query.setParameter("role", role);
        if (query.list().isEmpty()) return null; else
            return (List<User>) query.list();
    }
}
