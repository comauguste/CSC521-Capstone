package com.capstone.ics.model;
// Generated Mar 23, 2016 6:34:53 PM by Hibernate Tools 4.3.1

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Credentials generated by hbm2java
 */
@Entity
@Table(name = "credentials")
@Access(value = AccessType.PROPERTY)
public class Credentials implements java.io.Serializable {

    private Integer credentialsId;
    private Users users;
    private String username;    
    private String password;
    private boolean isAdministrator;
    private boolean accessReportModule;
    private boolean accessLogModule;
    private boolean receiveNotification;

    public Credentials() {
    }

    public Credentials(String username, String password) {
         this.username = username;
        this.password = password;
    }

    public Credentials( String username, String password, boolean isAdministrator, boolean accessReportModule, boolean accessLogModule) {
       this.username = username;
       this.password = password;
       this.isAdministrator = isAdministrator;
       this.accessReportModule = accessReportModule;
       this.accessLogModule = accessLogModule;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CREDENTIALS_ID")
    public Integer getCredentialsId() {
        return this.credentialsId;
    }

    public void setCredentialsId(Integer credentialsId) {
        this.credentialsId = credentialsId;
    }

   

    @Column(name = "USERNAME")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "IS_ADMINISTRATOR")
    public boolean isIsAdministrator() {
        return isAdministrator;
    }

    
    public void setIsAdministrator(boolean isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    @Column(name = "ACCESS_REPORT_MODULE")
    public boolean isAccessReportModule() {
        return accessReportModule;
    }

    public void setAccessReportModule(boolean accessReportModule) {
        this.accessReportModule = accessReportModule;
    }

    @Column(name = "ACCESS_LOG_MODULE")
    public boolean isAccessLogModule() {
        return accessLogModule;
    }

    
    public void setAccessLogModule(boolean accessLogModule) {
        this.accessLogModule = accessLogModule;
    }    

    @Column(name = "RECEIVE_NOTIFICATION")
    public boolean isReceiveNotification() {
        return receiveNotification;
    }

    public void setReceiveNotification(boolean receiveNotification) {
        this.receiveNotification = receiveNotification;
    }
    
    

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CR_USER_ID", referencedColumnName = "PK_USER_ID")
    public Users getUsers() {
        return users;
    }
   
    public void setUsers(Users users) {
        this.users = users;
    }    
    

    public String convertAccessLevelToString()
    {
        if(isIsAdministrator()== false)
        {
            return "Regular User";
        }
        else
        {
            return "Administrator";
        }
    }
    
    public String convertReportAccessLevelToString()
    {
        if(isAccessReportModule()== false)
        {
            return "No";
        }
        else
        {
            return "Yes";
        }
    }
    
    public String convertLogAccessLevelToString()
    {
        if(isAccessLogModule()== false)
        {
            return "No";
        }
        else
        {
            return "Yes";
        }
    }
    
     public String convertReceiveNotificationToString()
    {
        if(isReceiveNotification()== false)
        {
            return "No";
        }
        else
        {
            return "Yes";
        }
    }
    
    public void returnAccessLevelAsBoolean(String acl)
    {
        if(acl.equals("Administrator"))
        {
            setIsAdministrator(true);
        }
        else{
            setIsAdministrator(false);
        }
        
    }
    
    public void returnReportAccessLevelAsBoolean(String acl)
    {
        if(acl.equals("Yes"))
        {
            setAccessReportModule(true);
        }
        else
        {
            setAccessReportModule(false);
        }
    }
    
    public void returnLogAccessLevelAsBoolean(String acl)
    {
        if(acl.equals("Yes"))
        {
            setAccessLogModule(true);
        }
        else
        {
            setAccessLogModule(false);
        }
    }
    
    public void returnReceiveNotificationAsBoolean(String acl)
    {
        if(acl.equals("Yes"))
        {
            setReceiveNotification(true);
        }
        else
        {
            setReceiveNotification(false);
        }
    }
    
    
}