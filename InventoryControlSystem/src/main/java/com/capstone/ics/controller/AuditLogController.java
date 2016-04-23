/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import com.capstone.ics.model.AuditLog;
import com.capstone.ics.service.LogService;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Auguste C
 */
public class AuditLogController {
    
     @FXML
    private TableView<AuditLog> auditTable;
    @FXML
    private TableColumn<AuditLog, String> userColumn, moduleColumn, actionPerformedColumn, activityDetailColumn;
    
    @FXML
    private TableColumn<AuditLog, Number> logIDColumn;
    
     @FXML
    private TableColumn<AuditLog, Date> dateColumn;
    
    private LogService logService;

    /**
     * Initializes the controller class.
     */
    
   
    @FXML
    private void initialize() {
        
       logService = new LogService();
       auditTable.setItems(logService.getLogsAsObservableList());
       
       //Initialize the person table with the two columns
        logIDColumn.setCellValueFactory(CellData -> CellData.getValue().auditIdIntegerProperty());
        userColumn.setCellValueFactory(CellData -> CellData.getValue().userProperty());//Initialize the person table with the two columns
        moduleColumn.setCellValueFactory(CellData -> CellData.getValue().moduleProperty());
        actionPerformedColumn.setCellValueFactory(CellData -> CellData.getValue().activityProperty());//Initialize the person table with the two columns
        activityDetailColumn.setCellValueFactory(CellData -> CellData.getValue().activityDetailProperty());
        dateColumn.setCellValueFactory(CellData -> CellData.getValue().dateProperty());
    }    
    
}
