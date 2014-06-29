package org.serjk.f451.controllers;

import org.serjk.f451.dao.UserDAO;
import org.serjk.f451.error.ErrorInfo;
import org.serjk.f451.model.User;
import org.serjk.f451.model.SimpleUser;
import org.serjk.f451.model.enums.UserType;
import org.serjk.f451.service.InitService;
import org.serjk.f451.service.UserService;
import org.serjk.f451.service.impl.UserLoginService;
import org.serjk.f451.util.UserTypeUtil;
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
        User loginUser  = userLoginService.getLoginUser();
        model.addAttribute("loginUser",loginUser);
        return "user";
    }

    @RequestMapping("/user/profile")
    public String getUserProfile(Model model) {
        User loginUser  = userLoginService.getLoginUser();
        UserType role = UserTypeUtil.getUserTypeByDbRoleId(loginUser.getRole());
        model.addAttribute("loginUser",loginUser);
        model.addAttribute("role",role);
        return "userprofile";
    }

    @RequestMapping("/admin/services/startinit/")
    public @ResponseBody
    String startInitService () {
        initService.initUsersTable();
        return  "Default users inserted...";
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

    @RequestMapping(value = "/rest/admin/user/updaterole", method = RequestMethod.POST)
    public @ResponseBody
    ErrorInfo setUserRole(@RequestParam(value ="userId") long  userId,
                          @RequestParam(value ="roleId") String dbRoleId) {
        User user = userService.getUserById(userId);
        UserType userType = UserTypeUtil.getUserTypeByDbRoleId(dbRoleId);
        if ( user == null ){
            ErrorInfo errorInfo = new ErrorInfo("user.doesnotexist", "Пользователь не существует");
            return errorInfo;
        }
        else if (userType == null){
            ErrorInfo errorInfo = new ErrorInfo("user.role.doesnotexist", "Роль не существует");
            return errorInfo;
        }
        else  {
            ErrorInfo errorInfo = new ErrorInfo("user.role.updated", "Роль обновлена");
            user.setRole(userType.getDbRoleId());
            userService.addUser(user);
            return errorInfo;
        }
    }

    @RequestMapping("/user/rest/user/{userId}")
    public @ResponseBody SimpleUser getSimpleUser(@PathVariable("userId") long userId) {
       return userService.getSimpleUserById(userId);
    }

    @RequestMapping("/user/rest/user/all")
    public @ResponseBody
    List <SimpleUser> getSimpleUserList(){
        return userService.getSimpleUserList();
    }



}
