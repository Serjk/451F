package org.serjk.f451.model;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 02.05.14
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "F_RECORD")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "Date")
    private Date date;

    @Column(name = "ReportId")
    private long  reportId;

    @Column(name = "FiremanId")
    private long firemanId;

    @Column(name = "PolicemanId")
    private long policemanId;

    @Column(name = "Column")
    private long column;

    @Column(name = "CountBook")
    private long countBook;


    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
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

    public Date getDate(){
        return this.date;
    }

    public void setDate(){
        this.date = new Date();
    }

    public long getCountBook(){
        return this.countBook;
    }

    public void setCountBook(long countBook){
        this.countBook = countBook;
    }
    public long getColumn(){
        return this.column;
    }

    public void setColumn(long column){
        this.column = column;
    }

    public long getReportId(){
        return this.reportId;
    }

    public void setReportId(long reportId){
        this.reportId = reportId;
    }
}