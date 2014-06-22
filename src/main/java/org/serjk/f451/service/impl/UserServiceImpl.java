package org.serjk.f451.service.impl;

import org.serjk.f451.dao.UserDAO;
import org.serjk.f451.model.SimpleUser;
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
    public List<User> listReportUser(String lastName, String firstName) {
        return userDAO.listReportUser(lastName, firstName);
    }

    @Override
    public void removeUser(long id) {
        userDAO.removeUser(id);
    }

    @Override
    public User getUserById(long userId){
        return userDAO.getUserById(userId);
    }

    public List<User> getFiremanAssigners(){
        return userDAO.getFiremanAssigners();
    }

    public List<User> getPolicemanAssigners(){
        return userDAO.getPolicemanAssigners();
    }

    public SimpleUser getSimpleUserById(long userId){
        return  userDAO.getSimpleUserById(userId);
    }

}
