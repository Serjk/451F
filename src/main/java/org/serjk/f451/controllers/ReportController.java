
package org.serjk.f451.controllers;

import org.serjk.f451.error.ErrorInfo;
import org.serjk.f451.model.User;
import org.serjk.f451.service.impl.UserLoginService;
import org.serjk.f451.model.Report;
import org.serjk.f451.service.ReportService;
import org.serjk.f451.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        model.addAttribute("listSuspectUser", userService.listReportUser(lastName, firstName));
        return "finduser";
    }

    @RequestMapping(value = "/user/report/add", method = RequestMethod.POST)
    public String addNewReport(@RequestParam("id") String id,
                               @RequestParam("summary") String summary,
                               @RequestParam("description") String description) {
        Report report = new Report();
        long reporterId = userLoginService.getLoginUser().getId();
        report.setReporterId(reporterId);
        report.setSuspectId(Long.parseLong(id));
        report.setSummary(summary);
        report.setDate();
        report.setDescription(description);
        reportService.addReport(report);
        return "redirect:/user/report/find/"+String.valueOf(report.getId());
    }

    @RequestMapping(value = "/user/report/assignee/fireman/{reportId}/{firemanId}")
    public String assignReportToFireman(@PathVariable("reportId") long reportId,
                                        @PathVariable("firemanId") long firemanId) {
        reportService.assignReportToFireman(firemanId, reportId);
        return "redirect:/user/report/find/"+String.valueOf(reportId);
    }

    @RequestMapping(value = "/user/report/assignee/policeman/{reportId}/{policemanId}")
    public String assignReportToPoliceman(@PathVariable("reportId") Long reportId,
                                          @PathVariable("policemanId") Long policemanId) {
        reportService.assignReportToPoliceman(policemanId, reportId);
        return "redirect:/user/report/find/"+String.valueOf(reportId);
    }

    @RequestMapping(value = "/user/report/step/{reportId}/{stepId}")
    public @ResponseBody ErrorInfo moveReportToStep(@PathVariable("reportId") Long reportId,
                                                    @PathVariable("stepId") Long stepId) {
        Report report = reportService.getReport(reportId);
        ErrorInfo errorInfo =new ErrorInfo();
        //Когда офицер полиции берёт в работу он должен назаначить исполнителя
        if(stepId==91){
            System.out.println("Whe are here");
            if (userService.getUserById(report.getPolicemanId())==null){
                errorInfo.setErrorCode("label.workflow.validation.policeman.empty");
                errorInfo.setMessage("Не установлен полицейский офицер");
                return errorInfo;
            }
            else{
                reportService.moveReportToStep(stepId,reportId);
                errorInfo.setErrorCode("label.workflow.validation.ok");
                errorInfo.setMessage("Валидация прошла нормально");
                return errorInfo;
                //return "redirect:/user/report/find/"+String.valueOf(reportId);
            }
        }

        if(stepId==91){
            System.out.println("Whe are here");
            if (userService.getUserById(report.getPolicemanId())==null){
                errorInfo.setErrorCode("label.workflow.validation.policeman.empty");
                errorInfo.setMessage("Не установлен полицейский офицер");
                return errorInfo;
            }
            else{
                reportService.moveReportToStep(stepId,reportId);
                errorInfo.setErrorCode("label.workflow.validation.ok");
                errorInfo.setMessage("Валидация прошла нормально");
                return errorInfo;
                //return "redirect:/user/report/find/"+String.valueOf(reportId);
            }
        }
        //Для пердачи пожарному необходима проверка псом
        //reportService.moveReportToStep(stepId,reportId);

        //Когда пожарный берёт в работу то он должен назначтить исполнителя
        //reportService.moveReportToStep(stepId,reportId);
        //return "redirect:/user/report/find/"+String.valueOf(reportId);
        return null;
    }
}
