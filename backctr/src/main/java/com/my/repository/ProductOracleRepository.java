package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

public class ProductOracleRepository implements ProductRepository {

	@Override
	public void insert(Product product) throws AddException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> selectAll() throws FindException {
//		List<Map<String,Object>> sample = new ArrayList<>();
		List<Product> products = new ArrayList<>();
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectProductAllSQL = "SELECT * FROM product ORDER BY prod_no ASC";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectProductAllSQL);
			//pstnt.setString(1, ~~~); //바인더변수? 쓸 필요 없음
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String prod_no = rs.getString("prod_no");//상품목록을 DB에서 가져옴
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
//				Map<String,Object> map1 = new HashMap<>();
//				map1.put("prod_no", prod_no);
//				map1.put("prod_name", prod_name);
//				map1.put("prod_price", prod_price);
				Product p = new Product(prod_no, prod_name, prod_price);
				
//				sample.add(map1);
				products.add(p);
			}
			if(products.size() == 0) {
				throw new FindException("상품이 없습니다");
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con); 
		}
//		return null;
	}

	@Override
	public Product selectByProdNo(String prodNo) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectProdNoSQL = "SELECT * FROM product WHERE prod_no=?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectProdNoSQL);
			pstmt.setString(1,  prodNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String prodName = rs.getString("prod_name");
				int prodPrice = rs.getInt("prod_price");
				String prodInfo = rs.getString("prod_info");
				java.sql.Date prodMfd = rs.getDate("PROD_MFD");
				
				Product p = new Product(prodNo, 
						prodName, 
						prodPrice, 
						prodInfo, 
						prodMfd);
				return p;
			}else {
				throw new FindException("상품이 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs,  pstmt, con);
		}
	}

	@Override
	public List<Product> selectByProdNoOrProdName(String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

}
