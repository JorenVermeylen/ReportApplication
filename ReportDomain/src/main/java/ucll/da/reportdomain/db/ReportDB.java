package ucll.da.reportdomain.db;

import ucll.da.reportdomain.domain.Report;

import java.util.List;

/**
 * Created by verme on 3/05/2017.
 */
public interface ReportDB {

    List<Report> getAllReports() throws DBException;

    Report getReportById(Long id) throws DBException;

    void addReport(Report report) throws DBException;

    void deleteReport(Long id) throws DBException;

}
