/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics;

import com.capstone.ics.model.Credentials;
import com.capstone.ics.model.Site;
import com.capstone.ics.model.UserAddresses;
import com.capstone.ics.model.Users;
import com.capstone.ics.util.HibernateUtil;
import java.util.Date;
import org.hibernate.Session;

/**
 *
 * @author Auguste C
 */
public class Test {
    
    public static void main (String[] args)
    {
        Site aSite = new Site();
        aSite.setSiteName("New World");
        aSite.setSiteAddressLine1("315 Lafayette st");
        aSite.setSiteCity("salem");
        aSite.setSiteState("MA");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(aSite);
        session.getTransaction().commit();
        session.close();
        
        
        
    }
    
    public void testDbConnectionToUser()
    {
         Users user = new Users();
        user.setFirstName("tester03");
        user.setLastName("testing");
        user.setEmailAddress("test@test.com");
        user.setGender("F");
        user.setBirthDate(new Date());
        user.setPhoneNumber("234823402348");
        user.setFkJobTitleCode(5);
        
        Credentials aCredentials = new Credentials();
        aCredentials.setUsername("tester");
        aCredentials.setPassword("password");
        aCredentials.setIsAdministrator(true);
        aCredentials.setAccessLogModule(true);
        aCredentials.setAccessReportModule(true);
        
        UserAddresses addresses = new UserAddresses();
        addresses.setUserAddressLine1("315 lafayyette");
        addresses.setUserAddressLine2("315 lafayyette");
        addresses.setCity("Salem");
        addresses.setZipCode("01970");
        addresses.setState("MA");
        addresses.setCountry("USA");
        
        aCredentials.setUsers(user);
        addresses.setUsers(user);
        user.setUserCredentials(aCredentials);
        user.setAddress(addresses);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
    
}
