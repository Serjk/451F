package org.serjk.f451.controllers;

import org.serjk.f451.model.Report;

import org.serjk.f451.model.User;
import org.serjk.f451.service.ReportService;
import org.serjk.f451.service.UserService;
import org.serjk.f451.service.impl.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
    private UserLoginService userLoginService;

    @RequestMapping("/user/report/all")
    public String listReportAll(Model model) {
        model.addAttribute("listReport", reportService.listReport());
        return "reportfilter";
    }

    @RequestMapping("/user/report/tome")
    public String listReportToMe(Model model) {
        long currentUser = userLoginService.getLoginUserId();
        model.addAttribute("listReport", reportService.listReportedToMe(currentUser));
        return "reportfilter";
    }

    @RequestMapping("/user/report/my")
    public String listReportMy(Model model) {
        long currentUser = userLoginService.getLoginUserId();
        model.addAttribute("listReport", reportService.listMyReports(currentUser));
        return "reportfilter";
    }

    @RequestMapping(value = "/user/report/find/{reportId}", method = RequestMethod.GET)
    public String addPreReport(@PathVariable("reportId") long reportId,
                               Model model) {
        model.addAttribute("report", reportService.getReport(reportId));
        return "reportdetals";
    }
}
