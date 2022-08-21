package com.my.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dto.Product;

/**
 * Servlet implementation class AddCartServlet
 */
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodNo = request.getParameter("prod_no");
		String quantity = request.getParameter("quantity");

		
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");                                                                                     
		if(cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}

		Product p = new Product(); p.setProdNo(prodNo);
		int newQuantity = Integer.parseInt(quantity);
		Integer oldQuantity = cart.get(p);
		if(oldQuantity != null) { //상품이 있는 경우
			newQuantity+= oldQuantity;
		}
		cart.put(p, newQuantity);

		System.out.println("장바구니 목록수 :" + cart.size());
		System.out.println(cart);
	}
}
		
		
		
		

//		Map<Product, Integer> map = new HashMap<>();
//		cart.add(map);
//		
		
//		boolean exist = false; //상품 존재 여부
//		outer : for(Map<Product, Integer> map: cart) {
//			Set<Product> products = map.keySet(); //장바구니의 상품들
//			inner : for(Product p: products) { //상품 
//				if(p.getProdNo().equals(prodNo)) { //상품번호가 있으면
//					//수량만 증가, 반복종료
//					int oldQuantity = map.get(p);
//					map.put(p, oldQuantity+Integer.parseInt(quantity));
//					exist = true;
//					break outer;
//					
//				}
//			}
//		}
//		if(!exist) {
//		//장바구니에 추가
//		Product p = new Product();
//		p.setProdNo(prodNo);
//		Map<Product, Integer> map = new HashMap<>();
//		map.put(p, Integer.parseInt(quantity));
//		cart.add(map);
//		}
		

	
	
	
	
	
	
	
	