package org.serjk.f451.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.dao.WageDAO;
import org.serjk.f451.model.Wage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kreker on 27.06.14.
 */

@Repository
public class WageDAOImpl implements WageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void addWage(Wage wage){
        openSession().saveOrUpdate(wage);
    }

    @Transactional
    public Wage getWageById(long wageId){
        Query query;
        query = openSession()
                .createQuery("FROM Wage  as w WHERE w.id =:wageId");
        query.setParameter("wageId", wageId);
        if (!query.list().isEmpty())
            return  (Wage)query.list().get(0);
        else return  null;
    }

    @Transactional
    public List<Wage> getWageList(){
        Query query;
        query = openSession()
                .createQuery("FROM Wage");
        if (!query.list().isEmpty())
            return  (List<Wage>)query.list();
        else  return null;

    }
}
