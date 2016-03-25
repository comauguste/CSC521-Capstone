/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.DAO.UsersDAO;
import com.capstone.ics.model.Users;
import com.capstone.ics.util.DateUtil;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class UsersManagerController {

    @FXML
    private TableView<Users> personTable;
    @FXML
    private TableColumn<Users, String> firstNameColumn;
    @FXML
    private TableColumn<Users, String> lastNameColumn;
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

    private final StageManager aStage = new StageManager();

    @FXML
    private void initialize() {
        UsersDAO usersList = new UsersDAO();
        personTable.setItems(usersList.getUsersData());

        //Initialize the person table with the two columns
        firstNameColumn.setCellValueFactory(CellData -> CellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(CellData -> CellData.getValue().lastNameProperty());

        //Clear Details
        showUserDetails(null);

        //Listen for selection changes and show the person details when changed
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> showUserDetails(newValue));

    }

    private void showUserDetails(Users aUser) {
        if (aUser != null) {
            firstNameLabel.setText(aUser.getFirstName());
            lastNameLabel.setText(aUser.getLastName());
            emailAddressLabel.setText(aUser.getEmailAddress());
            phoneNumberLabel.setText(aUser.getPhoneNumber());
            genderLabel.setText(aUser.getGender());
            birthdayLabel.setText(DateUtil.format(aUser.getBirthDate()));

//            address1Label;
//            address2Label;
//            cityLabel;
//            postalCodeLabel;
//            birthdayCode;
//            accessLeveLabel;
//            stateLabel;
//            birthdayLabel;
//            countryLabel;
        } else {

            firstNameLabel.setText("");
            lastNameLabel.setText("");
            emailAddressLabel.setText("");
            phoneNumberLabel.setText("");
            genderLabel.setText("");
            birthdayLabel.setText("");

        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new user.
     */
    @FXML
    private void handleNewUser() {
        Users tempUser = new Users();
        UsersDAO userData = new UsersDAO();
        boolean okClicked = showUserEditDialog(tempUser);
        if (okClicked) {
            userData.getPersonData().add(tempUser);
            //TO complete later
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Users selectedUser = personTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            boolean okClicked =showUserEditDialog(selectedUser);
            if (okClicked) {
                showUserEditDialog(selectedUser);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks on the delete button
     */
    @FXML
    public void deleteSelectedUser() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
            System.out.println(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No User Selected");
            alert.setContentText("Please select a user in the table");

            alert.showAndWait();
        }

    }

    public boolean showUserEditDialog(Users aUser) {
        try {

            // Create the dialog Stage.  
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/EditUserDetailsDialog.fxml"));
            Scene scene = new Scene(parent);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);
            dialogStage.show();          
            

            // Set the person into the controller.
            EditUserDetailsDialogController controller = new EditUserDetailsDialogController();
            controller.setDialogStage(dialogStage);
            controller.setUser(aUser);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
