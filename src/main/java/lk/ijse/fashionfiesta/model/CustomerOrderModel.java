package lk.ijse.fashionfiesta.model;

import lk.ijse.fashionfiesta.db.DBConnection;
import lk.ijse.fashionfiesta.dto.CustomerOrder;
import lk.ijse.fashionfiesta.tm.OrderTm;
import lk.ijse.fashionfiesta.utill.CrudUtil;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderModel {
    public static boolean addCusOrder(CustomerOrder cusOrder) throws SQLException, ClassNotFoundException {
        System.out.println(cusOrder.toString());
        return CrudUtil.crudUtil("INSERT INTO customer_order VALUES(?,?,?,?,?)",
                cusOrder.getCustomer_id(),
                cusOrder.getCustomer_order_id(),
                cusOrder.getCustomer_order_date(),
                cusOrder.getCustomer_order_time(),
                cusOrder.getPayment()
        );
    }

    public static ArrayList<String> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT customer_order_id FROM customer_order ORDER BY LENGTH(customer_order_id),customer_order_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public static CustomerOrder get(String id) throws SQLException, ClassNotFoundException {
        CustomerOrder cusOrder = new CustomerOrder();
        ResultSet set = CrudUtil.crudUtil("SELECT * from customer_order where customer_id=?", id);
        if (set.next()) {
            cusOrder.setCustomer_id(set.getString(1));
            cusOrder.setCustomer_order_id(set.getString(2));
            cusOrder.setCustomer_order_date(set.getString(3));
            cusOrder.setCustomer_order_time(set.getString(4));
            cusOrder.setPayment(set.getString(5));

        }
        return cusOrder;
    }

    public static boolean update(CustomerOrder cusOrder) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("UPDATE customer_order SET customer_order_time=?,customer_order_date=?,customer_order_id=? WHERE customer_id=?",
                cusOrder.getCustomer_id(),
                cusOrder.getCustomer_order_id(),
                cusOrder.getCustomer_order_date(),
                cusOrder.getPayment()

        );
    }

    public static boolean remove(String customer_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("DELETE FROM customer_order WHERE supplier_id=?", customer_id);
    }

    public static boolean addCustomerOrderDetails(CustomerOrder cusOrder, ArrayList<OrderTm> details) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < details.size(); i++) {
            System.out.println( details.get(i).toString());
            if (CrudUtil.crudUtil("INSERT INTO customer_order_details VALUES(?,?,?,?)",
                    cusOrder.getCustomer_order_id(),
                    details.get(i).getItem_Id(),
                    details.get(i).getQty(),
                    details.get(i).getPrice()
            )){}else {
                return false;
            }

        }
        return true;

    }

    public static boolean placeOrder(CustomerOrder cusOrder, ArrayList<OrderTm> details) throws SQLException {

        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            if (CustomerOrderModel.addCusOrder(cusOrder)) {
                System.out.println("1");
                if (CustomerOrderModel.addCustomerOrderDetails(cusOrder,details)) {
                    System.out.println("2");
                    if (StockModel.CustomerOrderupdateData(details)) {
                        System.out.println("3");
                        connection.commit();
                    } else {
                       System.out.println("3-else");
                        connection.rollback();
                        return false;
                    }
                } else {
                    System.out.println("2-else");
                    connection.rollback();
                    return false;
                }

            } else {
                System.out.println("1-else");
                connection.rollback();
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();
        ResultSet set = CrudUtil.crudUtil("SELECT customer_id from customer_order where customer_id LIKE ? or customer_order_id LIKE ? or customer_order_date LIKE ?", id + "%", id + "%", id + "%");
        while (set.next()) {
            ids.add(set.getString(1));
        }
        return ids;
    }

    public static String getCustOrder() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT COUNT(customer_order_id) FROM customer_order");
        String count = null;
        if (set.next()) {
            count = set.getString(1);
        }
        return count;
    }

    public static String getCustomerOrder(int i) throws SQLException, ClassNotFoundException {
        String dateNow = DateTimeUtil.dateNow();
        String[] date = dateNow.split("-");
        String currentDate = null;
        if (String.valueOf(i).length() == 1) {
            currentDate = "0" + i;
        } else {
            currentDate = String.valueOf(i);
        }
        ResultSet set = CrudUtil.crudUtil("SELECT payment FROM customer_order WHERE customer_order_date =?", date[0] + "-" + date[1] + "-" + currentDate);

        while (set.next()) {
            return set.getString(1);
        }
        return "0";
    }

    public static ArrayList<String> AllId() throws SQLException, ClassNotFoundException {
//SELECT customer_order_id FROM customer_order ORDER BY LENGTH(customer_order_id),customer_order_id

        ResultSet set = CrudUtil.crudUtil("SELECT customer_order_id FROM customer_order ORDER BY LENGTH(customer_order_id),customer_order_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }
}
