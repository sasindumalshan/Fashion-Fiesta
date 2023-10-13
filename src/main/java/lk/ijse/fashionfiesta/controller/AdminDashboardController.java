package lk.ijse.fashionfiesta.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lk.ijse.fashionfiesta.dto.CustomerOrder;
import lk.ijse.fashionfiesta.model.CustomerOrderModel;
import lk.ijse.fashionfiesta.model.EmployeeAttendanceModel;
import lk.ijse.fashionfiesta.model.SupplierOrderModel;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;
import lk.ijse.fashionfiesta.utill.Navigation;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable{

    public Pane pane;
    public Text m;
    public Text h;
    public Text dat;


    public void Employee(javafx.scene.input.MouseEvent mouseEvent) {
       /* try {
            Navigation.switchNavigation("CustomerForm.fxml",mouseEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) {
        Navigation.onTheTopNavigation(pane,"EmployeeRegisterForm.fxml");
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) {
        Navigation.onTheTopNavigation(pane,"SupplierForm.fxml");
    }

    public void stockOnAction(ActionEvent actionEvent) {
            Navigation.onTheTopNavigation(pane,"StockForm.fxml");

    }

    public void btnReportOnAction(ActionEvent actionEvent) {
        Navigation.onTheTopNavigation(pane,"RepoartForm.fxml");
    }

    public void homeOnAction(ActionEvent actionEvent) {
        pane.getChildren().clear();
    }

    public void logOut(MouseEvent mouseEvent) {
        try {
            Navigation.switchNavigation("LoginForm.fxml",mouseEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDate();
        setTime();
    }
    private void setDate() {
        SimpleDateFormat format=new SimpleDateFormat("EEE, MMM d");
        dat.setText(  format.format(new Date()));
    }

    private void setTime() {
        Thread thread=new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true){
                    Thread.sleep(1000);
                    SimpleDateFormat format=new SimpleDateFormat("HH-mm");
                    String format1 = format.format(new Date());
                    //System.out.println(format1);
                    String[] split = format1.split("-");
                    h.setText(split[0]);
                    m.setText(split[1]);


                }
            }
        });
        thread.start();

    }
}
