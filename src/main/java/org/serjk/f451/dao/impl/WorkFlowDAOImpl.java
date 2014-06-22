package org.serjk.f451.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.dao.WorkFlowDAO;
import org.serjk.f451.model.Step;
import org.serjk.f451.model.Transition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.color.ICC_Profile;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 10.06.14
 * Time: 0:28
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class WorkFlowDAOImpl implements WorkFlowDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void addTransition(Transition transition) {
        openSession().saveOrUpdate(transition);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Transition> listTransition() {
        Query query = openSession().createQuery("from Transition");
        return query.list();
    }

    @Transactional
    public void addStep(Step step){
        openSession().saveOrUpdate(step);
    }

    @Transactional
    public Step getStep(long stepId){
        Query query = openSession().createQuery("FROM Step  as s WHERE s.id=:stepId");
        query.setParameter("stepId",stepId);
        if (query.list().isEmpty()) return null; else
            return (Step) query.list().get(0);
    }

    @Transactional
    public List<Step> listStep(){
        Query query = openSession().createQuery("FROM Step");
        return query.list();
    }

    @Transactional
    public List<Transition> incomingTransitionsID(long stepId, String role){
        Query query = openSession().createQuery("FROM Transition  as t WHERE t.stepOut=:stepId AND t.permission =:role");
        query.setParameter("stepId",stepId);
        query.setParameter("role",role);
        if (query.list().isEmpty()) return null; else
            return (List<Transition>) query.list();
    }

    @Transactional
    public List<Transition> outgoingTransitionsID(long stepId,String role){
        Query query = openSession().createQuery("FROM Transition  as t WHERE t.stepIn=:stepId AND t.permission =:role");
        query.setParameter("stepId",stepId);
        query.setParameter("role",role);
        if (query.list().isEmpty()) return null; else
            return (List<Transition>) query.list();
    }

    @Transactional
    public  void  runSqlQery(String sqlQuery){
        Query query = openSession().createSQLQuery(sqlQuery);
        query.executeUpdate();
    }
}
