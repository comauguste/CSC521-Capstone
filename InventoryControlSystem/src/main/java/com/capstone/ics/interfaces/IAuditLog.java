/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.interfaces;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Auguste C
 */
public interface IAuditLog<T> {
    
    public void save(T entity);    

    public ObservableList<T> getLogsAsObservableList();
    
}
