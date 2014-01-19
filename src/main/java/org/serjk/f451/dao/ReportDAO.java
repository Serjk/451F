package org.serjk.f451.dao;

import org.serjk.f451.model.Report;

import java.util.List;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public interface ReportDAO {

    public void addReport(Report report);

    public List<Report> listRepContact();

    public Report get(long id);

    public void removeReport(long id);

}
