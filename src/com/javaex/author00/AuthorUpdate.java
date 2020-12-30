package com.javaex.author00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorUpdate {

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
			
		    /*update author
			  set author_desc = '서울시 서초구 하이미디어 별관'
			  where author_id = 7;     */
			
			/* step 1: 문자열 만들기 */
//			String query =""; //빈문자를 만들고
//			query += "update author"; //+=누적을 의미한다.
//			query += "set author_desc = '서울시 서초구 하이미디어 별관'";
//			query += "where author_id = 7"; 
			
			/* step 2: 문자열 만들기  */
			String query =""; 
			query += " update author "; 
			query += " set author_desc = ? ";
			query += " where author_id = ? ";
			
			/* step 3: 쿼리문으로 만들기 */
			pstmt = conn.prepareStatement(query);   //prepareStatement  //pstmt 리턴형
			pstmt.setString(1,"서울시 서초구 하이미디어 별관");
			pstmt.setInt(2, 8);
			
			/* step 4: 실행 */
			int count = pstmt.executeUpdate(); //몇 개의 값을 수정했는지 알려준다.  //executeUpdate :실행의 역활
			
		    // 4.결과처리
			System.out.println(count + "건이 수정되었습니다.");
			
		} catch (ClassNotFoundException e) {                       //걸리게 해주고 step 1
		    System.out.println("error: 드라이버 로딩 실패 - " + e);      
		} catch (SQLException e) {                                 //걸리게 해주고 step 2
		    System.out.println("error:" + e);
		} finally {                                                //안 걸려도 자원정리
		   
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
