package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.fxml.FXML;
import  javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.fashionfiesta.dto.Employee;
import lk.ijse.fashionfiesta.dto.Supplier;
import lk.ijse.fashionfiesta.model.SupplierModel;
import lk.ijse.fashionfiesta.dto.Supplier;
import lk.ijse.fashionfiesta.model.SupplierModel;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;
import java.sql.ResultSet;

public class SupplierAddController implements Initializable {

    public JFXButton btnAdd;
    @FXML
    private JFXTextField txtSupId;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtStreet;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtLane;

    @FXML
    private JFXTextField txtContact;

    private ResultSet set;
    private ObservableList<Supplier> data;

    public void addOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier();
        supplier.setSupplier_id(txtSupId.getText());
        supplier.setSupplier_Fname(txtFirstName.getText());
        supplier.setSupplier_Lname(txtLastName.getText());
        supplier.setStreet(txtStreet.getText());
        supplier.setCity(txtCity.getText());
        supplier.setLane(txtLane.getText());
        supplier.setContact(txtContact.getText());

        try {
            boolean add = SupplierModel.addSupplier(supplier);
            if (add){
                SupplierFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Added").show();
                Navigation.close(actionEvent);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error Added").show();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void firstNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("firstNameKeyReleased");
        RegexUtil.regex(btnAdd,txtFirstName,txtFirstName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void lastNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("lastNameKeyReleased");
        RegexUtil.regex(btnAdd,txtLastName,txtLastName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void streetKeyReleased(KeyEvent keyEvent) {
        System.out.println("streetKeyReleased");
        RegexUtil.regex(btnAdd,txtStreet,txtStreet.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");

    }

    public void cityKeyReleased(KeyEvent keyEvent) {
        System.out.println("cityKeyReleased");
        RegexUtil.regex(btnAdd,txtCity,txtCity.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");

    }

    public void laneKeyReleased(KeyEvent keyEvent) {
        System.out.println("laneKeyReleased");
        RegexUtil.regex(btnAdd,txtLane,txtLane.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }
}

