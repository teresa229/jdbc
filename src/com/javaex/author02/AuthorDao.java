package com.javaex.author02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	//필드
	// 0. import java.sql.*;
	private Connection conn = null;          //public 대신 private  
	private PreparedStatement pstmt = null;  //public 대신 private
	private ResultSet rs = null;             //select를 위해 올려준다. public 대신 private
		
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
			
	//생성자 : 디폴트만 만든다. 생략 가능. (다른 생성자가 생기면 만들어야 겠지?)
	
	//메소드 g/s
	
	//메소드 일반
	
	/* DB접속 */
	private void getConnection() {  //public 대신 : 다른 곳에서 사용하지 못하도록 private로 만든다.
		
		//1.JDBC 드라이브(Oracle)로딩
		try{
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

		    // 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
			System.out.println("[접속성공]");
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
	}
	
	/* 자원정리  */
	private void close() { //public 대신 : 다른 곳에서 사용하지 못하도록 private로 만든다.
		
		//5. 자원정리
	    try {
	    	if (rs != null) {
		            rs.close();
		        }                   //select에 있는 것이라 올려준다.              
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
	
	
	//작가 저장 기능 (insert)
	public int authorInsert(AuthorVo authorVo) {
		
		//DB접속
		getConnection();
			

			int count = 0;
	
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
				String query ="";
				query += " insert into author" ;
				query += " values(seq_author_id.nextval, ?, ? )";

				System.out.println(query);
							
				pstmt = conn.prepareStatement(query);//쿼리로 만들기
				
				pstmt.setString(1, authorVo.authorName);
				pstmt.setString(2, authorVo.authorDesc);
				
				count = pstmt.executeUpdate(); //int count
				
			    // 4.결과처리
				System.out.println("[dao]" + count + " 건 등록");
	
			} catch (SQLException e) { //나머지 문제 잡아줌
			    System.out.println("error:" + e);
			} 
			
			//자원정리
			close(); //finally {}
			
			return count;
		}
	
	//작가 (Update)
	public int authorUpdate(AuthorVo authorVo) { 
		
		int count = 0;	
		
		//DB접속
		getConnection();

	
		try {
		     // 3. SQL문 준비 / 바인딩 / 실행
			String query ="";
			query += " update author ";
			query += " set author_name = ?, ";
			query += "     author_desc = ? ";
			query += " where author_id = ? ";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,authorVo.getAuthorName());
			pstmt.setString(2,authorVo.getAuthorDesc());
			pstmt.setInt(3,authorVo.getAuthorId());
			
			count = pstmt.executeUpdate(); 
		    
		    // 4.결과처리
			System.out.println("[dao]"+ count + " 건 수정");
			
	
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		//자원정리
		close(); //finally {}
	
		return count;
	}
	
	
	//작가 (delete)
		public int authorDelete(int authorId) {
			
			int count = 0;
			
			//DB접속
			getConnection();
		
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
				String query ="";
				query += " delete from author "; 
				query += " where author_id = ? ";
				
				//테스트
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, authorId); 
				
				count = pstmt.executeUpdate(); //int count = pstmt.executeUpdate();
		
			    // 4.결과처리
				System.out.println("[dao]" + count + " 건 삭제");
		
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			//자원정리
			close(); //finally {}
			
			return count;
		}
	
		//작가 리스트 가져오기
		public List<AuthorVo> getAuthorList(){
			
		   List<AuthorVo> authorVoList = new ArrayList<AuthorVo>();
		
		   //DB접속
		   getConnection();
		   
				try {
				    // 3. SQL문 준비 / 바인딩 / 실행
				    String query ="";
				    query += " select author_id,";
				    query += "        author_name,";
				    query += "        author_desc";
				    query += " from author ";
				    
				    //System.out.println(query);
				    
				    pstmt = conn.prepareStatement(query); //쿼리 만들기
				    rs = pstmt.executeQuery(); //쿼리 날리기
				    
				    // 4.결과처리
				    // rs에 있는 데이터를 List<AuthorVo>로 구성해야 한다.
				    while(rs.next()) {
					    int authorId = rs.getInt("author_id");           //author_id 숫자 1로도 가능하다.
					    String authorName = rs.getString("author_name"); //author_name 숫자 2로도 가능하다.
					    String authorDesc = rs.getString("author_desc"); //author_desc 숫자 3로도 가능하다.
					    			   
				        AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				        authorVoList.add(vo);
				    }
			
				} catch (SQLException e) {
				    System.out.println("error:" + e);
				} 
				
				//자원정리
				close(); 
				
				return authorVoList;
		}
}
