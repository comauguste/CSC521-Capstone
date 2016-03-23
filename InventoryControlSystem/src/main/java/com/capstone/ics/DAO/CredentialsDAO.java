/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.DAO;

import com.capstone.ics.model.Credentials;
import com.capstone.ics.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Auguste C
 */
public class CredentialsDAO {

    SessionFactory factory;
    Session session;
    org.hibernate.Transaction tx;
    private List<Credentials> users = new ArrayList<>();

    public CredentialsDAO() {

    }

    public List<Credentials> retrieveAllowedUsers() {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();            
            
            Query query = session.createQuery("select u from Credentials u");
            users = query.list();
            tx.commit();
            return users;
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            factory.close();
        }

        return users;
    }

}
