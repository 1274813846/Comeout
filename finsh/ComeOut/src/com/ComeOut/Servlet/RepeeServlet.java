package com.ComeOut.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ComeOut.imple.SqlImple;

/**
 * Servlet implementation class RepeeServlet
 */
@WebServlet("/repeeServlet")
public class RepeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String first= request.getParameter("first");
		String second= request.getParameter("second");
		String third= request.getParameter("third");
		String finalschool= request.getParameter("finalschool");
		String type= request.getParameter("type");
		String major= request.getParameter("major");
		String sid= request.getParameter("sid");
		
		String sql= "update pee set first = '"+first
				+"' ,second = '"+second
				+"' ,third = '"+third
				+"' ,finalschool = '"+finalschool
				+"' ,major = '"+major
				+"' ,type = '"+type
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
