package lk.ijse.fashionfiesta.model;

import lk.ijse.fashionfiesta.db.DBConnection;
import lk.ijse.fashionfiesta.dto.SupplierOrder;
import lk.ijse.fashionfiesta.dto.SupplierOrderDetails;
import lk.ijse.fashionfiesta.utill.CrudUtil;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderModel {
    public static boolean addSupOrder(SupplierOrder supOrder) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO supplier_order VALUES(?,?,?,?,?)",
                supOrder.getSupplier_id(),
                supOrder.getSupplier_order_id(),
                supOrder.getSupplier_order_date(),
                supOrder.getSupplier_order_time(),
                supOrder.getPayment()
        );

}
    public static ArrayList<String> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT supplier_order_id FROM supplier_order ORDER BY LENGTH(supplier_order_id),supplier_order_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
    public static SupplierOrder get(String id) throws SQLException, ClassNotFoundException {
        SupplierOrder supOrder=new SupplierOrder();
        ResultSet set = CrudUtil.crudUtil("SELECT * from supplier_order where supplier_id=?", id);
        if (set.next()){
            supOrder.setSupplier_id(set.getString(1));
            supOrder.setSupplier_order_id(set.getString(2));
            supOrder.setSupplier_order_date(set.getString(3));
            supOrder.setSupplier_order_time(set.getString(4));
            supOrder.setPayment(set.getString(5));

        }
        return supOrder;
    }
    public static boolean update(SupplierOrder supOrder) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("UPDATE supplier_order SET supplier_order_time=?,supplier_order_date=?,supplier_order_id=? WHERE supplier_id=?",
                supOrder.getSupplier_id(),
                supOrder.getSupplier_order_id(),
                supOrder.getSupplier_order_date(),
                supOrder.getPayment()

        );
    }
    public static boolean remove(String supplier_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("DELETE FROM supplier_order WHERE supplier_id=?",supplier_id);
    }

    public static boolean addSupplierOrderDetails(ArrayList<SupplierOrderDetails> details) throws SQLException, ClassNotFoundException {
        for (SupplierOrderDetails s:
             details) {
            if ( CrudUtil.crudUtil("INSERT INTO supplier_order_details VALUES(?,?,?,?)",
                    s.getSupplier_order_id(),
                    s.getItem_id(),
                    s.getQuantity(),
                    s.getPrice()
            )){

            }else {
                return false;
            }
        }
        return true;

    }
    public static boolean placeOrder(SupplierOrder supOrder, ArrayList<SupplierOrderDetails> details_list) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            if (SupplierOrderModel.addSupOrder(supOrder)){
                if (SupplierOrderModel.addSupplierOrderDetails(details_list)){
                    if (StockModel.updateData(details_list)){
                        connection.commit();
                        return true;
                    }else {
                        connection.rollback();
                        return false;
                    }

                }else {
                    connection.rollback();
                    return false;
                }
            }else {
                connection.rollback();
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;
    }
   public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set=CrudUtil.crudUtil("SELECT supplier_id from supplier_order where supplier_id LIKE ? or supplier_order_id LIKE ? or supplier_order_date LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }

    public static String getSupOrderCount() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(supplier_order_id) FROM supplier_order");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }
    public static String getSupplierOrder() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(supplier_order_id) FROM supplier_order");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }

    public static String getCustomerOrder(int i) throws SQLException, ClassNotFoundException {
        String dateNow = DateTimeUtil.dateNow();
        String[] date = dateNow.split("-");
        String currentDate=null;
        if (String.valueOf(i).length()==1){
            currentDate="0"+i;
        }else {
            currentDate= String.valueOf(i);
        }
        ResultSet set=CrudUtil.crudUtil("SELECT payment FROM supplier_order WHERE supplier_order_date =?",date[0]+"-"+date[1]+"-"+currentDate);

        while (set.next()){
            return set.getString(1);
        }
        return "0";
    }

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT supplier_order_id FROM supplier_order ORDER BY LENGTH(supplier_order_id),supplier_order_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
}
