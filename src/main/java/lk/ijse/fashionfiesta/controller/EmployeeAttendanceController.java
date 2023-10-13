package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.fashionfiesta.dto.EmployeeAttendanceDto;
import lk.ijse.fashionfiesta.model.EmployeeAttendanceModel;
import lk.ijse.fashionfiesta.model.EmployeeModel;
import lk.ijse.fashionfiesta.tm.CustomerTm;
import lk.ijse.fashionfiesta.tm.EmployeeAttendanceTm;
import lk.ijse.fashionfiesta.utill.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeAttendanceController implements Initializable {

    public TableView EmployeeAttendanceTbl;
    public TableColumn tblId;
    public JFXTextField txtid;
    public TableColumn tblFirstNameCol;
    public TableColumn tblLastNameCol;
    public TableColumn tblTimeCol;
    public TableColumn tblDateCol;
    public JFXTextField txtSearch;

    ObservableList<EmployeeAttendanceTm> list= FXCollections.observableArrayList();

    public void btnCustomerOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("CustomerForm.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnOrdersOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("OrderForm.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnStockOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("CashierStockForm.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("CashierDashboardForm.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tblMouseClick(MouseEvent mouseEvent) {
    }

    public void txtIdOnKeyRelease(KeyEvent keyEvent) {

    }
    public void setAllId(){
        try {
            ArrayList<String> ids = EmployeeAttendanceModel.getAllId();
            for (int i = 0; i < ids.size(); i++) {
                setAttendanceData(ids.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setAttendanceData(String id){
        try {
            EmployeeAttendanceDto attendanceDto = EmployeeAttendanceModel.getData(id);
            EmployeeAttendanceTm attendanceTm = new EmployeeAttendanceTm();
            attendanceTm.setEmployee_id(attendanceDto.getEmployee_id());
            attendanceTm.setFirst_Name(attendanceDto.getFirst_Name());
            attendanceTm.setLast_Name(attendanceDto.getLast_Name());
            attendanceTm.setTime(attendanceDto.getTime());
            attendanceTm.setDate(attendanceDto.getDate());
            list.addAll(attendanceTm);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataTable();
        tblId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        tblFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("first_Name"));
        tblLastNameCol.setCellValueFactory(new PropertyValueFactory<>("last_Name"));
        tblTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        tblDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        EmployeeAttendanceTbl.setItems(list);
    }
    public void loadDataTable() {
        list.clear();
        EmployeeAttendanceTbl.getItems().clear();
        setAllId();
    }



    public void searchKeyReleased(KeyEvent keyEvent) {
        EmployeeAttendanceTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids= EmployeeAttendanceModel.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setAttendanceData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void enterOnAction(ActionEvent actionEvent) {
        try {
            if (EmployeeAttendanceModel.isEmployeeExist(txtid.getText())){
                if (EmployeeAttendanceModel.isEmployeeTodayExist(txtid.getText())){
                    if (EmployeeAttendanceModel.add(txtid.getText())){
                        txtid.setText("");
                        loadDataTable();
                        new Alert(Alert.AlertType.CONFIRMATION,"Added").show();
                    }
                }else {
                    txtid.setText("");
                    new Alert(Alert.AlertType.ERROR,"Error").show();
                }
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


}

