/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.RefItemCategories;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class AddOrEditCategoryController {

    @FXML
    private TextField categoryNameField;

    @FXML
    private TextArea categoryDescriptionField;

    private Stage mDialogStage;
    private RefItemCategories mCategories;
    private boolean okCliked = false;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {

    }

    public void setDialogStage(Stage newDialogStage) {
        mDialogStage = newDialogStage;
    }

    public void setCategory(RefItemCategories aCategory) {
        mCategories = aCategory;
        categoryNameField.setText(mCategories.getItemCategoryName());
        categoryDescriptionField.setText(mCategories.getItenCategoryDescription());

    }

    public boolean isOkClicked() {
        return okCliked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            mCategories.setItemCategoryName(categoryNameField.getText());
            mCategories.setItenCategoryDescription(categoryDescriptionField.getText());
            okCliked = true;
            mDialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        mDialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (categoryNameField.getText() == null || categoryNameField.getText().length() == 0) {
            errorMessage += "Empty category name!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mDialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
