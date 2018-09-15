package com.ComeOut.login;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ComeOut.DAO.Dao;
import com.ComeOut.entity.User;
import com.ComeOut.imple.DaoImple;
import com.ComeOut.json.myJson;
import com.ComeOut.json.myJsonStev;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Writer out=response.getWriter();
		try {
			User user;
			String str=request.getParameter("login");
			out.write(str);
			System.out.println(str);
			myJson<User> mj=new myJsonStev<>();
			JSONObject jo=mj.JsonA2O(str);
			out.write(jo.toString());
			System.out.println(jo.toString());
			user=mj.Json2User(jo);
			Dao<User> dao=new DaoImple<>(); 
			dao.add(user);
			out.write(user.getOpenid());
			System.out.println(user.getOpenid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
