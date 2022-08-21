package com.my.control;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCycleServlet
 */
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifeCycleServlet() {
    	System.out.println("LifeCycleServlet의 생성자 호출됨");
//    	ServletContext sc = this.getServletContext();
//    	String developer = sc.getInitParameter("developer");
//    	System.out.println(developer);
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config); //
		System.out.println("LifeCycleServlet의 init() 호출됨");
		
		ServletContext sc = this.getServletContext();
    	String developer = sc.getInitParameter("developer");
    	System.out.println(developer);
    	
    	String fileName = this.getInitParameter("fileName");
    	System.out.println(fileName);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("LifeCycleServlet의 destroy() 호출됨");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 sercive() 호출됨");
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 doDet() 호출됨");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet의 doPost() 호출됨");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println("요청전달데이터 id=" + id + ", pwd=" + pwd);
	}

}



















