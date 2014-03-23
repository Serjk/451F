package org.serjk.f451.controllers;

import org.serjk.f451.model.User;
import org.serjk.f451.service.UserService;
import org.serjk.f451.service.impl.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping("/admin/index")
    public String listUser(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("listUser", userService.listUser());
        model.addAttribute("roles", userLoginService.getRoles());
        return "user";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
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

    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") long userId) {

        userService.removeUser(userId);
        return "redirect:/admin/user";
    }
}
