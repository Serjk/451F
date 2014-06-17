
package org.serjk.f451.controllers;

import org.serjk.f451.error.ErrorInfo;
import org.serjk.f451.model.Step;
import org.serjk.f451.model.Transition;
import org.serjk.f451.model.User;
import org.serjk.f451.service.WorkFlowService;
import org.serjk.f451.service.impl.UserLoginService;
import org.serjk.f451.model.Report;
import org.serjk.f451.service.ReportService;
import org.serjk.f451.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

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

    @Autowired
    private WorkFlowService workFlowService;

    static final Logger logger = Logger.getLogger(ReportController.class);

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
        Report report = reportService.getReport(reportId);
        User user = userService.getUserById(firemanId);
        if (user!=null &&  report!=null)
            reportService.assignReport(user,report);
        else
            logger.error(String.format("No valid user with ID %s",firemanId));
        return "redirect:/user/report/find/"+String.valueOf(reportId);
    }

    @RequestMapping(value = "/user/report/assignee/policeman/{reportId}/{policemanId}")
    public String assignReportToPoliceman(@PathVariable("reportId") Long reportId,
                                          @PathVariable("policemanId") Long policemanId) {
        Report report = reportService.getReport(reportId);
        User user = userService.getUserById(policemanId);
        if (user!=null &&  report!=null)
            reportService.assignReport(user,report);
        else
            logger.error(String.format("No valid user with ID %s",policemanId));
        return "redirect:/user/report/find/"+String.valueOf(reportId);
    }

    @RequestMapping(value = "/user/report/step/{reportId}/{stepId}")
    public @ResponseBody ErrorInfo moveReportToStep(@PathVariable("reportId") Long reportId,
                                                    @PathVariable("stepId") Long stepId) {
        Report report = reportService.getReport(reportId);
        ErrorInfo errorInfo =new ErrorInfo();
        //Когда офицер полиции берёт в работу он должен назаначить исполнителя
        logger.info(String.format("Step id -  %s, start validation",stepId));
        if(stepId==91 && userService.getUserById(report.getPolicemanId())==null){
            errorInfo.setErrorCode("label.workflow.validation.policeman.empty");
            errorInfo.setMessage("Не установлен полицейский офицер");
            logger.error(String.format("Step id -  %s, validation filed - no policeman ID",report.getStepId()));
            return errorInfo;
        }
        else if(stepId==92){
            //функция генерации колличества книг
            reportService.moveReportToStep(stepId,report);
            logger.error(String.format("Step id -  %s, electric dof find books",report.getStepId()));
            return errorInfo;
        }
        else if(stepId==98 && report.getCountBook()==-1){
            errorInfo.setErrorCode("label.workflow.validation.nodogvalidation.empty");
            errorInfo.setMessage("Пёс не проверил дом на наличие книг");
            logger.error(String.format("Step id -  %s, validation filed - no electric dog repo",report.getStepId()));
            return errorInfo;
        }
        else if(stepId==99 && userService.getUserById(report.getFiremanId())==null){
            errorInfo.setErrorCode("label.workflow.validation.firemanid.empty");
            errorInfo.setMessage("Не установлен пожарный офицер, пожарный расчет не может выехать, пока не установлен пожарный офицер");
            logger.error(String.format("Step id -  %s, validation filed - no fireman ID",report.getStepId()));
            return errorInfo;
        }
        else if(stepId==666){
            //автоматически устанавливаетя время закрытия запроса и считаем общее время в обработке
            errorInfo.setMessage("Не установлен пожарный офицер, вы не можете выехать, пока не установите офицер");
            logger.error(String.format("Step id -  %s, validation filed - no fireman ID",report.getStepId()));
            return errorInfo;
        }
        else{
            reportService.moveReportToStep(stepId,report);
            errorInfo.setErrorCode("label.workflow.validation.ok");
            errorInfo.setMessage("Валидация прошла нормально");
            logger.error("Step id -  ${report.getStepId()}, validation passed, new step ${stepId}");
            return errorInfo;
        }
        //необходима проверка на наличие бюджета, необходимого на выполнение задания
    }

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
