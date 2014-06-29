package org.serjk.f451.dao;

import org.serjk.f451.model.Payment;

import java.util.List;

/**
 * Created by kreker on 29.06.14.
 */
public interface PaymentDAO {
    public List<Payment> getPaymentList();
    public void addPayment(Payment payment);
}
