package com.capstone.ics.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.capstone.ics.model.Site;
import com.capstone.ics.util.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class BranchPageController {

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

    private Stage mDialogStage;
    private Site branch;
    private boolean okCliked = false;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage newDialogStage) {
        mDialogStage = newDialogStage;
    }

    private void showBranchDetails(Site theCompany) {

        //Note to myself: Need to revise this part -> Assignment of reference to local variable????
        branch = theCompany;

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

    public void setBranch(Site aBranch) {
        this.branch = aBranch;

        companyNameField.setText(branch.getSiteName());
        address1Field.setText(branch.getSiteAddressLine1());
        address2Field.setText(branch.getSiteAddressLine2());
        cityField.setText(branch.getSiteCity());
        stateField.setText(branch.getSiteState());
        zipcodeField.setText(branch.getSiteZipCode());
        phoneField.setText(branch.getSiteOfficePhone());
        faxField.setText(branch.getSiteFax());
        emailField.setText(branch.getSiteEmail());
        websiteField.setText(branch.getSiteWebsite());

    }

    @FXML
    private void handleSaveBranch() {

        if (isInputValid()) {
            branch.setSiteName(companyNameField.getText());
            branch.setSiteAddressLine1(address1Field.getText());
            branch.setSiteAddressLine2(address2Field.getText());
            branch.setSiteCity(cityField.getText());
            branch.setSiteState(stateField.getText());
            branch.setSiteZipCode(zipcodeField.getText());
            branch.setSiteOfficePhone(phoneField.getText());
            branch.setSiteFax(faxField.getText());
            branch.setSiteEmail(emailField.getText());
            branch.setSiteWebsite(websiteField.getText());
            branch.setMainOfficeID(1);

            okCliked = true;
            mDialogStage.close();

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

}
