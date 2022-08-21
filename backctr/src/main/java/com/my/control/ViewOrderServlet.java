package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.OrderInfo;
import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.OrderOracleRepository;
import com.my.repository.OrderRepository;

public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1.요청전달데이터
		String loginedId = request.getParameter("orderId");
//		String orderNo = request.getParameter("order_no");
//		String orderDt = request.getParameter("order_dt");
//		String orderP = request.getParameter("order_p");
//		String prodName = request.getParameter("prod_name");
//		String prodNo = request.getParameter("prod_no");
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String result = "";

		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");
		
		if(loginedId == null) {
			Map<String, Object> map = new HashMap<>();
			map.put("status", 0);
			map.put("msg", "로그인하세요");
			result = mapper.writeValueAsString(map);
		}else {
			OrderRepository repository = new OrderOracleRepository();
			try {
				List<OrderInfo> infos = repository.selectById(loginedId);
				result = mapper.writeValueAsString(infos);
				System.out.println(result);
			} catch (FindException e) {
				e.printStackTrace();
				Map<String, Object> map = new HashMap<>();
				map.put("status", -1);
				map.put("msg", e.getMessage());
				result = mapper.writeValueAsString(map);
			}
		}
	}

}
