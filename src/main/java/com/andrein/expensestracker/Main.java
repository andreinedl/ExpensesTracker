package com.andrein.expensestracker;

import atlantafx.base.theme.CupertinoDark;
import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.Styles;
import com.andrein.expensestracker.Controllers.Database;
import com.andrein.expensestracker.Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import com.andrein.expensestracker.Controllers.Database;

public class Main extends Application {
    User loggedInUser;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loginPageFile = new FXMLLoader(Main.class.getResource("LoginScreen/login.fxml"));
        Scene scene = new Scene(loginPageFile.load(), 900, 600);
        stage.requestFocus();
        stage.setScene(scene);
        stage.setTitle("Expenses Tracker");
        stage.setResizable(false);
        stage.show();
        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
    }

    public static void main(String[] args) {
        launch();
    }
}