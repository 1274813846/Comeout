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
 * Servlet implementation class PeeServlet
 */
@WebServlet("/peeServlet")
public class PeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String source=request.getParameter("source");
		String Professional_code=request.getParameter("Professional_code");
		String years=request.getParameter("years");
		System.out.println(source+Professional_code+years);
		String sql=String.format("select * from Retest_fractional_line where source like '%%%s%%' and Professional_code like '%%%s%%' and years like '%%%s%%' ",source,Professional_code,years);
		System.out.println(sql);
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
