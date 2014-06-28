package org.serjk.f451.dao.impl;

import org.hibernate.Query;import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.dao.BankDAO;
import org.serjk.f451.model.Bank;
import org.serjk.f451.model.Wage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kreker on 28.06.14.
 */
@Repository
public class BankDAOImpl implements BankDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addBank(Bank bank){
        openSession().saveOrUpdate(bank);
    }

    public Bank getBankByWageId(long wageId){
        Query query = openSession().createQuery("FROM Bank as b where b.wageId=:wageId");
        query.setParameter("wageId", wageId);
        return (Bank)query.list().get(0);
    }

    public List<Bank> getBankList(){
        Query query = openSession().createQuery("FROM Bank");
        return (List<Bank>) query.list();
    }

}
