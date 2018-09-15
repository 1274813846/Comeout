package com.ComeOut.imple;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ComeOut.driver.JdbcUtils;
import com.mysql.jdbc.Connection;

public class SqlImple {

	
	/**
	 * 杩炴帴灞炴��
	 */
	private static Connection conn=null;
	
	
	public  static JSONArray query_sql(String sql){

		PreparedStatement pre =null;
		ResultSet rs = null;
		conn=(Connection) JdbcUtils.getConnection();
		
		//鏂板缓JsonArray
		JSONArray json_array=new JSONArray();
		
		try {
			pre=conn.prepareStatement(sql);
			rs=pre.executeQuery();

			ResultSetMetaData data=rs.getMetaData();
			
			
			while(rs.next()){
				JSONObject json_object=new JSONObject();
				for(int i=1;i<=data.getColumnCount();++i){
					//鍒楀悕
					String columnName=data.getColumnName(i);
					String rst=rs.getString(i);
					json_object.put(columnName, rst);
				}
				json_array.put(json_object);
			}
//			System.out.println(json_array.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json_array;
	}
	
	
	public static int update_sql(String sql){
		PreparedStatement pre =null;
		ResultSet rs = null;
		conn=(Connection) JdbcUtils.getConnection();
		try {
			pre=conn.prepareStatement(sql);

			return pre.executeUpdate();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return 0;
		
	}
	
	public static void main(String[] args) {
		String sql="select * from Swap";
		query_sql(sql);
	}
	
}





