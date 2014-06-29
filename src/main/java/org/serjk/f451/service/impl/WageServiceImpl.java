package org.serjk.f451.service.impl;

import org.serjk.f451.dao.WageDAO;
import org.serjk.f451.model.Wage;
import org.serjk.f451.service.WageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kreker on 27.06.14.
 */

@Service
public class WageServiceImpl implements WageService{

    @Autowired
    WageDAO wageDAO;

    public void addWage(Wage wage){
        wageDAO.addWage(wage);
    }

    public void editWage(Wage wage){
        wageDAO.addWage(wage);
    }

    public Wage getWageById(long wageId){
        return wageDAO.getWageById(wageId);
    }

    public List<Wage> getWageList(){
        return wageDAO.getWageList();
    }

}

