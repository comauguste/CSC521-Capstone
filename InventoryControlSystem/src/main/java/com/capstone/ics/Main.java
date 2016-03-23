/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics;


import com.capstone.ics.DAO.UsersDAO;
import com.capstone.ics.controller.UsersManagerController;
import com.capstone.ics.model.Users;
import javafx.application.Application;
import javafx.collections.ObservableList;
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

            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/UsersManager.fxml"));
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
