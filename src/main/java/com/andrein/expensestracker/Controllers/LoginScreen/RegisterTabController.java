package com.andrein.expensestracker.Controllers.LoginScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import com.andrein.expensestracker.Controllers.Database;

public class RegisterTabController {
    private LoginScreenController loginScreenController;

    private Database database = new Database();

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button registerButton;

    @FXML
    public void addLoginController(LoginScreenController loginScreenController) {
        this.loginScreenController = loginScreenController;
    }

    @FXML
    private void initialize() {
        System.out.println("Register Tab controller initialized");
    }

    public void registerAction(ActionEvent e) {
        database.createUser(usernameField.getText(), passwordField.getText(), firstNameField.getText(), lastNameField.getText());
    }
}
