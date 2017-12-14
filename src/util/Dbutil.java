package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbutil {

	private String dbUrl="jdbc:mysql://localhost:3306/warehouse??characterEncoding=utf8&useSSL=true";
	private String dbUserName="root";
	private String dbPassword="123456";
	private String jdbcName="com.mysql.jdbc.Driver";
	
	/* 
	 * 获取数据库连接
	 */
	public  Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
	/*
	 * 关闭数据库连接
	 */
	public void closeCon(Connection con) throws Exception{
		if(con != null){
			con.close();			
		}
	}
	public static void main(String arg[]){
		Dbutil dbutil= new Dbutil();
			try {
				dbutil.getCon();
				System.out.println("数据库连接成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	}
	
	
}
