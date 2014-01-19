package org.serjk.f451.service;

import org.serjk.f451.model.User;

import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public interface UserService {

    public void addUser(User user);

    public List<User> listUser();

    public List<User> listReportUser(String lastName, String firstName);

    public void removeUser(long id);

}
