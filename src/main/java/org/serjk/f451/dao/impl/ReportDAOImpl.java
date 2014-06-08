package org.serjk.f451.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.dao.ReportDAO;
import org.serjk.f451.model.Report;
import org.serjk.f451.model.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
* @author Koyushev Sergey (mailto: serjk91@gmail.com)
*/

@Repository
public  class ReportDAOImpl implements ReportDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void addReport(Report report) {
        openSession().save(report);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Report> listRepContact() {
        Query query = openSession().createQuery("FROM Report");
        return query.list();
    }

    @Override
    @Transactional
    public Report getReport(long id) {
       Query query = openSession().createQuery("FROM Report  as r WHERE r.id=:id");
       query.setParameter("id",id);
       if (query.list().isEmpty()) return null; else
           return (Report) query.list().get(0);
    }

    @Transactional
    public void removeReport(long id) {
        Report report = (Report) openSession().load(Report.class, id);
        if (report!=null) {
            openSession().delete(report);
        }
    }

    @Transactional
    public List<Report>  listReportedToMe(long userId){
        Query query = openSession().createQuery("FROM Report as r where r.suspectId=:userId");
        query.setParameter("userId",userId);
        return query.list();
    }

    @Transactional
    public List<Report>  listMyReports(long userId){
        Query query = openSession().createQuery("FROM Report as r where r.reporterId=:userId");
        query.setParameter("userId",userId);
        return query.list();
    }

    @Transactional
    public  void assignReport(long assigneId,long reportId){
        Query query = openSession().createQuery("FROM Report as r where r.id=:reportId");
        query.setParameter("reportId",reportId);
        Report report = (Report) query.list().get(0);
        report.setAssignee(assigneId);
        openSession().save(report);

    }

    @Transactional
    public void moveReportToStep(long stepId, long reportId)
    {
        Query query = openSession().createQuery("FROM Report as r where r.id=:reportId");
        query.setParameter("reportId",reportId);
        Report report = (Report) query.list().get(0);
        report.setStepId(stepId);
        openSession().save(report);

    }

    @Transactional
    public void addStep(Step step){
        openSession().save(step);
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
}
