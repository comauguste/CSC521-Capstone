/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.Credentials;
import com.capstone.ics.model.Users;
import com.capstone.ics.service.UserService;
import com.capstone.ics.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
public class RegularUserProfileController {

    @FXML
    private TextField firstNameTextField, lastNameTextField, emailTextField, phoneTextField, genderTextField, address1TextField, address2TextField, stateTextField, postalCodeTextField, countryTextField,
            usernameTextField, cityTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private DatePicker birthdayDatePicker;

    private Stage mDialogStage;
    private Users mUsers;
    private Credentials loggedUser;
    private UserService userService;
    ObservableList<String> yesOrNo = FXCollections.observableArrayList("Yes", "No");
    ObservableList<String> userCredential = FXCollections.observableArrayList("Administrator", "Regular User");

    @FXML
    public void initialize() {
        LoginController user = new LoginController();
        loggedUser = user.getLoggedUser();
        userService = new UserService();
        mUsers = userService.findById(loggedUser.getUsers().getPkUserId());
        setUser();
    }

    public void setUser() {

        firstNameTextField.setText(mUsers.getFirstName());
        lastNameTextField.setText(mUsers.getLastName());
        emailTextField.setText(mUsers.getEmailAddress());
        phoneTextField.setText(mUsers.getPhoneNumber());
        genderTextField.setText(mUsers.getGender());
        birthdayDatePicker.setValue(DateUtil.fromDate(mUsers.getBirthDate()));
        address1TextField.setText(mUsers.getAddress().getUserAddressLine1());
        address2TextField.setText(mUsers.getAddress().getUserAddressLine2());
        cityTextField.setText(mUsers.getAddress().getCity());
        stateTextField.setText(mUsers.getAddress().getState());
        postalCodeTextField.setText(mUsers.getAddress().getZipCode());
        countryTextField.setText(mUsers.getAddress().getCountry());
        usernameTextField.setText(mUsers.getUserCredentials().getUsername());
        passwordTextField.setText(mUsers.getUserCredentials().getPassword());

    }

    @FXML
    private void handleOk(Event onMouseClicked) {
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

            userService.update(mUsers);

            ((Node) (onMouseClicked.getSource())).getScene().getWindow().hide();
        }
    }

    @FXML
    private void handleCancel(Event onMouseClicked) {
        ((Node) (onMouseClicked.getSource())).getScene().getWindow().hide();
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

    private String getChoice(ChoiceBox<String> choice) {
        return choice.getValue();
    }

}
