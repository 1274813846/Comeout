package com.ComeOut.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ComeOut.imple.SqlImple;

/**
 * Servlet implementation class RepairServlet
 */
@WebServlet("/repairServlet")
public class RepairServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String teacher= request.getParameter("teacher");
		String sid= request.getParameter("sid");
		String name= request.getParameter("name");
		String major= request.getParameter("major");
		String _class= request.getParameter("_class");
		String psd= request.getParameter("psd");
	
		String sql= "update base set teacher = '"+teacher
				+"' ,name = '"+name
				+"' ,major = '"+major
				+"' ,class = '"+_class
				+"' ,psd = '"+psd
				+"' where sid = "+sid;
		SqlImple.update_sql(sql);
		System.out.println(sql);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
