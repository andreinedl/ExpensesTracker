package com.andrein.expensestracker.Controllers.MainScreen;

import com.andrein.expensestracker.Main;
import com.andrein.expensestracker.Models.User;
import com.andrein.expensestracker.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.text.Text;

import java.util.Random;

public class MainScreenController {
    User loggedInUser = UserSession.getInstance().getLoggedUser();
    @FXML
    private Text greetingTextDash;

    @FXML
    private PieChart dashboardPieChart;

    Random rand = new Random();
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Living", rand.nextInt(10, 30)),
            new PieChart.Data("Entertainment", rand.nextInt(10, 30)),
            new PieChart.Data("Health", rand.nextInt(10, 30)),
            new PieChart.Data("Food", rand.nextInt(10, 30))
    );

    @FXML
    private void initialize() {
        System.out.printf("Main Screen controller initialized");
        greetingTextDash.setText("Hello, " + loggedInUser.getFullName());
        dashboardPieChart.setData(pieChartData);
        dashboardPieChart.setTitle("Expenses distribution");
        dashboardPieChart.setPrefHeight(340);
    }
}
