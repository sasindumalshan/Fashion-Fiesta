package lk.ijse.fashionfiesta.model;

import lk.ijse.fashionfiesta.dto.CusOrderTmDto;
import lk.ijse.fashionfiesta.dto.CustomerOrder;
import lk.ijse.fashionfiesta.dto.CustomerOrderDetails;
import lk.ijse.fashionfiesta.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDetailsModel {

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT customer_order_id FROM customer_order_details ORDER BY LENGTH(customer_order_id),customer_order_id");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public static CusOrderTmDto get(String id) throws SQLException, ClassNotFoundException {
        CusOrderTmDto cusOrderTmDto = new CusOrderTmDto();
        ResultSet set = CrudUtil.crudUtil("SELECT d.item_id, d.customer_order_id ,d.quantity ,d.price,o.customer_order_date,o.payment FROM customer_order_details d INNER JOIN customer_order o ON d.customer_order_id = o.customer_order_id where o.customer_order_id=?",id);
        if (set.next()){
        cusOrderTmDto.setItem_id(set.getString(1));
            cusOrderTmDto.setCustomer_order_id(set.getString(2));
            cusOrderTmDto.setQuantity(set.getInt(3));
            cusOrderTmDto.setPrice(Double.parseDouble(set.getString(4)));
            cusOrderTmDto.setCustomer_order_date(set.getString(5));
            cusOrderTmDto.setPayment(Double.parseDouble(set.getString(6)));
        }
        return cusOrderTmDto;
    }
}
