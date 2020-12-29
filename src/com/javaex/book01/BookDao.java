package com.javaex.book01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	//필드
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "webdb";
	String pw = "webdb";
	//생성자 : 디폴트만 만든다. 생략 가능. (다른 생성자가 생기면 만들어야 겠지?)
	
	//메소드 g/s
	
	//메소드 일반
	
	//북 수정하기
	public int bookUpdate(BookVo bookVo) {
	
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
			    // 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);
				
			    // 2. Connection 얻어오기
				//String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
				conn = DriverManager.getConnection(url, id, pw);

			    // 3. SQL문 준비 / 바인딩 / 실행
				String query ="";
				query += " update book " ;
				query += " set title = ? ,";
				query += "     pubs = ? ,";
				query += "     pub_date = ? ,";
				query += "     author_id = ? ";
				query += " where book_id = ? ";
				
				pstmt = conn.prepareStatement(query);//쿼리로 만들기
				
				pstmt.setString(1, bookVo.title);
				pstmt.setString(2, bookVo.pubs);
				pstmt.setString(3, bookVo.pub_date);
				pstmt.setString(4, bookVo.authorId);
				pstmt.setInt(5, bookVo.bookId);
				
				System.out.println(query);
				
				int count = pstmt.executeUpdate();
				
			    // 4.결과처리
				System.out.println("[dao]"+ count+ "건 수정");
				
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

	//책 삭제하기
	public int bookDelete(int bookId) {
	// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);

				// 2. Connection 얻어오기
				//String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, id, pw);
				System.out.println("접속성공");
				
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = ""; // 쿼리문 문자열만들기, ? 주의
				query += " delete book ";
				query += " where book_id = ? ";
				
				pstmt = conn.prepareStatement(query); // 쿼리로 만들기

				System.out.println(query);
				
				pstmt.setInt(1, bookId);
				
				int count = pstmt.executeUpdate();

				// 4.결과처리
				System.out.println("[dao]" + count + "건 삭제");

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
	    // 북 리스트 가져오기
		public List<BookVo> searchBook(String search){
			
			List<BookVo> bookList = new ArrayList<BookVo>(); 
			
				// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
	
				try {
					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName(driver);
	
					// 2. Connection 얻어오기
					//String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, id, pw);
					System.out.println("접속성공");
		
					// 3. SQL문 준비 / 바인딩 / 실행
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
						int bookId = rs.getInt("book_id");
						String title = rs.getString("title");
						String pubs = rs.getString("pubs");
						Date pubDate = rs.getDate("pub_date");
						String authorName = rs.getString("author_name");
						
						BookVo vo = new BookVo(bookId, title, pubs, pubDate, authorName);
						bookVoList.add(vo);
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
		
		//북 저장기능
		public int bookInsert(String name, String desc) {
			
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
			    // 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);
				
			    // 2. Connection 얻어오기
				//String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
				conn = DriverManager.getConnection(url, id, pw);

			    // 3. SQL문 준비 / 바인딩 / 실행
			
				String query ="";
				query += " insert into book " ;
				query += " values(seq_book.nextval, ?, ?, ?, ? ";
				
				pstmt = conn.prepareStatement(query);//쿼리로 만들기
				pstmt.setString(1, bookVo.title);
				pstmt.setString(2, bookVo.pubs);
				pstmt.setString(3, bookVo.pub_date);
				pstmt.setInt(4, bookVo.authorId);
				
				System.out.println(query);
				
				
				int count = pstmt.executeUpdate();
				
			    // 4.결과처리
				System.out.println("[dao]"+ count + "건 등록");
				
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