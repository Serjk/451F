
package org.serjk.f451.controllers;

import org.serjk.f451.model.Step;
import org.serjk.f451.service.impl.UserLoginService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.serjk.f451.model.Report;
import org.serjk.f451.service.ReportService;
import org.serjk.f451.service.UserService;
import org.serjk.f451.service.impl.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private UserService userService;

    @RequestMapping("/user/report/find")
    public String findReport(Model model) {
        return "finduser";
    }


    @RequestMapping(value = "/user/report/new", method = RequestMethod.POST)
    public String addReport(@RequestParam("lastName") String lastName,
                            @RequestParam("firstName") String firstName, Model model) {
        model.addAttribute("listSuspectUser", userService.listReportUser(lastName,firstName));
        return "finduser";
    }

    @RequestMapping(value = "/user/report/add", method = RequestMethod.POST)
    public String addNewReport(@RequestParam("id") String id,
                               @RequestParam("summary") String summary,
                               @RequestParam("description") String description) {
        Report report = new Report();
        long reporterId = userLoginService.getLoginUserId();
        report.setReporterId(reporterId);
        report.setSuspectId(Long.parseLong(id));
        report.setSummary(summary);
        report.setDate();
        report.setDescription(description);
        reportService.addReport(report);
        return "redirect:/user/report/find/"+String.valueOf(report.getId());
    }

    @RequestMapping(value = "/user/report/assignee/{reportId}/{assigneId}")
    public String assignReport(@PathVariable("reportId") Long reportId,
                               @PathVariable("assigneId") Long assigneId) {
        reportService.assignReport(assigneId,reportId);

        return "redirect:/user/report/find/"+String.valueOf(reportId);
    }

    @RequestMapping(value = "/user/report/step/{reportId}/{stepId}")
    public String moveReportToStep(@PathVariable("reportId") Long reportId,
                                   @PathVariable("stepId") Long stepId) {
        reportService.moveReportToStep(stepId,reportId);
        return "redirect:/user/report/find/"+String.valueOf(reportId);
    }

    @RequestMapping("/admin/steps")
    public String listUser(Model model) {
        model.addAttribute("step", new Step());
        model.addAttribute("listStep", reportService.listStep());
        return "steps";
    }

    @RequestMapping(value = "/admin/steps/new", method = RequestMethod.POST)
    public String addStep(@RequestParam("stepName") String stepName,
                          @RequestParam("stepSummary") String stepSummary) {
        Step step  = new Step();
        step.setStepName(stepName);
        step.setStepSummary(stepSummary);
        reportService.addStep(step);
        return "redirect:/admin/steps";
    }

    @RequestMapping("/user/report/steps")
    public @ResponseBody List<Step> getStepInJSON() {
        List <Step> steps = reportService.listStep();
        return steps;
    }
}
