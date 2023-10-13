package lk.ijse.fashionfiesta.model;

import lk.ijse.fashionfiesta.dto.Employee;
import lk.ijse.fashionfiesta.utill.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {

    public static boolean addEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?)",
                employee.getEmployee_id(),
                employee.getStreet(),
                employee.getCity(),
                employee.getLane(),
                employee.getRole(),
                employee.getEmployee_Fname(),
                employee.getEmployee_Lname(),
                employee.getContact()
                );
    }

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.crudUtil("SELECT employee_id FROM employee ORDER BY LENGTH(employee_id),employee_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    public static Employee get(String id) throws SQLException, ClassNotFoundException {
        Employee employee=new Employee();
        ResultSet set = CrudUtil.crudUtil("SELECT * from employee where employee_id=?", id);
        if (set.next()){
            employee.setEmployee_id(set.getString(1));
            employee.setStreet(set.getString(2));
            employee.setCity(set.getString(3));
            employee.setLane(set.getString(4));
            employee.setRole(set.getString(5));
            employee.setEmployee_Fname(set.getString(6));
            employee.setEmployee_Lname(set.getString(7));
            employee.setContact(set.getString(8));
        }
        return employee;
    }
    public static boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.crudUtil("UPDATE Employee SET street=?,city=?,lane=?,role=?,first_name=?,last_name=?,contact_number=? WHERE employee_id=?",
                employee.getStreet(),
                employee.getCity(),
                employee.getLane(),
                employee.getRole(),
                employee.getEmployee_Fname(),
                employee.getEmployee_Lname(),
                employee.getContact(),
                employee.getEmployee_id()
        );
    }
    public static boolean remove(String emp_id) throws SQLException, ClassNotFoundException {
        System.out.println(emp_id);
        return CrudUtil.crudUtil("DELETE FROM employee WHERE employee_id=?",emp_id);
    }

    public static ArrayList<String> getSearchIds(String id) throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        ResultSet set=CrudUtil.crudUtil("SELECT employee_id from employee WHERE employee_id LIKE ? or first_name LIKE ? or last_name LIKE ?",id+"%",id+"%",id+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids ;
    }
    public static String getEmpCount() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(employee_id) FROM employee");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }
    public static String getEmployee() throws SQLException, ClassNotFoundException {
        ResultSet set= CrudUtil.crudUtil("SELECT COUNT(employee_id) FROM employee");
        String count=null;
        if (set.next()){
            count=set.getString(1);
        }
        return count;
    }
}
