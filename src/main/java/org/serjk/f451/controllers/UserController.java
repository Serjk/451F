package org.serjk.f451.controllers;

import org.serjk.f451.model.User;
import org.serjk.f451.service.UserService;
import org.serjk.f451.service.impl.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping("/admin/user")
    public String listUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUser", userService.listUser());
        model.addAttribute("roles", userLoginService.getRoles());
        return "user";
    }

    @RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {
        userService.addUser(user);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/delete/{userId}")
    public String deleteUser(@PathVariable("userId") long userId) {
        userService.removeUser(userId);
        return "redirect:/admin/user";
    }
}
