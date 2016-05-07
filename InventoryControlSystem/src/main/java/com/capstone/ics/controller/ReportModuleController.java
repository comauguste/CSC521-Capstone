/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class ReportModuleController {

    private final Node rootIcon = new ImageView(
            new Image(getClass().getResourceAsStream("/images/Reports.png")));

    private String reportClassPath = "";

    @FXML
    private TreeView<String> reportTreeView;

    @FXML
    private Label title, description;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void initialize() {

        TreeItem<String> rootItem = new TreeItem<>("Inventory Reports");
        reportTreeView.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> setTitleAndDescription(newValue));

        TreeItem<String> type1 = new TreeItem<>("Inventory Summary");
        TreeItem<String> type2 = new TreeItem<>("Product Price List");
        TreeItem<String> type3 = new TreeItem<>("Total Quantity, Price and Cost");
        rootItem.getChildren().addAll(type1, type2, type3);
        //rootItem.getChildren().addAll(type1);
        rootItem.setExpanded(true);

        reportTreeView.setRoot(rootItem);

    }

    private void handleMouseClick() {

    }

    private void setTitleAndDescription(TreeItem<String> newValue) {
        if (newValue.getValue().equals("Inventory Summary")) {
            title.setText("Inventory Summary");
            description.setText("The summary of all products added by all users.");
            reportClassPath = "target/classes/ReportTemplates/InventorySummary.jasper";
        }
        if (newValue.getValue().equals("Product Price List")) {
            title.setText("Product Price List");
            description.setText("List of all products along with their price.");
            reportClassPath = "target/classes/ReportTemplates/ProductPriceList.jasper";
        }
        if (newValue.getValue().equals("Total Quantity, Price and Cost")) {
            title.setText("Total Quantity, Price and Cost");
            description.setText("List of all products along with their total quantity, price and cost.");
            reportClassPath = "target/classes/ReportTemplates/TotalQuantityAndPrice.jasper";
        }

    }

    @FXML
    public void generateReport() throws ClassNotFoundException {

        if (!reportClassPath.equals("")) {

            JasperPrint jasperPrint = null;
            Stage primaryStage = new Stage();

            //Need to change the following code below
            Class.forName("com.mysql.jdbc.Driver");
            try {
                //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory_system", "comauguste", "Journey1");
                Connection con = DriverManager.getConnection("jdbc:mysql://173.244.1.35:3306/S9269025", "S9269025", "New2016");

                jasperPrint = JasperFillManager.fillReport(reportClassPath, new HashMap(), con);
                JRViewerFx viewer = new JRViewerFx(jasperPrint, JRViewerFxMode.REPORT_PRINT, primaryStage);
                viewer.start(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(ReportModuleController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Report Type Selected");
            alert.setContentText("Please the tpe of report you want to generate.");

            alert.showAndWait();
        }

    }

}
