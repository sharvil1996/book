/*package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection conn = null;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bookberries", "root", "root");
			if (conn != null){
				return conn;
			}else{
				return conn;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		getConnection();
	}
}
*/
package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//Connection conn = DriverManager.getConnection("jdbc:mysql://127.12.83.130:3306/bookberries", "adminJdnpjzf","2TGE6WKRLTwh");
			//Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/bookberries","root", "");
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/bookberries","root", "");
			if (conn != null)
				return conn;
		} catch (SQLException e) {
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}