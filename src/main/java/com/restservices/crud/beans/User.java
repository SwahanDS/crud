package com.restservices.crud.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
    
    @Id
    @Column(name="ps_no")
    int ps;

    @Column(name="first_name")
    String fname;

    @Column(name="last_name")
    String lname;

    @Column(name="email")
    String email;

    @Column(name="blood_group")
    String bg;

    public User() 
    {
        
    }

    public User(int ps, String fname, String lname, String email, String bg) {
        this.ps = ps;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.bg = bg;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

}
