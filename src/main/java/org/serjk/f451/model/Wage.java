package org.serjk.f451.model;

import javax.persistence.*;

@Entity
@Table(name = "F_WAGE")
public class Wage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "Cash")
    private double  cash;

    @Column(name = "Type")
    private String  type;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public double getCash(){
        return this.cash;
    }

    public void setCash(double cash){
        this.cash = cash;
    }

    public String getType(){
        return this.type;
    }

}
