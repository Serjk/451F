package org.serjk.f451.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.serjk.f451.BaseTest;
import org.serjk.f451.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PaymentDAOTest extends BaseTest {

    @Autowired
    private PaymentDAO paymentDAO;

    @Test
    public void testCreatePayment() {
        createDefaultPayment();
    }

    @Test
    public void testListPayment() {
        createDefaultPayment();
        createDefaultPayment();
        createDefaultPayment();
        List<Payment> payments = paymentDAO.getPaymentList();
        assertEquals(payments.size(), 3);
    }

    private void createDefaultPayment() {
        Payment payment = new Payment();
        payment.setCount(123);
        payment.setDate(new Date());
        payment.setUserId(321);
        payment.setWageId(112);
        paymentDAO.addPayment(payment);
    }
}
