/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.Users;
import com.capstone.ics.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private TextField address1TextField;

    @FXML
    private TextField address2TextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField postalCodeTextField;

    @FXML
    private TextField stateTextField;

    @FXML
    private TextField birthdayTextField;

    private Stage mDialogStage;
    private Users mUsers;
    private boolean okCliked = false;

    @FXML
    public void initialize() {
        // TODO
    }

    public void setDialogStage(Stage newDialogStage) {
        mDialogStage = newDialogStage;
    }

    public void setUser(Users aUser) {
        mUsers = aUser;
        firstNameTextField.setText(aUser.getFirstName());
        lastNameTextField.setText(aUser.getLastName());
        birthdayTextField.setText(DateUtil.format(aUser.getBirthDate()));
        birthdayTextField.setPromptText("dd.mm.yyyy");

    }

    public boolean isOkClicked() {
        return okCliked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            mUsers.setFirstName(firstNameTextField.getText());
            mUsers.setLastName(lastNameTextField.getText());
            //mUsers.setBirthDate(DateUtil.parse(birthdayTextField.getText()));
            
            okCliked = true;
            mDialogStage.close();
        }
    }
    
    @FXML
    private void handleCancel()
    {
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
        if (birthdayTextField.getText() == null || birthdayTextField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else if (!DateUtil.validDate(birthdayTextField.getText())) {
            errorMessage += "No valid birthday. User the format dd.mm.yyyy\n";
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

}
