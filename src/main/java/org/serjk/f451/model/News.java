package org.serjk.f451.model;

import javax.persistence.*;
import java.io.Serializable;
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

    @Column(name = "Title")
    private String title;

    @Column(name = "Summary")
    private String summary;

    @Column(name = "Text")
    private String text;

    @Column(name = "Date")
    private Date date;

    @Column(name = "AutorId")
    private long  autorId;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getText(){
        return this.text;
    }

    public void setText(String text){
        this.text = text;
    }

    public  void  setSummary(String summary){
        this.summary =summary;
    }

    public  String getSummary(){
        return this.summary;
    }

    public  void  setAutorId (long autorId) {
        this.autorId = autorId;
    }

    public long getAutorId() {
        return  this.autorId;
    }
}
