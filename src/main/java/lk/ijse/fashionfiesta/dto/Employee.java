package lk.ijse.fashionfiesta.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import lk.ijse.fashionfiesta.tm.EmployeeTm;

public class Employee extends RecursiveTreeObject<Employee> {
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
    private String employee_id;
    private String employee_Fname;
    private String employee_Lname;
    private String street;
    private String city;
    private String lane;
    private String role;
    private String contact;

    public Employee(String employee_id, String employee_Fname, String employee_Lname, String street, String city, String lane, String role, String contact) {
        this.employee_id = employee_id;
        this.employee_Fname = employee_Fname;
        this.employee_Lname = employee_Lname;
        this.street = street;
        this.city = city;
        this.lane = lane;
        this.role = role;
        this.contact = contact;
    }

    public Employee() {
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_Fname() {
        return employee_Fname;
    }

    public void setEmployee_Fname(String employee_Fname) {
        this.employee_Fname = employee_Fname;
    }

    public String getEmployee_Lname() {
        return employee_Lname;
    }

    public void setEmployee_Lname(String employee_Lname) {
        this.employee_Lname = employee_Lname;
    }

    public String getStreet() {
        return street;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void toEntity(EmployeeTm employeeTm) {
        this.employee_id = employeeTm.getEmp_Id();
        this.employee_Fname = employeeTm.getFistName();
        this.employee_Lname = employeeTm.getLastName();
        this.street = employeeTm.getStreet();
        this.city = employeeTm.getCity();
        this.lane = employeeTm.getLane();
        this.role = employeeTm.getRole();
        this.contact = employeeTm.getContact();
    }
}
