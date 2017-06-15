package ucll.da.reportdomain.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import ucll.da.reportdomain.db.DBException;
import ucll.da.reportdomain.db.ReportDB;
import ucll.da.reportdomain.domain.Report;
import ucll.da.reportdomain.gatherer.DetailsGatherer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import ucll.da.reportdomain.domain.DomainException;

/**
 * Created by Joren on 4/05/2017.
 */
@Named
@RequestScoped
@Default
public class ReportServiceImpl implements ReportService {

    @Inject
    private ReportDB reportDB;

    @Inject
    private DetailsGatherer detailsGatherer;

    @Override
    public List<Report> getAllReports() throws ServiceException {
        try {
            return reportDB.getAllReports();
        } catch (DBException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Report getReportById(Long id) throws ServiceException {
        try {
            return reportDB.getReportById(id);
        } catch (DBException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteReport(Long id) throws ServiceException {
        try {
            reportDB.deleteReport(id);
        } catch (DBException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void generateReportFromPlace(String reportName, Long placeId) throws ServiceException {
        try {
            Report report = new Report(reportName);
            report.setDetails(detailsGatherer.getPlace(placeId).toString());
            reportDB.addReport(report);
            Files.write(Paths.get(reportName + ".txt"), detailsGatherer.getPlace(placeId), StandardOpenOption.CREATE);
            System.out.println("done writing file");
        } catch (IOException | DBException | DomainException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
