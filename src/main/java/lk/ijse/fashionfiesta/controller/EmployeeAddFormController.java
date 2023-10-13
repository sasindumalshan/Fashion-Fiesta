package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.fashionfiesta.dto.Employee;
import lk.ijse.fashionfiesta.model.EmployeeModel;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeAddFormController implements Initializable {

    public JFXComboBox txtRole;
    public JFXButton btnAdd;
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

    private ResultSet set;
    private ObservableList<Employee> data;

    public void addOnAction(ActionEvent actionEvent) {
        Employee employee = new Employee();
        employee.setEmployee_id(txtCustId.getText());
        employee.setEmployee_Fname(txtFirstName.getText());
        employee.setEmployee_Lname(txtLastName.getText());
        employee.setStreet(txtStreet.getText());
        employee.setCity(txtCity.getText());
        employee.setLane(txtLane.getText());
        employee.setRole(getRole());
        employee.setContact(txtContact.getText());

        try {
            boolean add = EmployeeModel.addEmployee(employee);
            if (add){
                EmployeeRegisterFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Added").show();
                Navigation.close(actionEvent);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error Added").show();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setDataInComboBox() {
        ArrayList<String> role = new ArrayList<>();
        role.add("Admin");
        role.add("Cashier");
        role.add("Salsmen");
        role.add("Other");
        txtRole.getItems().addAll(role);
    }
    public String getRole(){
        return String.valueOf(txtRole.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDataInComboBox();
    }

    public void firstNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("firstNameKeyReleased");
        RegexUtil.regex(btnAdd,txtFirstName,txtFirstName.getText(),"[a-zA-Z-']+[ a-zA-Z-']");
    }

    public void lastNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("lastNameKeyReleased");
        RegexUtil.regex(btnAdd,txtLastName,txtLastName.getText(),"[a-zA-Z-']+[ a-zA-Z-']");
    }

    public void streetKeyReleased(KeyEvent keyEvent) {
        System.out.println("streetKeyReleased");
        RegexUtil.regex(btnAdd,txtStreet,txtStreet.getText(),"[a-zA-Z-']+[ a-zA-Z-']");
    }

    public void cityKeyReleased(KeyEvent keyEvent) {
        System.out.println("cityKeyReleased");
        RegexUtil.regex(btnAdd,txtCity,txtCity.getText(),"[a-zA-Z-']+[ a-zA-Z-']");
    }

    public void laneKeyReleased(KeyEvent keyEvent) {
        System.out.println("laneKeyReleased");
        RegexUtil.regex(btnAdd,txtLane,txtLane.getText(),"[a-zA-Z-']+[ a-zA-Z-']");
    }

    public void contactNumberKeyReleased(KeyEvent keyEvent) {
        System.out.println("contacKeyReleased");
        RegexUtil.regex(btnAdd, (JFXTextField) txtContact,txtContact.getText(),"^(?:0|94|\\+94)?(?:7(0|1|2|4|5|6|7|8)\\d)d{6}$");
    }
}
