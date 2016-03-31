package com.capstone.ics.model;
// Generated Mar 23, 2016 6:34:53 PM by Hibernate Tools 4.3.1

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Credentials generated by hbm2java
 */
@Entity
@Table(name = "credentials")
@Access(value = AccessType.PROPERTY)
public class Credentials implements java.io.Serializable {

    private Integer credentialsId;

    
    private int fkCrUserId;

    private Users users;
    private String username;    
    private String password;
    private boolean isAdministrator;
    private boolean accessReportModule;
    private boolean accessLogModule;

    public Credentials() {
    }

    public Credentials(int fkCrUserId, String username, String password) {
        this.fkCrUserId = fkCrUserId;
        this.username = username;
        this.password = password;
    }

    public Credentials(int fkCrUserId, String username, String password, boolean isAdministrator, boolean accessReportModule, boolean accessLogModule) {
       this.fkCrUserId = fkCrUserId;
       this.username = username;
       this.password = password;
       this.isAdministrator = isAdministrator;
       this.accessReportModule = accessReportModule;
       this.accessLogModule = accessLogModule;
    }

    @Id
    @GeneratedValue(generator ="gen")
    @GenericGenerator(name = "gen", strategy = "foreign", parameters = {@Parameter(name = "property", value ="users")})
    @Column(name = "CREDENTIALS_ID")
    public Integer getCredentialsId() {
        return this.credentialsId;
    }

    public void setCredentialsId(Integer credentialsId) {
        this.credentialsId = credentialsId;
    }

    @Column(name = "FK_CR_USER_ID")
    public int getFkCrUserId() {
        return this.fkCrUserId;
    }

    public void setFkCrUserId(int fkCrUserId) {
        this.fkCrUserId = fkCrUserId;
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

    @OneToOne
    @PrimaryKeyJoinColumn
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
    
    
}
