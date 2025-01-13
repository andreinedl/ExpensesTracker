package com.andrein.expensestracker.Controllers.MainScreen;

import atlantafx.base.controls.Message;
import atlantafx.base.theme.Styles;
import com.andrein.expensestracker.Models.User;
import com.andrein.expensestracker.UserSession;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainScreenController {
    @FXML
    private DashboardTabController dashboardTabController;
    @FXML
    private AddExpenseTabController addExpenseTabController;
    @FXML
    private UserInfoTabController userInfoTabController;
    @FXML
    private AnalyzerTabController analyzerTabController;
    User loggedInUser = UserSession.getInstance().getLoggedUser();

    @FXML
    private VBox notificationContainer;

    @FXML
    private TabPane tabPane;

    @FXML
    private void initialize() {
        System.out.printf("Main Screen controller initialized");
        showLoginSuccessNotif();
        userInfoTabController.setMainScreenController(this);
        dashboardTabController.setMainScreenController(this);
        addExpenseTabController.setMainScreenController(this);
        analyzerTabController.setMainScreenController(this);
    }

    private void showLoginSuccessNotif() {
        var notification = new Message("Success", "You have been logged in successfully");

        notification.setMaxHeight(80);
        //clear the notification container before a new notification is displayed
        notificationContainer.getChildren().clear();
        notification.getStyleClass().add(Styles.SUCCESS);
        notificationContainer.getChildren().add(notification);

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> notificationContainer.getChildren().remove(notification));
        delay.play();
    }

    public void refreshUI() {
        dashboardTabController.refreshUI();
        userInfoTabController.refreshUI();
        analyzerTabController.refreshUI();
    }

}
