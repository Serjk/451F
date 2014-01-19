package org.serjk.f451.model;

import javax.persistence.*;


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

    @Column(name = "Description")
    private String description;

    @Column(name = "Date")
    private String date;

    @Column(name = "ReportId")
    private long  reporterId;

    @Column(name = "SuspectId")
    private long suspectId;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
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

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date= date;
    }
}