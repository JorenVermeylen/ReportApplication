package ucll.da.reportweb.facade;

import ucll.da.reportdomain.domain.Report;
import ucll.da.reportdomain.service.ServiceException;

import java.util.List;

/**
 * Created by verme on 4/05/2017.
 */
public interface ReportFacade {

    List<Report> getAllReports() throws ServiceException;

    Report getReportById(Long id) throws ServiceException;
    
    void deleteReport(Long id) throws ServiceException;

    void generateReportFromPlace(String reportName, Long placeId) throws ServiceException;
    
}
