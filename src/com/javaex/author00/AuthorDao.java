package com.javaex.author00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDao {

	public static void main(String[] args) {
		
	//필드
		
	//생성자
		
	//디폴트 생성자 생략가능
		
	//메소드 g/s
		
	    //작가 저장 기능
		/* insert */
		public int authorInsert(String name, String desc) {
			
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			int count = 0;

			try {
			    // 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");

			    // 2. Connection 얻어오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, "webdb", "webdb");

			    // 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " insert into author ";
				query += " values(seq_author_id.nextval, ?, ? )";
				
				pstmt = conn.prepareStatement(query); 
				
				pstmt.setString(1, name);
				pstmt.setString(2, desc); 
				
				/* 실행 */
				count = pstmt.executeUpdate();      
				
			    // 4.결과처리
				System.out.println("[dao]" + count +"건 저장");

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
			return count;
		}

	}
}
