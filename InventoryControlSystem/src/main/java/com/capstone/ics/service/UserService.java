/*
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.service;

import com.capstone.ics.DAO.UsersDAO;
import com.capstone.ics.model.Users;
import com.capstone.ics.util.HibernateUtil;
import java.util.List;
import javafx.collections.ObservableList;


/**
 *
 * @author Auguste C
 */
public class UserService {

    private static UsersDAO usersDao;
    private ObservableList<Users> observableUsersLists;

    public UserService() {
        usersDao = new UsersDAO();
    }

    public static UsersDAO getUsersDao() {
        return usersDao;
    }

    public void save(Users aUser) {
        HibernateUtil.openCurrentSessionWithTransaction();
        usersDao.save(aUser);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

    public void update(Users aUser) {
        HibernateUtil.openCurrentSessionWithTransaction();
        usersDao.update(aUser);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

    public Users findById(Integer id) {
        HibernateUtil.openCurrentSession();
        Users aUser = usersDao.findById(id);
        HibernateUtil.closeCurrentSession();

        return aUser;
    }

    //Do not forget to change the ID data type from String TO int or long( whatever appropriate   
    public void delete(Integer id) {
        HibernateUtil.openCurrentSessionWithTransaction();
        Users aUser = usersDao.findById(id);
        usersDao.delete(aUser);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

    public List<Users> getAllUsers() {
        HibernateUtil.openCurrentSession();
        List<Users> users = usersDao.getAllUsers();
        HibernateUtil.closeCurrentSession();

        return users;

    }

    public ObservableList<Users> getUsersAsObservableList() {

        HibernateUtil.openCurrentSession();
        observableUsersLists = usersDao.getUsersAsObservableList();
        HibernateUtil.closeCurrentSession();

        return observableUsersLists;
    }
    
    public ObservableList<Users> getUsersData() {
        return observableUsersLists;
    }
    
    
    public static void main(String[] args) {
        UserService test = new UserService();
        Users aUser = test.findById(1);
        System.out.println(aUser.getFirstName());
    }
}
