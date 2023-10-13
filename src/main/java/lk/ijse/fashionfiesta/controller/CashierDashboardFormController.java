package lk.ijse.fashionfiesta.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lk.ijse.fashionfiesta.model.CustomerOrderModel;
import lk.ijse.fashionfiesta.model.EmployeeModel;
import lk.ijse.fashionfiesta.model.SupplierOrderModel;
import lk.ijse.fashionfiesta.tm.StockTm;
import lk.ijse.fashionfiesta.utill.Navigation;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static lk.ijse.fashionfiesta.controller.StockUpdateFormController.stockTm;

public class CashierDashboardFormController implements Initializable {

    public Label txtEmployee;
    public Label txtCustOrder;
    public Pane pane;
    public Text h;
    public Text m;
    public Text dae;

    @FXML
    void btnAttendanceOnAction(ActionEvent event) {
        Navigation.onTheTopNavigation(pane,"Cashier/EmployeeAttendanceForm.fxml");
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        Navigation.onTheTopNavigation(pane,"Cashier/CustomerForm.fxml");
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {
        Navigation.onTheTopNavigation(pane,"Cashier/OrderForm.fxml");

    }

    @FXML
    void btnStockOnAction(ActionEvent event) {
        //CashierDashboardFormController.getData(stockTm);
        Navigation.onTheTopNavigation(pane,"Cashier/CashierStockForm.fxml");
    }

    private static void getData(StockTm stockTm) {
    }

    public void setEmpCount() {
   /*     try {
            txtEmployee.setText(EmployeeModel.getEmpCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
*/
    }

    public void setCustOrder() {
        try {
            txtCustOrder.setText(CustomerOrderModel.getCustOrder());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setEmpCount();
        //setCustOrder();

        setTime();
        setDate();
    }

    private void setDate() {
        SimpleDateFormat format=new SimpleDateFormat("EEE, MMM d");
        dae.setText(  format.format(new Date()));
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

    public void logout(MouseEvent mouseEvent) {
        try {
            Navigation.switchNavigation("LoginForm.fxml",mouseEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void homeOnAction(ActionEvent actionEvent) {
        pane.getChildren().clear();
    }
}
