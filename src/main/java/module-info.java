module com.andrein.expensestracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.andrein.expensestracker to javafx.fxml;
    exports com.andrein.expensestracker;
}