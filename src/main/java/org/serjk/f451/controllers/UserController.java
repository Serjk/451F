package org.serjk.f451.controllers;

import org.serjk.f451.dao.UserDAO;
import org.serjk.f451.error.ErrorInfo;
import org.serjk.f451.model.User;
import org.serjk.f451.model.SimpleUser;
import org.serjk.f451.service.InitService;
import org.serjk.f451.service.UserService;
import org.serjk.f451.service.impl.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private InitService initService;

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping("/admin/user")
    public String listUser(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("listUser", userService.listUser());
        model.addAttribute("roles", userLoginService.getRoles());
        return "user";
    }

    @RequestMapping("/admin/services/startinit/")
    public @ResponseBody
    String startInitService () {
        initService.initUsersTable();
        return  "Started...";
    }

    @RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
    public String addUser(@RequestParam(value ="firstName") String firstName,
                          @RequestParam(value ="lastName") String lastName,
                          @RequestParam(value ="login") String login,
                          @RequestParam(value ="address") String address,
                          @RequestParam(value ="role") String role,
                          @RequestParam(value ="password") String password) {

        User user = new User();
        PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setLogin(login);

        user.setPassword(passwordEncoder.encodePassword(password,null));
        user.setRole(role);

        userService.addUser(user);
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/rest/admin/user/add", method = RequestMethod.POST)

    public @ResponseBody
    ErrorInfo setNewUser(@RequestParam(value ="firstName") String firstName,
                         @RequestParam(value ="lastName") String lastName,
                         @RequestParam(value ="login") String login,
                         @RequestParam(value ="address") String address,
                         @RequestParam(value ="password1") String password1,
                         @RequestParam(value ="password2") String password2) {

        if (userService.getUserByLogin(login)==null){
            if(password1.equals(password2)){
                User user = new User();
                ErrorInfo errorInfo = new ErrorInfo("rest.createuser.success",
                                                    "Пользователь создан, ввойдите в систему");
                PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAddress(address);
                user.setLogin(login);
                user.setRole("ROLE_USER");
                user.setPassword(passwordEncoder.encodePassword(password1,null));
                userService.addUser(user);
                return  errorInfo;
            }
            else  {
                ErrorInfo errorInfo = new ErrorInfo("rest.createuser.passerror",
                        "Введённые пароли не совпадают");
                return errorInfo;
            }
        }
        else  {
            ErrorInfo errorInfo = new ErrorInfo("rest.createuser.userexists",
                    "Пользователь уже существует в системе");
            return errorInfo;
        }

    }


    @RequestMapping("/admin/user/delete/{userId}")
    public String deleteUser(@PathVariable("userId") long userId) {
        userService.removeUser(userId);
        return "redirect:/admin/user";
    }

    @RequestMapping("/user/report/user/{userId}")
    public @ResponseBody SimpleUser getSimpleUserInJSON(@PathVariable("userId") long userId) {
       return userService.getSimpleUserById(userId);
    }
}
