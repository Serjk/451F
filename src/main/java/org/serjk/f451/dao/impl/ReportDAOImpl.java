package org.serjk.f451.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.dao.ReportDAO;
import org.serjk.f451.model.Report;
import org.serjk.f451.model.Step;
import org.serjk.f451.model.User;
import org.serjk.f451.model.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public void moveReportToStep(long stepId, long reportId)
    {
        Query query = openSession().createQuery("FROM Report as r where r.id=:reportId");
        query.setParameter("reportId",reportId);
        Report report = (Report) query.list().get(0);
        report.setStepId(stepId);
        openSession().save(report);
    }

    @Transactional
    public void assignReportToFireman(long firemanId,long reportId){
        Query query = openSession().createQuery("FROM Report  as r WHERE r.id=:reportId");
        query.setParameter("reportId",reportId);
        Report report = (Report) query.list().get(0);
        report.setFiremanId(firemanId);
        openSession().save(report);
    }

    @Transactional
    public void assignReportToPoliceman(long policemanId,long reportId){
        Query query = openSession().createQuery("FROM Report as r WHERE r.id=:reportId");
        query.setParameter("reportId",reportId);
        Report record = (Report) query.list().get(0);
        record.setPolicemanId(policemanId);
        openSession().save(record);
    }

    @Transactional
    public void setRecordCountBook(long reportId, long countBook){
        Query query = openSession().createQuery("FROM Report  as r WHERE r.id=:reportId");
        query.setParameter("reportId",reportId);
        Report report = (Report) query.list().get(0);
        report.setCountBook(countBook);
        openSession().save(report);
    }

    @Transactional
    public void setRecordResolutionDate(long reportId, Date date){
        Query query = openSession().createQuery("FROM Report  as r WHERE r.id=:reportId");
        query.setParameter("reportId",reportId);
        Report report = (Report) query.list().get(0);
        //report.setDate(date);
        openSession().save(report);
    }

}
