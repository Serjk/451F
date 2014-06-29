package org.serjk.f451.dao;

import org.serjk.f451.model.Report;
import org.serjk.f451.model.SimpleReport;
import org.serjk.f451.model.User;
import org.serjk.f451.model.enums.Step;
import org.serjk.f451.model.enums.Transition;

import java.util.Date;
import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */

public interface ReportDAO {

    public void addReport(Report report);

    public List<Report> getReportList();

    public Report getReport(long id);

    public List<Report>  getMyReportList(User user);

    public List<Report>  getToMeReportList(User user);

    public List<Report> getAssignedToMeReportList(User user);

    public void updateReport(Report report);

    public void removeReport(long id);

    public void setReportAssigne(User user,Report report);

    public List<SimpleReport>  getToMeSimpleReportList(User user);

    public List<SimpleReport>  getMySimpleReportList(User user);

    public List<SimpleReport> getAssignedToMeSimpleReportList  (User user);

    public List<SimpleReport> getSimpleReportList();

    public List<SimpleReport>  getDateRangeSimpleReportList(Date startDate,
                                                            Date endDate);

    public List<SimpleReport>  getUnasigneedSimpleReportList();

    public List<SimpleReport> getReportsByStep(int stepId);

    public  void  runSqlQery(String sqlQuery);
}
