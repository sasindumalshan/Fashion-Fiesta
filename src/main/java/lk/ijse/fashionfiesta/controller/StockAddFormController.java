package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
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
import lk.ijse.fashionfiesta.dto.Customer;
import lk.ijse.fashionfiesta.dto.Stock;
import lk.ijse.fashionfiesta.model.CustomerModel;
import lk.ijse.fashionfiesta.model.StockModel;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StockAddFormController implements Initializable {
    public JFXButton btnAdd;
    @FXML
    private JFXTextField txtItemId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtColor;

    @FXML
    private JFXTextField txtCategory;

    @FXML
    void addOnAction(ActionEvent event) {
        Stock stock = new Stock();
        stock.setItem_id(txtItemId.getText());
        stock.setItem_name(txtName.getText());
        stock.setQuantity(0);
        stock.setPrice(Double.parseDouble(txtPrice.getText()));
        stock.setModel_color(txtColor.getText());
        stock.setCategory(txtCategory.getText());

        try {
            boolean add = StockModel.addStock(stock);
            if (add){
                ItemControllerForm.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").show();
                Navigation.close(event);

            }else {
                new Alert(Alert.AlertType.CONFIRMATION,"Error Added").show();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void nameKeyReleased(KeyEvent keyEvent) {
        System.out.println("laneKeyReleased");
        RegexUtil.regex(btnAdd,txtName,txtName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

   /* public void priceKeyReleased(KeyEvent keyEvent) {
        System.out.println("priceKeyReleased");
        RegexUtil.regex(btnAdd,txtPrice,txtPrice.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }*/

    public void colorKeyReleased(KeyEvent keyEvent) {
        System.out.println("colorKeyReleased");
        RegexUtil.regex(btnAdd,txtColor,txtColor.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void categaryKeyReleased(KeyEvent keyEvent) {
        System.out.println("categaryKeyReleased");
        RegexUtil.regex(btnAdd,txtCategory,txtCategory.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill");
    }

    public void priceKeyReleased(KeyEvent keyEvent) {
    }
}
