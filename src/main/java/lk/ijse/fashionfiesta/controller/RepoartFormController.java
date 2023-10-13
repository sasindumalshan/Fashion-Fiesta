package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lk.ijse.fashionfiesta.db.DBConnection;
import lk.ijse.fashionfiesta.model.CustomerOrderModel;
import lk.ijse.fashionfiesta.model.EmployeeAttendanceModel;
import lk.ijse.fashionfiesta.model.SalaryModel;
import lk.ijse.fashionfiesta.model.SupplierOrderModel;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;
import lk.ijse.fashionfiesta.utill.Navigation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

public class RepoartFormController implements Initializable {
    public Pane pane;
    public AreaChart chart;
    public Label txtEmpAtt;
    public Label txtSuppOrder;
    public Label txtTodayCrustOrder;
    public Label txtSalary;
    private void setCount() {
        try {
            txtSalary.setText(SalaryModel.getCount());
            txtEmpAtt.setText("+ 0"+EmployeeAttendanceModel.getEmployeeCount());
            txtSuppOrder.setText("+ 0"+SupplierOrderModel.getSupOrderCount());
            txtTodayCrustOrder.setText("+ 0"+CustomerOrderModel.getCustOrder());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public void btnEmployeeAttendanceOnAction(ActionEvent actionEvent) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\GCV\\Downloads\\fashionfiestazzzzzzz\\fashionfiesta\\src\\main\\resources\\Report\\EmployeeAttendance.jrxml");
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(Arrays.asList(new Object()));

            HashMap hm = new HashMap();


            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, connection);
            JasperViewer.viewReport(jasperPrint, false);
//
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSupplierOrderOnAction(ActionEvent actionEvent) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\GCV\\Desktop\\fashionfiestaNethmiThathsaraniGDSE66\\fashionfiesta2\\src\\main\\resources\\Report\\SupplierOrder.jrxml");
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(Arrays.asList(new Object()));

            HashMap hm = new HashMap();


            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, connection);
            JasperViewer.viewReport(jasperPrint, false);
//
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCustomerOrderOnAction(ActionEvent actionEvent) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\GCV\\Desktop\\fashionfiestaNethmiThathsaraniGDSE66\\fashionfiesta2\\src\\main\\resources\\Report\\customer.jrxml");
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(Arrays.asList(new Object()));

            HashMap hm = new HashMap();


            Connection connection = DBConnection.getInstance().getConnection();                                   //C:\Users\GCV\Desktop\fashionfiestaNethmiThathsaraniGDSE66\fashionfiesta2\.idea
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, connection);
            JasperViewer.viewReport(jasperPrint, false);
//
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSalaryOnAction(ActionEvent actionEvent) {
        try {
            Navigation.popupNavigation("SalaryForm.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setData() {
        XYChart.Series series=new XYChart.Series();
        series.setName("Order");
        try {
            /*ArrayList<String> all = CustomerOrderModel.getAll();

            ArrayList<CustomerOrder> orders=new ArrayList<>();
            for (int i = 0; i < all.size(); i++) {
                CustomerOrder customerOrder = CustomerOrderModel.get(all.get(i));
                orders.add(customerOrder);
            }*/
            String dateNow = DateTimeUtil.dateNow();
            String[] date = dateNow.split("-");
            int days = DateTimeUtil.getDays(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
            String count=null;
            for (int i = 0; i < days; i++) {
                count= CustomerOrderModel.getCustomerOrder(i);
                int ii=i;
                ii++;
                series.getData().add(new XYChart.Data(String.valueOf(ii), Double.parseDouble(count)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        chart.getData().add(series);
    }

    private void setDataSupplierOrder() {
        XYChart.Series series=new XYChart.Series();
        series.setName("Supplier Order");
        try {

            String dateNow = DateTimeUtil.dateNow();
            String[] date = dateNow.split("-");
            int days = DateTimeUtil.getDays(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
            String count=null;
            for (int i = 0; i < days; i++) {
                count= SupplierOrderModel.getCustomerOrder(i);
                int ii=i;
                ii++;
                series.getData().add(new XYChart.Data(String.valueOf(ii), Double.parseDouble(count)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        chart.getData().add(series);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
        setDataSupplierOrder();
        setCount();
    }


}
