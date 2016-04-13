/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.service;

import com.capstone.ics.DAO.ItemCategoryDAO;
import com.capstone.ics.model.RefItemCategories;
import com.capstone.ics.util.HibernateUtil;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Auguste C
 */
public class CategoryService {

    private static ItemCategoryDAO itemCategoryDAO;
    private ObservableList<RefItemCategories> listOfCategories;

    public CategoryService() {
        itemCategoryDAO = new ItemCategoryDAO();
    }

    public static ItemCategoryDAO getItemCategoryDAO() {
        return itemCategoryDAO;
    }

    public void add(RefItemCategories aCategory) {
        HibernateUtil.openCurrentSessionWithTransaction();
        itemCategoryDAO.add(aCategory);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

    public void update(RefItemCategories aCategory) {
        HibernateUtil.openCurrentSessionWithTransaction();
        itemCategoryDAO.update(aCategory);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

    public void remove(String name) {
        HibernateUtil.openCurrentSessionWithTransaction();
        itemCategoryDAO.remove(name);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

    public List<RefItemCategories> getAllCategories() {

        HibernateUtil.openCurrentSession();
        List<RefItemCategories> categoryList = itemCategoryDAO.getAllCategories();
        HibernateUtil.closeCurrentSession();

        return categoryList;
    }

    public ObservableList<RefItemCategories> getCategoriesAsObservableList() {
        HibernateUtil.openCurrentSession();
        listOfCategories = itemCategoryDAO.getCategoriesAsObservableList();
        HibernateUtil.closeCurrentSession();

        return listOfCategories;
    }

}
