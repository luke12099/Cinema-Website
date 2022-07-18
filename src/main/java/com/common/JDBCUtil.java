package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
// JDBC 共用資源
// 使用方式: 在 DAO 層用 con = JDBCUtil.getConnection(); 取得連線，記得要關資源or歸還連線 con.close();
public class JDBCUtil {
	public static final String DR = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	// GCP root 641641
//	public static final String URL = "jdbc:mysql://35.194.154.140:23306/movietheater?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PW = "password";
	
	static Connection con;
	
	// true = 使用連線池，false = 不使用連線池
	static boolean useConnectionPool = true;
	
	public static Connection getConnection() throws SQLException, NamingException, ClassNotFoundException {
		if(useConnectionPool) { // 使用連線池
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/HireMe");
			con = ds.getConnection();
		} else { // 不使用連線池
			Class.forName(DR);
			con = DriverManager.getConnection(URL, USER, PW);
		}
		
		return con;
	}

}
