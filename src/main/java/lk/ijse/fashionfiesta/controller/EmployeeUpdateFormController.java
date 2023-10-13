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
import lk.ijse.fashionfiesta.model.EmployeeModel;
import lk.ijse.fashionfiesta.tm.EmployeeTm;
import lk.ijse.fashionfiesta.tm.SupplierTm;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeUpdateFormController implements Initializable {
    private static EmployeeTm employeeTm;
    public JFXButton btnUpdate;
    @FXML
    private JFXTextField txtCustId;
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
    @FXML
    private JFXComboBox txtRole;

    public static void getData(EmployeeTm employeeTm) {
        EmployeeUpdateFormController.employeeTm = employeeTm;
        System.out.println(EmployeeUpdateFormController.employeeTm.getCity());
    }

    public void updateOnAction(ActionEvent actionEvent) {

        try {
            boolean update = EmployeeModel.update(new Employee(
                    EmployeeUpdateFormController.employeeTm.getEmp_Id(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtStreet.getText(),
                    txtCity.getText(),
                    txtLane.getText(),
                    (String) txtRole.getValue(),
                    txtContact.getText()
            ));
            if (update){
                EmployeeRegisterFormController.getInstance().loadDataTable();
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
        txtFirstName.setText(EmployeeUpdateFormController.employeeTm.getFistName());
        txtLastName.setText(EmployeeUpdateFormController.employeeTm.getLastName());
        txtCity.setText(EmployeeUpdateFormController.employeeTm.getCity());
        txtContact.setText(EmployeeUpdateFormController.employeeTm.getContact());
        txtLane.setText(EmployeeUpdateFormController.employeeTm.getLane());
        txtRole.setValue(EmployeeUpdateFormController.employeeTm.getRole());
        txtStreet.setText(EmployeeUpdateFormController.employeeTm.getStreet());
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
        RegexUtil.regex(btnUpdate, (JFXTextField) txtContact,txtContact.getText(),"^(?:0|94|\\+94)?(?:7(0|1|2|4|5|6|7|8)\\d)d{6}$");
    }
}

