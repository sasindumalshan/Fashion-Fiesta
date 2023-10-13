package lk.ijse.fashionfiesta.model;

import lk.ijse.fashionfiesta.dto.CustomerOrderDetails;
import lk.ijse.fashionfiesta.dto.Stock;
import lk.ijse.fashionfiesta.dto.SupplierOrderDetails;
import lk.ijse.fashionfiesta.tm.OrderTm;
import lk.ijse.fashionfiesta.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockModel {

    public static boolean addStock(Stock stock) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO item VALUES(?,?,?,?,?,?)",
                stock.getItem_id(),
                stock.getItem_name(),
                stock.getQuantity(),
                stock.getPrice(),
                stock.getModel_color(),
                stock.getCategory()

        );
    }

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT item_id FROM item ORDER BY LENGTH(item_id),item_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public static Stock get(String id) throws SQLException, ClassNotFoundException {
        Stock stock = new Stock();
        ResultSet set = CrudUtil.crudUtil("SELECT * from item where item_id=?", id);
        if (set.next()) {
            stock.setItem_id(set.getString(1));
            stock.setItem_name(set.getString(2));
            stock.setQuantity(Integer.parseInt(set.getString(3)));
            stock.setPrice(Double.parseDouble(set.getString(4)));
            stock.setModel_color(set.getString(5));
            stock.setCategory(set.getString(6));

        }
        return stock;
    }

    public static boolean update(Stock stock) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("UPDATE item SET category=?,model_color=?,price=?,item_name=? WHERE item_id=?",
                stock.getCategory(),
                stock.getModel_color(),
                stock.getPrice(),
                stock.getItem_name(),
                stock.getItem_id()
        );
    }

    public static boolean remove(String emp_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("DELETE FROM item WHERE item_id=?", emp_id);
    }

    public static boolean updateData(ArrayList<SupplierOrderDetails> details) throws SQLException, ClassNotFoundException {
        for (SupplierOrderDetails s : details
        ) {
            if (CrudUtil.crudUtil("UPDATE item SET quantity=quantity+? WHERE item_id=?",
                    s.getQuantity(),
                    s.getItem_id()
            )) {

            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean CustomerOrderupdateData(ArrayList<OrderTm> details) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < details.size(); i++) {
            if (CrudUtil.crudUtil("UPDATE item SET quantity=quantity-? WHERE item_id=?",
                    details.get(i).getQty(),
                    details.get(i).getItem_Id()
            )) {

            } else {
                return false;
            }
        }


        return true;
    }

    public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();
        ResultSet set = CrudUtil.crudUtil("SELECT item_id from item where item_id LIKE ? or item_name LIKE ?", id + "%", id + "%");
        while (set.next()) {
            ids.add(set.getString(1));
        }
        return ids;
    }

    public static String getStock() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT COUNT(item_id) FROM item");
        String count = null;
        if (set.next()) {
            count = set.getString(1);
        }
        return count;
    }
}
