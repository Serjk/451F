package org.serjk.f451.service;

import org.serjk.f451.model.Report;
import org.serjk.f451.model.Step;

import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public interface ReportService {

    public void addReport(Report report);

    public Report getReport(long reportId);

    public List<Report> listReport();

    public List<Report> listMyReports(long userId);

    public List<Report> listReportedToMe(long userId);

    public void assignReport(long assigneId,long reportId);

    public void moveReportToStep(long stepId,long reportId);

    public void removeReport(long id);

    public void addStep(Step step);

    public Step getStep(long stepId);

    public List<Step> listStep();

}
