/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Auguste C
 */
public class Main extends Application {

   
    @Override
    public void start(Stage newStage) {

        try {

            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/InventoryManager.fxml"));
            Scene scene = new Scene(parent);
            newStage= new Stage();
            newStage.setTitle("Login");
            newStage.setScene(scene);
            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }      

    }
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }

    

}
