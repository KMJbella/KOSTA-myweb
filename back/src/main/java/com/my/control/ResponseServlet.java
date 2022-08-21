package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseServlet
 */
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답형식설정 : MIME(text/plain, text/html, application/json)
		response.setContentType("text/html;charset=UTF-8"); //기본인코딩 ISO_88859_1
		
		PrintWriter out = response.getWriter(); //응답출력스트림 얻기
		//System.out의 자료형은 PrintStream
		out.print("<html>"); //응답출력스트림에 쓰기
		out.print("<body>");
		for(int i=1; i<=5; i++) {
			out.print("<h" + i + ">");
			out.print("제목" + i);
			out.print("</h" + i+ ">");
		}
		out.print("</body>");
		out.print("</html>");
	}

}
