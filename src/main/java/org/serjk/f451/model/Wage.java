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
    private long  cash;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getCash(){
        return this.id;
    }

    public void setCash(long cash){
        this.cash = cash;
    }
}
