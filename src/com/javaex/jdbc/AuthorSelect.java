package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelect {

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
		    query += " select author_id,";
		    query += "        author_name,";
		    query += "        author_desc";
		    query += " from author ";  //모두 가져오는 것이라 ?표가 없다.
		    
		    System.out.println(query);
		    
		    pstmt = conn.prepareStatement(query); //?표가 없어서 다음줄이 생략된다.
		    
		    rs = pstmt.executeQuery(); //여기서부터 달라진다.
			
		    // 4.결과처리
		    while(rs.next()) {
			    int authorId = rs.getInt("author_id");           //author_id 숫자 1로도 가능하다.
			    String authorName = rs.getString("author_name"); //author_name 숫자 2로도 가능하다.
			    String authorDesc = rs.getString("author_desc"); //author_desc 숫자 3로도 가능하다.
			    
			    System.out.println(authorId + "," + authorName +"," + authorDesc);	
		    }
		    
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
