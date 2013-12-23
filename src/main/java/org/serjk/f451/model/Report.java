package org.serjk.f451.model;

import javax.persistence.*;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
@Entity
@Table(name = "REPORT")
public class Report {
    private long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "")
    private User reporter;
    private User suspect;

    public Report(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public long getId(){
        return this.id;
    }

    private void setId(long id){
        this.id = id;
    }

    @Column(name = "TEXT")
    public String getText(){
        return this.text;
    }
    public void setText(String text){
        this.text = text;
    }


}
