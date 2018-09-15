package com.ComeOut.driver;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import java.sql.Connection;

public class JdbcUtils {
	
	static final String url = "jdbc:mysql://localhost:3306/job-hunting";
	static final String username="comeout";
	static final String password="123456";
	
	//闈欐�佷唬鐮佸潡
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public static Connection getConnection(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//鏌ヨ
	public static void query(){
		Connection conn=null;
		PreparedStatement prest=null;
		ResultSet rs = null;
		try {
			conn=JdbcUtils.getConnection();
			
			String sql="select * from xiaoyuan";
			prest=conn.prepareStatement(sql);
			rs= prest.executeQuery(sql);
			
			while(rs.next()){
				System.out.println(rs.getObject("url"));
				System.out.println(rs.getObject("position"));
				System.out.println(rs.getObject("company"));
				System.out.println(rs.getObject("time"));
				System.out.println(rs.getObject("duty"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.free(conn, (Statement) prest, rs);
		}
	}

	//鎻掑叆
	public static void add(){
		Connection conn=null;
		PreparedStatement prest=null;
		ResultSet rs = null;
		try {
			conn=JdbcUtils.getConnection();
			String sql="insert into xiaoyuan(url,position,company,time,duty)values(?,?,?,?,?)";
			prest=conn.prepareStatement(sql);
			prest.setString(1, "url1");
			prest.setString(2, "position");
			prest.setString(3, "company");
			prest.setObject(4, new java.util.Date());
			prest.setString(5, "duty");
			prest.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtils.free(conn, (Statement) prest, rs);
		}
	}
	
	
	
	/**
	 * 閲婃斁璧勬簮
	 * @return 
	 */
	public static void free(Connection conn,Statement st, ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(st!=null){
					try {
						st.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						if(conn!=null){
							try {
								conn.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
