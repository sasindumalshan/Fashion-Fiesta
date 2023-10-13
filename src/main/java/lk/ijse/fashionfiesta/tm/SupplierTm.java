package lk.ijse.fashionfiesta.tm;

import javafx.scene.control.Button;
import lombok.ToString;

@ToString
public class SupplierTm {
    private String Sup_Id;
    private String fistName;
    private String lastName;
    private String city;
    private String contact;
    private String lane;
    private String street;

    public SupplierTm() {
    }

    public SupplierTm(String sup_Id, String fistName, String lastName, String city, String contact, Button option, Button view) {
        Sup_Id = sup_Id;
        this.fistName = fistName;
        this.lastName = lastName;
        this.city = city;
        this.contact = contact;
    }
    public String getSup_Id() { return Sup_Id;}

    public void setSup_Id(String sup_Id) {
        Sup_Id = sup_Id;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


}
