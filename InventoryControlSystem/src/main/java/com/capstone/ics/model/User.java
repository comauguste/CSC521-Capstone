/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Auguste C
 */

@Entity
@Table(name="user")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long userID;
    
    @Column(name ="username")
    private String username;
    
    @Column(name ="password")
    private String password;
    
    @Column(name ="created_date")
    private Date createdDate;

    public User() {
    }
     
    public User(Long userID) {
        this.userID = userID;
    }

    public User(Long userID, String username, String password, Date createdDate) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
    }
    
    
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    
}
