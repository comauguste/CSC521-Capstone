/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.Users;
import com.capstone.ics.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class EditUserDetailsDialogController {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField genderTextField;

    @FXML
    private TextField address1TextField;

    @FXML
    private TextField address2TextField;

    @FXML
    private TextField stateTextField;

    @FXML
    private TextField postalCodeTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField usernameTextField;
    
    @FXML
    private TextField cityTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ChoiceBox accessLevelChoiceBox;

    @FXML
    private ChoiceBox reportChoiceBox;

    @FXML
    private ChoiceBox logChoiceBox;

    @FXML
    private DatePicker birthdayDatePicker;

    private Stage mDialogStage;
    private Users mUsers;
    private boolean okCliked = false;
    ObservableList<String> yesOrNo = FXCollections.observableArrayList("Yes", "No");
    ObservableList<String> userCredential = FXCollections.observableArrayList("Administrator", "Regular User");

    @FXML
    public void initialize() {
        accessLevelChoiceBox.setItems(userCredential);
        reportChoiceBox.setItems(yesOrNo);
        logChoiceBox.setItems(yesOrNo);        
    }

    public void setDialogStage(Stage newDialogStage) {
        mDialogStage = newDialogStage;
    }

    public void setUser(Users aUser) {
        mUsers = aUser;
        firstNameTextField.setText(aUser.getFirstName());
        lastNameTextField.setText(aUser.getLastName());
        emailTextField.setText(aUser.getEmailAddress());
        phoneTextField.setText(aUser.getPhoneNumber());
        genderTextField.setText(aUser.getGender());
        address1TextField.setText(aUser.getAddress().getUserAddressLine1());
        address2TextField.setText(aUser.getAddress().getUserAddressLine2());
        cityTextField.setText(aUser.getAddress().getCity());
        stateTextField.setText(aUser.getAddress().getState());
        postalCodeTextField.setText(aUser.getAddress().getZipCode());
        countryTextField.setText(aUser.getAddress().getCountry());
        usernameTextField.setText(aUser.getUserCredentials().getUsername());
        passwordTextField.setText(aUser.getUserCredentials().getPassword());        
        birthdayDatePicker.setValue(DateUtil.fromDate(aUser.getBirthDate())); 
        accessLevelChoiceBox.setValue(aUser.getUserCredentials().convertAccessLevelToString());
        reportChoiceBox.setValue(aUser.getUserCredentials().convertReportAccessLevelToString());
        logChoiceBox.setValue(aUser.getUserCredentials().convertLogAccessLevelToString());
        

    }

    public boolean isOkClicked() {
        return okCliked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            mUsers.setFirstName(firstNameTextField.getText());
            mUsers.setLastName(lastNameTextField.getText());
            mUsers.setEmailAddress(emailTextField.getText());
            mUsers.setPhoneNumber(phoneTextField.getText());
            mUsers.setGender(genderTextField.getText());
            mUsers.setBirthDate(DateUtil.convertToDate(birthdayDatePicker.getValue()));
            mUsers.getAddress().setUserAddressLine1(address1TextField.getText());
            mUsers.getAddress().setUserAddressLine2(address2TextField.getText());
            mUsers.getAddress().setState(stateTextField.getText());
            mUsers.getAddress().setZipCode(postalCodeTextField.getText());
            mUsers.getAddress().setCity(cityTextField.getText());
            mUsers.getAddress().setCountry(countryTextField.getText());
            mUsers.getUserCredentials().setUsername(usernameTextField.getText());
            mUsers.getUserCredentials().setPassword(passwordTextField.getText());
            mUsers.getUserCredentials().returnAccessLevelAsBoolean(getChoice(accessLevelChoiceBox));
            mUsers.getUserCredentials().returnReportAccessLevelAsBoolean(getChoice(reportChoiceBox));
            mUsers.getUserCredentials().returnLogAccessLevelAsBoolean(getChoice(logChoiceBox));
            
            okCliked = true;
            mDialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        mDialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameTextField.getText() == null || firstNameTextField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameTextField.getText() == null || lastNameTextField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
//        if (birthdayTextField.getText() == null || birthdayTextField.getText().length() == 0) {
//            errorMessage += "No valid birthday!\n";
//        } else if (!DateUtil.validDate(birthdayTextField.getText())) {
//            errorMessage += "No valid birthday. User the format dd.mm.yyyy\n";
//        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mDialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
    
    private String getChoice(ChoiceBox<String> choice)
    {
        return choice.getValue();
    }

}
