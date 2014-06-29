package org.serjk.f451.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "F_PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "UserID")
    private long userId;

    @Column(name = "WageID")
    private long wageId ;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Count")
    private double count ;


    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getUserId(){
        return this.userId;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public long getWageId(){
        return this.wageId;
    }

    public void setWageId(long wageId){
        this.wageId =  wageId;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date =  date;
    }

    public double getCount(){
        return this.count;
    }

    public void setCount(double count){
        this.count = count;
    }
}
