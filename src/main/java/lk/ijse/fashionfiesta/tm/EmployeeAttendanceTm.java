package lk.ijse.fashionfiesta.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EmployeeAttendanceTm {
private String employee_id;
private String first_Name;
private String last_Name;
private String time;
private String date;
}
