package org.serjk.f451.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.serjk.f451.BaseTest;
import org.serjk.f451.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReportDAOTest extends BaseTest {

    @Autowired
    ReportDAO reportDAO;

    private long REPORT_ID = 42;
    private long SUSPECT_ID = 42;
    private String SUMMARY = "Login0";

    @Test
    public void testCreateReport() {
        createDefaultReport();
    }

    @Test
    public void testGetReport() {
        createDefaultReport();
        Report report = reportDAO.getReport(1);
        assertEquals(report.getSummary(), SUMMARY);
        assertEquals(report.getReporterId(),REPORT_ID);
        assertEquals(report.getSuspectId(),SUSPECT_ID);
    }

    @Test
    public void testRemoveReport() {
        testGetReport();
        reportDAO.removeReport(1);
        Report report  = reportDAO.getReport(1);
        assertNull(report);
    }

    @Test
    public void testGetReportList() {
        createDefaultReport();
        createDefaultReport();
        createDefaultReport();
        List<Report> reports = reportDAO.getReportList();
        assertEquals(reports.size(), 3);
    }

    @Test
    public void testUpdateReport() {
        createDefaultReport();
        Report report = reportDAO.getReport(1);

        int newStepID = 2;
        long newPoliceID = 123;

        report.setStepId(newStepID);
        report.setPolicemanId(newPoliceID);

        reportDAO.updateReport(report);

        Report newReport = reportDAO.getReport(1);

        assertEquals(newReport.getStepId(), newStepID);
        assertEquals(newReport.getPolicemanId(), newPoliceID);
    }

    private void createDefaultReport() {
        Report report = new Report();
        report.setReporterId(REPORT_ID);
        report.setSuspectId(SUSPECT_ID);
        report.setSummary(SUMMARY);
        report.setCountBook(-1);
        report.setDate(new Date());
        report.setStepId(1);

        reportDAO.addReport(report);
    }
}
