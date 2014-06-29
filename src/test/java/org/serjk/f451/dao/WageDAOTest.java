package org.serjk.f451.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.serjk.f451.BaseTest;
import org.serjk.f451.model.Wage;
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
public class WageDAOTest extends BaseTest {

    @Autowired
    private WageDAO wageDAO;

    private long cashValue = 100L;
    private String cashType = "type";

    @Test
    public void testAddBank() {
        createDefaultBank();
        Wage wage = wageDAO.getWageById(1);
        assertEquals(wage.getCash(), cashValue);
        assertEquals(wage.getType(), cashType);
    }

    @Test
    public void testListBank() {
        createDefaultBank();
        createDefaultBank();
        List<Wage> wageList = wageDAO.getWageList();
        assertEquals(wageList.size(), 2);
    }

    private void createDefaultBank() {
        Wage wage = new Wage();
        wage.setCash(cashValue);
        wage.setCash(cashType);
        wageDAO.addWage(wage);
    }
}
