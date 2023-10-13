package lk.ijse.fashionfiesta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierOrder {
    private String supplier_id;
    private String supplier_order_id;
    private String supplier_order_date;
    private String supplier_order_time;
    private String payment;
}
