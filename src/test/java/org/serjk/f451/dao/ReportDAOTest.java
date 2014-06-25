package org.serjk.f451.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.serjk.f451.BaseTest;
import org.serjk.f451.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

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
    public void createReport() {
        createDefaultReport();
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
