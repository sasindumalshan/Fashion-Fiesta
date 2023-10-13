package lk.ijse.fashionfiesta.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalaryTm {
    private String employee_id;
    private String salary_id;
    private String date;
    private String salary;
    private String employee_attandance_count;
}
