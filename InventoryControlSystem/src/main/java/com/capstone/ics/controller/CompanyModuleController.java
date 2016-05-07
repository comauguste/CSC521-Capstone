/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.AuditLog;
import com.capstone.ics.model.Credentials;
import com.capstone.ics.model.Site;
import com.capstone.ics.service.CompanyService;
import com.capstone.ics.service.LogService;
import com.capstone.ics.util.Validator;
import java.io.IOException;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class CompanyModuleController {

    @FXML
    private TableView<Site> warehouseTable;

    @FXML
    private TableColumn<Site, String> warehouseNameColumn, cityColumn, stateColumn, phoneNumberColumn;

    @FXML
    private TextField companyNameField, address1Field, address2Field, cityField, stateField, zipcodeField,
            phoneField, faxField, emailField, websiteField;

    @FXML
    private Button saveButton, cancelButton, newBranch, updateSelectedBranch, deleteSelectedBranch;

    private Credentials loggedUser;
    private CompanyService companyService;
    private LogService logService;
    private Site company;
    private boolean okCliked = false;

    @FXML
    private void initialize() {
        company = new Site();
        companyService = new CompanyService();
        logService = new LogService();
        Site aSite = companyService.getCompanyInformation();
        showCompanyDetails(aSite);

        warehouseTable.setItems(companyService.getBranchAsObservableList());

        //Initialize the sub-location table with the two columns
        warehouseNameColumn.setCellValueFactory(CellData -> CellData.getValue().siteNameProperty());
        cityColumn.setCellValueFactory(CellData -> CellData.getValue().siteCityProperty());
        stateColumn.setCellValueFactory(CellData -> CellData.getValue().siteStateProperty());
        phoneNumberColumn.setCellValueFactory(CellData -> CellData.getValue().siteOfficePhoneProperty());

        //Disable all textfields if the logged user has regular access
        LoginController user = new LoginController();
        loggedUser = user.getLoggedUser();

        if (loggedUser.isIsAdministrator() == false) {
            disableAllTexfieldsAndButtons();
        }
    }

    private void disableAllTexfieldsAndButtons() {
        companyNameField.setEditable(false);
        address1Field.setEditable(false);
        address2Field.setEditable(false);
        cityField.setEditable(false);
        stateField.setEditable(false);
        zipcodeField.setEditable(false);
        phoneField.setEditable(false);
        faxField.setEditable(false);
        emailField.setEditable(false);
        websiteField.setEditable(false);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
        newBranch.setVisible(false);
        updateSelectedBranch.setVisible(false);
        deleteSelectedBranch.setVisible(false);
    }

    private void handleTreeTableView() {

    }

    private void showCompanyDetails(Site theCompany) {

        //Note to myself: Need to revise this part -> Assignment of reference to local variable????
        company = theCompany;

        if (theCompany != null) {
            companyNameField.setText(theCompany.getSiteName());
            address1Field.setText(theCompany.getSiteAddressLine1());
            address2Field.setText(theCompany.getSiteAddressLine2());
            cityField.setText(theCompany.getSiteCity());
            stateField.setText(theCompany.getSiteState());
            zipcodeField.setText(theCompany.getSiteZipCode());
            phoneField.setText(theCompany.getSiteOfficePhone());
            faxField.setText(theCompany.getSiteFax());
            emailField.setText(theCompany.getSiteEmail());
            websiteField.setText(theCompany.getSiteWebsite());

        } else {

            companyNameField.setText("");
            address1Field.setText("");
            address2Field.setText("");
            cityField.setText("");
            stateField.setText("");
            zipcodeField.setText("");
            phoneField.setText("");
            faxField.setText("");
            emailField.setText("");
            websiteField.setText("");

        }
    }

    @FXML
    private void handleSave() {

        if (isInputValid()) {
            company.setSiteName(companyNameField.getText());
            company.setSiteAddressLine1(address1Field.getText());
            company.setSiteAddressLine2(address2Field.getText());
            company.setSiteCity(cityField.getText());
            company.setSiteState(stateField.getText());
            company.setSiteZipCode(zipcodeField.getText());
            company.setSiteOfficePhone(phoneField.getText());
            company.setSiteFax(faxField.getText());
            company.setSiteEmail(emailField.getText());
            company.setSiteWebsite(websiteField.getText());

            companyService.updateCompanyInformation(company);
            okCliked = true;

        }

    }

    @FXML
    private void handleNewBranch() {
        Site tempSite = new Site();
        String actionPerformed = "New Branch";

        boolean okClicked = showBranchEditDialog(tempSite);
        if (okClicked) {
            companyService.getSiteData().add(tempSite);
            companyService.saveNewBranch(tempSite);
            logIt(tempSite, actionPerformed);
        }
    }

    @FXML
    private void handleUpdateSelectedBranch() {
        Site selectedSite = warehouseTable.getSelectionModel().getSelectedItem();
        String actionPerformed = "Update Branch";
        if (selectedSite != null) {
            boolean okClicked = showBranchEditDialog(selectedSite);
            if (okClicked) {
                showCompanyDetails(selectedSite);
                companyService.updateCompanyInformation(selectedSite);
                logIt(selectedSite, actionPerformed);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteSelectedBranch() {
        int selectedIndex = warehouseTable.getSelectionModel().getSelectedIndex();
        Site selectedSite = warehouseTable.getSelectionModel().getSelectedItem();
        String actionPerformed = "Delete Branch";
        if (selectedIndex >= 0) {
            warehouseTable.getItems().remove(selectedIndex);
            companyService.delete(selectedSite.getPkSiteId());
            logIt(selectedSite, actionPerformed);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Site Selected");
            alert.setContentText("Please select a site in the table");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancel() {

    }

    private boolean isInputValid() {
        String errorMessage = "";
        Validator check = new Validator();

if (companyNameField.getText() == null || companyNameField.getText().length() == 0) {
            errorMessage += "No valid company name!\n";
        }
        if (address1Field.getText() == null || address1Field.getText().length() == 0) {
            errorMessage += "No valid address!\n";
        }
        if (check.validateEmail(emailField.getText()) == false) {
            errorMessage += "No valid email!\n";
        }
        if (check.isPhoneNumberCorrect(phoneField.getText()) == false) {
            errorMessage += "No valid phone number!\n";
        }
        if (check.isPhoneNumberCorrect(faxField.getText()) == false) {
            errorMessage += "No valid fax number!\n";
        }
        if (stateField.getText() == null || stateField.getText().length() == 0) {
            errorMessage += "No valid state name !\n";
        }
        if (zipcodeField.getText() == null || zipcodeField.getText().length() == 0) {
            errorMessage += "No postal code!\n";
        }
        
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

    public boolean isOkClicked() {
        return okCliked;
    }

    public boolean showBranchEditDialog(Site aSite) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/BranchPage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage. 
            Scene scene = new Scene(page);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Branch Information");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            // Set the user into the controller.
            BranchPageController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBranch(aSite);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void logIt(Site tempSite, String activity) {
        AuditLog log = new AuditLog("admin", "Site module", activity, getCompanyDetails(tempSite), new Date());

        logService.save(log);
    }

    private String getCompanyDetails(Site aSite) {
        StringBuilder sb = new StringBuilder();
        sb.append(" Site Id: ").append(aSite.getPkSiteId()).append(" Site name: ")
                .append(aSite.getSiteName()).append(" City: ").append(aSite.getSiteCity());

        return sb.toString();
    }

}
