package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.fashionfiesta.dto.Employee;
import lk.ijse.fashionfiesta.model.CustomerModel;
import lk.ijse.fashionfiesta.model.EmployeeAttendanceModel;
import lk.ijse.fashionfiesta.model.EmployeeModel;
import lk.ijse.fashionfiesta.tm.EmployeeTm;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeRegisterFormController implements Initializable {

    private static EmployeeRegisterFormController controller;
    private static EmployeeTm employeeTm;
    private static String employee_id;
    @FXML
    public TableView EmployeeTbl;
    @FXML
    public TableColumn tblId;
    @FXML
    public TableColumn tblFirstName;
    @FXML
    public TableColumn tblLastName;
    @FXML
    public TableColumn tblCity;
    @FXML
    public TableColumn tblContact;
    public Label txtEmpAttendance2;
    public Label txtAllEmployee2;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtContact;
    public JFXComboBox txtRole;
    public JFXTextField txtStreet;
    public JFXTextField txtCity;
    public JFXTextField txtLane;
    public JFXButton update;
    public JFXButton remove;
    public JFXButton add;
    ObservableList<EmployeeTm> list = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtSearch;

    public EmployeeRegisterFormController() {
        controller = this;
    }

    public static EmployeeRegisterFormController getInstance() {
        return controller;
    }

    private String id() {
        try {
            ArrayList<String> allId = EmployeeModel.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                //System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("E00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
               // System.out.println(idIndex);
                return "E00" + idIndex;
            } catch (Exception e) {
                return "E001";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Employee employee = new Employee();
        employee.setEmployee_id(id());
        employee.setEmployee_Fname(txtFirstName.getText());
        employee.setEmployee_Lname(txtLastName.getText());
        employee.setStreet(txtStreet.getText());
        employee.setCity(txtCity.getText());
        employee.setLane(txtLane.getText());
        employee.setRole(String.valueOf(txtRole.getValue()));
        employee.setContact(txtContact.getText());
        try {
            if (EmployeeModel.addEmployee(employee)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Ok Ok ").show();
                clear();
                loadDataTable();
            } else {
                new Alert(Alert.AlertType.WARNING, "something Wrong ! ").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAllIds();
        tblId.setCellValueFactory(new PropertyValueFactory<>("Emp_Id"));
        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("fistName"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tblContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        EmployeeTbl.setItems(list);
        setEmployee();
        setEmpAttendance();
        setAllRoleComboBox();
    }

    private void setAllRoleComboBox() {
        ArrayList<String> role = new ArrayList<>();
        role.add("Admin");
        role.add("Cashier");
        role.add("Salesmen");
        role.add("Other");
        txtRole.getItems().addAll(role);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    /*    EmployeeUpdateFormController.getData(employeeTm);
        try {
            System.out.println();
            Navigation.popupNavigation("EmployeeUpdateForm.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            boolean update = EmployeeModel.update(new Employee(
                    employee_id,
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtStreet.getText(),
                    txtCity.getText(),
                    txtLane.getText(),
                    (String) txtRole.getValue(),
                    txtContact.getText()
            ));
            if (update) {
                EmployeeRegisterFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Updated").show();
                clear();
                loadDataTable();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clear() {
        txtFirstName.clear();
        txtLastName.clear();
        txtStreet.clear();
        txtCity.clear();
        txtLane.clear();
        txtRole.getItems().clear();
        txtContact.clear();
        setAllRoleComboBox();
    }

    public void tblMouseClick(MouseEvent mouseEvent) {
        Employee employee = new Employee();
        employee.toEntity((EmployeeTm) EmployeeTbl.getSelectionModel().getSelectedItem());

        txtFirstName.setText(employee.getEmployee_Fname());
        txtLastName.setText(employee.getEmployee_Lname());
        txtCity.setText(employee.getCity());
        txtContact.setText(employee.getContact());
        txtLane.setText(employee.getLane());
        txtStreet.setText(employee.getStreet());
        employee_id = employee.getEmployee_id();
        txtRole.setValue(employee.getRole());


    }

    public void btnEmployeeDelete(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ? .. .", ButtonType.OK, ButtonType.NO);
            alert.showAndWait();

            if (ButtonType.OK.equals(alert.getResult())) {
                System.out.println("in the if >>> ");

                if (EmployeeModel.remove(employee_id)) {
                    loadDataTable();
                    clear();
                    new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Updated").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getAllIds() {
        try {
            ArrayList<String> list = EmployeeModel.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setEmployeeData(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setEmployeeData(String id) {

        try {
            Employee employee = EmployeeModel.get(id);
            EmployeeTm tm = new EmployeeTm();
            tm.setEmp_Id(employee.getEmployee_id());
            tm.setFistName(employee.getEmployee_Fname());
            tm.setLastName(employee.getEmployee_Lname());
            tm.setCity(employee.getCity());
            tm.setContact(employee.getContact());
            tm.setLane(employee.getLane());
            tm.setRole(employee.getRole());
            tm.setStreet(employee.getStreet());
            list.add(tm);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadDataTable() {
        list.clear();
        EmployeeTbl.getItems().clear();
        getAllIds();
        setEmployee();
    }
    @FXML
    void searchKeyReleased(KeyEvent event) {
        EmployeeTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids = EmployeeModel.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setEmployeeData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setEmployee() {
        try {
            txtAllEmployee2.setText("+ "+EmployeeModel.getEmployee());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setEmpAttendance() {
        try {
            txtEmpAttendance2.setText("+ "+EmployeeAttendanceModel.getEmpAttendance());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void firstNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("firstNameKeyReleased");
        RegexUtil.regex(txtFirstName,txtFirstName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);
    }

    public void lastNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("lastNameKeyReleased");
        RegexUtil.regex(txtLastName,txtLastName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);
    }

    public void streetKeyReleased(KeyEvent keyEvent) {
        System.out.println("streetKeyReleased");
        RegexUtil.regex(txtStreet,txtStreet.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);
    }

    public void cityKeyReleased(KeyEvent keyEvent) {
        System.out.println("cityKeyReleased");
        RegexUtil.regex(txtCity,txtCity.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);
    }

    public void laneKeyReleased(KeyEvent keyEvent) {
        System.out.println("laneKeyReleased");
        RegexUtil.regex(txtLane,txtLane.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);
    }

    public void contactNumberKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(  txtContact,txtContact.getText(),"^(?:7|0|(?:\\+94))[0-9]{9,10}$","-fx-text-fill: black",add,remove,update);
    }


}
