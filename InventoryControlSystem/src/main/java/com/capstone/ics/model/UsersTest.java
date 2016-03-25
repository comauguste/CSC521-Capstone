/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.model;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
@Table(name = "users_test")
public class UsersTest implements Serializable {
    
    private Long userID;
    private StringProperty firstName = new SimpleStringProperty(this,"FIRST_NAME");
    private StringProperty lastName =  new SimpleStringProperty(this,"LAST_NAME");

        public UsersTest() {
    }

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_USER_ID")
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
    
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstNameProperty().get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    public StringProperty firstNameProperty()
    {
        return this.firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastNameProperty().get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    
    public StringProperty lastNameProperty()
    {
        return this.firstName;
    }
    
}
