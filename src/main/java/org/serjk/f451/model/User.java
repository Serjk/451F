package org.serjk.f451.model;

import javax.persistence.*;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name="ADDRESS")
    private String address;

    @Column(name = "TYPE")
    private UserType type;


    public long getId(){
        return this.id;
    }

    private void setId(long id){
        this.id = id;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }


    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }


    public UserType getType(){
        return this.type;
    }

    public void  setType(UserType type){
        this.type = type;
    }
}
