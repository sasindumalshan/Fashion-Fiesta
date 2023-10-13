package lk.ijse.fashionfiesta.model;

import lk.ijse.fashionfiesta.dto.Salary;
import lk.ijse.fashionfiesta.utill.CrudUtil;
import lk.ijse.fashionfiesta.utill.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryModel {

    public static ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String>ids=new ArrayList<>();
        String date= DateTimeUtil.dateNow();
        String[] arDate = date.split("-");
        ResultSet set= CrudUtil.crudUtil("SELECT salary_id FROM salary WHERE date LIKE ?",arDate[0]+"-"+arDate[1]+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids;
    }

    public static boolean add(Salary salary) throws SQLException, ClassNotFoundException {
        System.out.println(salary.toString());
        return CrudUtil.crudUtil("INSERT INTO salary VALUES (?,?,?,?,?)",
                salary.getEmployee_id(),
                salary.getSalary_id(),
                salary.getDate(),
                salary.getSalary(),
                salary.getEmployee_attandance_count()
        );
    }
    public static Salary getData(String id) throws SQLException, ClassNotFoundException {

        ResultSet set = CrudUtil.crudUtil("SELECT * FROM salary where salary_id=?", id);
        Salary salary = new Salary();
        System.out.println("Sal Id : "+ id);
        while (set.next()) {

            salary.setEmployee_id(set.getString(1));
            salary.setSalary_id(set.getString(2));
            salary.setDate(set.getString(3));
            salary.setSalary(Double.parseDouble(set.getString(4)));
            salary.setEmployee_attandance_count(Integer.parseInt(set.getString(5)));

        }
        return salary;
    }

    public static String getCount() {
        try {
            String s = DateTimeUtil.dateNow();
            String[] split = s.split("-");
            ResultSet o = CrudUtil.crudUtil("SELECT COUNT(salary_id) from salary WHERE date LIKE ?", split[0] + "-" + split[1] + "-%");
            if (o.next()){
                return "+ 0"+o.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "+00";
    }
}
