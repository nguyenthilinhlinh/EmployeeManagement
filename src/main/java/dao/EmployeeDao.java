package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;
import entity.Employees;

public class EmployeeDao {
	public static CallableStatement createCS(Connection con, int id) throws Exception {
		var cs = con.prepareCall("{call selectEmpID(?)}");
		cs.setInt(1, id);
		return cs;
	}
	public List<Employees> getEmp(int id) {
		List<Employees> list = new ArrayList<>(); 
		try (
				var con = ConnectDB.connect();
				var cs = createCS(con, id);
				var result = cs.executeQuery();
		){
			
			while (result.next()) {
				var emp = new Employees();
				emp.setEmployeeID(result.getInt("EmployeeID"));
				emp.setEmployeeName(result.getString("Name"));
				emp.setEmail(result.getString("Email"));
				emp.setPassword(result.getString("Password"));
				emp.setRole(result.getString("Role"));
				list.add(emp);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
