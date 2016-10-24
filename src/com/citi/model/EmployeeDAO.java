package com.citi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.citi.pojo.Employee;

//@Component
public class EmployeeDAO {
//	@Autowired
	private DBConnection dbConnection;
	public void create(Employee emp){
		Connection conn = null;
		PreparedStatement pStatement = null;
		String sql = "INSERT INTO EMPLOYEE(CODE,NAME) VALUES(?,?)";
		conn  = dbConnection.createConnection();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1,emp.getCode());
			pStatement.setString(2,emp.getName());
			pStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection(conn);
		}
		
	}
	public List<Employee> search(){
		List<Employee> list = new ArrayList<Employee>();
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM EMPLOYEE";
		conn  = dbConnection.createConnection();
		try {
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getString("EMPLOYEE_ID"));
				emp.setCode(rs.getString("CODE"));
				emp.setName(rs.getString("NAME"));
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection(conn);
			DBConnection.closePreparedStatement(pStatement);
			DBConnection.closeResultSett(rs);
		}
		
		return list;
	}
	
	
	public Employee search(String id){
		Employee employee = new Employee();
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
		conn  = dbConnection.createConnection();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, id);
			rs = pStatement.executeQuery();
			if (rs.next()) {
				employee.setId(rs.getString("EMPLOYEE_ID"));
				employee.setCode(rs.getString("CODE"));
				employee.setName(rs.getString("NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection(conn);
			DBConnection.closePreparedStatement(pStatement);
			DBConnection.closeResultSett(rs);
		}
		
		return employee;
	}
	
	public int update(Employee emp,String id){
		if(!emp.getId().equalsIgnoreCase(id)){
			return 0;
		}
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		int row =0;
		String sql = "UPDATE EMPLOYEE SET CODE =? ,NAME = ? WHERE EMPLOYEE_ID = ?";
		conn  = dbConnection.createConnection();
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, emp.getCode());
			pStatement.setString(2, emp.getName());
			pStatement.setString(3, emp.getId());
			row = pStatement.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.closeConnection(conn);
			DBConnection.closePreparedStatement(pStatement);
			DBConnection.closeResultSett(rs);
		}
		return row;
	}
	
	public DBConnection getDbConnection() {
		return dbConnection;
	}
	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
}
