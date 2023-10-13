package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lk.ijse.fashionfiesta.dto.Employee;
import lk.ijse.fashionfiesta.dto.Salary;
import lk.ijse.fashionfiesta.model.EmployeeAttendanceModel;
import lk.ijse.fashionfiesta.model.EmployeeModel;
import lk.ijse.fashionfiesta.model.SalaryModel;
import lk.ijse.fashionfiesta.tm.EmployeeTm;
import lk.ijse.fashionfiesta.tm.SalaryTm;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SalaryFormController implements Initializable {
    public TableColumn colEmployeeId;
    public TableColumn ColSalary;
    public TableColumn colDate;
    public TableColumn colSalary;
    public TableColumn colAttendance;
    public JFXButton btnDone;
    public JFXButton btnApply;
    public JFXComboBox cmbEmployeeId;
    public JFXTextField txtDailySalary;
    public JFXTextField txtBonus;
    public Text txtNetSalary;
    public Text txtName;
    public Text txtAttendance;
    public TableColumn ColSalaryId;
    public TableView tblSalary;
    String salaryId = id();

   static String employeeId = null;
   int count = 0;
    ObservableList<SalaryTm> list= FXCollections.observableArrayList();

    public void btnDoneOnaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Salary salary = new Salary();
        System.out.println("employeeId : "+employeeId);
        salary.setEmployee_id(employeeId);
        salary.setSalary_id(salaryId);
        salary.setDate(DateTimeUtil.dateNow());
        salary.setSalary(Double.parseDouble(txtNetSalary.getText()));
        salary.setEmployee_attandance_count(count);
        if (SalaryModel.add(salary)){
            new Alert(Alert.AlertType.CONFIRMATION,"Done").show();
            loadDataTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
        cmbEmployeeId.getItems().clear();
        setEmployeeId();
        txtName.setText("");
        txtDailySalary.setText("");
        txtAttendance.setText("");
        txtBonus.setText("");

    }

    public void loadDataTable() {
        list.clear();
        tblSalary.getItems().clear();
        loadAllId();
    }
    public void btnApplyOnAction(ActionEvent event) {
        count = attendanceCount();
        System.out.println(employeeId);
        if (txtNetSalary.getText().equals("")) {
            int deilySalary = Integer.parseInt(txtDailySalary.getText());
            int totalSalary = deilySalary * attendanceCount();
            int bonusSalary = Integer.parseInt(txtBonus.getText());
            txtNetSalary.setText(String.valueOf(totalSalary + bonusSalary));

        } else {
            txtNetSalary.setText("");
            int deilySalary = Integer.parseInt(txtDailySalary.getText());
            int totalSalary = deilySalary * attendanceCount();
            int bonusSalary = Integer.parseInt(txtBonus.getText());
            txtNetSalary.setText(String.valueOf(totalSalary + bonusSalary));

        }
    }

    public void cmbEmployeeIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Employee employee = EmployeeModel.get(String.valueOf(cmbEmployeeId.getValue()));
        txtName.setText(employee.getEmployee_Fname() + " " + employee.getEmployee_Lname());
        txtAttendance.setText(String.valueOf(attendanceCount()));
        employeeId = String.valueOf(cmbEmployeeId.getValue());
    }

    public void setEmployeeId() {
        ArrayList<String> allId = null;
        try {
            allId = EmployeeModel.getAllId();
            cmbEmployeeId.getItems().addAll(allId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAllId(){
        try {
            ArrayList<String> ids = SalaryModel.getAllId();
            for (int i = 0; i < ids.size(); i++) {
                setData(ids.get(i));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData(String id){
        SalaryTm salaryTm = new SalaryTm();

        try {
            Salary salary = SalaryModel.getData(id);

            System.out.println(salary.getEmployee_id());
            salaryTm.setEmployee_id(salary.getEmployee_id());
            salaryTm.setSalary_id(salary.getSalary_id());
            salaryTm.setDate(salary.getDate());
            salaryTm.setSalary(String.valueOf(salary.getSalary()));
            salaryTm.setEmployee_attandance_count(String.valueOf(salary.getEmployee_attandance_count()));
            list.add(salaryTm);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setEmployeeId();
        loadDataTable();
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        ColSalaryId.setCellValueFactory(new PropertyValueFactory<>("salary_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAttendance.setCellValueFactory(new PropertyValueFactory<>("employee_attandance_count"));
        tblSalary.setItems(list);
    }

    public int attendanceCount() {
        String employeeCount = null;
        try {
            employeeCount = EmployeeAttendanceModel.getEmloyeeCount(String.valueOf(cmbEmployeeId.getValue()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return Integer.parseInt(employeeCount);
    }

    public String id() {
        try {
            ArrayList<String> allId = SalaryModel.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
            }
            try {
                String[] split = lastId.split("SA00");
                int index = Integer.parseInt(split[1]);
                index++;
                return "SA00" + index;
            } catch (Exception e) {
                return "SA001";
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
