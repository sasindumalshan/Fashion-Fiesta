package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.fashionfiesta.dto.Employee;
import lk.ijse.fashionfiesta.dto.Supplier;
import lk.ijse.fashionfiesta.model.EmployeeModel;
import lk.ijse.fashionfiesta.model.SupplierModel;
import lk.ijse.fashionfiesta.tm.EmployeeTm;
import lk.ijse.fashionfiesta.tm.SupplierTm;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierUpdateFormController implements Initializable {
    private static SupplierTm supplierTm;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtStreet;
    public JFXTextField txtCity;
    public JFXTextField txtLane;
    public JFXTextField txtContact;
    public JFXButton btnUpdate;
//    @FXML
//    private JFXTextField txtCustId;
//    @FXML
//    private JFXTextField txtFirstName;
//    @FXML
//    private JFXTextField txtLastName;
//    @FXML
//    private JFXTextField txtStreet;
//    @FXML
//    private JFXTextField txtCity;
//    @FXML
//    private JFXTextField txtLane;
//    @FXML
//    private JFXTextField txtContact;

    public static void getData(SupplierTm supplierTm) {
        SupplierUpdateFormController.supplierTm = supplierTm;
        System.out.println(SupplierUpdateFormController.supplierTm.getCity());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            boolean update = SupplierModel.update(new Supplier(
                    SupplierUpdateFormController.supplierTm.getSup_Id(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtStreet.getText(),
                    txtCity.getText(),
                    txtLane.getText(),
                    txtContact.getText()
            ));
            if (update){
                SupplierFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Updated").show();
                Navigation.close(actionEvent);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtFirstName.setText(supplierTm.getFistName());
        txtLastName.setText(supplierTm.getLastName());
        txtStreet.setText(supplierTm.getStreet());
        txtCity.setText(supplierTm.getCity());
        txtLane.setText(supplierTm.getLane());
        txtContact.setText(supplierTm.getContact());



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
}
