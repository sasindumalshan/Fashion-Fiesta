package lk.ijse.fashionfiesta.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Stock  {

    private String item_id;
    private String item_name;
   private int quantity;
    private double price;
    private String model_color;
    private String category;

}
