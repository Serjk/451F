package org.serjk.f451.service.impl;

import org.serjk.f451.model.SimpleReport;
import org.serjk.f451.model.User;
import org.serjk.f451.model.enums.Transition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.serjk.f451.dao.ReportDAO;
import org.serjk.f451.model.Report;
import org.serjk.f451.service.ReportService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 19.03.14
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDAO reportDAO;

    @Transactional
    public void addReport(Report report) {
        reportDAO.addReport(report);
    }

    @Override
    public List<Report> getReportList() {
        return reportDAO.getReportList();
    }

    @Override
    public Report getReport(long reportId){
        return reportDAO.getReport(reportId);
    }

    @Override
    public List<Report> getMyReportList(User user) {
        return reportDAO.getMyReportList(user);
    }

    @Override
    public List<Report> getToMeReportList(User user) {
        return reportDAO.getToMeReportList(user);
    }

    @Override
    public void setReport(Report report){
        reportDAO.updateReport(report);
    }

    @Override
    public void removeReport(long id) {
        reportDAO.removeReport(id);
    }

    @Override
    public void setReportAssigne(User user,Report report){
        reportDAO.setReportAssigne(user,report);
    }

    @Override
    public  List <Report> getAssignedToMeReportList (User user){
        return  reportDAO.getAssignedToMeReportList(user);
    }

    public List<SimpleReport>  getToMeSimpleReportList(User user){
        return  reportDAO.getToMeSimpleReportList(user);
    }

    @Override
    public List<SimpleReport>  getMySimpleReportList(User user){
        return  reportDAO.getMySimpleReportList(user);
    }

    @Override
    public List<SimpleReport> getAssignedToMeSimpleReportList  (User user){
        return  reportDAO.getAssignedToMeSimpleReportList(user);
    }

    @Override
    public List<SimpleReport> getSimpleReportList(){
        return  reportDAO.getSimpleReportList();
    }

    @Override
    public List<SimpleReport>  getDateRangeSimpleReportList(long starttimestamp,
                                                            long endtimestamp){
        return reportDAO.getDateRangeSimpleReportList(starttimestamp,endtimestamp);
    }

    @Override
    public List<SimpleReport>  getUnasigneedSimpleReportList(){
        return  reportDAO.getUnasigneedSimpleReportList();
    }

    @Override
    public List<SimpleReport>  getByStepSimpleReportList(int stepId){
         return  reportDAO.getReportsByStep(stepId);
    }

    @Override
    public List <Transition> getOutgoingTransitionsID(int stepId, String role){


        List <Transition>  transitionList  =  new ArrayList<Transition>();

        for (Transition transition : Transition.values()){
            if(transition.getStepIn()==stepId && transition.getPermission().equals(role))
                transitionList.add(transition);
        }
         if (transitionList.isEmpty() )return  null;
            else  return  transitionList;

    }
}
