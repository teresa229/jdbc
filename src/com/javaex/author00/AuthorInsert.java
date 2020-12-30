package com.javaex.author00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorInsert {

	public static void main(String[] args) {
	
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			/* 준비 */
			String query = "insert into author values(seq_author_id.nextval, ?, ? )";
			
			/* 바인딩 */
			pstmt = conn.prepareStatement(query); //14번의 conn, 28번의 query, pstmt만들어준 이름
			
			pstmt.setNString(1, "김경아");
			pstmt.setNString(2, "서울시 강남구"); // 2: 28번의 두번째 물음표 의미
			
			/* 실행 */
			int count = pstmt.executeUpdate();      
			
		    // 4.결과처리
			System.out.println("건이 처리 되었습니다.");

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
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

