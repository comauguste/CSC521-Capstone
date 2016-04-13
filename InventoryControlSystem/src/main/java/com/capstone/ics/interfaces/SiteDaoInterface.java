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
 */
public interface SiteDaoInterface<T> {

    public void updateCompanyInformation(T entity);
    public Site getCompanyInformation();
    public ObservableList<Site> getSitesAsObservableList();

}
