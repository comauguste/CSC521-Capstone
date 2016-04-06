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
/**
 *
 * @author Auguste C
 */
public class UsersDAO implements UsersDaoInterface<Users, Integer> {

    private List<Users> users = new ArrayList<>();

    public UsersDAO() {
    }

    @Override
    public void save(Users aUser) {
        HibernateUtil.getCurrentSession().save(aUser);
    }

    @Override
    public void update(Users aUser) {
        HibernateUtil.getCurrentSession().update(aUser);
    }

    @Override
    public Users findById(Integer id) {
        Users aUser = (Users) HibernateUtil.getCurrentSession().get(Users.class, id);
        return aUser;
    }

    @Override
    public void delete(Users aUser) {
        HibernateUtil.getCurrentSession().delete(aUser);
    }

    @Override
    public List<Users> getAllUsers() {
        Query query = HibernateUtil.getCurrentSession().createQuery("select u from Users u");
        users = query.list();
        return users;
    }

    @Override
    public ObservableList<Users> getUsersAsObservableList() {
        ObservableList<Users> observableUsersList;
        Query query = HibernateUtil.getCurrentSession().createQuery("select u from Users u");
        users = query.list();
        observableUsersList = FXCollections.observableArrayList(users);
        return observableUsersList;
    }

}
