package org.serjk.f451.service;

import org.serjk.f451.model.Wage;

import java.util.List;

/**
 * Created by kreker on 27.06.14.
 */

public interface WageService {

    public void addWage(Wage wage);
    public void editWage(Wage wage);
    public Wage getWageById(long  wageId);
    public List<Wage> getWageList();

}
