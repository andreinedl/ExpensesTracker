module com.andrein.expensestracker {

    requires atlantafx.base;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.naming;
    requires sql2o;
    requires java.sql;

    opens com.andrein.expensestracker to javafx.fxml;
    opens com.andrein.expensestracker.Models to sql2o;

    exports com.andrein.expensestracker;
    exports com.andrein.expensestracker.Controllers.LoginScreen;
    exports com.andrein.expensestracker.Controllers.MainScreen;
    opens com.andrein.expensestracker.Controllers.LoginScreen to javafx.fxml;
    opens com.andrein.expensestracker.Controllers.MainScreen to javafx.fxml;
}