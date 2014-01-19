package org.serjk.f451.service.impl;

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
    public void removeReport(long id) {
        reportDAO.removeReport(id);
    }
}
