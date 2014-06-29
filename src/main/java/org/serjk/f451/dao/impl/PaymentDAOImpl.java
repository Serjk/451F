package org.serjk.f451.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.dao.PaymentDAO;
import org.serjk.f451.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kreker on 29.06.14.
 */
@Repository
public class PaymentDAOImpl  implements PaymentDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public List<Payment> getPaymentList(){
        Query query = openSession().createQuery("from Payment");
        return query.list();
    }

    @Transactional
    public void addPayment(Payment payment){
        openSession().saveOrUpdate(payment);
    }
}
