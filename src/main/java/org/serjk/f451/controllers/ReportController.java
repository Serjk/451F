package org.serjk.f451.controllers;


import org.serjk.f451.model.Report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;


@Controller
public class ReportController {

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ModelAndView report() {
        return new ModelAndView("report", "command", new Report());
    }

    @RequestMapping(value = "/addReport", method = RequestMethod.POST)
    public String addReport(@ModelAttribute("SpringWeb")Report rep,
                             ModelMap model) {
        model.addAttribute("id", rep.getId());
        model.addAttribute("text", rep.getText());

        return "represult";
    }
}
