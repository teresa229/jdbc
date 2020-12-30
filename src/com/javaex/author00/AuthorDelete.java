package com.javaex.author00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorDelete {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			/*delete from author
              where author_id = 7;*/
			
			/* step 1: 문자열 만들기  */
			String query =""; 
			query += " delete from author "; 
			query += " where author_id = ? ";
			
			/* step 2: 쿼리문으로 만들기 */
			pstmt = conn.prepareStatement(query);   
			pstmt.setInt(1,8);
			
			/* step 3: 실행 */
			int count = pstmt.executeUpdate(); 
			
		    // 4.결과처리
			System.out.println(count + "건이 수정되었습니다.");
			
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


