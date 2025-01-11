package com.andrein.expensestracker.Controllers.LoginScreen;

import atlantafx.base.controls.Message;
import atlantafx.base.theme.Styles;
import atlantafx.base.util.Animations;
import com.andrein.expensestracker.Models.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import com.andrein.expensestracker.Controllers.Database;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;
import java.util.Objects;

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

    private VBox notificationContainer;


    @FXML
    public void addLoginController(LoginScreenController loginScreenController) {
        this.loginScreenController = loginScreenController;
    }

    @FXML
    private void initialize() {
        System.out.println("Register Tab controller initialized");
    }

    public void registerAction(ActionEvent e) {
        handleRegister();
    }

    private void handleRegister() {
        List<User> users = database.getUsers();

        if (usernameField.getText() == "" || passwordField.getText() == "" || firstNameField.getText() == "" || lastNameField.getText() == "") {
            loginScreenController.showNotification("Error", "All fields must be completed", Styles.DANGER);
            return;
        }

        //check if a username is already taken
        for(User user : users) {
            if(Objects.equals(user.getUsername(), usernameField.getText())) {
                loginScreenController.showNotification("Error", "This username is already taken", Styles.DANGER);
                return;
            }
        }
        database.createUser(usernameField.getText(), passwordField.getText(), firstNameField.getText(), lastNameField.getText());

    }
}
