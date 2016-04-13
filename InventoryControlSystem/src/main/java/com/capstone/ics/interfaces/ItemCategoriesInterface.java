/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.interfaces;

import com.capstone.ics.model.RefItemCategories;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Auguste C
 * @param <T>
 */
public interface ItemCategoriesInterface<T> {
    
    public void add(T entity);

    public void update(T entity);  

    public void remove(String name);

    public List<T> getAllCategories();

    public ObservableList<RefItemCategories> getCategoriesAsObservableList();
    
}
