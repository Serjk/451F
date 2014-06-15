package org.serjk.f451.controllers;

import org.serjk.f451.model.Step;
import org.serjk.f451.model.Transition;
import org.serjk.f451.model.User;
import org.serjk.f451.service.UserService;
import org.serjk.f451.service.WorkFlowService;
import org.serjk.f451.service.impl.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 10.06.14
 * Time: 20:54
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class WorkFlowController {

    @Autowired
    private WorkFlowService workFlowService;

    @Autowired
    private UserLoginService userLoginService;


    @RequestMapping("/admin/workflow")
    public String listUser(Model model) {

        model.addAttribute("transition", new Transition());
        model.addAttribute("listTransition", workFlowService.listTransition());
        model.addAttribute("permission", userLoginService.getRoles());
        model.addAttribute("step", new Step());
        model.addAttribute("listStep", workFlowService.listStep());
        return "workflow";
    }

    @RequestMapping(value = "/admin/workflow/transition/new", method = RequestMethod.POST)
    public String addUser(@RequestParam(value ="name") String name,
                          @RequestParam(value ="stepIn") long stepIn,
                          @RequestParam(value ="stepOut") long stepOut,
                          @RequestParam(value ="permission") String permission) {

        Transition transition = new Transition();
        transition.setName(name);
        transition.setStepIn(stepIn);
        transition.setStepOut(stepOut);
        transition.setPermission(permission);
        workFlowService.addTransition(transition);
        return "redirect:/admin/workflow";
    }


    @RequestMapping(value = "/admin/workflow/step/new", method = RequestMethod.POST)
    public String addStep(@RequestParam("stepName") String stepName,
                          @RequestParam("stepSummary") String stepSummary) {
        Step step  = new Step();
        step.setStepName(stepName);
        step.setStepSummary(stepSummary);
        workFlowService.addStep(step);
        return "redirect:/admin/workflow";
    }

}
