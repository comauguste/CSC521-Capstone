/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.DAO.CredentialsDAO;
import com.capstone.ics.model.Credentials;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    private Label errorLabel;

    private List<Credentials> users = new ArrayList<>();
    private final StageManager newStage = new StageManager();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CredentialsDAO usersList = new CredentialsDAO();
        users = usersList.retrieveAllowedUsers();

    }

    @FXML
    private void loginAction(ActionEvent event) {
        
        
        for (Credentials aUser : users) {
            if (usernameField.getText().equals(aUser.getUsername()) && passwordField.getText().equals(aUser.getPassword())) {

                newStage.nextStageUsingAnActionEvent(event, "/fxml/MainControlPanel.fxml");

            } else {
                errorLabel.setText(" Username or Password is invalid");
            }
        }
    }

}
