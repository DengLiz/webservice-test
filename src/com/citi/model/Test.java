package com.citi.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.citi.pojo.Employee;

public class Test {
	public static void main(String[] args){
		//Load Spring Context
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext("WebContent\\WEB-INF\\applicationContext.xml");
		//get DBConection object
		EmployeeDAO employeeDAO = applicationContext.getBean(EmployeeDAO.class);
		Employee emp = new Employee();
		emp.setName("Kip");
		emp.setCode("E090");
		employeeDAO.create(emp);
		DBConnection connection = applicationContext.getBean(DBConnection.class);
//		DBConnection connection = (DBConnection) applicationContext.getBean("dataConnection");
//		connection.setUsername("123");
//		System.out.println(connection);
//		DBConnection connection1 = applicationContext.getBean(DBConnection.class);
//		System.out.println(connection1);
//		System.out.println(connection.getUsername());
		//Test Connection
		connection.createConnection();
		
		
	}

}
