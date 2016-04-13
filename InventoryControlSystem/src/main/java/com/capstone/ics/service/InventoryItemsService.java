/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.service;

import com.capstone.ics.DAO.InventoryItemsDAO;
import com.capstone.ics.model.InventoryItems;
import com.capstone.ics.util.HibernateUtil;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Auguste C
 */
public class InventoryItemsService {

    private static InventoryItemsDAO inventoryItemsDAO;
    private ObservableList<InventoryItems> observableItemsList;

    public InventoryItemsService() {
        inventoryItemsDAO = new InventoryItemsDAO();
        observableItemsList = FXCollections.observableArrayList();
    }

    public void save(InventoryItems newItem) {
        HibernateUtil.openCurrentSessionWithTransaction();
        inventoryItemsDAO.save(newItem);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

    public void update(InventoryItems newItem) {
        HibernateUtil.openCurrentSessionWithTransaction();
        inventoryItemsDAO.update(newItem);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

    public InventoryItems findById(Integer id) {
        HibernateUtil.openCurrentSession();
        InventoryItems item = inventoryItemsDAO.findById(id);
        HibernateUtil.closeCurrentSession();

        return item;
    }

    public void delete(InventoryItems newItem) {
        HibernateUtil.openCurrentSessionWithTransaction();
        inventoryItemsDAO.delete(newItem);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

    public List<InventoryItems> getAllItems() {
        HibernateUtil.openCurrentSession();
        List<InventoryItems> items = inventoryItemsDAO.getAllItems();
        HibernateUtil.closeCurrentSession();

        return items;
    }

    public ObservableList<InventoryItems> getItemsAsObservableList() {
        HibernateUtil.openCurrentSession();
        observableItemsList = inventoryItemsDAO.getItemsAsObservableList();
        HibernateUtil.closeCurrentSession();
       
        return observableItemsList;
    }

     public ObservableList<InventoryItems> getItemsData() {
        return observableItemsList;
    }
}
