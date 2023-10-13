package lk.ijse.fashionfiesta.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Supplier extends RecursiveTreeObject<Employee> {
//    private final SimpleStringProperty employee_id = new SimpleStringProperty();
//    private final SimpleStringProperty employee_Fname = new SimpleStringProperty();
//    private final SimpleStringProperty employee_Lname = new SimpleStringProperty();
//    private final SimpleStringProperty street = new SimpleStringProperty();
//    private final SimpleStringProperty city = new SimpleStringProperty();
//    private final SimpleStringProperty lane = new SimpleStringProperty();
//    private final SimpleStringProperty role = new SimpleStringProperty();
//    private final SimpleStringProperty contact = new SimpleStringProperty();


    //    public SimpleStringProperty getEmployee_id() {
//        return employee_id;
//    }
//
//    public SimpleStringProperty getEmployee_Fname() {
//        return employee_Fname;
//    }
//
//    public SimpleStringProperty getEmployee_Lname() {
//        return employee_Lname;
//    }
//
//    public SimpleStringProperty getStreet() {
//        return street;
//    }
//
//    public SimpleStringProperty getCity() {
//        return city;
//    }
//
//    public SimpleStringProperty getLane() {
//        return lane;
//    }
//
//    public SimpleStringProperty getRole() {
//        return role;
//    }
//
//    public SimpleStringProperty getContact() {
//        return contact;
//    }
//
//    public Employee() {
//    }
    private String supplier_id;
    private String supplier_Fname;
    private String supplier_Lname;
    private String street;
    private String city;
    private String lane;
    private String contact;

    public Supplier(String supplier_id, String supplier_Fname, String supplier_Lname, String street, String city, String lane, String contact) {
        this.supplier_id = supplier_id;
        this.supplier_Fname = supplier_Fname;
        this.supplier_Lname = supplier_Lname;
        this.street = street;
        this.city = city;
        this.lane = lane;
        this.contact = contact;
    }

    public Supplier() {
    }

    public String getSupplier_id() { return supplier_id;}

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_Fname() {
        return supplier_Fname;
    }

    public void setSupplier_Fname(String supplier_Fname) {
        this.supplier_Fname = supplier_Fname;
    }

    public String getSupplier_Lname() {
        return supplier_Lname;
    }

    public void setSupplier_Lname(String supplier_Lname) {
        this.supplier_Lname = supplier_Lname;
    }

    public String getStreet() { return street;}

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
