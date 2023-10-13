package lk.ijse.fashionfiesta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Salary {
    private String employee_id;
    private String salary_id;
    private String date;
    private double salary;
    private int employee_attandance_count;
}
