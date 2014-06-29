package org.serjk.f451.dao;

import org.serjk.f451.model.Wage;

import java.util.Date;
import java.util.List;

/**
 * Created by kreker on 27.06.14.
 */
public interface WageDAO {

    public void addWage(Wage wage);
    public Wage getWageById(long wageId);
    public List<Wage> getWageList();

}
