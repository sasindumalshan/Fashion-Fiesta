package lk.ijse.fashionfiesta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CustomerOrderDetails {
    private String quantity;
    private String item_id;
    private String customer_order_id;
    private String price;
}
