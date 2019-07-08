package com.web.db;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
	private static String username = "root";
	private static String password = "root";
	private static String URL = "jdbc:mysql://localhost:3306/address";
//	jdbc:mysql://localhost:3306/XX
	/**
	 * 获取connection
	 * @return
	 */
	public Connection getConnection() {
		Connection connection = null; 
		try{
		    	//加载MySql的驱动类    
		    	Class.forName("com.mysql.jdbc.Driver") ; 
		    	
		    	connection = DriverManager.getConnection(URL,username,password);
		    }catch(ClassNotFoundException e){
			    System.out.println("找不到驱动程序类 ，加载驱动失败！");
			    e.printStackTrace();
		    } catch (SQLException e) {
				e.printStackTrace();
			} 
		return connection;
	}
}
