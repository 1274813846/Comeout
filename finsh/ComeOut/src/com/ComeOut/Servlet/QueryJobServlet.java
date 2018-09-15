package com.ComeOut.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ComeOut.DAO.Dao;
import com.ComeOut.entity.Job;
import com.ComeOut.imple.DaoImple;
import com.ComeOut.json.myJson;
import com.ComeOut.json.myJsonStev;

/**
 * Servlet implementation class QueryJobServlet
 */
@WebServlet("/queryJobServlet")
public class QueryJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");


		try {
			Job job;
			String str=request.getParameter("jobquery");

			System.out.println(str);
			myJson<Job> mj=new myJsonStev<>();
			JSONObject reqjo=mj.JsonA2O(str);

			System.out.println(reqjo.toString());
			job=mj.Json2Job(reqjo);
			Dao<Job> dao=new DaoImple<>(); 
			List<Job>jobs=dao.query(job);
			JSONArray reqja=new JSONArray(jobs);
			
			
			JSONObject respjo=null;
			JSONArray respja=new JSONArray();
			if(jobs.size()>0){
				for(Job item:jobs){
					respjo=new JSONObject(item);
					respja.put(respjo);
				}
			}
			PrintWriter out=response.getWriter();
			out.println(respja.toString());
			System.out.println(respja.toString());

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
