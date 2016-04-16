/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.interfaces;

import com.capstone.ics.model.Users;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Auguste C
 */
public interface UsersDaoInterface<T> {

    public void save(T entity);

    public void update(T entity);

    public T findById(Integer id);

    public void delete(T entity);

    public List<T> getAllUsers();

    public ObservableList<T> getUsersAsObservableList();

}
