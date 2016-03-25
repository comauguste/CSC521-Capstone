/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics;

import com.capstone.ics.model.Credentials;
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
        Users user = new Users();
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmailAddress("test@test.com");
        user.setGender("F");
        user.setBirthDate(new Date());
        user.setPhoneNumber("234823402348");
        user.setFkJobTitleCode(5);
        user.setFkUserAddressId(8);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        
        
        
    }
    
}
