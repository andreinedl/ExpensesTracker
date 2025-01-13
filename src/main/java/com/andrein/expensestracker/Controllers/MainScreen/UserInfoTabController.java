package com.andrein.expensestracker.Controllers.MainScreen;
import com.andrein.expensestracker.Main;
import com.andrein.expensestracker.Models.User;
import com.andrein.expensestracker.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInfoTabController {
    User loggedInUser = UserSession.getInstance().getLoggedUser();

    @FXML
    private Text userInitials;

    @FXML
    private Text userFirstName;

    @FXML
    private Text userLastName;

    @FXML
    private Text userUsername;

    @FXML
    private Text currentUserName;

    @FXML
    private Button logoutBtn;

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController msc) {
        this.mainScreenController = msc;
    }
    @FXML
    private void initialize() {
        System.out.println("User Info Tab controller initialized");
        setUserData();
    }

    private void setUserData() {
        userInitials.setText(loggedInUser.getFirstName().substring(0,1) + loggedInUser.getLastName().substring(0,1));
        userFirstName.setText(loggedInUser.getFirstName());
        userLastName.setText(loggedInUser.getLastName());
        userUsername.setText(loggedInUser.getUsername());
        currentUserName.setText(loggedInUser.getFullName());
    }

    public void logoutAction() {
        UserSession.getInstance().setUser(null);
        try {
            FXMLLoader loginfile = new FXMLLoader(Main.class.getResource("LoginScreen/login.fxml"));
            Scene loginScene = new Scene(loginfile.load(), 900, 600);
            Stage window = (Stage) logoutBtn.getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshUI() {
        setUserData();
    }
}
