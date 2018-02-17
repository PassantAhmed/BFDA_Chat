/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Passant
 */
public class DashboardController implements Initializable {

    @FXML
    private Pane basePane;
    @FXML
    private Button viewClientsBtn;
    @FXML
    private Button viewStatisticsBtn;
    @FXML
    private Button announcementsBtn;
    @FXML
    private Button settingsBtn;
    private List<Button> buttons = new ArrayList<>();

    private Node viewClientNode;
    private Node viewStatNode;
    private Node viewAnnounce;
    private Node viewSettings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonsArray();
        initNodes();
        try {
            Node newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/StatisticsScene.fxml"));
            basePane.getChildren().clear();
            basePane.getChildren().add(viewStatNode);
            setButtonStyle(viewStatisticsBtn);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewClient(ActionEvent actionEvent) throws IOException {
        basePane.getChildren().clear();
        basePane.getChildren().add(viewClientNode);
        setButtonStyle(viewClientsBtn);
    }

    public void viewStatistics(ActionEvent actionEvent) throws IOException {
        Node newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/StatisticsScene.fxml"));
        basePane.getChildren().clear();
        basePane.getChildren().add(newLoadedPane);
        setButtonStyle(viewStatisticsBtn);

    }

    public void viewAnnounce(ActionEvent actionEvent) throws IOException {
        Node newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/AnnouncementsScene.fxml"));
        basePane.getChildren().clear();
        basePane.getChildren().add(viewAnnounce);
        setButtonStyle(announcementsBtn);

    }

    public void viewSettings(ActionEvent actionEvent) throws IOException {
        Node newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/SettingsScene.fxml"));
        basePane.getChildren().clear();
        basePane.getChildren().add(viewSettings);
        setButtonStyle(settingsBtn);

    }

    public void setButtonStyle(Button btn) {
        for (Button button : buttons) {
            String NOT_Clicked = "-fx-background-color:  #b9798b;";
            String clicked = "-fx-background-color:  #b0697d;";

            if (button == btn) {
                button.setStyle(clicked);
            } else {
                button.setStyle(NOT_Clicked);
            }
        }
    }

    private void buttonsArray() {
        buttons.add(viewClientsBtn);
        buttons.add(viewStatisticsBtn);
        buttons.add(announcementsBtn);
        buttons.add(settingsBtn);
    }

    private void initNodes() {
        try {
            viewClientNode = FXMLLoader.load(getClass().getResource("/fxml/ClientsScene.fxml"));
            viewStatNode = FXMLLoader.load(getClass().getResource("/fxml/StatisticsScene.fxml"));
            viewAnnounce = FXMLLoader.load(getClass().getResource("/fxml/AnnouncementsScene.fxml"));
            viewSettings = FXMLLoader.load(getClass().getResource("/fxml/SettingsScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
