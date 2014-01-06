package org.serjk.f451.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Date;
/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
@Entity
@Table(name = "REPORT")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "DATE")
    @Temporal(value=TemporalType.DATE)
    private Date date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suspect_id")

    private User suspect;

    public Report(String text, Date date, User reporter, User suspect) {
        this.text = text;
        this.date = date;
        this.reporter = reporter;
        this.suspect = suspect;
    }

    public Report(String text, User suspect, User reporter) {
        this.text = text;
        this.date = new Date();
        this.suspect = suspect;
        this.reporter = reporter;
    }

    public Report (User suspect, User reporter) {
        this.date = new Date();
        this.suspect = suspect;
        this.reporter = reporter;
    }

//    public Report () {
//        this.date = new Date();
//        this.suspect  = new User("Suspect_FirstName","Suspect_SecondName","Suspect_Address", UserType.HABITANT);
//        this.reporter = new User("Reporter_FirstName","Reporter_SecondName","Reporter_Address", UserType.HABITANT);
//    }

    public long getId(){
        return this.id;
    }

    private void setId(long id){
        this.id = id;
    }


    public String getText(){
        return this.text;
    }

    public void setText(String text){
        this.text = text;
    }

    public User getReporter(){
        return this.reporter;
    }

    public void setReporter(User reporter){
        this.reporter = reporter;
    }

    public User getSuspect(){
        return this.suspect;
    }

    public void setSuspect(User suspect){
        this.suspect = suspect;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date= date;
    }
}