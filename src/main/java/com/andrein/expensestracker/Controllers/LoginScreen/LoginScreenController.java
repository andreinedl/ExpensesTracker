package com.andrein.expensestracker.Controllers.LoginScreen;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;

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

}