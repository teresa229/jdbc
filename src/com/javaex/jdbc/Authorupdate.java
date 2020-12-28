package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Authorupdate {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null; --범용으로 적어 둔 것이라 가려준다.

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; //현재 상황을 반드시 확인해야한다.
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		    // 3. SQL문 준비 / 바인딩 / 실행
			/*
			update author
			set author_name = '하이미디어',
			    author_desc = '서울시 강남구'
			where author_id = 7;
			*/
			String query ="";
			query += " update author" ;
			query += " set author_desc = ? ";
			query += " where author_id = ? ";
			
			pstmt = conn.prepareStatement(query);//쿼리로 만들기
			pstmt.setNString(1, "서울시 서초구 하이미디어 별관");
			pstmt.setInt(2,7);
			
			System.out.println(query);
			/*
			update author
			set author_name = '하이미디어',
			author_desc = '서울시 강남구'
			where author_id = 7;
			*/
			int count = pstmt.executeUpdate();
		    
		    // 4.결과처리
			System.out.println("건이 수정되었습니다.");
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		
		    try {
		    	
		    	/*
		        if (rs != null) {
		            rs.close();
		        } 
		        
		        */               
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}


	}

}
