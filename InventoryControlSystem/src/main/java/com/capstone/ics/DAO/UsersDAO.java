/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.DAO;

import com.capstone.ics.interfaces.UsersDaoInterface;
import com.capstone.ics.model.Users;
import com.capstone.ics.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Auguste C
 */
public class UsersDAO implements UsersDaoInterface<Users, Integer> {

    private Session currentSession;

    private Transaction currentTransaction;

    private List<Users> users = new ArrayList<>();

    public UsersDAO() {
    }

    public Session openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
    
    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();;
        currentSession.close();
    }


    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    @Override
    public void save(Users aUser) {
        getCurrentSession().save(aUser);
    }

    @Override
    public void update(Users aUser) {
        getCurrentSession().update(aUser);
    }

    @Override
    public Users findById(Integer id) {
        Users aUser = (Users) getCurrentSession().get(Users.class, id);
        return aUser;
    }

    @Override
    public void delete(Users aUser) {
        getCurrentSession().delete(aUser);
    }

    @Override
    public List<Users> getAllUsers() {
        Query query = currentSession.createQuery("select u from Users u");
        users = query.list();        
        return users;
    }

    @Override
    public ObservableList<Users> getUsersAsObservableList() {
        ObservableList<Users> observableUsersList;
        Query query = currentSession.createQuery("select u from Users u");
            users = query.list();
            observableUsersList = FXCollections.observableArrayList(users);          
            return observableUsersList;
    }

}
