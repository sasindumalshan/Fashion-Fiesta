package lk.ijse.fashionfiesta.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderTm {
    String item_Id;
    String order_Id;
    String date;
    double price;
    int qty;
    double payment;
}
