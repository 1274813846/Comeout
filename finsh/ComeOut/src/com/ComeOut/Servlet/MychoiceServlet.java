package com.ComeOut.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ComeOut.imple.SqlImple;

/**
 * Servlet implementation class MychoiceServlet
 */
@WebServlet("/mychoiceServlet")
public class MychoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		

		String sid= request.getParameter("sid");
		String mychoose= request.getParameter("mychoose");

	
		String sql= "update base set mychoose = '"+mychoose
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
