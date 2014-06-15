package org.serjk.f451.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "F_TRANSITION")
public class Transition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "Name")
    private  String name;

    @Column(name = "StepIn")
    private  long stepIn;

    @Column(name = "StepOut")
    private  long stepOut;

    @Column(name = "Permission")
    private String permission;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setPermission(String permission){
        this.permission = permission;
    }
    public String getPermission(){
        return this.permission;
    }

    public void setName(String name){
        this.name =name;
    }

    public String getName(){
        return this.name;
    }

    public long getStepIn(){
        return this.stepIn;
    }

    public void setStepIn(long stepIn){
        this.stepIn = stepIn;
    }

    public void setStepOut(long stepOut){
        this.stepOut = stepOut;
    }

    public long getStepOut(){
        return this.stepOut;
    }
}
