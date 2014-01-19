package org.serjk.f451.controllers;

import org.serjk.f451.model.Report;
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

    @RequestMapping("/user/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/user/index";
    }
}
