package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductOracleRepository;
import com.my.repository.ProductRepository;

/**
 * Servlet implementation class ProductListServlet
 */
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
//		List<Map<String, Object>> sample = new ArrayList<>();
		ProductRepository repository = new ProductOracleRepository();
		List<Product> products;
		try {
		products = repository.selectAll();
		} catch (FindException e) {
			e.printStackTrace();
			products = new ArrayList<>();
		}
		//---------------------------------
		
		String result = "[";
		for(int i=0; i<products.size(); i++) {
			if(i > 0) {
				result +=",";
			}
			Product p = products.get(i);
			result += "{";
			result += "\"prod_no\":"; result += "\"" + p.getProdNo()  + "\""; result +=",";
			result += "\"prod_name\":"; result += "\"" + p.getProdName()  + "\""; result +=",";	
			result += "\"prod_price\":"; result += p.getProdPrice(); 
			result += "}";
		}
		result += "]";
		out.print(result);		
	}

}


























