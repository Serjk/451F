package org.serjk.f451.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */

@Entity
@Table(name = "F_REPORT")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "Summary")
    private String summary;

    @Column(name = "Date")
    private Date date;

    @Column(name = "ReportId")
    private long  reporterId;

    @Column(name = "SuspectId")
    private long suspectId;

    @Column(name = "StepId")
    private  int stepId;


    @Column(name = "FiremanId")
    private long firemanId;

    @Column(name = "PolicemanId")
    private long policemanId;

    @Column(name = "CountBook")
    private long countBook;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getSummary(){
        return this.summary;
    }

    public void setSummary(String summary){
        this.summary = summary;
    }

    public long getReporterId(){
        return this.reporterId;
    }

    public void setReporterId(long reporterId){
        this.reporterId = reporterId;
    }

    public long getSuspectId(){
        return this.suspectId;
    }

    public void setSuspectId(long suspectId){
        this.suspectId = suspectId;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public int getStepId(){
        return stepId;
    }

    public void setStepId(int stepId){
        this.stepId = stepId;
    }

    public long getCountBook(){
        return this.countBook;
    }

    public void setCountBook(long countBook){
        this.countBook = countBook;
    }

    public long getFiremanId(){
        return this.firemanId;
    }

    public void setFiremanId(long firemanId){
        this.firemanId = firemanId;
    }

    public long getPolicemanId(){
        return this.policemanId;
    }

    public void setPolicemanId(long policemanId){
        this.policemanId = policemanId;
    }

}