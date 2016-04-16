package com.capstone.ics.model;
// Generated Mar 26, 2016 2:14:13 PM by Hibernate Tools 4.3.1

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
 * UserAddresses generated by hbm2java
 */
@Entity
@Table(name = "user_addresses")
@Access(value = AccessType.PROPERTY)
public class UserAddresses  implements java.io.Serializable {


     private Integer pkAddressId;
     private Users users;
     private String userAddressLine1;
     private String userAddressLine2;
     private String city;
     private String zipCode;
     private String state;
     private String country;

    public UserAddresses() {
    }

	
    public UserAddresses(Users users, String userAddressLine1, String city, String zipCode, String state, String country) {
        this.users = users;
        this.userAddressLine1 = userAddressLine1;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.country = country;
    }
    public UserAddresses(Users users, String userAddressLine1, String userAddressLine2, String city, String zipCode, String state, String country) {
       this.users = users;
       this.userAddressLine1 = userAddressLine1;
       this.userAddressLine2 = userAddressLine2;
       this.city = city;
       this.zipCode = zipCode;
       this.state = state;
       this.country = country;
    }
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ADDRESS_ID")
    public Integer getPkAddressId() {
        return this.pkAddressId;
    }
    
    public void setPkAddressId(Integer pkAddressId) {
        this.pkAddressId = pkAddressId;
    }
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "PK_USER_ID")
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    
    @Column(name = "USER_ADDRESS_LINE_1")
    public String getUserAddressLine1() {
        return this.userAddressLine1;
    }
    
    public void setUserAddressLine1(String userAddressLine1) {
        this.userAddressLine1 = userAddressLine1;
    }
    
    @Column(name = "USER_ADDRESS_LINE_2")
    public String getUserAddressLine2() {
        return this.userAddressLine2;
    }
    
    public void setUserAddressLine2(String userAddressLine2) {
        this.userAddressLine2 = userAddressLine2;
    }
    
    @Column(name = "CITY")
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    
    @Column(name = "ZIP_CODE")
    public String getZipCode() {
        return this.zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    @Column(name = "STATE")
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    @Column(name = "COUNTRY")
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

}
