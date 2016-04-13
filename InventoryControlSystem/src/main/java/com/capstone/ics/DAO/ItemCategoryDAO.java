/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.DAO;

import com.capstone.ics.interfaces.ItemCategoriesInterface;
import com.capstone.ics.model.RefItemCategories;
import com.capstone.ics.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Auguste C
 */
public class ItemCategoryDAO implements ItemCategoriesInterface<RefItemCategories> {

    private List<RefItemCategories> categories = new ArrayList<>();

    public ItemCategoryDAO() {

    }

    @Override
    public void add(RefItemCategories aCategory) {
        HibernateUtil.getCurrentSession().save(aCategory);
    }

    @Override
    public void update(RefItemCategories aCategory) {
        HibernateUtil.getCurrentSession().update(aCategory);
    }

    @Override
    public void remove(String name) {
        RefItemCategories aCategory = (RefItemCategories) HibernateUtil.getCurrentSession()
                .createCriteria(RefItemCategories.class).add(Restrictions.eq("itemCategoryName", name)).uniqueResult();
        HibernateUtil.getCurrentSession().delete(aCategory);
    }

    @Override
    public List<RefItemCategories> getAllCategories() {
        Query query = HibernateUtil.getCurrentSession().createQuery("select c from RefItemCategories c");
        categories = query.list();
//        for(RefItemCategories aCategories :categories)
//        {
//            System.out.println(aCategories.getItemCategoryName());
//        }
        return categories;
    }

    @Override
    public ObservableList<RefItemCategories> getCategoriesAsObservableList() {
        ObservableList<RefItemCategories> observableCategoryList;
        categories = getAllCategories();
        observableCategoryList = FXCollections.observableArrayList(categories);
        return observableCategoryList;
    }

}
