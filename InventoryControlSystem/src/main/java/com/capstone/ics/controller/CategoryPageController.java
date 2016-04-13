/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.RefItemCategories;
import com.capstone.ics.service.CategoryService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Auguste C
 */
public class CategoryPageController {

    @FXML
    TreeView<String> categoryTreeView;

    private CategoryService categoryService;
    private List<RefItemCategories> listOfCategories;
    private TreeItem root;

    @FXML
    private void initialize() {

        categoryService = new CategoryService();
         root = new TreeItem("Current Category");
        root.setExpanded(true);
        root.getChildren().addAll(getCategoryList());
        categoryTreeView.setRoot(root);
        //categoryTreeView.setEditable(true);

    }

    private ArrayList<TreeItem> getCategoryList() {
        categoryService = new CategoryService();
        listOfCategories = categoryService.getAllCategories();

        ArrayList<TreeItem> categoryList = new ArrayList<>();

        for (RefItemCategories aCategory : listOfCategories) {
            TreeItem anItem = new TreeItem(aCategory.getItemCategoryName());
            categoryList.add(anItem);
        }

        return categoryList;
    }

    // Helper Method for Adding an Item
    private void addItem(String value) {
        if (value == null || value.trim().equals("")) {
            System.out.println("Item cannot be empty.");
            return;
        }

        TreeItem<String> parent = categoryTreeView.getSelectionModel().getSelectedItem();

        if (parent == null) {
            System.out.println("Select a node to add this item to.");
            return;
        }

        // Check for duplicate
        for (TreeItem<String> child : parent.getChildren()) {
            if (child.getValue().equals(value)) {
                System.out.println("Already existed");
                return;
            }
        }

        TreeItem<String> newItem = new TreeItem<String>(value);
        root.getChildren().add(newItem);

        if (!parent.isExpanded()) {
            parent.setExpanded(true);
        }
    }

    // Helper Method for Removing an Item
    @FXML
    private void removeCategory() {
        TreeItem<String> selectedCategory = categoryTreeView.getSelectionModel().getSelectedItem();

        if (selectedCategory == null) {
            System.out.println("Select a node to remove.");
            return;
        }

        TreeItem<String> parent = selectedCategory.getParent();
        if (parent == null) {
            System.out.println("Cannot remove the root node.");
        } else {
            parent.getChildren().remove(selectedCategory);
            categoryService.remove(selectedCategory.getValue());
        }
    }

     /**
     * Called when the user clicks the new button. Opens a dialog to add a new category
     */
    @FXML
    private void handleNewCategory() {
        RefItemCategories tempCategory = new RefItemCategories();        

        boolean okClicked = showNewCategoryDialog(tempCategory);
        if (okClicked) {
            addItem(tempCategory.getItemCategoryName());
            //TO complete later 
            categoryService.add(tempCategory);
        }
    }
    
    
     public boolean showNewCategoryDialog(RefItemCategories aCategory) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/AddOrEditCategory.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage. 
            Scene scene = new Scene(page);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit User Information");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AddOrEditCategoryController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCategory(aCategory);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
