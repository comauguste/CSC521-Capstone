/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.service;

import com.capstone.ics.DAO.CredentialsDAO;
import com.capstone.ics.model.Credentials;
import com.capstone.ics.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Auguste C
 */
public class CredentialsService {
    private static CredentialsDAO credentialsDAO;
    private List<Credentials> approvedUsers = new ArrayList<>();

    public CredentialsService() {
        credentialsDAO = new CredentialsDAO();
    }

    public static CredentialsDAO getCredentialsDAO() {
        return credentialsDAO;
    }
    
     public List<Credentials> getApproveUsers()
     {
         HibernateUtil.openCurrentSession();
        approvedUsers = credentialsDAO.getApproveUsers();
        HibernateUtil.closeCurrentSession();

        return approvedUsers;
     }
        
}
