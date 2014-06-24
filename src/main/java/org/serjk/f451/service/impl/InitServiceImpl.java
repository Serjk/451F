package org.serjk.f451.service.impl;

import org.serjk.f451.dao.UserDAO;
import org.serjk.f451.model.User;
import org.serjk.f451.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 22.06.14
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */

@Service
public class InitServiceImpl implements InitService {

    @Autowired
    UserDAO userDAO;

    @Override
    public void initUsersTable(){
        String [] firstName  = {"Admin",   "User",
                                "Fireman", "Policeman" };

        String [] lastName  = {"Admin",   "User",
                               "Fireman", "Policeman" };

        String [] login  =   {"admin",   "user",
                              "fireman", "policeman" };

        String [] password = {"21232f297a57a5a743894a0e4a801fc3",
                              "ee11cbb19052e40b07aac0ca060c23ee",
                              "4fe814abb1adb58a7788269de4408c8f",
                              "441216d0829401b6ae1b500f3d8e0b57" };

        String [] addres = {"Новоизмайловский 16 к3",
                            "Новоизмайловский 16 к2",
                            "Новоизмайловский 16 к4",
                            "Новоизмайловский 16 к1" };

        String [] role = {"ROLE_ADMIN",
                          "ROLE_USER",
                          "ROLE_FIREMAN",
                          "ROLE_POLICE"};

        for (int i = 0; i<firstName.length; i++){
            User user =new User();
            user.setFirstName(firstName[i]);
            user.setLastName(lastName[i]);
            user.setLogin(login[i]);
            user.setPassword(password[i]);
            user.setAddress(addres[i]);
            user.setRole(role[i]);
            userDAO.addUser(user);
        }
    }
}
