/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics;

import com.capstone.ics.model.Credentials;
import com.capstone.ics.model.InventoryItems;
import com.capstone.ics.model.RefItemCategories;
import com.capstone.ics.model.Site;
import com.capstone.ics.model.SiteItemsQuantity;
import com.capstone.ics.model.UserAddresses;
import com.capstone.ics.model.Users;
import com.capstone.ics.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;

/**
 *
 * @author Auguste C
 */
public class Test {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        InventoryItems item = (InventoryItems) session.get(InventoryItems.class, 7);
        List<SiteItemsQuantity> sites = new ArrayList<>();
        sites.addAll(item.getSiteItemsQuantities());

        for (SiteItemsQuantity site : sites) {
            System.out.println("Inventory name: " + site.getSite().getSiteName() + " Item Name: " + site.getInventoryItems().getItemName() +
                    "Item Quantity: "+ site.quantityIntegerProperty());
        }

        session.getTransaction().commit();
        session.close();

    }

    public void storeAnItemInManySites() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Users user = (Users) session.get(Users.class, 1);

        InventoryItems item = new InventoryItems();

        item.setItemName("Computers");
        item.setItemCategory("Laptops");
        item.setCreatedBy("Auguste");
        item.setItemCost(BigDecimal.ZERO);
        item.setReorderLevel(Integer.MIN_VALUE);
        item.setItemPrice(BigDecimal.ZERO);
        item.setUsers(user);
        item.setQuantityInStock(20);

        Site site1 = (Site) session.get(Site.class, 4);
        Site site2 = (Site) session.get(Site.class, 6);

        //Store item in 1st site
        SiteItemsQuantity siteItem1 = new SiteItemsQuantity();
        siteItem1.setItemQuantity(Integer.MIN_VALUE);
        siteItem1.setInventoryItems(item);
        siteItem1.setSite(site1);
        item.addSiteItemsQuantities(siteItem1);

        //Store item in 2nd site
        SiteItemsQuantity siteItem2 = new SiteItemsQuantity();
        siteItem2.setItemQuantity(Integer.MIN_VALUE);
        siteItem2.setInventoryItems(item);
        siteItem2.setSite(site2);

        item.addSiteItemsQuantities(siteItem2);

        session.save(item);

        session.getTransaction().commit();
        session.close();
    }

    public void testDbConnectionToUser() {
        Users user = new Users();
        user.setFirstName("tester03");
        user.setLastName("testing");
        user.setEmailAddress("test@test.com");
        user.setGender("F");
        user.setBirthDate(new Date());
        user.setPhoneNumber("234823402348");

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

    public void testDbConnectionToItems() {
        Site aSite = new Site();
        aSite.setSiteName("New World");
        aSite.setSiteAddressLine1("315 Lafayette st");
        aSite.setSiteAddressLine2("315 Lafayette st");
        aSite.setSiteEmail("test");
        aSite.setSiteCity("salem");
        aSite.setSiteState("MA");

    }

}
