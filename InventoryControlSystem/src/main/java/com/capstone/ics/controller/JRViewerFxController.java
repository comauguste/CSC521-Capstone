/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capstone.ics.controller;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Popup;
import javafx.stage.Stage;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

/**
 *
 * @author Auguste C
 */
public class JRViewerFxController implements Initializable {

    private JRViewerFxMode printMode;
    private String reportFilename;
    private JRDataSource reportDataset;
    @SuppressWarnings("rawtypes")
    private Map reportParameters;
    private ChangeListener<Number> zoomListener;
    private JasperPrint jasperPrint;
    @FXML
    private ImageView imageView;
    @FXML
    ComboBox<Integer> pageList;
    @FXML
    Slider zoomLevel;  
    @FXML
    protected Node view;
    private Stage parentStage;
    private Double zoomFactor;
    private double imageHeight;
    private double imageWidth;
    private List<Integer> pages;
    private Popup popup;
    private Label errorLabel;
    boolean showingToast;

    public void show() {
        if (reportParameters == null) {
            reportParameters = new HashMap();
        }
        if (printMode == null || printMode == JRViewerFxMode.REPORT_VIEW) {
             popup = new Popup();
            errorLabel = new Label("Error");
            errorLabel.setWrapText(true);
            errorLabel.setMaxHeight(200);
            errorLabel.setMinSize(100, 100);
            errorLabel.setMaxWidth(100);
            errorLabel.setAlignment(Pos.TOP_LEFT);
            errorLabel.getStyleClass().add("errorToastLabel");
            popup.getContent().add(errorLabel);
            errorLabel.opacityProperty().bind(popup.opacityProperty());
            zoomFactor = 1d;
            zoomLevel.setValue(100d);
            imageView.setX(0);
            imageView.setY(0);
            imageHeight = jasperPrint.getPageHeight();
            imageWidth = jasperPrint.getPageWidth();
            if (zoomListener != null) {
                zoomLevel.valueProperty().removeListener(zoomListener);
            }
            zoomListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                zoomFactor = newValue.doubleValue() / 100;
                imageView.setFitHeight(imageHeight * zoomFactor);
                imageView.setFitWidth(imageWidth * zoomFactor);
             };

            zoomLevel.valueProperty().addListener(zoomListener);
            if (jasperPrint.getPages().size() > 0) {
                viewPage(0);
                pages = new ArrayList<>();
                for (int i = 0; i < jasperPrint.getPages().size(); i++) {
                    pages.add(i + 1);
                }
            }
            pageList.setItems(FXCollections.observableArrayList(pages));
            pageList.getSelectionModel().select(0);
        } else if (printMode == JRViewerFxMode.REPORT_PRINT) {
            print();
        }

    }

    private WritableImage getImage(int pageNumber) {
        BufferedImage image = null;
        try {
            image = (BufferedImage) JasperPrintManager.printPageToImage(jasperPrint, pageNumber, 2);
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        WritableImage fxImage = new WritableImage(jasperPrint.getPageWidth(), jasperPrint.getPageHeight());
        return SwingFXUtils.toFXImage(image, fxImage);

    }

    private void viewPage(int pageNumber) {
        imageView.setFitHeight(imageHeight * zoomFactor);
        imageView.setFitWidth(imageWidth * zoomFactor);
        imageView.setImage(getImage(pageNumber));
    }

    public void clear() {
        // TODO Auto-generated method stub

    }

    @FXML
    private void print() {
        try {
            JasperPrintManager.printReport(jasperPrint, true);
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void pageListSelected(final ActionEvent event) {
        System.out.println(pageList.getSelectionModel().getSelectedItem() - 1);
        viewPage(pageList.getSelectionModel().getSelectedItem() - 1);
    }

    public JRViewerFxMode getPrintMode() {
        return printMode;
    }

    public void setPrintMode(JRViewerFxMode printMode) {
        this.printMode = printMode;
    }

    public String getReportFilename() {
        return reportFilename;
    }

    public void setReportFilename(String reportFilename) {
        this.reportFilename = reportFilename;
    }

    public JRDataSource getReportDataset() {
        return reportDataset;
    }

    public void setReportDataset(JRDataSource reportDataset) {
        this.reportDataset = reportDataset;
    }

    public Map getReportParameters() {
        return reportParameters;
    }

    public void setReportParameters(Map reportParameters) {
        this.reportParameters = reportParameters;
    }

    public Node getView() {
        return view;
    }

    public void setView(Node view) {
        this.view = view;
    }

    public void close() {
        parentStage.close();
    }

    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

}
