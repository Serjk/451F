package org.serjk.f451.controllers;

import org.serjk.f451.model.Report;
import org.serjk.f451.model.Transition;
import org.serjk.f451.model.User;
import org.serjk.f451.model.Step;
import org.serjk.f451.model.enums.UserType;
import org.serjk.f451.service.ReportService;
import org.serjk.f451.service.UserService;
import org.serjk.f451.service.WorkFlowService;
import org.serjk.f451.service.impl.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 23.03.14
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ReportFilterController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService  userService;

    @Autowired
    private WorkFlowService workFlowService;

    @Autowired
    private UserLoginService userLoginService;


    @RequestMapping("/user/report/all")
    public String listReportAll(Model model) {
        model.addAttribute("listReport", reportService.listReport());
        return "reportfilter";
    }

    @RequestMapping("/user/report/tome")
    public String listReportToMe(Model model) {
        long currentUser = userLoginService.getLoginUser().getId();
        model.addAttribute("listReport", reportService.listReportedToMe(currentUser));
        return "reportfilter";
    }

    @RequestMapping("/user/report/my")
    public String listReportMy(Model model) {
        long currentUser = userLoginService.getLoginUser().getId();
        model.addAttribute("listReport", reportService.listMyReports(currentUser));
        return "reportfilter";
    }

    @RequestMapping(value = "/user/report/find/{reportId}", method = RequestMethod.GET)
    public String addPreReport(@PathVariable("reportId") long reportId, Model model) {

        String role = userLoginService.getLoginUser().getRole();
        Report report  = reportService.getReport(reportId);
        List<Transition> transitions = workFlowService.outgoingTransitionsID(report.getStepId(),role);
        User fireman =  userService.getUserById(report.getFiremanId());
        User policeman =  userService.getUserById(report.getPolicemanId());
        User reporter =  userService.getUserById(report.getReporterId());
        User suspect =  userService.getUserById(report.getSuspectId());
        Step step  = workFlowService.getStep(report.getStepId());
        List<User> policemanAssigners  = userService.getPolicemanAssigners();
        List<User> firemanAssigners = userService.getFiremanAssigners();
        User loginUser  = userLoginService.getLoginUser();
        Map<String,String> roles =   userLoginService.getRoles();

        model.addAttribute("report", report);
        model.addAttribute("fireman", fireman);
        model.addAttribute("policeman", policeman);
        model.addAttribute("reporter", reporter);
        model.addAttribute("suspect", suspect);
        model.addAttribute("step", step);
        model.addAttribute("transitions", transitions);
        model.addAttribute("policemanAssigners",policemanAssigners);
        model.addAttribute("firemanAssigners",firemanAssigners);
        model.addAttribute("loginUser",loginUser);
        model.addAttribute("userType", roles);

        return "reportdetals";
    }
}
