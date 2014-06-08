package org.serjk.f451.service.impl;

import org.serjk.f451.model.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.serjk.f451.dao.ReportDAO;
import org.serjk.f451.model.Report;
import org.serjk.f451.service.ReportService;
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
    public List<Report> listReport() {
        return reportDAO.listRepContact();
    }

    @Override
    public Report getReport(long reportId){
        return reportDAO.getReport(reportId);
    }

    @Override
    public List<Report> listMyReports(long userId) {

        return reportDAO.listMyReports(userId);
    }

    @Override
    public List<Report> listReportedToMe(long userId) {

        return reportDAO.listReportedToMe(userId);
    }

    @Override
    public void assignReport(long assigneId,long reportId)
    {
        reportDAO.assignReport(assigneId,reportId);
    }

    @Override
    public void moveReportToStep(long stepId,long reportId)
    {
        reportDAO.moveReportToStep(stepId,reportId);
    }

    @Override
    public void removeReport(long id) {
        reportDAO.removeReport(id);
    }

    public void addStep(Step step) {
        reportDAO.addStep(step);

    }

    public Step getStep(long stepId) {
        return  reportDAO.getStep(stepId);
    }

    public List<Step> listStep(){
        return reportDAO.listStep();
    }
}
