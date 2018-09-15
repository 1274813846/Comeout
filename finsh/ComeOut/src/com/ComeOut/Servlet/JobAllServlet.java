package com.ComeOut.Servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ComeOut.DAO.Dao;
import com.ComeOut.entity.Job;
import com.ComeOut.entity.User;
import com.ComeOut.imple.DaoImple;
import com.ComeOut.json.myJson;
import com.ComeOut.json.myJsonStev;

/**
 * Servlet implementation class JobAllServlet
 */
@WebServlet("/jobAllServlet")
public class JobAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		Dao<Job> dp = new DaoImple<>();
		myJson<Job> mj=new myJsonStev<>();
		List<Job> list=dp.query(new Job());
		String str=mj.Class2Json(list).toString();
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
