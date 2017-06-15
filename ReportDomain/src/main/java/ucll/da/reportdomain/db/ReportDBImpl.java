package ucll.da.reportdomain.db;

import ucll.da.reportdomain.domain.Report;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by verme on 3/05/2017.
 */
@Named
@RequestScoped
@Default
public class ReportDBImpl implements ReportDB {

    @PersistenceContext(unitName = "ReportPUnit")
    private EntityManager em;

    @Override
    public List<Report> getAllReports() throws DBException {
        try {
            Query query = em.createQuery("SELECT r FROM Report r");
            return (List<Report>) query.getResultList();
        } catch (Exception e) {
            throw new DBException("Can't retrieve all reports. " + e.getMessage());
        }
    }

    @Override
    public Report getReportById(Long id) throws DBException {
        try {
            return em.find(Report.class, id);
        } catch (Exception e) {
            throw new DBException("Can't retrieve the report. " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean addReport(Report report) throws DBException {
        try {
            em.persist(report);
            em.flush();
            return true;
        } catch (Exception e) {
            throw new DBException("Can't add the report. " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean deleteReport(Long id) throws DBException {
        try {
            Report report = this.getReportById(id);
            em.remove(report);
            em.flush();
            return true;
        } catch (Exception e) {
            throw new DBException("Can't delete the report. " + e.getMessage());
        }
    }
}
