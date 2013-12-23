package org.serjk.f451.model;



import javax.persistence.*;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
@Entity
@Table(name = "USER")
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private UserType type;


    public User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public long getId(){
        return this.id;
    }

    private void setId(long id){
        this.id = id;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @Column(name="ADDRESS")
    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    @Column(name = "TYPE")
    public UserType getType(){
        return this.type;
    }

    public void  setType(UserType type){
        this.type = type;
    }
}
