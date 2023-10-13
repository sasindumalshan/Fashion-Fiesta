package lk.ijse.fashionfiesta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeAttendanceDto {

    private String employee_id;
    private String first_Name;
    private String last_Name;
    private String time;
    private String date;
}
