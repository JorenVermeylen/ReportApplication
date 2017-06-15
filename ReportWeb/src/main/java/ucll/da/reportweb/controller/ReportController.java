package ucll.da.reportweb.controller;

import ucll.da.reportdomain.domain.Report;
import ucll.da.reportdomain.service.ServiceException;
import ucll.da.reportweb.facade.ReportFacade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Joren on 3/05/2017.
 */
@Named
@RequestScoped
@Path("/report")
public class ReportController {

    @Inject
    ReportFacade facade;

    public ReportController() {

    }

    @GET
    @Path("/health")
    public String getHealth() {
        return "Up & Running!";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Report getReport(@PathParam("id") Long id) throws ServiceException {
        return facade.getReportById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Report> getAllReports() throws ServiceException {
        return facade.getAllReports();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteReport(@PathParam("id") Long id) throws ServiceException {
        return facade.deleteReport(id);
    }

    @POST
    @Path("/{reportName}/{placeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Report generateReportFromPlace(@PathParam("reportName") String reportName, @PathParam("placeId") Long placeId) throws ServiceException {
        return facade.generateReportFromPlace(reportName, placeId);
    }
}
