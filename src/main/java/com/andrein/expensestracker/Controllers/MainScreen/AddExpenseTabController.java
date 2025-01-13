package com.andrein.expensestracker.Controllers.MainScreen;

import atlantafx.base.controls.Message;
import atlantafx.base.theme.Styles;
import atlantafx.base.util.Animations;
import com.andrein.expensestracker.Controllers.Database;
import com.andrein.expensestracker.Models.User;
import com.andrein.expensestracker.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import atlantafx.base.controls.Calendar;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.time.LocalDate;

public class AddExpenseTabController {
    private Database database = new Database();

    @FXML
    private TextField merchantField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField priceField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button addButton;
    @FXML
    private RadioButton livingRadio;
    @FXML
    private RadioButton entertainmentRadio;
    @FXML
    private RadioButton healthRadio;
    @FXML
    private RadioButton foodRadio;
    @FXML
    private RadioButton tripsRadio;
    @FXML
    private VBox notificationContainer;

    private ToggleGroup toggleGroup = new ToggleGroup();

    private User loggedInUser = UserSession.getInstance().getLoggedUser();

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController msc) {
        this.mainScreenController = msc;
    }

    @FXML
    private void initialize() {
        setToggleGroup();
        datePicker.setValue(LocalDate.now());
    }

    private void setToggleGroup() {
        livingRadio.setToggleGroup(toggleGroup);
        entertainmentRadio.setToggleGroup(toggleGroup);
        healthRadio.setToggleGroup(toggleGroup);
        foodRadio.setToggleGroup(toggleGroup);
        tripsRadio.setToggleGroup(toggleGroup);
    }

    public void addButtonAction() {
        int userId = loggedInUser.getId();
        double price = Double.parseDouble(priceField.getText());
        String merchant = merchantField.getText();
        String description = descriptionField.getText();
        Integer day = datePicker.getValue().getDayOfMonth();
        Integer month = datePicker.getValue().getMonthValue();
        Integer year = datePicker.getValue().getYear();
        String category = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

        if(database.createExpense(userId, price, merchant, description, day, month, year, category)) {
            showNotification("Success", "Your expense has been successfully added", Styles.SUCCESS);
            resetFields();
        } else {
            showNotification("Error", "Your expense could not be added", Styles.DANGER);
            resetFields();
        }

        mainScreenController.refreshUI();
    }

    private void showNotification(String title, String description, String style) {
        var notification = new Message(title, description);

        //clear the notification container before a new notification is displayed
        notificationContainer.getChildren().clear();

        notification.getStyleClass().add(style);
        notification.setOnClose(e -> {
            var animation = Animations.fadeOut(notification, Duration.millis(500));
            animation.setOnFinished(event -> notificationContainer.getChildren().remove(notification));
            animation.play();
        });
        notificationContainer.getChildren().add(notification);
    }

    private void resetFields() {
        merchantField.setText("");
        descriptionField.setText("");
        priceField.setText("");
        datePicker.setValue(LocalDate.now());
        toggleGroup.selectToggle(null);
    }
}
