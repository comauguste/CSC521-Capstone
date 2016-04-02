/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.service;

import com.capstone.ics.DAO.UsersDAO;
import com.capstone.ics.model.Users;
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
        usersDao.openCurrentSessionWithTransaction();
        usersDao.save(aUser);
        usersDao.closeCurrentSessionWithTransaction();
    }

    public void update(Users aUser) {
        usersDao.openCurrentSessionWithTransaction();
        usersDao.update(aUser);
        usersDao.closeCurrentSessionWithTransaction();
    }

    public Users findById(Integer id) {
        usersDao.openCurrentSession();
        Users aUser = usersDao.findById(id);
        usersDao.closeCurrentSession();

        return aUser;
    }

    //Do not forget to change the ID data type from String TO int or long( whatever appropriate   
    public void delete(Integer id) {
        usersDao.openCurrentSessionWithTransaction();
        Users aUser = usersDao.findById(id);
        usersDao.delete(aUser);
        usersDao.closeCurrentSessionWithTransaction();
    }

    public List<Users> getAllUsers() {
        usersDao.openCurrentSession();
        List<Users> users = usersDao.getAllUsers();
        usersDao.closeCurrentSession();

        return users;

    }

    public ObservableList<Users> getUsersAsObservableList() {

        usersDao.openCurrentSession();
        observableUsersLists = usersDao.getUsersAsObservableList();
        usersDao.closeCurrentSession();

        return observableUsersLists;
    }
    
    public ObservableList<Users> getUsersData() {
        return observableUsersLists;
    }
}
