package com.ComeOut.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;


public class MysqlDriver{

	public static void main(String[] args){
		query();
		add();
		query();
	}

	public static void query(){
		// TODO Auto-generated method stub
		Connection conn=null;
		Statement st=null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/job-hunting";
			String username="comeout";
			String password="123456";
			conn=DriverManager.getConnection(url,username,password);
			
			st= (Statement) conn.createStatement();
			
			String sql="select * from xiaoyuan";
			rs= st.executeQuery(sql);
			
			while(rs.next()){
				System.out.println(rs.getObject("url"));
				System.out.println(rs.getObject("position"));
				System.out.println(rs.getObject("company"));
				System.out.println(rs.getObject("time"));
				System.out.println(rs.getObject("duty"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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

	public static void add(){
		Connection conn=null;
		Statement st=null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/job-hunting";
			String username="comeout";
			String password="123456";
			conn=DriverManager.getConnection(url,username,password);
			
			//st= (Statement) conn.createStatement();
			
			String sql="insert into xiaoyuan(url,position,company,time,duty)values(?,?,?,?,?)";
			PreparedStatement prest=conn.prepareStatement(sql);
			prest.setString(1, "url1");
			prest.setString(2, "position");
			prest.setString(3, "company");
			prest.setObject(4, new java.util.Date());
			prest.setString(5, "duty");
			prest.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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

	public static void update(){
		
	}
	
	public static void delete(){
		
	}
}
