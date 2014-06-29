package org.serjk.f451.controllers;

import org.serjk.f451.error.ErrorInfo;
import org.serjk.f451.model.User;
import org.serjk.f451.model.Wage;
import org.serjk.f451.service.WageService;
import org.serjk.f451.service.impl.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kreker on 27.06.14.
 */

@Controller
public class WageController {

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    WageService wageService;

    @RequestMapping(value = "/admin/wage/edit/", method = RequestMethod.POST)
    public @ResponseBody
    ErrorInfo addWage(@RequestParam(value ="value") Double value,
                      @RequestParam(value ="wageId") long wageId){
        Wage wage =wageService.getWageById(wageId);
        if(value>0 && wage!=null){
            wage.setCash(value);
            wageService.addWage(wage);
            return new ErrorInfo("wage.add.succses","Ставка успешно добавлена");
        }
        else {
            return new ErrorInfo("wage.add.filed","Невозможно добавить некорректные данные");
        }
    }

    @RequestMapping(value = "/admin/wage")
    public String getNews( Model model){
        User loginUser  = userLoginService.getLoginUser();
        List <Wage> wageList = wageService.getWageList();
        model.addAttribute("loginUser",loginUser);
        model.addAttribute("wageList",wageList);
        return "wage";
    }

}
