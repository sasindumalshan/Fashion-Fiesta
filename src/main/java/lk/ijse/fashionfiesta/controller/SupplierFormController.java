package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.text.Text;
import lk.ijse.fashionfiesta.dto.Supplier;
import lk.ijse.fashionfiesta.model.SupplierModel;
import lk.ijse.fashionfiesta.model.SupplierOrderModel;
import lk.ijse.fashionfiesta.tm.SupplierTm;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    private static SupplierFormController controller;
    private static SupplierTm supplierTm;
    private static String currentId;
    public TableColumn supplierCol;
    public TableColumn tblId;
    public Text txtSupplier;
    public Text txtSupOrder;
    public EmployeeRegisterFormController supplierRegisterFormController;
    public JFXTextField txtLastName1;
    public JFXTextField txtFirstName1;
    public JFXTextField txtContact1;
    public JFXTextField txtStreet1;
    public JFXTextField txtCity1;
    public JFXTextField txtLane1;
    public JFXButton add;
    public JFXButton delete;
    public JFXButton update;
    ObservableList<SupplierTm> list = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private ToggleGroup supplier;
    @FXML
    private TableView<SupplierTm> EmployeeTbl;
    @FXML
    private TableColumn<?, ?> tblFirstName;
    @FXML
    private TableColumn<?, ?> tblLastName;
    @FXML
    private TableColumn<?, ?> tblCity;
    @FXML
    private TableColumn<?, ?> tblContact;

    public SupplierFormController() {
        controller = this;
    }

    public static SupplierFormController getInstance() {
        return controller;
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        Supplier supplier = new Supplier();
        supplier.setSupplier_id(id());
        supplier.setSupplier_Fname(txtFirstName1.getText());
        supplier.setSupplier_Lname(txtLastName1.getText());
        supplier.setStreet(txtStreet1.getText());
        supplier.setCity(txtCity1.getText());
        supplier.setLane(txtLane1.getText());
        supplier.setContact(txtContact1.getText());

        try {
            boolean add = SupplierModel.addSupplier(supplier);
            if (add) {
                SupplierFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Added").show();
                clearText();
                loadDataTable();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Error Added").show();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private String id() {
        try {
            ArrayList<String> allId = SupplierModel.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("S00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                System.out.println(idIndex);
                return "S00" + idIndex;
            } catch (Exception e) {
                return "S001";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    private void getAllIds() {
        try {
            ArrayList<String> list = SupplierModel.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setSupplierData(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setSupplierData(String id) {
        try {
            Supplier supplier = SupplierModel.get(id);
            SupplierTm tm = new SupplierTm();

            tm.setSup_Id(supplier.getSupplier_id());
            tm.setFistName(supplier.getSupplier_Fname());
            tm.setLastName(supplier.getSupplier_Lname());
            tm.setStreet(supplier.getStreet());
            tm.setCity(supplier.getCity());
            tm.setLane(supplier.getLane());
            tm.setContact(supplier.getContact());
            System.out.println(supplier.getSupplier_id());
            list.add(tm);
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
        tblId.setCellValueFactory(new PropertyValueFactory<>("Sup_Id"));
        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("fistName"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tblContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        EmployeeTbl.setItems(list);
        setSupplier();
        setSupOrder();
    }

    public void tblMouseClick(MouseEvent mouseEvent) {
        supplierTm = EmployeeTbl.getSelectionModel().getSelectedItem();
        currentId = supplierTm.getSup_Id();
        txtFirstName1.setText(supplierTm.getFistName());
        txtLastName1.setText(supplierTm.getLastName());
        txtStreet1.setText(supplierTm.getStreet());
        txtCity1.setText(supplierTm.getCity());
        txtLane1.setText(supplierTm.getLane());
        txtContact1.setText(supplierTm.getContact());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            boolean update = SupplierModel.update(new Supplier(
                    currentId,
                    txtFirstName1.getText(),
                    txtLastName1.getText(),
                    txtStreet1.getText(),
                    txtCity1.getText(),
                    txtLane1.getText(),
                    txtContact1.getText()
            ));
            if (update) {
                SupplierFormController.getInstance().loadDataTable();
                new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Updated").show();
                loadDataTable();
                clearText();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearText() {
        txtFirstName1.clear();
        txtLastName1.clear();
        txtStreet1.clear();
        txtCity1.clear();
        txtLane1.clear();
        txtContact1.clear();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ? .. .", ButtonType.OK, ButtonType.NO);
            alert.showAndWait();
            if (ButtonType.OK.equals(alert.getResult())) {
                if (SupplierModel.remove(currentId)) {
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

    public void btnNewOrdersOnAction(ActionEvent actionEvent) {
        try {
            Navigation.popupNavigation("SupplierOrderForm.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchKeyReleased(KeyEvent event) {
        EmployeeTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids = SupplierModel.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setSupplierData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setSupplier() {
        try {
            txtSupplier.setText(SupplierModel.getSupplier());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setSupOrder() {
        try {
            txtSupOrder.setText(SupplierOrderModel.getSupplierOrder());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void lastNameKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(txtLastName1, txtLastName1.getText(), "[a-zA-Z-']+[ a-zA-Z-']", "-fx-text-fill", add, update, delete);
    }

    public void firstNameKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(txtFirstName1, txtFirstName1.getText(), "[a-zA-Z-']+[ a-zA-Z-']", "-fx-text-fill", add, update, delete);
    }

    public void streetKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(txtStreet1, txtStreet1.getText(), "[a-zA-Z-']+[ a-zA-Z-']", "-fx-text-fill", add, update, delete);

    }

    public void cityKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(txtCity1, txtCity1.getText(), "[a-zA-Z-']+[ a-zA-Z-']", "-fx-text-fill", add, update, delete);
    }

    public void laneKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(txtLane1, txtLane1.getText(), "[a-zA-Z-']+[ a-zA-Z-']", "-fx-text-fill", add, update, delete);
    }
}

