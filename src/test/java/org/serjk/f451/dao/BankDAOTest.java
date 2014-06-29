package org.serjk.f451.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.serjk.f451.BaseTest;
import org.serjk.f451.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BankDAOTest extends BaseTest {

    @Autowired
    private BankDAO bankDAO;

    private double BUDGET = 100;
    private long wageId = 213;


    @Test
    public void testAddBank() {
        createDefaultBank();
        Bank bank = bankDAO.getBankByWageId(wageId);
        assertEquals(bank.getBuget(),BUDGET);
    }

    @Test
    public void testListBank() {
        createDefaultBank();
        createDefaultBank();
        List<Bank> bankList = bankDAO.getBankList();
        assertEquals(bankList.size(),2);
    }

    private void createDefaultBank() {
        Bank bank = new Bank();
        bank.setBuget(100);
        bank.setWageId(wageId);
        bankDAO.addBank(bank);
    }
}
