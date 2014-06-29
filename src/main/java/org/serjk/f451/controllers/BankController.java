package org.serjk.f451.controllers;

import org.serjk.f451.error.ErrorInfo;
import org.serjk.f451.model.Bank;
import org.serjk.f451.model.User;
import org.serjk.f451.model.Wage;
import org.serjk.f451.service.BankService;
import org.serjk.f451.service.WageService;
import org.serjk.f451.service.impl.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kreker on 28.06.14.
 */

@Controller
public class BankController {
    @Autowired
    BankService bankService;

    @Autowired
    WageService wageService;

    @Autowired
    UserLoginService userLoginService;

    @RequestMapping("/admin/bank")
    public String getBank(Model model) {
        User loginUser  = userLoginService.getLoginUser();
        List<Bank> bankList = bankService.getBankList();
        List<Wage> wageList = wageService.getWageList();
        model.addAttribute("loginUser",loginUser);
        model.addAttribute("bankList",bankList);
        model.addAttribute("wageList",wageList);
        return "bank";
    }

    @RequestMapping(value = "/admin/bank/increaseBudget/", method = RequestMethod.POST)
    public @ResponseBody
    ErrorInfo increaseBudget(@RequestParam("wage") long wageId,
    @RequestParam("value") double value){


        Wage wage =wageService.getWageById(wageId);

        if(wage !=null){
            Math.abs(value);
            bankService.increaseBudget(wage,value);
            return new ErrorInfo("bank.add.succsess","Счет успешно пополнен");
        }
        else {
            return new ErrorInfo("bank.add.error","Не найдена указанная ставка");
        }
    }

    @RequestMapping(value = "/admin/bank/decreaseBudget/", method = RequestMethod.POST)
    public @ResponseBody
    ErrorInfo decreaseBudget(@RequestParam("wage") long wageId,
                             @RequestParam("value") double value){

        Wage wage =wageService.getWageById(wageId);

        if(wage !=null){
            Math.abs(value);
            bankService.decreaseBudget(wage,value);
            return new ErrorInfo("bank.add.succsess","Значение счета успешно уменьшено");

        }
        else {
            return new ErrorInfo("bank.add.error","Не найдена указанная ставка");
        }
    }
}
