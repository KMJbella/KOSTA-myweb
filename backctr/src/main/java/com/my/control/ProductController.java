package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductOracleRepository;
import com.my.repository.ProductRepository;

public class ProductController implements Controller {
	private ProductRepository repository;

	public ProductController() {
		repository = new ProductOracleRepository();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if ("/productlist".equals(servletPath)) {// 상품목록
			return list(request, response);
		} else if ("/viewproduct".equals(servletPath)) {
			return view(request, response);
		}
		return null;
	}

	private String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ProductRepository repository = new ProductOracleRepository();
		ObjectMapper mapper = new ObjectMapper();

		List<Product> products;
		try {
			products = repository.selectAll();
		} catch (FindException e) {
			e.printStackTrace();
			products = new ArrayList<>();
		}
		String result = mapper.writeValueAsString(products);
		return result;

	}

	private String view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prod_no = request.getParameter("prod_no");
		String result = "";
		try {
			Product p = repository.selectByProdNo(prod_no);
			Map<String, Object> map = new HashMap<>();
			map.put("status", 1);
			map.put("p", p);
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(map);
		} catch (FindException e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<>();
			map.put("status", 0);
			map.put("msg", e.getMessage());

			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(map);
		}
		System.out.println("result : " + result);
		return result;
	}
}
