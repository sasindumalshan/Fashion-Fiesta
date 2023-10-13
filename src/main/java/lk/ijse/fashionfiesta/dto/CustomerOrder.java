package lk.ijse.fashionfiesta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CustomerOrder {
    private String customer_id;
    private String customer_order_id;
    private String customer_order_date;
    private String customer_order_time;
    private String payment;
}
