package com.andrein.expensestracker.Controllers.LoginScreen;

import javafx.fxml.FXML;

public class RegisterTabController {
    private LoginScreenController loginScreenController;

    @FXML
    public void addLoginController(LoginScreenController loginScreenController) {
        this.loginScreenController = loginScreenController;
    }

    @FXML
    private void initialize() {
        System.out.println("Register Tab controller initialized");
    }
}
