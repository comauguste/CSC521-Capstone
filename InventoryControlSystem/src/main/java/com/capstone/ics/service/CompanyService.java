/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.service;

import com.capstone.ics.DAO.SiteDAO;
import com.capstone.ics.model.Site;
import com.capstone.ics.util.HibernateUtil;
import javafx.collections.ObservableList;

/**
 *
 * @author Auguste C
 */
public class CompanyService {

    private static SiteDAO aSite;
    private ObservableList<Site> observableSitesLists;

    public CompanyService() {
        aSite = new SiteDAO();
    }

    public static SiteDAO getaSite() {
        return aSite;
    }

    public void updateCompanyInformation(Site theCompany) {
        HibernateUtil.openCurrentSessionWithTransaction();
        aSite.updateCompanyInformation(theCompany);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

    public Site getCompanyInformation() {
        HibernateUtil.openCurrentSession();
        Site theCompany = aSite.getCompanyInformation();
        HibernateUtil.closeCurrentSession();
        return theCompany;
    }

    public ObservableList<Site> getSitesAsObservableList() {
        HibernateUtil.openCurrentSession();
        observableSitesLists = aSite.getSitesAsObservableList();
        HibernateUtil.closeCurrentSession();

        return observableSitesLists;
    }

}
