/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.DAO;

import com.capstone.ics.model.Users;
import com.capstone.ics.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Auguste C
 */
public class UsersDAO {

    SessionFactory factory;
    Session session;
    org.hibernate.Transaction tx;
    private List<Users> users = new ArrayList<>();
    private ObservableList<Users> userData;

    public UsersDAO() {

    }

    public void saveOrUpdateUser(Users user) {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            session.saveOrUpdate(user);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            factory.close();
        }

    }

    public List<Users> getAllUsers() {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("select u from Users u");
            users = query.list();            
            
            for(Users aUser: users)
            {
                System.out.println(aUser.getFirstName());
            }
            
            tx.commit();
            return users;
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            factory.close();
        }

        return users;
    }

    public List<Users> getUserById(Integer id) {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            users = session.createCriteria(Users.class).add(Restrictions.idEq(id)).list();
            tx.commit();
            return users;
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            factory.close();
        }

        return users;
    }

    public void deleteUserById(Integer id) {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            Users aUser = (Users) session.createCriteria(Users.class).add(Restrictions.idEq(id)).uniqueResult();
            if (aUser != null) {
                session.delete(aUser);
            }
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            factory.close();
        }
    }

    public ObservableList<Users> getUsersData() {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("select u from Users u");
            users = query.list();
            //System.out.println(users);

            userData = FXCollections.observableArrayList(users);

            tx.commit();
            return userData;
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            factory.close();
        }

        return userData;
    }

    public static void main(String[] args) {
        
        UsersDAO test = new UsersDAO();
        test.getAllUsers();
        
    }

}
