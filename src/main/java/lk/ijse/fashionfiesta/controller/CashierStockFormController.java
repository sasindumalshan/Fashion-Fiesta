package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.fashionfiesta.dto.Stock;
import lk.ijse.fashionfiesta.model.SalaryModel;
import lk.ijse.fashionfiesta.model.StockModel;
import lk.ijse.fashionfiesta.tm.StockTm;
import lk.ijse.fashionfiesta.utill.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class CashierStockFormController   implements Initializable {



    public TableView tblStockView;
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colPrice;
    public TableColumn colModelColour;
    public TableColumn colCategory;
    public static CashierStockFormController controller;
    public JFXTextField txtSearch;

    ObservableList<StockTm> list = FXCollections.observableArrayList();


    public CashierStockFormController(){
        controller = this;
    }
    public static CashierStockFormController getInstance(){
        return controller;
    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("CashierDashboardForm.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void btnAttendanceOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("EmployeeAttendanceForm.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void getAllIds() {
        try {
            ArrayList<String> list= StockModel.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setCashierStockData(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCashierStockData(String id) {
        try {
            Stock stock = StockModel.get(id);
            StockTm tm=new StockTm();
            tm.setItemId(stock.getItem_id());
            tm.setItemName(stock.getItem_name());
            tm.setPrice(String.valueOf(stock.getPrice()));
            tm.setModel_color(stock.getModel_color());
            tm.setCategory(stock.getCategory());
            list.add(tm);
            System.out.println(tm.getItemId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadDataTable() {
        list.clear();
        tblStockView.getItems().clear();
        getAllIds();

    }
    public static void getData(StockTm stockTm) {
        //ItemControllerForm.stockTm = stockTm;
//        System.out.println(CarInventoryFormController.carTm.getModelColor());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataTable();
        //getData(stockTm);
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colModelColour.setCellValueFactory(new PropertyValueFactory<>("model_color"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblStockView.setItems(list);

    }

    public void searchKeyReleased(KeyEvent keyEvent) {
        tblStockView.getItems().clear();
            list.clear();
            try {
                ArrayList<String> ids= StockModel.getSearchIds(txtSearch.getText());
                for (int i = 0; i < ids.size(); i++) {
                    setCashierStockData(ids.get(i));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

