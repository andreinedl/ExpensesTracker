package com.andrein.expensestracker.Controllers.LoginScreen;

import atlantafx.base.controls.Message;
import atlantafx.base.controls.Notification;
import atlantafx.base.theme.Styles;
import atlantafx.base.util.Animations;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LoginScreenController {
    //define login and register controller objects
    @FXML
    private LoginTabController loginTabController;

    @FXML
    private RegisterTabController registerTabController;

    @FXML
    private Text text;

    @FXML
    private TabPane tabs;

    @FXML
    private VBox notificationContainer;

    @FXML
    private void initialize() {
        System.out.printf("Login Screen controller initialized");
        loginTabController.addLoginController(this);
        registerTabController.addLoginController(this);
    }

    public void changeTab(int tabNumber) {
        try {
            tabs.getSelectionModel().select(tabNumber);
        } catch (Exception e) {
            throw new RuntimeException("Error changing tab", e);
        }
    }

    public void showNotification(String title, String description, String style) {
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

}