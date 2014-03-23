package org.serjk.f451.service;

import org.serjk.f451.model.Report;

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

    public void removeReport(long id);

}
