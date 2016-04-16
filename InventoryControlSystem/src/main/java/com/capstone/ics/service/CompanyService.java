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

    private static SiteDAO siteDAO;
    private ObservableList<Site> observableSitesLists;

    public CompanyService() {
        siteDAO = new SiteDAO();
    }

    public static SiteDAO getaSite() {
        return siteDAO;
    }

    public void saveNewBranch(Site theCompany) {
        HibernateUtil.openCurrentSessionWithTransaction();
        siteDAO.saveBranch(theCompany);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

    public void updateCompanyInformation(Site theCompany) {
        HibernateUtil.openCurrentSessionWithTransaction();
        siteDAO.updateCompanyInformation(theCompany);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

     public Site findById(Integer id) {
        HibernateUtil.openCurrentSession();
        Site aSite = siteDAO.findById(id);
        HibernateUtil.closeCurrentSession();

        return aSite;
    }

    //Do not forget to change the ID data type from String TO int or long( whatever appropriate   
    public void delete(Integer id) {
        HibernateUtil.openCurrentSessionWithTransaction();
        Site aUser = siteDAO.findById(id);
        siteDAO.delete(aUser);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }
    
    public Site getCompanyInformation() {
        HibernateUtil.openCurrentSession();
        Site theCompany = siteDAO.getCompanyInformation();
        HibernateUtil.closeCurrentSession();
        return theCompany;
    }

    public ObservableList<Site> getSitesAsObservableList() {
        HibernateUtil.openCurrentSession();
        observableSitesLists = siteDAO.getSitesAsObservableList();
        HibernateUtil.closeCurrentSession();

        return observableSitesLists;
    }
    
    public ObservableList<Site> getBranchAsObservableList() {
        
        HibernateUtil.openCurrentSession();
        observableSitesLists = siteDAO.getBranchesAsObservableList();
        HibernateUtil.closeCurrentSession();

        return observableSitesLists;
        
    }

     public ObservableList<Site> getSiteData() {
        return observableSitesLists;
    }
    
}
