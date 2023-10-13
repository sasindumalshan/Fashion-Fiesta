package lk.ijse.fashionfiesta.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CustomerOrderTm {
    private String item_id;
    private String customer_order_id;
    private String Customer_order_date;
    private double payment;
    private double price;
    private int quantity;
}
