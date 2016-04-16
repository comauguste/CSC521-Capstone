/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.interfaces;

import com.capstone.ics.model.Site;
import javafx.collections.ObservableList;

/**
 *
 * @author Auguste C
 * @param <T>
 */
public interface SiteDaoInterface<T> {

    public void updateCompanyInformation(T entity);

    public void saveBranch(T entity);

    public Site getCompanyInformation();

    public T findById(Integer id);

    public void delete(T entity);

    public ObservableList<T> getBranchesAsObservableList();

    public ObservableList<T> getSitesAsObservableList();

}
