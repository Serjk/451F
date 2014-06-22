package org.serjk.f451.model;

import net.sf.ehcache.constructs.blocking.SelfPopulatingCache;

import java.util.Date;


public class SimpleReport {
    private long id;
    private String summary;
    private String description;
    private Date date;
    private String  reporterDisplayName;
    private String suspectDisplayName;
    private String stepName;
    private String firemanDisplayName;
    private String policemanDisplayName;
    private long countBook;

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

    public String getReporterDisplayName(){
        return this.reporterDisplayName;
    }

    public void setReporterDisplayName(String reporterDisplayName){
        this.reporterDisplayName = reporterDisplayName;
    }

    public String getSuspectDisplayName(){
        return this.suspectDisplayName;
    }

    public void setSuspectDisplayName(String suspectDisplayName){
        this.suspectDisplayName = suspectDisplayName;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public String getStepName(){
        return stepName;
    }

    public void setStepName(String stepName){
        this.stepName = stepName;
    }

    public long getCountBook(){
        return this.countBook;
    }

    public void setCountBook(long countBook){
        this.countBook = countBook;
    }

    public String getFiremanDisplayName(){
        return this.firemanDisplayName;
    }

    public void setFiremanDisplayName(String firemanDisplayName){
        this.firemanDisplayName = firemanDisplayName;
    }

    public String getPolicemanDisplayName(){
        return this.policemanDisplayName;
    }

    public void setPolicemanDisplayName(String policemanDisplayName){
        this.policemanDisplayName = policemanDisplayName;
    }

}
