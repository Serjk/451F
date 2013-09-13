package org.serjk.f451.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */

/**
 * Контроллер для главной страницы приложения.
 */
@Controller
public class IndexController {

    private int visitorCount = 0;

    @RequestMapping("/index.html")
    public String index(Model model) {
        model.addAttribute("visitorCount", visitorCount++);
        return "WEB-INF/jsp/index.jsp";
    }

}
