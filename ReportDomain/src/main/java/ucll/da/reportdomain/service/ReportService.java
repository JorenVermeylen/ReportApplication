package ucll.da.reportdomain.service;

import ucll.da.reportdomain.domain.Report;

import java.util.List;

/**
 * Created by Joren on 4/05/2017.
 */
public interface ReportService {

    List<Report> getAllReports() throws ServiceException;

    Report getReportById(Long id) throws ServiceException;

    boolean deleteReport(Long id) throws ServiceException;

    Report generateReportFromPlace(String reportName, Long placeId) throws ServiceException;
}
