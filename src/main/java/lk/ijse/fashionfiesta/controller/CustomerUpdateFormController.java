package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.fashionfiesta.model.CustomerModel;
import lk.ijse.fashionfiesta.tm.CustomerTm;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerUpdateFormController implements Initializable {
    private static CustomerTm customerTm;
    public JFXButton btnUpdate;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtLane;


    public static void getData(CustomerTm customerTm) {
        CustomerUpdateFormController.customerTm = customerTm;



//        System.out.println(CustomerUpdateFormController.customerTm.getCity());

    }

    @FXML
    public void updateOnAction(ActionEvent actionEvent) {
        customerTm.setFistName(txtFirstName.getText());
        customerTm.setLastName(txtLastName.getText());
        customerTm.setStreet(txtStreet.getText());
        customerTm.setCity(txtCity.getText());
        customerTm.setLane(txtLane.getText());
        customerTm.setContact_number(txtContactNumber.getText());

//        try {
//            boolean update = CustomerModel.update(
//                    customerTm
//            );
//            if (update) {
//                CustomerFormController.getInstance().loadDataTable();
//                new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Updated").show();
//                Navigation.close(actionEvent);
//            } else {
//                new Alert(Alert.AlertType.CONFIRMATION, "Error").show();
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtFirstName.setText(CustomerUpdateFormController.customerTm.getFistName());
        txtLastName.setText(CustomerUpdateFormController.customerTm.getLastName());
        txtCity.setText(CustomerUpdateFormController.customerTm.getCity());
        txtContactNumber.setText(CustomerUpdateFormController.customerTm.getContact_number());
        txtLane.setText(CustomerUpdateFormController.customerTm.getLane());
        txtStreet.setText(CustomerUpdateFormController.customerTm.getStreet());
    }

    public void firstNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("FirstNameKeyReleased");
        RegexUtil.regex(btnUpdate, (JFXTextField) txtFirstName,txtFirstName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void lastNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("lastNameKeyReleased");
        RegexUtil.regex(btnUpdate, (JFXTextField) txtLastName,txtLastName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void streetKeyReleased(KeyEvent keyEvent) {
        System.out.println("streetKeyReleased");
        RegexUtil.regex(btnUpdate, (JFXTextField) txtStreet,txtStreet.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void cityKeyReleased(KeyEvent keyEvent) {
        System.out.println("cityKeyReleased");
        RegexUtil.regex(btnUpdate, (JFXTextField) txtCity,txtCity.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void laneKeyReleased(KeyEvent keyEvent) {
        System.out.println("laneKeyReleased");
        RegexUtil.regex(btnUpdate, (JFXTextField) txtLane,txtLane.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void contactNumberKeyReleased(KeyEvent keyEvent) {
        System.out.println("contacKeyReleased");
        RegexUtil.regex(btnUpdate, (JFXTextField) txtContactNumber,txtContactNumber.getText(),"^(?:0|94|\\+94)?(?:7(0|1|2|4|5|6|7|8)\\d)d{6}$");
    }
}


