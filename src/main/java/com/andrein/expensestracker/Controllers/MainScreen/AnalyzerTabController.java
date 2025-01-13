package com.andrein.expensestracker.Controllers.MainScreen;

import com.andrein.expensestracker.Controllers.ExpensesController;
import com.andrein.expensestracker.Models.Expense;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.Month;
import java.util.List;

public class AnalyzerTabController {
    private MainScreenController mainScreenController;
    private ExpensesController expensesController = new ExpensesController();

    @FXML
    private ComboBox monthPicker;

    @FXML
    private TableView expensesTable;

    @FXML
    private TableColumn<Expense, String> dateColumn;

    @FXML
    private TableColumn<Expense, String> merchantColumn;

    @FXML
    private TableColumn<Expense, String> descriptionColumn;

    @FXML
    private TableColumn<Expense, String> categoryColumn;

    @FXML
    private TableColumn<Expense, String> priceColumn;

    public void setMainScreenController(MainScreenController msc) {
        this.mainScreenController = msc;
    }

    @FXML
    private void initialize() {
        refreshUI();
    }

    private void setMonthPicker() {
        List<String> availableExpensesMonths = expensesController.getAvailableExpensesMonths();
        ObservableList<String> comboBoxMonths = FXCollections.observableArrayList(availableExpensesMonths);
        monthPicker.setItems(comboBoxMonths);
        monthPicker.getSelectionModel().selectLast(); //select the current month

        String monthPickerValue = monthPicker.getValue().toString();
        String[] parts = monthPickerValue.split(" ");
        String monthString = parts[0];
        int year = Integer.parseInt(parts[1]);
        Month month = Month.valueOf(monthString.toUpperCase());
        int monthValue = month.getValue();

        System.out.println(monthValue);
        System.out.println(year);
    }

    public void setExpensesTable() {
        String monthPickerValue = monthPicker.getValue().toString();
        String[] parts = monthPickerValue.split(" ");
        String monthString = parts[0];
        int year = Integer.parseInt(parts[1]);
        Month month = Month.valueOf(monthString.toUpperCase());
        int monthValue = month.getValue();

        List<Expense> expenses = expensesController.getExpensesByMonth(monthValue, year);
        ObservableList<Expense> expensesData = FXCollections.observableArrayList(expenses);

        dateColumn.setCellValueFactory(
                c -> new SimpleStringProperty(c.getValue().getEntireDate())
        );

        merchantColumn.setCellValueFactory(
                c -> new SimpleStringProperty(c.getValue().getMerchant())
        );

        descriptionColumn.setCellValueFactory(
                c -> new SimpleStringProperty(c.getValue().getDescription())
        );

        categoryColumn.setCellValueFactory(
                c -> new SimpleStringProperty(c.getValue().getCategory())
        );

        priceColumn.setCellValueFactory(
                c -> new SimpleStringProperty(Double.toString(c.getValue().getPrice()))
        );

        expensesTable.setItems(expensesData);
    }

    public void refreshUI() {
        setMonthPicker();
        setExpensesTable();
    }
}
