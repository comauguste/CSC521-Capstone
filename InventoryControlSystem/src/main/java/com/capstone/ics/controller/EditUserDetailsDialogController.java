/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.Credentials;
import com.capstone.ics.model.UserAddresses;
import com.capstone.ics.model.Users;
import com.capstone.ics.util.DateUtil;
import com.capstone.ics.util.Validator;
import com.capstone.ics.util.Encryption;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private ChoiceBox notificationChoiceBox;

    @FXML
    private ChoiceBox logChoiceBox;

    @FXML
    private DatePicker birthdayDatePicker;

    private Stage mDialogStage;
    private Users mUsers;
    private UserAddresses mAddresses;
    private Credentials mCredentials;
    private boolean okCliked = false;
    ObservableList<String> yesOrNo = FXCollections.observableArrayList("Yes", "No");
    ObservableList<String> userCredential = FXCollections.observableArrayList("Administrator", "Regular User");

    @FXML
    public void initialize() {
        accessLevelChoiceBox.setItems(userCredential);
        reportChoiceBox.setItems(yesOrNo);
        logChoiceBox.setItems(yesOrNo);
        notificationChoiceBox.setItems(yesOrNo);
    }

    public void setDialogStage(Stage newDialogStage) {
        mDialogStage = newDialogStage;
    }

    public void setUser(Users aUser, UserAddresses address, Credentials credentials) {
        mUsers = aUser;
        mAddresses = address;
        mCredentials = credentials;

        firstNameTextField.setText(mUsers.getFirstName());
        lastNameTextField.setText(mUsers.getLastName());
        emailTextField.setText(mUsers.getEmailAddress());
        phoneTextField.setText(mUsers.getPhoneNumber());
        genderTextField.setText(mUsers.getGender());
        birthdayDatePicker.setValue(DateUtil.fromDate(aUser.getBirthDate()));
        address1TextField.setText(mAddresses.getUserAddressLine1());
        address2TextField.setText(mAddresses.getUserAddressLine2());
        cityTextField.setText(mAddresses.getCity());
        stateTextField.setText(mAddresses.getState());
        postalCodeTextField.setText(mAddresses.getZipCode());
        countryTextField.setText(mAddresses.getCountry());
        usernameTextField.setText(mCredentials.getUsername());

        if (mCredentials.getPassword() == null) {
            passwordTextField.setText(mCredentials.getPassword());
        } else {

            try {
                passwordTextField.setText(Encryption.decrypt(mCredentials.getPassword()));
            } catch (Exception ex) {
                Logger.getLogger(EditUserDetailsDialogController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        accessLevelChoiceBox.setValue(mCredentials.convertAccessLevelToString());
        reportChoiceBox.setValue(mCredentials.convertReportAccessLevelToString());
        logChoiceBox.setValue(mCredentials.convertLogAccessLevelToString());
        notificationChoiceBox.setValue(mCredentials.convertReceiveNotificationToString());

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
            mAddresses.setUserAddressLine1(address1TextField.getText());
            mAddresses.setUserAddressLine2(address2TextField.getText());
            mAddresses.setState(stateTextField.getText());
            mAddresses.setZipCode(postalCodeTextField.getText());
            mAddresses.setCity(cityTextField.getText());
            mAddresses.setCountry(countryTextField.getText());
            mCredentials.setUsername(usernameTextField.getText());
            try {
                mCredentials.setPassword(Encryption.encrypt(passwordTextField.getText()));
            } catch (Exception ex) {
                Logger.getLogger(EditUserDetailsDialogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            mCredentials.returnAccessLevelAsBoolean(getChoice(accessLevelChoiceBox));
            mCredentials.returnReportAccessLevelAsBoolean(getChoice(reportChoiceBox));
            mCredentials.returnLogAccessLevelAsBoolean(getChoice(logChoiceBox));
            mCredentials.returnReceiveNotificationAsBoolean(getChoice(notificationChoiceBox));

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
        Validator check = new Validator();

        if (firstNameTextField.getText() == null || firstNameTextField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameTextField.getText() == null || lastNameTextField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (check.validateEmail(emailTextField.getText()) == false) {
            errorMessage += "No valid email!\n";
        }
        if (check.isPhoneNumberCorrect(phoneTextField.getText()) == false) {
            errorMessage += "No valid phone number!\n";
        }
        if (stateTextField.getText() == null || stateTextField.getText().length() == 0) {
            errorMessage += "No valid state name !\n";
        }
        if (postalCodeTextField.getText() == null || postalCodeTextField.getText().length() == 0) {
            errorMessage += "No postal code!\n";
        }
        if (cityTextField.getText() == null || cityTextField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }
        if (countryTextField.getText() == null || countryTextField.getText().length() == 0) {
            errorMessage += "No valid country!\n";
        }
        
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

    private String getChoice(ChoiceBox<String> choice) {
        return choice.getValue();
    }

}
