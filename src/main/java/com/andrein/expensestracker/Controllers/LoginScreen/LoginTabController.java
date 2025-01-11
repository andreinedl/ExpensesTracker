package com.andrein.expensestracker.Controllers.LoginScreen;

import atlantafx.base.theme.Styles;
import com.andrein.expensestracker.Controllers.MainScreen.MainScreenController;
import com.andrein.expensestracker.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.andrein.expensestracker.Controllers.Database;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class LoginTabController {
    private LoginScreenController loginScreenController;
    private Database database = new Database();
    //method to get the main login controller
    public void addLoginController(LoginScreenController loginScreenController) {
        this.loginScreenController = loginScreenController;
    }

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private Hyperlink createAccountLink;

    @FXML
    private Button loginButton;

    @FXML
    private void initialize() {
        System.out.println("Login Tab controller initialized");
    }

    public void createAccountClick() {
        loginScreenController.changeTab(1);
    }

    public void loginAction() {
        if(usernameInput.getText() == "" || passwordInput.getText() == "") {
            loginScreenController.showNotification("Error", "All fields must be completed", Styles.DANGER);
            return;
        }

        boolean login = database.login(usernameInput.getText(), passwordInput.getText());
        if(login) {
            loginScreenController.showNotification("Success", "You are now logged in", Styles.SUCCESS);
            navigateToMainScreen();
        } else {
            loginScreenController.showNotification("Error", "Incorrect credentials.", Styles.DANGER);
        }

    }

    private void navigateToMainScreen() {
        try {
            Stage window = (Stage) loginButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainScreen/mainscreen.fxml"));
            Scene scene = new Scene(loader.load());
            window.requestFocus();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
