package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답형식설정: MIME(text/plain, text/html, application/json)-응답형태를 지정하지 않으면 html형식을 적용하려 할 것임??
		response.setContentType("text/html;charset=UTF-8"); //기본 인코딩 ISO_88859_1
		
		PrintWriter out = response.getWriter();	//응답출력스트림 얻기
		//System.out의 자료형은 PrintStream
		out.print("<html>");//응답출력스트림에 쓰기
		out.print("<body>");
		for(int i=1; i<=5; i++) {
			out.println("<h"+i+">");//클라이언트에게 응답할때 줄바꿈하고 싶다면 println => print가 더 네트워크 속도 UP
			out.println("제목"+ i);
			out.println("</h" + i+">");
		}
		out.print("</body>");
		out.print("</html>");
		
	}

}
