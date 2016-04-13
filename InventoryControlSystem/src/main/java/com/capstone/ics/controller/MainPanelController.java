/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import javafx.event.Event;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class MainPanelController {

    private final String userModuleStage = "/fxml/UsersManager.fxml";
    private final String siteModuleStage = "/fxml/CompanyModule.fxml";
    private final String inventoryModuleStage = "/fxml/InventoryManager.fxml";

    private final StageManager newStage = new StageManager();

    @FXML
    private void goToUserManagerWindow(Event onMouseClicked) {
        newStage.nextStage(userModuleStage, "Users Module Manager");
        onMouseClicked.consume();
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

}
