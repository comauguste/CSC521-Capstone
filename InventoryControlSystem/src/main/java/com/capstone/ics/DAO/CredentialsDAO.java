/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.DAO;

import com.capstone.ics.interfaces.CredentialsDaoInterface;
import com.capstone.ics.model.Credentials;
import com.capstone.ics.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Auguste C
 */
public class CredentialsDAO implements CredentialsDaoInterface {

    private List<Credentials> approvedUsers = new ArrayList<>();

    public CredentialsDAO() {

    }

    @Override
    public List<Credentials> getApproveUsers() {
        Query query = HibernateUtil.getCurrentSession().createQuery("select u from Credentials u");
        approvedUsers = query.list();
        return approvedUsers;
    }

}
