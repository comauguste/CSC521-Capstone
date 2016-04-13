/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.InventoryItems;
import com.capstone.ics.model.RefItemCategories;
import com.capstone.ics.model.Site;
import com.capstone.ics.service.CategoryService;
import com.capstone.ics.service.CompanyService;
import com.capstone.ics.service.InventoryItemsService;
import com.capstone.ics.util.CurrencyUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class InventoryManagerController {

    private final StageManager newStage = new StageManager();
    private final String categoryModuleStage = "/fxml/CategoryPage.fxml";

    @FXML
    private TextField itemNameField, costField, sellingPriceField, sItemNameField, reorderTresholdField;

    @FXML
    private ComboBox categoryComBox, itemTypeComboBox, sCategoryComboBox;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TableView<InventoryItems> itemTable;
    @FXML
    private TableColumn<InventoryItems, String> itemsColumn;
    @FXML
    private TableColumn<InventoryItems, String> categoryColumn;

    @FXML
    private TableView<Site> locationTable;
    @FXML
    private TableColumn<Site, String> locationColumn;
    @FXML
    private TableColumn<Site, String> quantityColumn;

    private InventoryItems mItem;
    private InventoryItemsService itemsService;
    private CompanyService companyService;
    private CategoryService categoryService;
    private ObservableList<String> itemCatgories;
    private final ObservableList<String> itemTypes = FXCollections.observableArrayList("Stockable", "Non-Stockable");

    @FXML
    private void initialize() {
        itemsService = new InventoryItemsService();
        this.mItem = itemsService.getItemsAsObservableList().get(0);        
        companyService = new CompanyService();
        categoryService = new CategoryService();

        fillCategoryComboBox();

        itemTypeComboBox.setItems(itemTypes);
        categoryComBox.setItems(itemCatgories);

        //Initialize the item table with the two columns
        itemsColumn.setCellValueFactory(CellData -> CellData.getValue().itemNameProperty());
        categoryColumn.setCellValueFactory(CellData -> CellData.getValue().itemCategoryProperty());

        itemTable.setItems(itemsService.getItemsAsObservableList());
        locationTable.setItems(companyService.getSitesAsObservableList());

        
        //Clear Details
        showItemDetails(null);

        //Listen for selection changes and show the item details when changed
        itemTable.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> showItemDetails(newValue));

        //Show first Item. Do forget to move it to a helper private method
        showItemDetails(mItem);
        //setItem(firstItem);
    }

    //Still to  be implemented
    private void showFirstItem(ObservableList<InventoryItems> collectionOfitems) {

    }

    private void fillCategoryComboBox() {
        itemCatgories = FXCollections.observableArrayList();
        List<RefItemCategories> categories = categoryService.getAllCategories();
        for (RefItemCategories aCatefory : categories) {
            itemCatgories.add(aCatefory.getItemCategoryName());
        }
    }

    private void showItemDetails(InventoryItems item) {
        if (item != null) {

            itemNameField.setText(item.getItemName());
            categoryComBox.setValue(item.getItemCategory());
            itemTypeComboBox.setValue(item.getItemType());
            descriptionField.setText(item.getOtherItemDetails());
            costField.setText(item.getItemCost().toString());
            sellingPriceField.setText(item.getItemPrice().toString());
            reorderTresholdField.setText(item.getReorderLevel().toString());

            //System.out.println(item.getItemType());
        } else {
            clearAllField();
        }
    }

    @FXML
    private void handleNewItem() {
        mItem = null;
        clearAllField();
    }

    //Need to be changed
    @FXML
    private void saveOrUpdateItem() {

        if (mItem != null) {
            InventoryItems item = itemTable.getSelectionModel().getSelectedItem();
            //Update 
            System.out.println("Update Item");
            setItem(item);
            itemsService.update(mItem);
        } else {
            //New Item
            InventoryItems newItem = new InventoryItems();
            if (isInputValid()) {
                setItem(newItem);
                System.out.println("New Item");
                itemsService.getItemsData().add(newItem);
               itemsService.save(mItem); 

            }
        }
    }

    @FXML
    public void deleteSelectedItem() {
        int selectedIndex = itemTable.getSelectionModel().getSelectedIndex();
        //InventoryItems slectedItem = itemsTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            itemTable.getItems().remove(selectedIndex);
            itemsService.delete(mItem);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select an Item in the table");

            alert.showAndWait();
        }

    }

    private void setItem(InventoryItems item) throws NumberFormatException {
        this.mItem = item;
        mItem.setItemName(itemNameField.getText());
        mItem.setItemCategory(categoryComBox.getValue().toString());
        mItem.setItemType(itemTypeComboBox.getValue().toString());
        mItem.setItemDescription(descriptionField.getText());
        mItem.setItemCost(CurrencyUtil.convertStringToBigDecimal(costField.getText()));
        mItem.setItemPrice(CurrencyUtil.convertStringToBigDecimal(sellingPriceField.getText()));
        mItem.setReorderLevel(Integer.valueOf(reorderTresholdField.getText()));
    }

    private void clearAllField() {
        itemNameField.clear();
        categoryComBox.setValue("");
        itemTypeComboBox.setValue("");
        descriptionField.clear();
        costField.clear();
        sellingPriceField.clear();
        reorderTresholdField.clear();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (itemNameField.getText() == null || itemNameField.getText().length() == 0) {
            errorMessage += "No item name!\n";
        }
        // Do not forget to complete this part
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void goToCategoryManagerWindow(Event onMouseClicked) {
        newStage.nextStage(categoryModuleStage, "Site Module Manager");
        onMouseClicked.consume();
    }

}
