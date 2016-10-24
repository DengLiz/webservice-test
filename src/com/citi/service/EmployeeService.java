package com.citi.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;


import com.citi.model.EmployeeDAO;
import com.citi.pojo.Employee;

public class EmployeeService {
	private EmployeeDAO employeeDAO;

	@Context HttpServletRequest request;
	
	@GET
	@Path("/employees")
	@Consumes("application/json")
	@Produces("application/json")
	
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		List<Employee> list = employeeDAO.search();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			System.out.println(key+":"+value);
		}
		return list;
	}
	@POST
	@Path("/create")
	@Consumes("application/json")
	@Produces("application/json")
	public Employee create(Employee emp) {
		// TODO Auto-generated method stub
		employeeDAO.create(emp);
		return emp;
	}
	
	@GET
	@Path("/employees/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Employee getEmployee(@PathParam("id") String id) {
		// TODO Auto-generated method stub
		Employee emp = employeeDAO.search(id);
		return emp;
	}
	
	@PUT
	@Path("/update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public String updateEmployee(Employee employee,@PathParam("id") String id){
		int row = employeeDAO.update(employee,id);
		if(row==0){
			return "{\"code\":500,\"msg\":\"id is wrong\",\"data\":\"\"}";
		}
		return row+"";	
	}
	
@Deprecated
	private  List<Employee> init(){
		List<Employee> list  = new ArrayList<Employee>();
		Employee emp = new Employee();
		emp.setId("1");
		emp.setName("Liz");
		emp.setCode("E01");
		list.add(emp);
		return list;
	}
	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

}
