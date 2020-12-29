package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Authorinsert {

	public static void main(String[] args) {
		
		//insert into author values(seq_author_id.nextval,'황일영' '서울시 도봉구');
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;    //control + shift + o 모두 적용되게~

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb"); //DriverManager오류 import해주기

			
		    // 3. SQL문 준비 / 바인딩 / 실행    --정한 이름: conn
			String query = "Insert into author values(seq_author_id.nextval,?,?)"; //마침표를 보내지 않는다. 개발 코드 안에 들어갈 경우. 알아서 자동으로 붙여준다. 정리: 자바코드의 마침표(;)는 넣어주고, 쿼리의 마침표(;)는 넣지 않는다.
			pstmt = conn.prepareStatement(query); //prepareStatement은 static으로 되어있을 것이다. //쿼리로 만들기
			
			pstmt.setString(1,"황일영");           //pstmt.setString(parameterIndex,x); 구조
			pstmt.setString(2,"서울시 도봉구");      //pstmt.setInt(3,~~);가 되겠지
			
			//INSERT INTO author values(seq_author_id.nextval, '황일영','서울시 도봉구');
			int count = pstmt.executeUpdate(); //내용을 보내주는 역활 //=실행하다.
			     
		
		    // 4.결과처리
			System.out.println("건이 등 되었습니다.");

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리  //==>실제로 신경쓰지 않아도 되는 부분
		    try {
		        /*  수업중에는 사용하지 않기때문에 주석처리
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
