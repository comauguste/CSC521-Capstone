/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.DAO;

import com.capstone.ics.interfaces.InventoryItemsInterface;
import com.capstone.ics.model.InventoryItems;
import com.capstone.ics.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 *
 * @author Auguste C *
 */
public class InventoryItemsDAO implements InventoryItemsInterface<InventoryItems> {

    private List<InventoryItems> items = new ArrayList<>();

    public InventoryItemsDAO() {

    }

    @Override
    public void save(InventoryItems newItem) {
        HibernateUtil.getCurrentSession().save(newItem);
    }

    @Override
    public void update(InventoryItems newItem) {
        HibernateUtil.getCurrentSession().update(newItem);
    }

    @Override
    public InventoryItems findById(Integer id) {
        InventoryItems item = (InventoryItems) HibernateUtil.getCurrentSession().get(InventoryItems.class, id);
        return item;
    }

    @Override
    public void delete(InventoryItems newItem) {
        HibernateUtil.getCurrentSession().delete(newItem);
    }

    @Override
    public List<InventoryItems> getAllItems() {
        Query query = HibernateUtil.getCurrentSession().createQuery("select i from InventoryItems i");
        items = query.list();
        return items;
    }
    
    public ObservableList<InventoryItems> getAllItemsBasedOnUser(Integer id) {
        ObservableList<InventoryItems> observableItemsList;
        Query query = HibernateUtil.getCurrentSession().createQuery("select i from InventoryItems i "
                + "WHERE i.users.pkUserId = :id");
        query.setParameter("id", id);
        items = query.list();
        observableItemsList = FXCollections.observableArrayList(items);
        return observableItemsList;
    }

    @Override
    public ObservableList<InventoryItems> getItemsAsObservableList() {
        ObservableList<InventoryItems> observableItemsList;
        items = getAllItems();
        observableItemsList = FXCollections.observableArrayList(items);
        return observableItemsList;
    }

    
//    public static void main(String[] args) {
//        HibernateUtil.openCurrentSession();
//        InventoryItemsDAO test = new InventoryItemsDAO();
//        List<InventoryItems> lists = test.getAllItemsBasedOnUser(2);
//        
//        for(InventoryItems i : lists){
//            System.out.println(i.getItemName());
//        }
//        
//        HibernateUtil.closeCurrentSession();
//    }

}
