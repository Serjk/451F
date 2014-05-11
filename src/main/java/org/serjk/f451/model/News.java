package org.serjk.f451.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: kreker
 * Date: 02.05.14
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "F_NEWS")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "Date")
    private Date date;

    @Column(name = "RecordID")
    private long recordID;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(){
        this.date = new Date();
    }

    public long getRecord(){
        return this.recordID;
    }

    public void setRecordID(long recordID){
        this.recordID = recordID;
    }
}
