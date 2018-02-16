/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

/**
 *
 * @author Passant
 */
public class StatisticsController implements Initializable {

    @FXML private PieChart usersNoChart;
    @FXML private Label offlineUsersNo;
    @FXML private Label onlineUsersNo;
    @FXML private Circle offlineUsersCircle;
    @FXML private Circle onlineUsersCircle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setStatistics(700,50);
    }

    public void setStatistics(int allNo, int onlineNo) {
        onlineUsersNo.setText(String.valueOf(onlineNo));
        offlineUsersNo.setText(String.valueOf(allNo-onlineNo));

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Online Users", onlineNo),
                        new PieChart.Data("Offline Users", allNo-onlineNo)
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
        chartColors.add("#001a33");
        chartColors.add("#b9798b");
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle(
                "-fx-pie-color: " + chartColors.get(counter) + ";"
            );
            counter++;
        }
    }
}
