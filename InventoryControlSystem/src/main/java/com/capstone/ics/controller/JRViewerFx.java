/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Auguste C
 */
public class JRViewerFx extends Application{
    
    private JasperPrint jasperPrint;
    private JRViewerFxMode printMode;

    @Override
    public void start(Stage primaryStage) throws Exception {
       InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getResourceAsStream("/fxml/JRViewerFx.fxml");
            FXMLLoader loader = new FXMLLoader();
            Parent page = (Parent) loader.load(fxmlStream);
            Scene scene = new Scene(page);            
            primaryStage.setScene(scene);
            primaryStage.setTitle("ICS Report Window");
            primaryStage.show();
            Object o = loader.getController();
            if (o instanceof JRViewerFxController) {
                JRViewerFxController jrViewerFxController = (JRViewerFxController) o;
                jrViewerFxController.setJasperPrint(jasperPrint);
                jrViewerFxController.show();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public JRViewerFx(JasperPrint jasperPrint, JRViewerFxMode printMode, Stage primaryStage) {
        this.jasperPrint = jasperPrint;
        this.printMode = printMode;
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
