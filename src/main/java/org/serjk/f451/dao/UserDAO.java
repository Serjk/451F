package org.serjk.f451.dao;

import org.hibernate.HibernateException;
import org.serjk.f451.model.User;
import org.serjk.f451.model.SimpleUser;
import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public interface UserDAO {

    public void addUser(User user) throws HibernateException;

    public List<User> listContact() throws HibernateException;

    public List<User> listReportUser(String lastName, String firstName) throws HibernateException;

    public User getUserById(long userId) throws HibernateException;

    public SimpleUser getSimpleUserById(long userId)throws HibernateException;

    public void removeUser(long id)throws HibernateException;

    public User getUserByLogin(String login)throws HibernateException;

    public List<User> getUsersByRole(String role)throws HibernateException;

    public List<SimpleUser> getSimpleUserList ();
}
