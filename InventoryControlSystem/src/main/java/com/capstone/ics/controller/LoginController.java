/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.DAO.UserDAO;
import com.capstone.ics.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    private List<User> users = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserDAO usersList = new UserDAO();

        users = usersList.retrieveAllUsers();
    }

    @FXML
    private void loginAction(ActionEvent event) {

        //System.out.println("Button was clicked!");
        for (User aUser : users) {
            if (usernameField.getText().equals(aUser.getUsername()) && passwordField.getText().equals(aUser.getPassword())) {

                //System.out.println(passwordField.getText());
                try {
                    (((Node) event.getSource()).getScene()).getWindow().hide();
                    Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Greeting.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setTitle("Main");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                errorLabel.setText(" Username or Password is invalid");
            }
        }

//        for (User aUser : users) {
//            if (usernameField.getText().equals(aUser.getUsername())) {
//                
//                //System.out.println(usernameField.getText());
//                if (passwordField.getText().equals(aUser.getPassword())) {
//                    
//                    //System.out.println(passwordField.getText());
//                    try {
//                        (((Node) event.getSource()).getScene()).getWindow().hide();
//                        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Greeting.fxml"));
//                        Scene scene = new Scene(parent);
//                        Stage stage = new Stage();
//                        stage.setTitle("Main");
//                        stage.setScene(scene);
//                        stage.show();
//                    } catch (IOException ex) {
//                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                } else {
//                    errorLabel.setText("Password invalid");
//                }
//            } else {
//                errorLabel.setText("Username invalid");
//            }
//        }
    }

}
