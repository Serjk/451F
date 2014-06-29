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
@Table(name = "F_BANK")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "Buget")
    private double buget;

    @Column(name = "WageID")
    private long wageId ;


    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public double getBuget(){
        return this.buget;
    }

    public void setBuget(double buget){
        this.buget = buget;
    }

    public long getWageId(){
        return this.wageId;
    }

    public void setWageId(long wageId){
        this.wageId =  wageId;
    }

}
