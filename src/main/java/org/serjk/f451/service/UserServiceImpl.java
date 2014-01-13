package org.serjk.f451.service;

import org.serjk.f451.dao.UserDAO;
import org.serjk.f451.model.User;
import org.serjk.f451.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public List<User> listUser() {
        return userDAO.listContact();
    }

    @Override
    public void removeUser(long id) {
        userDAO.removeUser(id);
    }

}
