package lk.ijse.fashionfiesta.model;

import lk.ijse.fashionfiesta.dto.Customer;
import lk.ijse.fashionfiesta.tm.CustomerTm;
import lk.ijse.fashionfiesta.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO customer VALUES(?,?,?,?,?,?,?)",
                customer.getCustomer_Id(),
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getStreet(),
                customer.getCity(),
                customer.getLane(),
                customer.getContact_number()
        );
    }
    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT customer_id FROM customer ORDER BY LENGTH(customer_id),customer_id");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
    public static Customer get(String id) throws SQLException, ClassNotFoundException {
        Customer customer=new Customer();
        ResultSet set = CrudUtil.crudUtil("SELECT * from customer where customer_id=?", id);
        if (set.next()){
            customer.setCustomer_Id(set.getString(1));
            customer.setFirst_name(set.getString(2));
            customer.setLast_name(set.getString(3));
            customer.setStreet(set.getString(4));
            customer.setCity(set.getString(5));
            customer.setLane(set.getString(6));
            customer.setContact_number(set.getString(7));
        }
        return customer;
    }
    public static boolean update(Customer customer) throws SQLException, ClassNotFoundException {

        return CrudUtil.crudUtil("UPDATE Customer SET first_name=?,last_name=?,street=?,city=?,lane=?,contact_number=? WHERE customer_id=?",
               customer.getFirst_name(),
                customer.getLast_name(),
                customer.getStreet(),
                customer.getCity(),
                customer.getLane(),
                customer.getContact_number(),
                customer.getCustomer_Id()
        );
    }
    public static boolean remove(String cust_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("DELETE FROM customer WHERE customer_id=?",cust_id);
    }
    public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set=CrudUtil.crudUtil("SELECT customer_id from customer where customer_id LIKE ? or first_name LIKE ? or last_name LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

    public static String getCustomer() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(customer_id) FROM customer");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }
}





