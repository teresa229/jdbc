package com.javaex.book02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
			
	//DB 접속
	private void getConnection() {
		
	try {
		 Class.forName(driver);
		 conn = DriverManager.getConnection(url, id, pw);
		 
		 System.out.println("[접속성공]");
			
		}catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
	
	}
	
	private void close() {
		
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
	//작가 (Update)
	public int authorUpdate(AuthorVo authorVo) { 
		
		getConnection();
		
		int count = 0;
	
		try {
			String query ="";
			query += " update author ";
			query += " set author_name = ?, ";
			query += "     author_desc = ? ";
			query += " where author_id = ? ";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,authorVo.getAuthor_name());
			pstmt.setString(2,authorVo.getAuthor_desc());
			pstmt.setInt(3,authorVo.getAuthor_id());
			
			count = pstmt.executeUpdate(); 
		    
		    // 4.결과처리
			System.out.println("[dao]"+ count + " 건 수정");
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		close();
		return count;
	}
	
	
	//작가 (delete)
	public int authorDelete(int authorId) {
			
		getConnection();
		
		int count = 0;
	
		try {
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
		
			close();
			return count;
		}
	
	 //작가 리스트 가져오기
	 public List<AuthorVo> getAuthorList(){
			
	   List<AuthorVo> authorVoList = new ArrayList<AuthorVo>();
		
	   getConnection();
	   
		try {
		    String query ="";
		    query += " select author_id,";
		    query += "        author_name,";
		    query += "        author_desc";
		    query += " from author ";
		    				    
		    pstmt = conn.prepareStatement(query); //쿼리 만들기
		    rs = pstmt.executeQuery(); //쿼리 날리기
		    
		    // 4.결과처리
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
			
		close();
		return authorVoList;
		}
		
		
	//작가 저장 기능 (insert)
	public int authorInsert(AuthorVo authorVo) {
		
	    getConnection();
	    
		int count = 0;

		try {
			String query ="";
			query += " insert into author" ;
			query += " values(seq_author_id.nextval, ?, ? )";

			System.out.println(query);
						
			pstmt = conn.prepareStatement(query);//쿼리로 만들기
			
			pstmt.setString(1, authorVo.author_name);
			pstmt.setString(2, authorVo.author_desc);
			
			count = pstmt.executeUpdate(); //int count
			
		    // 4.결과처리
			System.out.println("[dao]" + count + " 건 등록");

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		close();
		return count;
		}
}
