//package org.serjk.f451.controllers;
//
//
//import org.serjk.f451.model.Report;
//import org.serjk.f451.model.User;
//
//import org.serjk.f451.model.UserType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.ui.ModelMap;
//
//@Controller
//public class ReportController {
//
//
//
//    @RequestMapping(value = "/report", method = RequestMethod.GET)
//    public ModelAndView report() {
//
//
////        User suspect  = new User("Suspect_FirstName","Suspect_SecondName","Suspect_Address", UserType.HABITANT);
////      User reporter = new User("Reporter_FirstName","Reporter_SecondName","Reporter_Address", UserType.HABITANT);
//        return new ModelAndView("report", "command", new Report(suspect,reporter) );
//    }
//
//    @RequestMapping(value = "/represult", method = RequestMethod.POST)
//    public String addReport(@ModelAttribute("SpringWeb")Report rep, ModelMap model) {
//
//        model.addAttribute("id", rep.getId());
//        model.addAttribute("text", rep.getText());
//        model.addAttribute("date", rep.getDate());
//        model.addAttribute("suspect", rep.getSuspect().getFirstName());
//
//        return "represult";
//    }
//}
