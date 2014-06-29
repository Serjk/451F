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

    @RequestMapping(value = "/user/rest/wage/new", method = RequestMethod.POST)
    public @ResponseBody
    ErrorInfo addWage(@RequestParam(value ="type") String type,
                     @RequestParam(value ="wageId") String wageId){
        if(wageId!=null||type!=null){
            return new ErrorInfo("wage.add.succses","Ставка успешно добавлена");
        }
        else {
            return new ErrorInfo("wage.add.filed","Невозможно добавить некорректные данные");
        }
    }

    public Wage getWageByType(String  type){
        return null;
    }

    @RequestMapping(value = "/user/rest/wage/all")
    public @ResponseBody
    List<Wage> getWageList(){
       return wageService.getWageList();
    }

    @RequestMapping(value = "/user/wage")
    public String getNews( Model model){
        User loginUser  = userLoginService.getLoginUser();
        model.addAttribute("loginUser",loginUser);
        return "wage";
    }

}
