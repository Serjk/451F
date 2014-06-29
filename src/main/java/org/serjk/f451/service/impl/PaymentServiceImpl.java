package org.serjk.f451.service.impl;

import org.serjk.f451.dao.PaymentDAO;
import org.serjk.f451.model.Bank;
import org.serjk.f451.model.Payment;
import org.serjk.f451.model.User;
import org.serjk.f451.model.Wage;
import org.serjk.f451.service.BankService;
import org.serjk.f451.service.PaymentService;
import org.serjk.f451.service.WageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by kreker on 29.06.14.
 */
@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentDAO paymentDAO;

    @Autowired
    BankService bankService;

    @Autowired
    WageService wageService;

    @Override
    public List<Payment> getPaymentList(){
        return paymentDAO.getPaymentList();
    }

    @Override
    public void addPayment(Payment payment){
        paymentDAO.addPayment(payment);
    }

    @Override
    public boolean payUser(User user, Wage wage ){
        Bank bank = bankService.getBankByWageId(wage);
        if(bank.getBuget()-wage.getCash()>=0){

            Payment payment =new Payment();
            payment.setUserId(user.getId());
            payment.setDate(new Date());
            payment.setWageId(wage.getId());
            payment.setCount(wage.getCash());
            addPayment(payment);
            ///
            double buget =bank.getBuget();
            bank.setBuget(buget-wage.getCash());
            bankService.addBank(bank);
            return true;
        }
        else{
            return false;
        }
    }
}
