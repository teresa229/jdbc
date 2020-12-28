package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bookdelete {

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
			String query ="";
			query += " update book " ;
			query += " set title = ? ,";
			query += "     pubs = ? ,";
			query += "     pub_date = ? ,";
			query += "     author_id = ? ";
			query += " where book_id = ? ";
		    
			//System.out.println(query);
			
			pstmt = conn.prepareStatement(query);//쿼리로 만들기
			pstmt.setNString(1,"29년");
			pstmt.setNString(2,"비대면수업");
			pstmt.setNString(3,"20-12-29");
			pstmt.setInt(4,2);
			pstmt.setInt(5,11);
			
			int count = pstmt.executeUpdate();
			
		    // 4.결과처리
			System.out.println(count + "건이 삭제 되었습니다.");

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
