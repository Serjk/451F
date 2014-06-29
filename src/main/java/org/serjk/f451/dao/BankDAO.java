package org.serjk.f451.dao;

import org.serjk.f451.model.Bank;
import org.serjk.f451.model.Wage;

import java.util.List;

/**
 * Created by kreker on 27.06.14.
 */
public interface BankDAO {

    public void addBank(Bank bank);
    public Bank getBankByWageId(long wageId);
    public List<Bank> getBankList();

}
