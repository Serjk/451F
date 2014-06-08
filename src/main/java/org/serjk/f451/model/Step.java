package org.serjk.f451.model;

import javax.persistence.*;

@Entity
@Table(name = "F_STEP")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "StepName")
    private  String stepName;

    @Column(name = "StepSummary")
    private  String stepSummary;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getStepName(){
        return this.stepName;
    }

    public void setStepName(String stepName){
        this.stepName = stepName;
    }

    public String getStepSummary(){
        return this.stepSummary;
    }

    public void setStepSummary(String stepSummary){
        this.stepSummary = stepSummary;
    }

}
