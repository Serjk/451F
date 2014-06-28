package org.serjk.f451.service;

import org.serjk.f451.model.Bank;
import org.serjk.f451.model.Wage;

import java.util.List;

/**
 * Created by kreker on 28.06.14.
 */
public interface BankService {

    public void addBank(Bank bank);
    public void increaseBudget(Wage wage, Double value);
    public void decrBudget(Wage wage, Double value);
    public List<Bank> getBankList();

}
