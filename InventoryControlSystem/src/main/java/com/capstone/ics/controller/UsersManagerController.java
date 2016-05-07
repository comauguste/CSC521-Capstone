/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.AuditLog;
import com.capstone.ics.model.Credentials;
import com.capstone.ics.model.UserAddresses;
import com.capstone.ics.model.Users;
import com.capstone.ics.service.LogService;
import com.capstone.ics.service.UserService;
import com.capstone.ics.util.DateUtil;
import java.io.IOException;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
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
    private Label firstNameLabel,lastNameLabel,emailAddressLabel,phoneNumberLabel,genderLabel,address1Label,
            address2Label,cityLabel,postalCodeLabel,stateLabel,birthdayLabel,countryLabel,accessLevelLabel,reportLabel,logLabel,notificationLabel;
   
    private final StageManager aStage = new StageManager();

    private UserService userService;
    private LogService logService;

    @FXML
    private void initialize() {
        userService = new UserService();
        logService = new LogService();
        personTable.setItems(userService.getUsersAsObservableList());

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
            address1Label.setText(aUser.getAddress().getUserAddressLine1());
            address2Label.setText(aUser.getAddress().getUserAddressLine2());
            cityLabel.setText(aUser.getAddress().getCity());
            postalCodeLabel.setText(aUser.getAddress().getZipCode());
            stateLabel.setText(aUser.getAddress().getState());
            countryLabel.setText(aUser.getAddress().getCountry());
            accessLevelLabel.setText(aUser.getUserCredentials().convertAccessLevelToString());
            reportLabel.setText(aUser.getUserCredentials().convertReportAccessLevelToString());
            logLabel.setText(aUser.getUserCredentials().convertLogAccessLevelToString());
            notificationLabel.setText(aUser.getUserCredentials().convertReceiveNotificationToString());
        } else {

            firstNameLabel.setText("");
            lastNameLabel.setText("");
            emailAddressLabel.setText("");
            phoneNumberLabel.setText("");
            genderLabel.setText("");
            birthdayLabel.setText("");
            address1Label.setText("");
            address2Label.setText("");
            cityLabel.setText("");
            postalCodeLabel.setText("");
            stateLabel.setText("");
            countryLabel.setText("");
            accessLevelLabel.setText("");
            reportLabel.setText("");
            logLabel.setText("");
            notificationLabel.setText("");

        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new user.
     */
    @FXML
    private void handleNewUser() {
        Users tempUser = new Users();
        String actionPerformed = "New User";
        UserAddresses tempAddresses = new UserAddresses();
        Credentials tempCredentials = new Credentials();

        boolean okClicked = showUserEditDialog(tempUser, tempAddresses, tempCredentials);
        if (okClicked) {
            
            userService.getUsersData().add(tempUser);
            //TO complete later 
            tempAddresses.setUsers(tempUser);
            tempCredentials.setUsers(tempUser);
            tempUser.setAddress(tempAddresses);
            tempUser.setUserCredentials(tempCredentials);

            userService.save(tempUser);
            
            logIt(tempUser, actionPerformed);
        }
    }

    private void logIt(Users tempUser, String activity) {
        AuditLog log = new AuditLog("admin", "User module", activity, getUserDetails(tempUser)
                , new Date());
        
        logService.save(log);
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Users selectedUser = personTable.getSelectionModel().getSelectedItem();
        String actionPerformed = "Update User";
        if (selectedUser != null) {
            UserAddresses selectedUserAddresses = selectedUser.getAddress();
            Credentials selectedUserCredentials = selectedUser.getUserCredentials();
            boolean okClicked = showUserEditDialog(selectedUser, selectedUserAddresses, selectedUserCredentials);
            if (okClicked) {
                showUserDetails(selectedUser);
                userService.update(selectedUser);
                logIt(selectedUser, actionPerformed);
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
        Users selectedUser = personTable.getSelectionModel().getSelectedItem();
        String actionPerformed = "Delete User";
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
            userService.delete(selectedUser.getPkUserId());
            logIt(selectedUser, actionPerformed);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No User Selected");
            alert.setContentText("Please select a user in the table");

            alert.showAndWait();
        }

    }

    public boolean showUserEditDialog(Users aUser, UserAddresses address, Credentials credentials) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/EditUserDetailsDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage. 
            Scene scene = new Scene(page);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit User Information");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            // Set the user into the controller.
            EditUserDetailsDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(aUser, address, credentials);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private String getUserDetails(Users aUser)
    {
         StringBuilder sb = new StringBuilder();
        sb.append(" User Id: ").append(aUser.getPkUserId()).append(" User name: ")
                .append(aUser.getFirstName()).append(" ").append(aUser.getLastName());
        
        return sb.toString();
    }
}
