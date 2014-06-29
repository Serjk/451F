package org.serjk.f451.service;

import org.serjk.f451.model.Payment;
import org.serjk.f451.model.User;
import org.serjk.f451.model.Wage;

import java.util.List;

/**
 * Created by kreker on 28.06.14.
 */
public interface PaymentService {
    public List<Payment> getPaymentList();
    public void addPayment(Payment  payment);
    public boolean payUser(User user, Wage wage );
}
