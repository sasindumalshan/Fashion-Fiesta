package lk.ijse.fashionfiesta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderTmDto {
        private String item_id;
        private String supplier_order_id;
        private String Supplier_order_date;
        private double payment;
        private double price;
        private int quantity;


}
