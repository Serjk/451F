package org.serjk.f451.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.dao.ReportDAO;
import org.serjk.f451.dao.UserDAO;
import org.serjk.f451.model.Report;
import org.serjk.f451.model.SimpleReport;
import org.serjk.f451.model.User;
import org.serjk.f451.model.enums.Step;
import org.serjk.f451.util.StepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author Koyushev Sergey (mailto: serjk91@gmail.com)
*/

@Repository
public  class ReportDAOImpl implements ReportDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserDAO userDAO;

    static final Logger logger = Logger.getLogger(ReportDAOImpl.class);

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void addReport(Report report) {
        openSession().save(report);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Report> getReportList() {
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
    public List<Report>  getToMeReportList(User user){
        Query query = openSession().createQuery("FROM Report as r where r.suspectId=:userId");
        query.setParameter("userId",user.getId());
        return query.list();
    }

    @Transactional
    public List<Report>  getMyReportList(User user){
        Query query = openSession().createQuery("FROM Report as r where r.reporterId=:userId");
        query.setParameter("userId",user.getId());
        return query.list();
    }

    @Transactional
    public List<Report> getAssignedToMeReportList  (User user){
        Query query = openSession().createQuery("FROM Report as r where r.firemanId=:userId or r.policemanId =:userId");
        query.setParameter("userId",user.getId());
        return query.list();
    }

    //////////

    @Transactional
    public List<SimpleReport> getAssignedToMeSimpleReportList  (User user){
        List <Report> reportList = getAssignedToMeReportList(user);
        return checkNull(reportList);
    }

    @Transactional
    public List<SimpleReport>  getToMeSimpleReportList(User user){
        List <Report> reportList = getToMeReportList(user);
        return checkNull(reportList);
    }

    @Transactional
    public List<SimpleReport>  getMySimpleReportList(User user){
        List <Report> reportList = getMyReportList(user);
        return checkNull(reportList);
    }

    @Transactional
    public List<SimpleReport> getSimpleReportList() {
        List <Report> reportList = getReportList();
        List <SimpleReport> simpleReportList = new ArrayList<SimpleReport>();

        for (Report report : reportList){
            simpleReportList.add(convertReportToSimpleReport(report));
        }
        return simpleReportList;
    }

    private SimpleReport convertReportToSimpleReport(Report report){
        SimpleReport simpleReport = new SimpleReport();
        simpleReport.setId(report.getId());

        User fireman = userDAO.getUserById(report.getFiremanId());
        User policeman = userDAO.getUserById(report.getPolicemanId());
        User suspect = userDAO.getUserById(report.getSuspectId());
        User reporter = userDAO.getUserById(report.getReporterId());
        Step step = StepUtil.getStepById(report.getStepId());

        simpleReport.setFiremanDisplayName(getUserDisplayName(fireman));
        simpleReport.setPolicemanDisplayName(getUserDisplayName(policeman));
        simpleReport.setSuspectDisplayName(getUserDisplayName(suspect));
        simpleReport.setReporterDisplayName(getUserDisplayName(reporter));

        simpleReport.setCountBook(report.getCountBook());
        simpleReport.setSummary(report.getSummary());
        simpleReport.setDescription(report.getDescription());
        simpleReport.setDate(report.getDate());
        simpleReport.setStepName(step.getStepName());
        return simpleReport;
    }

    private String getUserDisplayName(User user){
        if (user!=null)
           return   user.getFirstName() + " " + user.getLastName();
        else
            return  null;
    }

    @Transactional
    public List<SimpleReport>  getDateRangeSimpleReportList(Date startDate, Date endDate){
        List <Report> reportList = getDateRangeReportList(startDate, endDate);
        return checkNull(reportList);
    }

    private List <SimpleReport>  checkNull( List <Report> reportList){
        List <SimpleReport> simpleReportList = new ArrayList<SimpleReport>();
        if (reportList!=null){
            for (Report report : reportList){
                simpleReportList.add(convertReportToSimpleReport(report));
            }
            return simpleReportList;
        }
        else {return  null;}
    }

    @Transactional
    public List<SimpleReport>  getUnasigneedSimpleReportList(){
        List <Report> reportList = getUnasignedReportList();
        return checkNull(reportList);
    }

    @Transactional
    public List<SimpleReport> getReportsByStep(int stepId){
        List <Report> reportList = getByStepReportList(stepId);
        return checkNull(reportList);
    }

    //////////

    @Transactional
    public List<Report>  getDateRangeReportList (Date startDate, Date endDate){
        Query query = openSession().createQuery("FROM Report  AS r WHERE r.date>=:startDate AND r.date <=:endDate ");
        query.setParameter("startDate",  startDate);
        query.setParameter("endDate",  endDate);
        if (query.list().isEmpty()) return null; else
            return (List<Report>) query.list();

    }

    @Transactional
    public List<Report> getUnasignedReportList(){
        Query query = openSession().createQuery("FROM Report  AS r WHERE r.policemanId=0 OR r.firemanId=0 ");
        if (query.list().isEmpty()) return null; else
            return (List<Report>) query.list();
    }

    public List<Report>  getByStepReportList(int stepId){
        Query query = openSession().createQuery("FROM Report  AS r WHERE r.stepId=:stepId");
        query.setParameter("stepId", stepId);
        if (query.list().isEmpty()) return null; else
            return (List<Report>) query.list();

    }

    @Transactional
    public void updateReport(Report report)
    {
        openSession().update(report);
    }

    @Transactional
    public void setReportAssigne(User user, Report report){
        if(user.getRole().equals("ROLE_FIREMAN")) {
            report.setFiremanId(user.getId());
            openSession().update(report);
        }
        else if (user.getRole().equals("ROLE_POLICE")){
            report.setPolicemanId(user.getId());
            openSession().update(report);
        }
        else {
            logger.error(String.format("User with id %s not in Police or fireman role, user have %s role",user.getId(),user.getRole()));
        }
    }

    @Transactional
    public  void  runSqlQery(String sqlQuery){
            Query query = openSession().createSQLQuery(sqlQuery);
            query.executeUpdate();
        }

}
