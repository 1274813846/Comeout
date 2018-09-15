package com.ComeOut.imple;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.ComeOut.DAO.Dao;
import com.ComeOut.driver.JdbcUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


/**
 * 数据库操作接口实现
 * @author lk
 *
 * @param <T>
 */
public class DaoImple<T> implements Dao<T> {

	/**
	 * 连接属性
	 */
	private Connection conn=null;
	
	/**
	 * 数据库反射添加功能
	 */
	@Override
	public int add(T t) {
		// TODO Auto-generated method stub
		PreparedStatement pre =null;
		ResultSet rs = null;
		conn=(Connection) JdbcUtils.getConnection();
		try {
			Class<?extends Object>c=t.getClass();
			StringBuffer sb=new StringBuffer("insert into ");
			String tableName=c.getSimpleName();		//类名做表名
			sb.append(tableName+"(");
			Field[] fields=c.getDeclaredFields();	//获取所有属性数组
			for(int i=0;i<fields.length;++i){
				fields[i].setAccessible(true);
				sb.append(fields[i].getName());
				if(i!=fields.length-1){
					sb.append(",");
				}
			}
			sb.append(")values(");
			for(int i=0;i<fields.length;++i){
				sb.append("?");
				if(i!=fields.length-1){
					sb.append(",");
				}else{
					sb.append(")");
				}
			}
			pre=conn.prepareStatement(sb.toString());
			for(int i=0;i<fields.length;++i){
				Object o=fields[i].get(t);	//获取属性值
				String type=fields[i].getType().getSimpleName();//获取属性类型
				if("String".equals(type)){
					if(o==null){
						pre.setString((i+1), "");
					}else{
						pre.setString((i+1), o.toString());
					}
				}else if("double".equals(type)){
					if(o==null){
						pre.setDouble((i+1), 0.0);
					}else{
						pre.setDouble((i+1), Double.parseDouble(o.toString()));
					}
				}else if("date".equals(type)){
					if(o==null){
						pre.setDate((i+1), new Date(0));
					}else{
						pre.setDate((i+1), Date.valueOf(o.toString()));
					}
				}
			}
//			System.out.println(sb.toString());
			return pre.executeUpdate();
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.free(conn, (Statement) pre, rs);
		}
		return 0;
	}

	
	/**
	 * 数据库反射删除功能
	 */
	@Override
	public int delete(Object...t) {
		
		// TODO Auto-generated method stub
		PreparedStatement pre =null;
		ResultSet rs = null;
		conn=(Connection) JdbcUtils.getConnection();
		try {
			Class<?extends Object>c=t[0].getClass();
			StringBuffer sb=new StringBuffer("delete from ");
			String tableName=c.getSimpleName();	//获取类名，即为表名
			sb.append(tableName+" where ");
			String id="";
			String sql="";
			boolean isFirst=true;
			Object obj=null;
			for(int i=0;i<t.length;++i){
				Class<?extends Object> cs=t[i].getClass();
				Field[] fields=cs.getDeclaredFields();
				for(int j=0;j<fields.length;++j){
					fields[j].setAccessible(true);
					obj=fields[j].get(t[i]);
					if(obj==null){
						continue;
					}
					String name=null;
					if((fields[j].getAnnotation(XmlElement.class))!=null){
						name=fields[j].getAnnotation(XmlElement.class).name();
					}
//				System.out.println(name); 输出标签信息
					if(fields[j].getDeclaredAnnotations().length>0&&"IsId".equals(name)){
						id=fields[j].getName();
						if(isFirst){
							sb.append(id+" in(");
							isFirst=false;
						}
						sb.append("'"+obj+"'");
						if(i!=t.length-1){
							sb.append(",");
						}else{
							sb.append(")");
						}
					}
				}
			}
			sql=sb.toString();
			pre=conn.prepareStatement(sql);
//			System.out.println(sql);	//输出sql信息
			return pre.executeUpdate();
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 数据库反射更新功能
	 */
	@Override
	public int update(T t) {
		// TODO Auto-generated method stub
		PreparedStatement pre =null;
		ResultSet rs = null;
		conn=(Connection) JdbcUtils.getConnection();
		try {
			Class<?extends Object> c=t.getClass();	//加载反射
			StringBuffer sb=new StringBuffer("update ");
			String tableName=c.getSimpleName();	//获取类名作为表名
			sb.append(tableName+" set ");
			String id="";	//主键id
			String idValue="";	//主键id值
			String sql="";
			Object obj=null;
			Field[] fields=c.getDeclaredFields();//获取属性列表
			for(int i=0;i<fields.length;++i){
				fields[i].setAccessible(true);
				obj=fields[i].get(t);
				String type=fields[i].getType().getSimpleName();//获取属性类型
				String tname=fields[i].getName();	//获取属性名
				if(obj!=null){
					String name=null;
					if((fields[i].getAnnotation(XmlElement.class))!=null){
						name=fields[i].getAnnotation(XmlElement.class).name();
					}
//					System.out.println(name); 输出标签信息
					if(fields[i].getDeclaredAnnotations().length<1||!"IsId".equals(name)){
						if("String".equals(type)){
							sb.append(tname+"='"+obj+"', ");
						}else if("double".equals(type)){
							sb.append(tname+"="+obj+", ");
						}else if("date".equals(type)){
							sb.append(tname+"="+obj+", ");
						}
					}else{//判断如果有注解，则认定此主键是主键ID，获取相应的值以便日后使用
						idValue=obj.toString();
						id=tname;
					}
				}
			}
			sb=new StringBuffer(sb.substring(0,sb.lastIndexOf(",")));
			sb.append(" where "+id+"='"+idValue+"'");
			sql=sb.toString();
			pre=conn.prepareStatement(sql);
//			System.out.println(sql);  输出sql信息
			
			return pre.executeUpdate();
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 数据库反射查询功能
	 */
	@Override
	public  List<T> query(T t){
		// TODO Auto-generated method stub
		List list=new ArrayList();
		PreparedStatement pre =null;
		ResultSet rs = null;
		conn=(Connection) JdbcUtils.getConnection();
		
		try {
			String sql="";
			Object obj=null;
			Class<?extends Object>c=t.getClass();
			String cName=c.getSimpleName();
			StringBuffer sb=new StringBuffer("select* from "+cName+" where 1=1 ");
			Field[] fields=c.getDeclaredFields();
			for(int i=0;i<fields.length;++i){
				fields[i].setAccessible(true);
				obj=fields[i].get(t);
				String type=fields[i].getType().getSimpleName();	//获取属性类型
				String tName=fields[i].getName();	//获取属性名
				if(obj!=null){
//					System.out.println(type);	输出类型
					if("String".equals(type)){
						sb.append(" and "+tName+" like "+"'%"+obj+"%'");
					}else if("int".equals(type)&&(int)obj!=-1){
						sb.append(" and "+tName+" = "+obj);
					}else if("double".equals(type)&&(double)obj!=-1){
						sb.append(" and "+tName+" = "+obj);
					}
				}
			}
			sql=sb.toString();
			pre=conn.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next()){
				Object ins=c.newInstance();
				for(int i=0;i<fields.length;++i){
					String type=fields[i].getType().getSimpleName();	//获取属性类型
					String tname=fields[i].getName();	//获取属性名
					if("String".equals(type)){
						fields[i].set(ins, rs.getString(tname));
					}else if("Date".equals(type)){
						fields[i].set(ins, rs.getDate(tname));
					}else if("int".equals(type)){
						fields[i].set(ins, rs.getInt(tname));
					}else if("double".equals(type)){
						fields[i].set(ins, rs.getDouble(tname));
					}else if("float".equals(type)){
						fields[i].set(ins, rs.getFloat(tname));
					}
				}
				list.add(ins);
			}
			System.out.println(sql);  //输出sql语句
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | SQLException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
