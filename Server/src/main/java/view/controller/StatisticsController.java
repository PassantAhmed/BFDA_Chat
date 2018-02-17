/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.database.DatabaseUserOperation;

/**
 *
 * @author Passant
 */
public class StatisticsController implements Initializable {

    @FXML
    private PieChart usersNoChart;
    @FXML
    private Label offlineUsersNo;
    @FXML
    private Label onlineUsersNo;
    @FXML
    private Circle offlineUsersCircle;
    @FXML
    private Circle onlineUsersCircle;

    DatabaseUserOperation db;
    int online = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            DatabaseUserOperation db = new DatabaseUserOperation();
            online = db.getStatistics();
            setStatistics(db.getUsersNumber(), online);
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Timeline updates = new Timeline(new KeyFrame(Duration.seconds(120), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    db = new DatabaseUserOperation();
                    setStatistics(db.getUsersNumber(), db.getStatistics());
                } catch (SQLException ex) {
                    Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }));
        updates.setCycleCount(Timeline.INDEFINITE);
        updates.play();
    }

    public void setStatistics(int allNo, int onlineNo) {
        onlineUsersNo.setText(String.valueOf(onlineNo));
        offlineUsersNo.setText(String.valueOf(allNo - onlineNo));

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Online Users", onlineNo),
                        new PieChart.Data("Offline Users", allNo - onlineNo)
                );

        usersNoChart.setData(pieChartData);
        usersNoChart.setLegendSide(Side.RIGHT);
        usersNoChart.setLabelsVisible(false);
        usersNoChart.setLegendVisible(false);

        applyCustomColorSequence(pieChartData);

    }

    private void applyCustomColorSequence(
            ObservableList<PieChart.Data> pieChartData) {
        int counter = 0;
        List<String> chartColors = new ArrayList<>();
        chartColors.add("#b9798b");
        chartColors.add("#001a33");
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle(
                    "-fx-pie-color: " + chartColors.get(counter) + ";"
            );
            counter++;
        }
    }
}
