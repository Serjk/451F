package org.serjk.f451.dao;

import org.serjk.f451.model.Report;
import org.serjk.f451.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public interface ReportDAO {

    public void addReport(Report report);

    public List<Report> listRepContact();

    public Report getReport(long id);

    public List<Report>  listMyReports(long userId);

    public List<Report>  listReportedToMe(long userId);

    public void moveReportToStep(long stepId, long reportId);

    public void removeReport(long id);

    public void assignReportToFireman(long firemanId,long reportId);

    public void assignReportToPoliceman(long policemanId,long reportId);

    public void setRecordCountBook(long reportId, long countBook);

    public void setRecordResolutionDate(long reportId, Date date);
}
