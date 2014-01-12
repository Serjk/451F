package org.serjk.f451.DAO;

import org.serjk.f451.model.User;
import org.serjk.f451.model.UserType;

import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public interface UserDAO {

    public void addUser(User user);

    public List<User> listContact();

    public void removeUser(long id);

}
