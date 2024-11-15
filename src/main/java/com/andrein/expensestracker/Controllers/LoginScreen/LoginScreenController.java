package com.andrein.expensestracker.Controllers.LoginScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class LoginScreenController {

    @FXML
    private TabPane loginTabs;

    @FXML
    private void initialize() {
        loginTabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        //loginTabs.widthProperty().addListener((obs, oldVal, newVal) -> updateTabWidths());
        //updateTabWidths();
    }

    private void updateTabWidths() {
        double tabWidth = loginTabs.getWidth() / loginTabs.getTabs().size();
        for (Tab tab : loginTabs.getTabs()) {
            tab.setStyle("-fx-pref-width: " + tabWidth + "px;");
        }
    }
}