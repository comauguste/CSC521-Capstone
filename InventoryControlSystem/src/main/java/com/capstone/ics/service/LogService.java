/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.service;

import com.capstone.ics.DAO.AuditLogDAO;
import com.capstone.ics.model.AuditLog;
import com.capstone.ics.util.HibernateUtil;
import javafx.collections.ObservableList;

/**
 *
 * @author Auguste C
 */
public class LogService {
    
    private static AuditLogDAO logDao;
    private ObservableList<AuditLog> observableLogsList;

    public LogService() {
        logDao = new AuditLogDAO();
    }
    
     public void save(AuditLog aLog) {
        HibernateUtil.openCurrentSessionWithTransaction();
        logDao.save(aLog);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }
     
      public ObservableList<AuditLog> getLogsAsObservableList() {        
        HibernateUtil.openCurrentSession();
        observableLogsList = logDao.getLogsAsObservableList();
        HibernateUtil.closeCurrentSession();
        return observableLogsList;
    }
}
