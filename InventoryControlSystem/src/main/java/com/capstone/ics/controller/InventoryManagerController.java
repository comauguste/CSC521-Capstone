/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.AuditLog;
import com.capstone.ics.model.Credentials;
import com.capstone.ics.model.InventoryItems;
import com.capstone.ics.model.RefItemCategories;
import com.capstone.ics.model.Site;
import com.capstone.ics.model.SiteItemsQuantity;
import com.capstone.ics.model.SiteItemsQuantityId;
import com.capstone.ics.model.Users;
import com.capstone.ics.service.CategoryService;
import com.capstone.ics.service.CompanyService;
import com.capstone.ics.service.InventoryItemsService;
import com.capstone.ics.service.LogService;
import com.capstone.ics.util.CurrencyUtil;
import com.capstone.ics.util.HibernateUtil;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.converter.NumberStringConverter;
import javax.imageio.ImageIO;

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
    private Label saveProductText, totalQuantityLabel;

    @FXML
    private ComboBox categoryComBox, itemTypeComboBox;

    @FXML
    private TextArea descriptionField;

    @FXML
    private ImageView itemImage;
    private Image image;
    private FileChooser fileChooser;
    private File file;
    private byte[] bFile;

    @FXML
    private TableView<InventoryItems> itemTable;
    @FXML
    private TableColumn<InventoryItems, String> itemsColumn;
    @FXML
    private TableColumn<InventoryItems, String> categoryColumn;

    @FXML
    private TableView<SiteItemsQuantity> warehouseTable;
    @FXML
    private TableColumn<SiteItemsQuantity, String> locationColumn;
    @FXML
    private TableColumn<SiteItemsQuantity, Number> quantityColumn;

    private Credentials loggedUser;
    private InventoryItems mItem;
    private InventoryItemsService itemsService;
    private CompanyService companyService;
    private CategoryService categoryService;
    private LogService logService;
    private ObservableList<String> itemCatgories;
    private final ObservableList<String> itemTypes = FXCollections.observableArrayList("Stockable", "Non-Stockable");
    private ObservableList<InventoryItems> itemsAsObservableList;

    @FXML
    private void initialize() {
        //Get current logged User data
        LoginController user = new LoginController();
        loggedUser = user.getLoggedUser();
        logService = new LogService();
        itemsService = new InventoryItemsService();

        if (loggedUser.isIsAdministrator() == true) {
            itemsAsObservableList = itemsService.getItemsAsObservableList();

        } else {
            
            itemsAsObservableList = itemsService.getAllItemsBasedOnUser(loggedUser.getUsers().getPkUserId());
        }

        //this.mItem = itemsAsObservableList.get(0);
        companyService = new CompanyService();
        categoryService = new CategoryService();

        fillCategoryComboBox();
        manageItemImage();

        //Hide the "save Product Text" warning
        saveProductText.setVisible(false);

        //Populate the item type and category combo box
        itemTypeComboBox.setItems(itemTypes);
        categoryComBox.setItems(itemCatgories);

        //Populate the item table with products
        itemTable.setItems(itemsAsObservableList);
        //Initialize the item table with the two columns
        itemsColumn.setCellValueFactory(CellData -> CellData.getValue().itemNameProperty());
        categoryColumn.setCellValueFactory(CellData -> CellData.getValue().itemCategoryProperty());

        filterItemTableByNameOrCategory(itemsAsObservableList);

        //Listen for selection changes and show the item details when changed
        itemTable.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> showItemDetails(newValue));

        //Show first Item. Do forget to move it to a helper private method
        showItemDetails(null);

    }

    private void filterItemTableByNameOrCategory(final ObservableList<InventoryItems> itemsAsObservableList) {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<InventoryItems> filteredData = new FilteredList<>(itemsAsObservableList, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        sItemNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (item.getItemName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (item.getItemCategory().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<InventoryItems> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(itemTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        itemTable.setItems(sortedData);
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

            itemImage.setImage(null);
            itemNameField.setText(item.getItemName());
            categoryComBox.setValue(item.getItemCategory());
            itemTypeComboBox.setValue(item.getItemType());
            descriptionField.setText(item.getItemDescription());
            costField.setText(item.getItemCost().toString());
            sellingPriceField.setText(item.getItemPrice().toString());
            reorderTresholdField.setText(item.getReorderLevel().toString());
            totalQuantityLabel.setText(item.getQuantityInStock().toString());
            if (item.getItemImage() != null) {
                displaySelectedItemImage(item);
            }

            warehouseTable.setEditable(true);
            warehouseTable.setVisible(true);
            totalQuantityLabel.setVisible(true);
            saveProductText.setVisible(false);

            //Populate the warehouse with defined warehouse
            warehouseTable.setItems(itemsService.getItemLocationAndQuantityAsObservableList(item.getPkItemId()));
            //Initialize the item table with the two columns
            locationColumn.setCellValueFactory(CellData -> CellData.getValue().getSite().siteNameProperty());
            quantityColumn.setCellValueFactory(CellData -> CellData.getValue().quantityIntegerProperty());

            quantityColumn.setCellFactory(TextFieldTableCell.<SiteItemsQuantity, Number>forTableColumn(new NumberStringConverter()));
            quantityColumn.setOnEditCommit((CellEditEvent<SiteItemsQuantity, Number> q) -> {

                //Add new quantity to total quantity
                Integer oldValue, newValue, diff, currentTotal;
                currentTotal = item.getQuantityInStock();

                oldValue = (Integer) q.getOldValue().intValue();
                System.out.println("Old value: " + oldValue);

                updateItemQuantityInRelatedSite(q);

                newValue = (Integer) q.getNewValue().intValue();
                System.out.println("New value: " + newValue);
                diff = newValue - oldValue;
                System.out.println("Diff value: " + newValue);
                currentTotal += diff;
                System.out.println("Total value: " + currentTotal);
                item.setQuantityInStock(currentTotal);
                totalQuantityLabel.setText(item.getQuantityInStock().toString());

            });

        } else {
            clearAllField();
        }
    }

    private void updateItemQuantityInRelatedSite(CellEditEvent<SiteItemsQuantity, Number> q) {
        ((SiteItemsQuantity) q.getTableView().getItems().get(q.getTablePosition().getRow())).setItemQuantity(q.getNewValue().intValue());

        Integer itemId, siteId, itemQuantity;
        itemId = q.getTableView().getItems().get(q.getTablePosition().getRow()).getInventoryItems().getPkItemId();
        siteId = q.getTableView().getItems().get(q.getTablePosition().getRow()).getSite().getPkSiteId();
        itemQuantity = q.getNewValue().intValue();

        HibernateUtil.openCurrentSessionWithTransaction();
        InventoryItems sItem = (InventoryItems) HibernateUtil.getCurrentSession().get(InventoryItems.class, itemId);
        Site sSite = (Site) HibernateUtil.getCurrentSession().get(Site.class, siteId);
        SiteItemsQuantity itemSite = (SiteItemsQuantity) HibernateUtil.getCurrentSession().get(SiteItemsQuantity.class,
                new SiteItemsQuantityId(sSite, sItem));

        itemSite.setItemQuantity(itemQuantity);
        itemSite.setSite(sSite);
        itemSite.setInventoryItems(sItem);

        HibernateUtil.getCurrentSession().update(itemSite);
        HibernateUtil.closeCurrentSessionWithTransaction();
    }

    @FXML
    private void handleNewItem() {
        mItem = null;
        itemImage.setImage(null);
        //warehouseTable.setItems(null);
        warehouseTable.setVisible(false);
        totalQuantityLabel.setVisible(false);
        totalQuantityLabel.setText("0");
        saveProductText.setVisible(true);

        clearAllField();
    }

    //Need to be changed
    @FXML
    private void saveOrUpdateItem() {

        if (mItem != null) {
            InventoryItems item = itemTable.getSelectionModel().getSelectedItem();
            //Update 
            String actionPerformed = "Update Product";
            setItem(item);
            itemsService.update(mItem);
            logIt(mItem, actionPerformed);
        } else {
            //New Item
            mItem = new InventoryItems();
            if (isInputValid()) {
                setItem(mItem);

                String actionPerformed = "New Product";

                Users currentLoggedUser = loggedUser.getUsers();
                mItem.setUsers(currentLoggedUser);

                ObservableList<Site> branches = companyService.getBranchAsObservableList();

                for (Site aSite : branches) {
                    SiteItemsQuantity siteItem = new SiteItemsQuantity();
                    siteItem.setItemQuantity(0);
                    siteItem.setInventoryItems(mItem);
                    siteItem.setSite(aSite);
                    mItem.addSiteItemsQuantities(siteItem);
                }

                itemsService.save(mItem);
                logIt(mItem, actionPerformed);
                itemsService.getItemsData().add(mItem);
                saveProductText.setVisible(false);
                warehouseTable.setVisible(true);
                totalQuantityLabel.setVisible(true);
                warehouseTable.setItems(itemsService.getItemLocationAndQuantityAsObservableList(mItem.getPkItemId()));

            }
        }
    }

    @FXML
    public void deleteSelectedItem() {
        int selectedIndex = itemTable.getSelectionModel().getSelectedIndex();
        InventoryItems selectedItem = itemTable.getSelectionModel().getSelectedItem();
        String actionPerformed = "Delete Product";
        if (selectedIndex >= 0) {
            itemsAsObservableList.remove(selectedIndex);
            itemsService.delete(selectedItem.getPkItemId());
            logIt(mItem, actionPerformed);
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
        mItem.setQuantityInStock(Integer.valueOf(totalQuantityLabel.getText()));
        if (file != null) {
            saveSelectedImage();
            mItem.setItemImage(bFile);
        }

    }

    private void clearAllField() {
        itemNameField.clear();
        categoryComBox.setValue("");
        itemTypeComboBox.setValue("");
        descriptionField.clear();
        costField.clear();
        sellingPriceField.clear();
        reorderTresholdField.clear();
        itemImage.setImage(null);
        //warehouseTable.setItems(null);
        warehouseTable.setVisible(false);
        totalQuantityLabel.setVisible(false);
        totalQuantityLabel.setText("0");
        saveProductText.setVisible(true);

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

    private void manageItemImage() {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files",
                "Icons JPG, PNG, GIF", "*.jpg", "*.gif", "*.png"));
    }

    @FXML
    public void handleBrowse() {
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            System.out.println(file.getAbsolutePath());
            image = new Image(file.toURI().toString(), 200, 200, false, false);
            itemImage.setImage(image);
            itemImage.setFitWidth(200);
            //itemImage.setFitHeight(200);
            itemImage.setCache(true);
        }
    }

    public void saveSelectedImage() {

        bFile = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displaySelectedItemImage(InventoryItems item) {
        try {
            byte[] byteArray = item.getItemImage();
            ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
            BufferedImage read = ImageIO.read(in);
            itemImage.setImage(SwingFXUtils.toFXImage(read, null));
        } catch (IOException ex) {
            Logger.getLogger(InventoryManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void logIt(InventoryItems tempItem, String activity) {
        AuditLog log = new AuditLog(getUserFirstAndLastName(loggedUser), "Inventory module", activity, getItemDetails(tempItem), new Date());

        logService.save(log);
    }

    private String getItemDetails(InventoryItems aItem) {
        StringBuilder sb = new StringBuilder();
        sb.append(" Item Id: ").append(aItem.getPkItemId()).append(" Item name: ")
                .append(aItem.getItemName()).append(" Category : ").append(aItem.getItemCategory());

        return sb.toString();
    }

    private String getUserFirstAndLastName(Credentials aUser) {
        StringBuilder sb = new StringBuilder();
        sb.append(aUser.getUsers().getFirstName()).append(" ").append(aUser.getUsers().getLastName());

        return sb.toString();
    }

    //Need to be changed
    @FXML
    private void detectMouseClickAndSetSelectedItem() {
        mItem = itemTable.getSelectionModel().getSelectedItem();
    }

}
