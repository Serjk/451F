package org.serjk.f451.controllers;

import org.serjk.f451.model.Report;
import org.serjk.f451.model.User;
import org.serjk.f451.service.UserService;
import org.serjk.f451.service.impl.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 22.03.14
 * Time: 14:22
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping("/user/index")
    public String index(Model model) {
        User loginUser  = userLoginService.getLoginUser();
        model.addAttribute("loginUser",loginUser);

        return "index";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/user/index";
    }
}
