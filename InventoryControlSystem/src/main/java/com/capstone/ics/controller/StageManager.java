/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Auguste C
 */
public class StageManager {

    public void nextStageUsingAnActionEvent(ActionEvent event, String targetedStage) {

        try {
            (((Node) event.getSource()).getScene()).getWindow().hide();

            Parent parent = FXMLLoader.load(getClass().getResource(targetedStage));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Main");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nextStage(String targetedStage, String stageTitle) {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource(targetedStage));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle(stageTitle);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
