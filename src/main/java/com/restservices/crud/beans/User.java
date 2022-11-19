package com.restservices.crud.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
    
    @Id
    @Column(name="id")
    int id;

    @Column(name="user_name")
    String name;

    @Column(name="department")
    String dept;

    public User() 
    {
        
    }

    public User(int id, String name, String dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDept() {
        return dept;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    
}
