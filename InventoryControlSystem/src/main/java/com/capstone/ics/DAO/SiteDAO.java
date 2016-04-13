/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.DAO;

import com.capstone.ics.interfaces.SiteDaoInterface;
import com.capstone.ics.model.Site;
import com.capstone.ics.model.Users;
import com.capstone.ics.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 *
 * @author Auguste C
 */
public class SiteDAO implements SiteDaoInterface<Site> {

    private List<Site> sites = new ArrayList<>();
    
    @Override
    public void updateCompanyInformation(Site aSite) {
        HibernateUtil.getCurrentSession().update(aSite);
    }

    @Override
    public Site getCompanyInformation() {
        Integer siteID = 1;
        Site aSite = (Site) HibernateUtil.getCurrentSession().get(Site.class, siteID);
        return aSite;
    }

    @Override
    public ObservableList<Site> getSitesAsObservableList() {
        ObservableList<Site> observableSitesList;
        Query query = HibernateUtil.getCurrentSession().createQuery("select s from Site s");
        sites = query.list();
        observableSitesList = FXCollections.observableArrayList(sites);
        return observableSitesList;
    }

}
