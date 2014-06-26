package org.serjk.f451.service;

import org.serjk.f451.model.Report;
import org.serjk.f451.model.SimpleReport;
import org.serjk.f451.model.User;
import org.serjk.f451.model.enums.Transition;

import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public interface ReportService {

    public void addReport(Report report);

    public Report getReport(long reportId);

    public List<Report> getReportList();

    public List<Report> getMyReportList(User user);

    public List<Report> getToMeReportList(User user);

    public void removeReport(long id);

    public List <Report>  getAssignedToMeReportList(User user);

    public List<SimpleReport>  getToMeSimpleReportList(User user);

    public List<SimpleReport>  getMySimpleReportList(User user);

    public List<SimpleReport> getAssignedToMeSimpleReportList(User user);

    public List<SimpleReport> getSimpleReportList();

    public List<SimpleReport>  getDateRangeSimpleReportList(long starttimestamp,
                                                            long endtimestamp);

    public List<SimpleReport>  getUnasigneedSimpleReportList();

    public List<SimpleReport>  getByStepSimpleReportList(int stepId);

    public void setReportAssigne(User user,Report report);

    public void setReport(Report report);

}
