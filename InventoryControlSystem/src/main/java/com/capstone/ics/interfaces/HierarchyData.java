/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.interfaces;

import javafx.collections.ObservableList;

/**
 *
 * @author Auguste C
 */
public interface HierarchyData<T extends HierarchyData>{
    
   public ObservableList<T> getChildren();
    
}
