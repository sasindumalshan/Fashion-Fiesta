package lk.ijse.fashionfiesta.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.fashionfiesta.dto.Customer;
import lk.ijse.fashionfiesta.dto.CustomerOrder;
import lk.ijse.fashionfiesta.model.CustomerModel;
import lk.ijse.fashionfiesta.model.CustomerOrderModel;
import lk.ijse.fashionfiesta.model.EmployeeModel;
import lk.ijse.fashionfiesta.model.StockModel;
import lk.ijse.fashionfiesta.tm.CustomerTm;
import lk.ijse.fashionfiesta.utill.Navigation;
import lk.ijse.fashionfiesta.utill.RegexUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    private static CustomerFormController controller;
    private static String customer_id;
    public Text txtCustomer;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtContactNumber;
    public JFXComboBox txtRole;
    public JFXTextField txtStreet;
    public JFXTextField txtCity;
    public JFXTextField txtLane;
    public Label txtCustomerUk;
    public JFXButton update;
    public JFXButton remove;
    public JFXButton add;
    public Label txtCustomer1;
    ObservableList<CustomerTm> list = FXCollections.observableArrayList();
    private CustomerTm customerTm;
    @FXML
    private TableView CustomerTbl;
    @FXML
    private TableColumn tblCustId;
    @FXML
    private TableColumn tblFirstName;
    @FXML
    private TableColumn tblLastName;
    @FXML
    private TableColumn tblContactNumber;
    @FXML
    private TableColumn tblCity;
    @FXML
    private JFXTextField txtSearch;

    public CustomerFormController() {
        controller = this;
    }

    public static CustomerFormController getInstance() {
        return controller;
    }

    private String id() {
        try {
            ArrayList<String> allId = CustomerModel.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                //System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("C00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                // System.out.println(idIndex);
                return "C00" + idIndex;
            } catch (Exception e) {
                return "C001";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        //     Navigation.popupNavigation("Cashier/CustomerAddForm.fxml");
        Customer customer = new Customer();
        customer.setCustomer_Id(id());
        customer.setFirst_name(txtFirstName.getText());
        customer.setLast_name(txtLastName.getText());
        customer.setStreet(txtStreet.getText());
        customer.setCity(txtCity.getText());
        customer.setLane(txtLane.getText());
        customer.setContact_number(txtContactNumber.getText());

        try {
            if (CustomerModel.addCustomer(customer)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added").show();
                clear();
                loadDataTable();

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Error Added").show();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {

//        CustomerUpdateFormController.getData(customerTm);
//        if (customerTm == null) {
//            new Alert(Alert.AlertType.WARNING, "Something Warning").show();
//        } else {
//            Navigation.popupNavigation("Cashier/CustomerUpdateForm.fxml");
//        }
        Customer customer = new Customer();
        customer.setCustomer_Id(customer_id);
        customer.setFirst_name(txtFirstName.getText());
        customer.setLast_name(txtLastName.getText());
        customer.setStreet(txtStreet.getText());
        customer.setCity(txtCity.getText());
        customer.setLane(txtLane.getText());
        customer.setContact_number(txtContactNumber.getText());

        try {
            boolean update = CustomerModel.update(
                    customer);
            if (update) {
                new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Updated").show();
                clear();
                loadDataTable();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
      /*  Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please Select the Row ", ButtonType.YES, ButtonType.NO);
        alert.show();

        if (alert.equals(ButtonType.YES)) {
            try {
                boolean delete = CustomerModel.remove(customer_id);
                if (delete) {
                    clear();
                    loadDataTable();
                    new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Deleted").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }*/

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ? .. .", ButtonType.OK, ButtonType.NO);
            alert.showAndWait();

            if (ButtonType.OK.equals(alert.getResult())) {
                System.out.println("in the if >>> ");

                if (CustomerModel.remove(customer_id)) {
                    loadDataTable();
                    clear();
                    new Alert(Alert.AlertType.CONFIRMATION, "SuccessFully Updated").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        }


    private void clear() {
        txtFirstName.clear();
        txtLastName.clear();
        txtStreet.clear();
        txtCity.clear();
        txtLane.clear();
        txtRole.getItems().clear();
        txtContactNumber.clear();
    }

    public void loadDataTable() {
        list.clear();
        CustomerTbl.getItems().clear();
        getAllIds();
    }

    private void getAllIds() {
        try {
            ArrayList<String> list = CustomerModel.getAllId();
            for (int i = 0; i < list.size(); i++) {
                setCustomerData(list.get(i));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCustomerData(String id) {
        try {
            Customer customer = CustomerModel.get(id);
            CustomerTm tm = new CustomerTm();
            tm.setCust_id(customer.getCustomer_Id());
            tm.setFistName(customer.getFirst_name());
            tm.setLastName(customer.getLast_name());
            tm.setCity(customer.getCity());
            tm.setContact_number(customer.getContact_number());
            tm.setLane(customer.getLane());
            tm.setStreet(customer.getStreet());
            list.add(tm);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void tblMouseClick(MouseEvent mouseEvent) {
        customerTm = (CustomerTm) CustomerTbl.getSelectionModel().getSelectedItem();
        txtFirstName.setText(customerTm.getFistName());
        txtLastName.setText(customerTm.getLastName());
        txtCity.setText(customerTm.getCity());
        txtContactNumber.setText(customerTm.getContact_number());
        txtLane.setText(customerTm.getLane());
        txtStreet.setText(customerTm.getStreet());
        customer_id=customerTm.getCust_id();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllIds();
        tblCustId.setCellValueFactory(new PropertyValueFactory<>("Cust_id"));
        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("fistName"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblContactNumber.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
        tblCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        CustomerTbl.setItems(list);
        setCustomer();
    }

    @FXML
    void searchKeyReleased(KeyEvent event) {
        CustomerTbl.getItems().clear();
        list.clear();
        try {
            ArrayList<String> ids = CustomerModel.getSearchIds(txtSearch.getText());
            for (int i = 0; i < ids.size(); i++) {
                setCustomerData(ids.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setCustomer() {
        try {
            txtCustomer1.setText(CustomerModel.getCustomer()+" +");
            txtCustomerUk.setText(CustomerOrderModel.getCustOrder()+" + ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void firstNameKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(txtFirstName,txtFirstName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);

    }

    public void lastNameKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(txtLastName,txtLastName.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);

    }

    public void contactNumberKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(  txtContactNumber,txtContactNumber.getText(),"^(?:7|0|(?:\\+94))[0-9]{9,10}$","-fx-text-fill: black",add,remove,update);

    }

    public void streetKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(txtStreet,txtStreet.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);

    }

    public void cityKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(txtCity,txtCity.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill",add,remove,update);

    }

    public void laneKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex( (JFXTextField) txtLane,txtLane.getText(),"[a-zA-Z-']+[ a-zA-Z-']","-fx-text-fill: black",add,remove,update);

    }
}
