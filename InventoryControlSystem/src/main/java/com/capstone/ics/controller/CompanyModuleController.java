/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.Site;
import com.capstone.ics.service.CompanyService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class CompanyModuleController {

    @FXML
    private TreeTableView<Site> companyInformationTable;

    @FXML
    private TreeTableColumn subLocationColumn;

    @FXML
    private TreeTableColumn cityColumn;

    @FXML
    private TreeTableColumn stateColumn;

    @FXML
    private TreeTableColumn phoneNumberColumn;

    @FXML
    private TextField companyNameField;

    @FXML
    private TextField address1Field;

    @FXML
    private TextField address2Field;

    @FXML
    private TextField cityField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField zipcodeField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField faxField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField websiteField;

    private CompanyService companyService;
    private Site company;
    private boolean okCliked = false;
    
    

    @FXML
    private void initialize() {
        //Setting the tree table
        TreeItem<String> root = new TreeItem<>("Main Office");
        
        company = new Site();
        companyService = new CompanyService();
        Site aSite = companyService.getCompanyInformation();
        showCompanyDetails(aSite);
    }

    private void handleTreeTableView()
    {
        
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
    private void handleCancel() {

    }

    private boolean isInputValid() {
        String errorMessage = "";

//        if (firstNameTextField.getText() == null || firstNameTextField.getText().length() == 0) {
//            errorMessage += "No valid first name!\n";
//        }
//        if (lastNameTextField.getText() == null || lastNameTextField.getText().length() == 0) {
//            errorMessage += "No valid last name!\n";
//        }
//        if (birthdayTextField.getText() == null || birthdayTextField.getText().length() == 0) {
//            errorMessage += "No valid birthday!\n";
//        } else if (!DateUtil.validDate(birthdayTextField.getText())) {
//            errorMessage += "No valid birthday. User the format dd.mm.yyyy\n";
//        }
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

}
