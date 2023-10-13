package lk.ijse.fashionfiesta.model;

import lk.ijse.fashionfiesta.dto.Supplier;
import lk.ijse.fashionfiesta.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SupplierModel {

    public static boolean addSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO supplier VALUES(?,?,?,?,?,?,?)",
                supplier.getSupplier_id(),
                supplier.getSupplier_Fname(),
                supplier.getSupplier_Lname(),
                supplier.getStreet(),
                supplier.getCity(),
                supplier.getLane(),
                supplier.getContact()
        );
    }

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT supplier_id FROM supplier ORDER BY LENGTH(supplier_id),supplier_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public static Supplier get(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier=new Supplier();
        ResultSet set = CrudUtil.crudUtil("SELECT * from supplier where supplier_id=?", id);
        if (set.next()){
            supplier.setSupplier_id(set.getString(1));
            supplier.setStreet(set.getString(4));
            supplier.setCity(set.getString(5));
            supplier.setLane(set.getString(6));
            supplier.setSupplier_Fname(set.getString(2));
            supplier.setSupplier_Lname(set.getString(3));
            supplier.setContact(set.getString(7));
        }
        return supplier;
    }

    public static boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("UPDATE Supplier SET street=?,city=?,lane=?,first_name=?,last_name=?,contact_number=? WHERE supplier_id=?",
                supplier.getStreet(),
                supplier.getCity(),
                supplier.getLane(),
                supplier.getSupplier_Fname(),
                supplier.getSupplier_Lname(),
                supplier.getContact(),
                supplier.getSupplier_id()
        );
    }

    public static boolean remove(String sup_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("DELETE FROM supplier WHERE supplier_id=?",sup_id);
    }
    public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set=CrudUtil.crudUtil("SELECT supplier_id from supplier where supplier_id LIKE ? or first_name LIKE ? or last_name LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }
    public static String getSupplier() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(supplier_id) FROM supplier");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }
}
