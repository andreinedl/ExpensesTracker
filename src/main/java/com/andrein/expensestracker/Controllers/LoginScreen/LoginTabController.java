package com.andrein.expensestracker.Controllers.LoginScreen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class LoginTabController {
    private LoginScreenController loginScreenController;
    //method to get the main login controller
    public void addLoginController(LoginScreenController loginScreenController) {
        this.loginScreenController = loginScreenController;
    }

    @FXML
    private Hyperlink createAccountLink;

    @FXML
    private void initialize() {
        System.out.println("Login Tab controller initialized");

        //Handle the click for the create account hyperlink
        createAccountLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createAccountClick();
            }
        });
    }

    private void createAccountClick() {
        loginScreenController.changeTab(1);
    }

}
