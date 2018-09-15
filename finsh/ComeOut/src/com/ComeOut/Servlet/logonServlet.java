package com.ComeOut.Servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.ComeOut.imple.SqlImple;

/**
 * Servlet implementation class logonServlet
 */
@WebServlet("/logonServlet")
public class logonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String userid=(String)request.getParameter("userid");
		String psd=(String)request.getParameter("psd");
		System.out.println(userid+psd);
		
		String sql="select * from base where sid = "+userid+" and psd = "+psd;
		JSONArray json_array=new JSONArray();
		json_array=SqlImple.query_sql(sql);
		String str=json_array.toString();
		Writer out=response.getWriter();
		out.write(str);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
