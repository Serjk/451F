package org.serjk.f451.dao;

import org.serjk.f451.model.User;

import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public interface UserDAO {

    public void addUser(User user);

    public List<User> listContact();

    public List<User> listReportUser(String lastName, String firstName);

    public User getUserById(long userId);

    public void removeUser(long id);

    public User getUserByLogin(String login);

    public List<User> getFiremanAssigners();

    public List<User> getPolicemanAssigners();

}
