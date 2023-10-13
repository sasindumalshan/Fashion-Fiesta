package lk.ijse.fashionfiesta.tm;

import javafx.scene.control.Button;

public class EmployeeTm {
    private String Emp_Id;
    private String fistName;
    private String lastName;
    private String city;
    private String contact;
    private String lane;
    private String Role;
    private String street;

    public EmployeeTm() {
    }

    public EmployeeTm(String emp_Id, String fistName, String lastName, String city, String contact, Button option, Button view) {
        Emp_Id = emp_Id;
        this.fistName = fistName;
        this.lastName = lastName;
        this.city = city;
        this.contact = contact;
    }

    public String getEmp_Id() {
        return Emp_Id;
    }

    public void setEmp_Id(String emp_Id) {
        Emp_Id = emp_Id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
