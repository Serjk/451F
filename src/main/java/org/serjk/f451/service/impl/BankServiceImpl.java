package org.serjk.f451.service.impl;

import org.serjk.f451.dao.BankDAO;
import org.serjk.f451.model.Bank;
import org.serjk.f451.model.Wage;
import org.serjk.f451.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kreker on 28.06.14.
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    BankDAO bankDAO;

    @Override
    public void addBank(Bank bank){
        bankDAO.addBank(bank);
    }

    @Override
    public void increaseBudget(Wage wage, double value){
        Bank bank = bankDAO.getBankByWageId(wage.getId());
        bank.setBuget( bank.getBuget() + value);
        bankDAO.addBank(bank);
    }

    @Override
    public void decreaseBudget(Wage wage, double value){
        Bank bank = bankDAO.getBankByWageId(wage.getId());
        if(bank.getBuget() - value>=0){
            bank.setBuget( bank.getBuget() - value);
        } else
        {
            bank.setBuget(0);
        }
        bankDAO.addBank(bank);
    }

    @Override
    public Bank getBankByWageId(Wage wage){
       return  bankDAO.getBankByWageId(wage.getId());
    }

    @Override
    public List<Bank> getBankList(){
       return  bankDAO.getBankList();
    }

}
