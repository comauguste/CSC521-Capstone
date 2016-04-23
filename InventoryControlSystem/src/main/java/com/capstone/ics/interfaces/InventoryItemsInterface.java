/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.interfaces;

import com.capstone.ics.model.InventoryItems;
import com.capstone.ics.model.SiteItemsQuantity;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Auguste C
 * @param <T>
 */
public interface InventoryItemsInterface<T> {

    public void save(T entity);

    public void update(T entity);

    public T findById(Integer id);

    public void delete(T entity);

    public List<T> getAllItems();

    public ObservableList<InventoryItems> getItemsAsObservableList();
    
}
