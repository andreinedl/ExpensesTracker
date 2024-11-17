package com.andrein.expensestracker;

import atlantafx.base.theme.CupertinoDark;
import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.Styles;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginScreen/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.requestFocus();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
      //  stage.setTitle("Hello!");
      //  VBox.setMargin(scene.getRoot(), new javafx.geometry.Insets(101));
        
    }

    public static void main(String[] args) {
        launch();
    }
}