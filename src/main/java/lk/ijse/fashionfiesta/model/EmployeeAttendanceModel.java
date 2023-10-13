package lk.ijse.fashionfiesta.model;

import lk.ijse.fashionfiesta.dto.EmployeeAttendanceDto;
import lk.ijse.fashionfiesta.utill.CrudUtil;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeAttendanceModel {

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT employee_id FROM employee_attendance ORDER BY LENGTH(employee_id),employee_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public static boolean add(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO employee_attendance VALUES(?,?,?,?)",
                text,
                id(),
                DateTimeUtil.timeNow(),
                DateTimeUtil.dateNow()
        );
    }

    private static String id() {
        try {
            ArrayList<String> allId = EmployeeAttendanceModel.getAllId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                //System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("A00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                // System.out.println(idIndex);
                return "A00" + idIndex;
            } catch (Exception e) {
                return "A001";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static boolean isEmployeeExist(String text) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT * FROM employee WHERE employee_id=?",text);
        if (set.next()){
            return true;
        }
        return false;
    }

    public static boolean isEmployeeTodayExist(String text) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT * FROM employee_attendance WHERE employee_id=? AND date = ?",text,DateTimeUtil.dateNow());
        if (set.next()){
            return false;
        }
        return true;
    }

    public static EmployeeAttendanceDto getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT a.employee_id,a.time,a.date,e.first_name,e.last_name FROM employee_attendance a INNER JOIN employee e ON a.employee_id = e.employee_id WHERE a.employee_id=?",id);
        EmployeeAttendanceDto model = new EmployeeAttendanceDto();
        while (set.next()){
            model.setEmployee_id(set.getString(1));
            model.setTime(set.getString(2));
            model.setDate(set.getString(3));
            model.setFirst_Name(set.getString(4));
            model.setLast_Name(set.getString(5));
        }
        return model;
    }

    public static String getEmployeeCount() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(employee_id) FROM employee_attendance");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }
    public static String getEmpAttendance() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(employee_id) FROM employee_attendance");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }

    public static String getEmloyeeCount(String id) throws SQLException, ClassNotFoundException {
        String date=DateTimeUtil.dateNow();
        String[] arDate = date.split("-");
        ResultSet  set=  CrudUtil.crudUtil("SELECT COUNT(employee_id) FROM employee_attendance WHERE employee_id=? AND date LIKE ?",id,arDate[0]+"-"+arDate[1]+"%");
        if (set.next()){
            return set.getString(1);
        }
        return null;
    }

    public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set=CrudUtil.crudUtil("SELECT employee_id from Employee where employee_id LIKE ? or first_name LIKE ? or last_name LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }
}