package lk.ijse.fashionfiesta.tm;

import javafx.scene.control.Button;
import lombok.ToString;

@ToString
public class CustomerTm {
    private String Cust_id;
    private String fistName;
    private String lastName;
    private String city;
    private String lane;
    private String street;
    private String contact_number;

    public CustomerTm() {
    }

    public CustomerTm(String cust_id, String fistName, String lastName, String city, String contact_number, Button option, Button view) {
        Cust_id = cust_id;
        this.fistName = fistName;
        this.lastName = lastName;
        this.contact_number = contact_number;
        this.city = city;
    }


    public String getCust_id() {
        return Cust_id;
    }

    public void setCust_id(String cust_id) {
        this.Cust_id = cust_id;
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

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}


