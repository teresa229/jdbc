package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "Insert into book values(seq_book_id.nextval,?,?,?,?)"; 
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,"28년");           
			pstmt.setString(2,"수업주의");
			pstmt.setString(3,"20-12-28");
			pstmt.setInt(4,2);
			
			//INSERT INTO book values(seq_book_id.nextval, '28년', '수업주의', '20-12-28', '2');
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
