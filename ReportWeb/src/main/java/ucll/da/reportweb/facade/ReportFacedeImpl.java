package ucll.da.reportweb.facade;

import ucll.da.reportdomain.domain.Report;
import ucll.da.reportdomain.service.ReportService;
import ucll.da.reportdomain.service.ServiceException;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by verme on 4/05/2017.
 */
@Named
@RequestScoped
@Default
public class ReportFacedeImpl implements ReportFacade {

    @Inject
    private ReportService reportService;

    @Override
    public List<Report> getAllReports() throws ServiceException {
        return reportService.getAllReports();
    }

    @Override
    public Report getReportById(Long id) throws ServiceException {
        return reportService.getReportById(id);
    }
    
    @Override
    public void deleteReport(Long id) throws ServiceException {
        reportService.deleteReport(id);
    }

    @Override
    public void generateReportFromPlace(String reportName, Long placeId) throws ServiceException {
        reportService.generateReportFromPlace(reportName, placeId);
    }

}
