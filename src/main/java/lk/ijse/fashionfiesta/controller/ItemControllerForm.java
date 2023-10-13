package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.fashionfiesta.dto.Stock;
import lk.ijse.fashionfiesta.dto.Supplier;
import lk.ijse.fashionfiesta.model.StockModel;
import lk.ijse.fashionfiesta.model.SupplierModel;
import lk.ijse.fashionfiesta.tm.StockTm;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ItemControllerForm implements Initializable {

    protected static StockTm stockTm;
    private static ItemControllerForm controller;
    public TableColumn tblItemName;
    public TableColumn tblItemPrice;
    public TableColumn tblItemModelColor;
    public TableColumn tblCatagory;
    public Text txtStock;
    public JFXTextField txtName;
    public JFXTextField txtColor;
    public JFXTextField txtCategory;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;
    public JFXButton add;
    public JFXButton remove;
    public JFXButton update;

    ObservableList<StockTm> list = FXCollections.observableArrayList();
    @FXML
    private TableView<StockTm> EmployeeTbl;
    @FXML
    private TableColumn tblId;
    @FXML
    private JFXTextField txtSearch;
    private static String itemId;

    public ItemControllerForm() {
        controller = this;
    }

    public static ItemControllerForm getInstance() {
        return controller;
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        try {

            Stock stock = new Stock();
            stock.setItem_id(currentId);
            stock.setItem_name(txtName.getText());
            stock.setPrice(Double.parseDouble(txtPrice.getText()));
            stock.setCategory(txtCategory.getText());
            stock.setModel_color(txtColor.getText());
            stock.setQuantity(Integer.parseInt(txtQty.getText()));


            if (StockModel.update(stock)){
                loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Updated").show();
                clearText();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static String currentId;
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ? .. .", ButtonType.OK, ButtonType.NO);
            alert.showAndWait();
            if (ButtonType.OK.equals(alert.getResult())) {
                if (StockModel.remove(currentId)) {
                    loadDataTable();
                    clearText();
                    new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Updated").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void clearText() {
        txtName.clear();
        txtQty.clear();
        txtColor.clear();
        txtCategory.clear();
        txtPrice.clear();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Stock stock = new Stock();
        stock.setItem_id(id());
        stock.setItem_name(txtName.getText());
        stock.setQuantity(Integer.parseInt(txtQty.getText()));
        stock.setPrice(Double.parseDouble(txtPrice.getText()));
        stock.setModel_color(txtColor.getText());
        stock.setCategory(txtCategory.getText());

        try {
            boolean add = StockModel.addStock(stock);
            if (add){
                ItemControllerForm.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error Added").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private String id() {

        try {
            ArrayList<String> allId = StockModel.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("I00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                System.out.println(idIndex);
                return "I00" + idIndex;
            } catch (Exception e) {
                return "I001";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;

    }

    private void getAllIds() {
        try {
            ArrayList<String> list = StockModel.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setStockData(list.get(i));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setStockData(String id) {

        try {
            Stock stock = StockModel.get(id);
            StockTm tm = new StockTm();
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
        EmployeeTbl.getItems().clear();
        getAllIds();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAllIds();
        tblId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblItemModelColor.setCellValueFactory(new PropertyValueFactory<>("model_color"));
        tblCatagory.setCellValueFactory(new PropertyValueFactory<>("category"));
        EmployeeTbl.setItems(list);
        setStock();
    }

    public void tblMouseClick(MouseEvent mouseEvent) {
        try {
            stockTm = EmployeeTbl.getSelectionModel().getSelectedItem();
            Stock stock = StockModel.get(stockTm.getItemId());
            currentId=stockTm.getItemId();
            txtName.setText(stockTm.getItemName());
            txtQty.setText(String.valueOf(stock.getQuantity()));
            txtColor.setText(stockTm.getModel_color());
            txtCategory.setText(stockTm.getCategory());
            txtPrice.setText(stockTm.getPrice());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void searchKeyReleased(KeyEvent event) {
        EmployeeTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids = StockModel.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setStockData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setStock() {
        try {
            txtStock.setText(StockModel.getStock());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void nameKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex( (JFXTextField) txtName,txtName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill: black",add,update,remove);

    }

    public void colorKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex( (JFXTextField) txtColor,txtColor.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill: black",add,update,remove);

    }

    public void categaryKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex( (JFXTextField) txtCategory,txtCategory.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill: black",add,update,remove);

    }

    public void priceKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex((JFXTextField) txtPrice,txtPrice.getText(),"([+-]?[0-9]+(?:\\.[0-9]*)?)","-fx-text-fill: black",add,update,remove);

    }

    public void qtyKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex( (JFXTextField) txtQty,txtQty.getText(),"^0*(\\d{1,9})$","-fx-text-fill: black",add,update,remove);
    }
}

