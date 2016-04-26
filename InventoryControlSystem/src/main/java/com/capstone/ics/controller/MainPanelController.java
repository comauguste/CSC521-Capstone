/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.Credentials;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class MainPanelController {

    private final String userModuleStage = "/fxml/UsersManager.fxml";
    private final String regularUserModuleStage = "/fxml/RegularUserProfilePage.fxml";
    private final String siteModuleStage = "/fxml/CompanyModule.fxml";
    private final String inventoryModuleStage = "/fxml/InventoryManager.fxml";
    private final String logModuleStage = "/fxml/AuditLog.fxml";
    private final String loginPage = "/fxml/Login.fxml";

    @FXML
    private Label loggedUserFirstAndLastName, accessLevel;

    @FXML
    private ImageView reportModule, logModule, settingImage;

    private final StageManager newStage = new StageManager();
    private Credentials currentLoggedUser;

    @FXML
    private void initialize() {
        LoginController user = new LoginController();
        currentLoggedUser = user.getLoggedUser();
        managerUserAccess();
        loggedUserFirstAndLastName.setText(user.getCurrentUserFirstAndLastName());
        displayAccessLevel();

    }

    private void managerUserAccess() {
        if (currentLoggedUser.isIsAdministrator() == false) {
            settingImage.setVisible(false);
        }
        if (currentLoggedUser.isAccessLogModule() == false) {
            logModule.setVisible(false);
        }

        if (currentLoggedUser.isAccessReportModule() == false) {
            reportModule.setVisible(false);
        }
    }

    private void displayAccessLevel() {
        if (currentLoggedUser.isIsAdministrator() == true) {
            accessLevel.setText("Administrator");
        } else {
            accessLevel.setText("Regular User");
        }

    }

    @FXML
    private void goToUserManagerWindow(Event onMouseClicked) {

        if (currentLoggedUser.isIsAdministrator() == true) {
            newStage.nextStage(userModuleStage, "Users Module Manager");
            onMouseClicked.consume();
        }
        else
        {
            newStage.nextStage(regularUserModuleStage, "Regular User Profil Page");
            onMouseClicked.consume();
        }

        
    }

    @FXML
    private void goToSiteManagerWindow(Event onMouseClicked) {
        newStage.nextStage(siteModuleStage, "Site Module Manager");
        onMouseClicked.consume();
    }

    @FXML
    private void goToInventoryManagerWindow(Event onMouseClicked) {
        newStage.nextStage(inventoryModuleStage, "Inventory Module Manager");
        onMouseClicked.consume();
    }

    @FXML
    private void goToLogManagerWindow(Event onMouseClicked) {
        newStage.nextStage(logModuleStage, "Log Module Manager");
        onMouseClicked.consume();
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        newStage.nextStageUsingAnActionEvent(event, loginPage, "Login");
        event.consume();
        LoginController user = new LoginController();
        user.setLoggedUser(null);
    }

}
