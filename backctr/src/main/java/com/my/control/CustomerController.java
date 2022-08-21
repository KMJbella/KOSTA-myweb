package com.my.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.CustomerService;

public class CustomerController implements Controller {
	//private CustomerRepository repository;
	
	private CustomerService service;
	
	public CustomerController() {
//		repository = new CustomerOracleRepository();
		service = new CustomerService();
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if ("/login".equals(servletPath)) {// 로그인작업
			return login(request, response);
		} else if ("/signup".equals(servletPath)) {// 가입작업
			return signup(request, response);
		} else if ("/iddupchk".equals(servletPath)) {// 아이디중복확인작업
			return iddupchk(request, response);
		} else if ("/loginstatus".equals(servletPath)) {// 아이디중복확인작업
			return loginstatus(request, response);
		}else if ("/logout".equals(servletPath)) {// 아이디중복확인작업
			return logout(request, response);
		}
		return null;
	}

	private String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청전달데이터 얻기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		// 응답결과
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		String result = null; // String result = "{\"status\": 0}"; //실패

		// 세션(클라이언트별 객체)얻기
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");

		// 비지니스로직 호출
		
//		try {
////			repository.selectByIdAndPwd(id, pwd);// ??
//			Customer c = repository.selectById(id);
//			if(c.getPwd().equals(pwd)) {//재사용성이 낮은 구문을 따로 처리(ex.로그인) 
//										//=> 재사용성이 낮은 특정기능은 생략하고 대체 매서드로 이용
//				map.put("status", 1);
//				session.setAttribute("loginInfo", id);
//			}
//			map.put("status", 1); // 초기값0에서 정상작동하면 1로 덮어쓰기
//			session.setAttribute("loginInfo", id);
//		} catch (FindException e) {
//		}
		try {
			Customer c = service.login(id, pwd);
			map.put("status", 1);
			session.setAttribute("loginInfo",id);
		} catch (FindException e) {
		}
		
		result = mapper.writeValueAsString(map);
		return result;
	}

	private String signup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String buildingno = request.getParameter("buildingno");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
//		String result = "{\"status\":0, \"msg\": \"가입실패\"}";
		String result = null;
		map.put("status", 0);
		map.put("msg", "가입실패");

		Customer customer = new Customer(id, pwd, name, addr, 1, buildingno);
		try {
			repository.insert(customer);
//			result = "{\"status\":1, \"msg\": \"가입성공\"}";
			map.put("status", 1);
			map.put("msg", "가입성공");
		} catch (AddException e) {

		}
		result = mapper.writeValueAsString(map);
		return result;
	}
	private String iddupchk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		map.put("msg", "이미 사용중인 아이디입니다");
//		String result = null;
		try {
			Customer c = repository.selectById(id); //이미 존재하는 아이디인 경우
//			map.put("status", 0);
//			map.put("msg", e.getMessage());
		} catch (FindException e) { //사용가능한 아이디인 경우
			map.put("status", 1);
			map.put("msg", "사용가능한 아이디입니다");
		}
		return mapper.writeValueAsString(map);
	}
	
	private String loginstatus(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginInfo");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object>map = new HashMap<>();
		String result = null;
		
		if(loginedId == null) {
			map.put("status", 0);

		}else {
			map.put("status", 1);
		}
		result = mapper.writeValueAsString(map);
		return result;
		}
	
	private String logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		HttpSession session =request.getSession();
		session.removeAttribute("loginInfo");//세션객체는 존재, 속성제거
		//session.invalidate();//세션객체 제거
		//return ""; //응답내용이 비어있으면 빈값?
		return null;
		
	}
}
