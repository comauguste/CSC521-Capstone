/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.DAO.UsersDAO;
import com.capstone.ics.model.Users;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class UsersManagerController {

    private List<Users> users = new ArrayList<>();
    private ObservableList<Users> userData = FXCollections.observableArrayList(users);
    private UsersDAO usersData = new UsersDAO();

    @FXML
    private TableView<Users> personTable;
    @FXML
    private TableColumn<Users, String> firstColumn;
    @FXML
    private TableColumn<Users, String> lastColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label emailAddressLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label address1Label;
    @FXML
    private Label address2Label;
    @FXML
    private Label cityLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label birthdayCode;
    @FXML
    private Label accessLeveLabel;
    @FXML
    private Label stateLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label countryLabel;

    @FXML
    private void initialize() {        
        
        firstColumn.setCellValueFactory(CellData -> CellData.getValue().firstNameProperty());
        lastColumn.setCellValueFactory(CellData -> CellData.getValue().lastNameProperty());

    }
    
    public void setUsersData()
    {
        UsersDAO usersList = new UsersDAO();
        userData = usersList.getUsersData();
        personTable.setItems(userData);      
    }

}
