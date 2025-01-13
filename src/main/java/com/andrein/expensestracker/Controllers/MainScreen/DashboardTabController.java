package com.andrein.expensestracker.Controllers.MainScreen;

import atlantafx.base.controls.Message;
import atlantafx.base.theme.Styles;
import com.andrein.expensestracker.Controllers.ExpensesController;
import com.andrein.expensestracker.Models.Expense;
import com.andrein.expensestracker.Models.User;
import com.andrein.expensestracker.UserSession;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DashboardTabController {
    User loggedInUser = UserSession.getInstance().getLoggedUser();
    ExpensesController expensesController = new ExpensesController();
    @FXML
    private Text greetingText;

    @FXML
    private PieChart PieChart;

    @FXML
    private Text availableBudgetSum;

    @FXML
    private ProgressBar availableBudgetProgressBar;

    @FXML
    private Text biggestExpenseName;

    @FXML
    private Text biggestExpensePrice;

    @FXML
    private Text mostExpensiveCategoryName;

    @FXML
    private Text mostExpensiveCategoryPrice;

    @FXML
    private FontIcon biggestExpenseIcon;

    @FXML
    private FontIcon mostExpensiveIcon;

    @FXML
    private TableView last7daysExpenses;

    @FXML
    private TableColumn<Expense, String> dateColumn;

    @FXML
    private TableColumn<Expense, String> merchantColumn;

    @FXML
    private TableColumn<Expense, String> descriptionColumn;

    @FXML
    private TableColumn<Expense, String> priceColumn;

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController msc) {
        this.mainScreenController = msc;
    }

    @FXML
    private void initialize() {
        System.out.println("Dashboard Tab controller initialized");
        greetingText.setText("Hello, " + loggedInUser.getFullName());
        setBudgetCard();
        setBiggestExpense();
        setMostExpensiveCategory();
        setPieChart();
        set7DaysExpenses();
    }

    private void setBudgetCard() {
        Double spent = (loggedInUser.getBudget() - expensesController.getExpensesSum());
        String budget = Integer.toString(loggedInUser.getBudget());
        availableBudgetSum.setText(Double.toString(spent) + "/" + budget);
        Double percentage = 100 - ((expensesController.getExpensesSum() * 100)/ loggedInUser.getBudget());
        availableBudgetProgressBar.setProgress(percentage/100);
    }

    private void setBiggestExpense() {
        biggestExpenseName.setText(expensesController.getBiggestExpense().getMerchant());
        biggestExpensePrice.setText(Double.toString(expensesController.getBiggestExpense().getPrice()));
        biggestExpenseIcon.setIconLiteral(categoryGetIcon(expensesController.getBiggestExpense().getCategory()));
        biggestExpenseIcon.setIconSize(32);
    }

    private void setMostExpensiveCategory() {
        String mostExpensiveCategory = expensesController.getMostExpensiveCategory().split(";")[0];
        String categorySum = expensesController.getMostExpensiveCategory().split(";")[1];

        mostExpensiveCategoryName.setText(mostExpensiveCategory);
        mostExpensiveCategoryPrice.setText(categorySum);
        mostExpensiveIcon.setIconLiteral(categoryGetIcon(mostExpensiveCategory));
        mostExpensiveIcon.setIconSize(32);
    }

    private void setPieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Map<String, Double> categoriesPercentages = expensesController.getCategoriesPercentages();
        for(Map.Entry<String, Double> entry : categoriesPercentages.entrySet() ) {
            pieChartData.add(
                    new PieChart.Data(entry.getKey(), entry.getValue())
            );
        }

        PieChart.setData(pieChartData);
        PieChart.setTitle("Expenses distribution");
        PieChart.setPrefHeight(340);
    }

    private String categoryGetIcon(String category) {
        switch (category) {
            case "Entertainment": {
                return "mdi2g-gamepad-square-outline";
            }
            case "Living": {
                return "mdi2h-home-outline";
            }
            case "Health": {
                return "fas-pills";
            }
            case "Food/Groceries": {
                return "fas-hamburger";
            }
            case "Trips": {
                return "mdi2a-airplane-takeoff";
            }
            default: {
                return "far-question-circle";
            }
        }
    }

    private void set7DaysExpenses() {
        ArrayList<Expense> expenses = expensesController.getLast7DaysExpenses();
        ObservableList<Expense> expensesData = FXCollections.observableArrayList(expenses);

        // Set the columns data
        dateColumn.setCellValueFactory(
                c -> new SimpleStringProperty(c.getValue().getEntireDate())
        );

        merchantColumn.setCellValueFactory(
                c -> new SimpleStringProperty(c.getValue().getMerchant())
        );

        descriptionColumn.setCellValueFactory(
                c -> new SimpleStringProperty(c.getValue().getDescription())
        );

        priceColumn.setCellValueFactory(
                c -> new SimpleStringProperty(Double.toString(c.getValue().getPrice()))
        );

        last7daysExpenses.getColumns().setAll(dateColumn, merchantColumn, descriptionColumn, priceColumn);
        last7daysExpenses.setItems(expensesData);
    }

    public void refreshUI() {
        setBudgetCard();
        setPieChart();
        setMostExpensiveCategory();
        setBiggestExpense();
    }

}
