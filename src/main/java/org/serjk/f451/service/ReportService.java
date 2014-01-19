package org.serjk.f451.service;

import org.serjk.f451.model.Report;

import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public interface ReportService {

    public void addReport(Report report);

    public List<Report> listReport();

    public List<Report> listReportCurentUser(long userId);

    public void removeReport(long id);

}
