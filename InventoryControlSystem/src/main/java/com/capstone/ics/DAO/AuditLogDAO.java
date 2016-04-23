/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.DAO;

import com.capstone.ics.interfaces.IAuditLog;
import com.capstone.ics.model.AuditLog;
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
public class AuditLogDAO implements IAuditLog<AuditLog> {
    
    private List<AuditLog> logs = new ArrayList<>();

    @Override
    public void save(AuditLog entity) {
        HibernateUtil.getCurrentSession().save(entity);
    }

    @Override
    public ObservableList<AuditLog> getLogsAsObservableList() {        
        ObservableList<AuditLog> observableLogsList;
        Query query = HibernateUtil.getCurrentSession().createQuery("select a from AuditLog a");
        logs = query.list();
        observableLogsList = FXCollections.observableArrayList(logs);
        return observableLogsList;
    }

   
    
}
