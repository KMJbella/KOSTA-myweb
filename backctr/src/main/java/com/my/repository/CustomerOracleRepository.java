package com.my.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

public class CustomerOracleRepository implements CustomerRepository {
	
//	@Override
//	public Customer selectByIdAndPwd(String id, String pwd) throws FindException {
//		//DB와 연결
//		Connection con = null;
//		//SQL송신
//		PreparedStatement pstmt = null;
//		//송신결과
//		ResultSet rs = null;
//		try {
//			con = MyConnection.getConnection();
//			String selectIdNPwdSQL = "SELECT * FROM customer WHERE id=? AND pwd=?";
//			pstmt = con.prepareStatement(selectIdNPwdSQL);
//			pstmt.setString(1, id);
//			pstmt.setString(2, pwd);
//			rs = pstmt.executeQuery();
//			if(rs.next()) { //로그인 성공인 경우
//				return new Customer(rs.getString("id"),
//						rs.getString("pwd"),
//						rs.getString("name"),
//						rs.getString("ADDRESS"),
//						rs.getInt("STATUS"),
//						rs.getString("BUILDINGNO")
//						); 
//			}
//			throw new FindException("고객이 없습니다");
//		}catch(Exception e) {
//			throw new FindException(e.getMessage());
//		}finally {
//			MyConnection.close(rs, pstmt, con);
//		}
//	}

	@Override
	public void insert(Customer customer) throws AddException {
		//DB연결
		Connection con = null;
		//SQL송신
		PreparedStatement pstmt = null; //executeUpdate()
		String insertSQL = "INSERT INTO customer(id,pwd,name, status, buildingno, address) VALUES (?,?,?,1,?,?)";
				
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, customer.getId());
			pstmt.setString(2, customer.getPwd());
			pstmt.setString(3, customer.getName());
			pstmt.setString(4, customer.getBuildingno());
			pstmt.setString(5, customer.getAddress());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			//DB연결닫기
			MyConnection.close(pstmt, con);
		}
	}

	@Override
	public Customer selectById(String id) throws FindException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectIdDupChkSQL = "SELECT * FROM customer WHERE id = ?";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectIdDupChkSQL);
			pstmt.setString(1,  id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Customer(
						rs.getString("id"),
						rs.getString("pwd"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getInt("status"),
						rs.getString("buildngno")
						);
			}
			throw new FindException("고객이 없습니다"); //재사용성이 높은 메세지로 만들 것
													//상세메세지는 컨트롤러에서 만들것, repository에서x
		}catch(SQLException e) {
			throw new FindException(e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
	}


}
