package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.fashionfiesta.model.UserModel;
import lk.ijse.fashionfiesta.utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public Button btnLogin;
    public JFXPasswordField txtPassword;
    public JFXTextField txtUserName;


    @FXML
    void LoginOnAction(ActionEvent event) throws IOException {

        try {
            System.err.println(UserModel.checkUsernameAndPassword(txtUserName.getText(),txtPassword.getText()));

            if (UserModel.checkUsernameAndPassword(txtUserName.getText(),txtPassword.getText()).equals("Admin")) {
              Navigation.switchNavigation("AdminDashboardForm.fxml", event);
            } else if (UserModel.checkUsernameAndPassword(txtUserName.getText(),txtPassword.getText()).equals("cashier")) {
                Navigation.switchNavigation("Cashier/CashierDashboardForm.fxml", event);
            } else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
     //   Navigation.switchNavigation("Cashier/CashierDashboardForm.fxml", event);
      //  Navigation.switchNavigation("AdminDashboardForm.fxml", event);

    }
}
