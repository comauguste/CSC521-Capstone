/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.AuditLog;
import com.capstone.ics.model.Credentials;
import com.capstone.ics.service.CredentialsService;
import com.capstone.ics.service.LogService;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
    private LogService logService;
    private static Credentials loggedUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CredentialsService aService = new CredentialsService();
        users = aService.getApproveUsers();
        loggedUser = new Credentials();
        logService = new LogService();

    }

    @FXML
    private void loginAction(ActionEvent event) {
        
        
        for (Credentials aUser : users) {
            if (usernameField.getText().equals(aUser.getUsername()) && passwordField.getText().equals(aUser.getPassword())) {
                
                loggedUser = aUser;
                newStage.nextStageUsingAnActionEvent(event, "/fxml/MainControlPanel.fxml");
                logIt(loggedUser, "Logged In");

            } else {
                errorLabel.setText(" Username or Password is invalid");
            }
        }
    }
    
    private String getUserDetails(Credentials aUser)
    {
         StringBuilder sb = new StringBuilder();
        sb.append(" User Id: ").append(aUser.getUsers().getPkUserId()).append(" User name: ")
                .append(aUser.getUsername());
        
        return sb.toString();
    }
    
    private void logIt(Credentials tempUser, String activity) {
        AuditLog log = new AuditLog("admin", "Credential module", activity, getUserDetails(tempUser), new Date());
        logService.save(log);
    }

    public Credentials getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Credentials loggedUser) {
        this.loggedUser = loggedUser;
    }
    
    public String getCurrentUserFirstAndLastName()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(loggedUser.getUsers().getFirstName()).append(" ").append(loggedUser.getUsers().getLastName());
        return sb.toString();
    }

}
