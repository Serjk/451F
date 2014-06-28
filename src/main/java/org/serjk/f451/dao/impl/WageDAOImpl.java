package org.serjk.f451.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.dao.WageDAO;
import org.serjk.f451.model.Wage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kreker on 27.06.14.
 */

@Repository
public class WageDAOImpl implements WageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addWage(Wage wage){
        openSession().saveOrUpdate(wage);
    }

    public Wage getWageByType(String  type){
        Query query;
        query = openSession()
                .createQuery("FROM Wage  as w WHERE w.type =:type");
        query.setParameter("type", type);
        if (!query.list().isEmpty())
            return  (Wage)query.list().get(0);
        else return  null;
    }

    public List<Wage> getWageList(){
        Query query;
        query = openSession()
                .createQuery("FROM Wage");
        if (!query.list().isEmpty())
            return  (List<Wage>)query.list();
        else  return null;

    }
}
