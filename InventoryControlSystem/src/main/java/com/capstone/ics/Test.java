/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics;

import com.capstone.ics.model.Credentials;
import com.capstone.ics.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Auguste C
 */
public class Test {
    
    public static void main (String[] args)
    {
        Credentials user = new Credentials();
        user.setFkCrUserId(3);
        user.setUsername("ghislain");
        user.setPassword("1");
        user.setIsAdministrator(1);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        
        
        
    }
    
}
