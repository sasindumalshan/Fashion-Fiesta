package lk.ijse.fashionfiesta.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierOrderTm {
    private String item_id;
    private String Name;
    private int quantity;
    private double payment;
}
