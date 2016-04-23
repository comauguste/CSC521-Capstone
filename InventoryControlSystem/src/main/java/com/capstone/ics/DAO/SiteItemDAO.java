/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.DAO;

import com.capstone.ics.model.SiteItemsQuantity;
import com.capstone.ics.model.Users;
import com.capstone.ics.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Auguste C
 */
public class SiteItemDAO {
    
     private List<SiteItemsQuantity> siteItemsQuantity = new ArrayList<>();

    public SiteItemDAO() {
    }
    
    
    public List<SiteItemsQuantity> getAllUsers() {
        Query query = HibernateUtil.getCurrentSession().createQuery("select si from SiteItemsQuantity si");
        siteItemsQuantity = query.list();
        for( SiteItemsQuantity item : siteItemsQuantity)
        {
            System.out.println(item.getInventoryItems().getItemName());
        }
        return siteItemsQuantity;
    }
    
    
    public static void main(String[] args)
    {
         Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        SiteItemDAO test = new SiteItemDAO();
        test.getAllUsers();
        
        session.getTransaction().commit();
        session.close();
    }
     
}
