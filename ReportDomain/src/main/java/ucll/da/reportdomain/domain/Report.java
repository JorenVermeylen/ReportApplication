package ucll.da.reportdomain.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Calendar;
import javax.validation.constraints.NotNull;

/**
 * Created by Joren on 3/05/2017.
 */
@Entity(name = "Report")
public class Report {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true)
    private String fileName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+2")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    
    private String details;
    
    public Report() {

    }

    public Report(String fileName) throws DomainException {
        this.setFileName(fileName);
        this.setDate(Calendar.getInstance());
    }

    public Report(String fileName, Calendar date) throws DomainException {
        this.setFileName(fileName);
        this.setDate(date);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) throws DomainException {
        if (fileName == null || fileName.equals("")) {
            throw new DomainException("The fileName can't be null or empty.");
        }
        this.fileName = fileName;
    }
    
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) throws DomainException {
        if (date == null) {
            throw new DomainException("The date can't be null");
        }
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
    
}
