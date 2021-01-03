package com.javaex.book02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	int count=0;
	//생성자 : 디폴트만 만든다. 생략 가능. (다른 생성자가 생기면 만들어야 겠지?)
	//메소드 g/s
	//메소드 일반
	
	//DB접속
	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
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
	
	//북 저장기능 (insert)
	public int bookInsert(BookVo bookVo) {  //String name, String desc
		
			getConnection();
	
			try {
				String query ="";
				query += " insert into book " ;
				query += " values(seq_book_id.nextval, ?, ?, ?, ? ) ";
				
				pstmt = conn.prepareStatement(query);//쿼리로 만들기
				
				pstmt.setString(1, bookVo.getTitle());
				pstmt.setString(2, bookVo.getPubs());
				pstmt.setString(3, bookVo.getPub_date());
				pstmt.setInt(4, bookVo.getAuthor_id());
				
				System.out.println(query);
								
				count = pstmt.executeUpdate();
				
			    // 4.결과처리
				System.out.println("[dao]"+ count + "건 등록");
				
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			close();
			return count;
		}
		
	// 북 리스트 가져오기 (list)
	public List<BookVo> getbookList(){
			List<BookVo> bookList = new ArrayList<BookVo>(); 
			
			getConnection();

			try {
				String query = "";
				query += "select	bo.book_id,";
				query += " 			bo.title,";
				query += " 			bo.pubs,";
				query += " 			bo.pub_date,";
				query += " 			bo.author_id,";
				query += " 			au.author_name,";
				query += " 			au.author_desc";
				query += " from book bo left outer join author au ";
				query += " on bo.author_id = au.author_id ";
				query += " order by bo.book_id asc ";
				
				pstmt = conn.prepareStatement(query);
				
				rs = pstmt.executeQuery(); 
				
				// 4.결과처리
				while(rs.next()) {
					int book_id = rs.getInt("book_id");
					String title = rs.getString("title");
					String pubs = rs.getString("pubs");
					String pub_date = rs.getString("pub_date");
					int author_id = rs.getInt("author_id");
					String author_name = rs.getString("author_name");
					String author_desc = rs.getNString("author_desc");
					
					BookVo vo = new BookVo(book_id, title, pubs, pub_date, author_id, author_name, author_desc);
					bookList.add(vo);
				}
			
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			close();
			return bookList;
		}
	
	
	//북 수정하기 (update)
	public int bookUpdate(BookVo bookVo) {
	
			getConnection();
		
			try {
				String query ="";
				query += " update book " ;
				query += " set title = ? ,";
				query += "     pubs = ? ,";
				query += "     pub_date = ? ,";
				query += "     author_id = ? ";
				query += " where book_id = ? ";
				
				pstmt = conn.prepareStatement(query);//쿼리로 만들기
				
				pstmt.setString(1, bookVo.getTitle());
				pstmt.setString(2, bookVo.getPubs());
				pstmt.setString(3, bookVo.getPub_date());
				pstmt.setString(4, bookVo.getAuthor_name()); //불안
				pstmt.setInt(5, bookVo.getBook_id());
				
				System.out.println(query);
				
				count = pstmt.executeUpdate();
				
			    // 4.결과처리
				System.out.println("[dao]"+ count+ "건 수정");
				
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			}
			close();
			return count;
		}

	//책 삭제하기 (delete)
	public int bookDelete(int bookId) {		
		
		    getConnection();

			try {
				String query = ""; // 쿼리문 문자열만들기, ? 주의
				query += " delete from book ";
				query += " where book_id = ? ";
				
				pstmt = conn.prepareStatement(query); // 쿼리로 만들기

				System.out.println(query);
				
				pstmt.setInt(1, bookId);
				
				count = pstmt.executeUpdate();

				// 4.결과처리
				System.out.println("[dao]" + count + "건 삭제");

			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			close();
			return count;
		}
    
		
	
	
	

}