package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.fashionfiesta.dto.Stock;
import lk.ijse.fashionfiesta.model.StockModel;
import lk.ijse.fashionfiesta.tm.StockTm;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StockUpdateFormController implements Initializable {

    public static StockTm stockTm;
    public JFXTextField txtItemName;
    public JFXTextField txtQty;
    public JFXTextField txtPrice;
    public JFXTextField txtModelColor;
    public JFXTextField txtCategory;
    public JFXTextField txtContact;
    public JFXButton btnUpdate;


    public static void getData(StockTm stockTm) {
        StockUpdateFormController.stockTm = stockTm;
        System.out.println(StockUpdateFormController.stockTm.getPrice());
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {

        try {

            Stock stock = new Stock();
            stock.setItem_id(stockTm.getItemId());
            stock.setItem_name(txtItemName.getText());
            stock.setPrice(Double.parseDouble(txtPrice.getText()));
            stock.setCategory(txtCategory.getText());
            stock.setModel_color(txtModelColor.getText());
            stock.setQuantity(Integer.parseInt(txtQty.getText()));
            boolean update = StockModel.update(stock);
            if (update){
                ItemControllerForm.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"SuccessFully Updated").show();
                Navigation.close(actionEvent);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static ItemControllerForm getInstance() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setData() throws ClassNotFoundException {
        try {
            Stock stock = StockModel.get(stockTm.getItemId());
            txtItemName.setText(stockTm.getItemName());
            txtQty.setText(String.valueOf(stock.getQuantity()));
            txtModelColor.setText(stockTm.getModel_color());
            txtCategory.setText(stockTm.getCategory());
            txtPrice.setText(stockTm.getPrice());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void itemNameKeyReleased(KeyEvent keyEvent) {
        System.out.println("itemNameKeyReleased");
        RegexUtil.regex( (JFXTextField) txtItemName,txtItemName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",btnUpdate);
    }

   /* public void qtyKeyReleased(KeyEvent keyEvent) {
        System.out.println("qtyKeyReleased");
        RegexUtil.regex(btnUpdate, (JFXTextField) txtItemName,txtItemName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");

    }*/

    public void modelColorKeyReleased(KeyEvent keyEvent) {
        System.out.println("modelColorKeyReleased");
        RegexUtil.regex( (JFXTextField) txtModelColor,txtModelColor.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",btnUpdate);
    }

    public void categaryKeyReleased(KeyEvent keyEvent) {
        System.out.println("modelColorKeyReleased");
        RegexUtil.regex(btnUpdate, (JFXTextField) txtCategory,txtCategory.getText(),"[a-zA-Z-']+[ a-zA-Z-']");
    }

    public void qtyKeyReleased(KeyEvent keyEvent) {
        System.out.println("modelColorKeyReleased");
        RegexUtil.regex(btnUpdate, (JFXTextField) txtCategory,txtCategory.getText(),"^0*(\\d{1,9})$");
    }
}


