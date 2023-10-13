package lk.ijse.fashionfiesta.model;

import lk.ijse.fashionfiesta.dto.OrderTmDto;
import lk.ijse.fashionfiesta.dto.Supplier;
import lk.ijse.fashionfiesta.dto.SupplierOrderDetails;
import lk.ijse.fashionfiesta.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailsModel {

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT supplier_order_id FROM supplier_order_details ORDER BY LENGTH(supplier_order_id),supplier_order_id");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public static OrderTmDto get(String id) throws SQLException, ClassNotFoundException {
        OrderTmDto orderTmDto = new OrderTmDto();
        ResultSet set = CrudUtil.crudUtil("SELECT d.item_id, d.supplier_order_id ,d.quantity ,d.price,o.supplier_order_date,o.payment FROM supplier_order_details d INNER JOIN supplier_order o ON d.supplier_order_id = o.supplier_order_id where o.supplier_order_id=?",id);
        if (set.next()){
            orderTmDto.setItem_id(set.getString(1));
            orderTmDto.setSupplier_order_id(set.getString(2));
            orderTmDto.setQuantity(set.getInt(3));
            orderTmDto.setPrice(Double.parseDouble(set.getString(4)));
            orderTmDto.setSupplier_order_date(set.getString(5));
            orderTmDto.setPayment(Double.parseDouble(set.getString(6)));
        }
        return orderTmDto;
    }
}
