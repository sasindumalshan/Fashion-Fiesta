package lk.ijse.fashionfiesta.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockTm {
    private String itemId;
    private String itemName;
    private String price;
    private String model_color;
    private String category;




}
