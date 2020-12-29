package com.javaex.book01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.author01.AuthorVo;

public class BookDao {

	//필드
	//생성자 : 디폴트만 만든다. 생략 가능. (다른 생성자가 생기면 만들어야 겠지?)
	
	//메소드 g/s
	
	//메소드 일반
	
	//북 수정하기
	public int bookUpdate(String title, String pubs, String pub_date, int author_id) {
	
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
				  /*
				  update book
	              set title = '29년',
	                  pubs = '비대면수업',
	                  pub_date = '20-12-29',
	                  author_id = '2'
	               where book_id = 9;  */
				
				String query ="";
				query += " update book " ;
				query += " set title = ? ,";
				query += "     pubs = ? ,";
				query += "     pub_date = ? ,";
				query += "     author_id = ? ";
				query += " where book_id = ? ";
				
				pstmt = conn.prepareStatement(query);//쿼리로 만들기
				pstmt.setNString(1,"29년");
				pstmt.setNString(2,"비대면수업");
				pstmt.setNString(3,"20-12-29");
				pstmt.setInt(4,2);
				pstmt.setInt(5,11);
				
				System.out.println(query);
				
				
				int count = pstmt.executeUpdate();
				
			    // 4.결과처리
				System.out.println("건이 수정되었습니다.");
				
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
				Class.forName("oracle.jdbc.driver.OracleDriver");

				// 2. Connection 얻어오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, "webdb", "webdb");
				System.out.println("접속성공");
				
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = ""; // 쿼리문 문자열만들기, ? 주의
				query += " delete from book ";
				query += " where book_id = ? ";
				pstmt = conn.prepareStatement(query); // 쿼리로 만들기

				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				int count;
				pstmt.setInt(1, bookId);// ?(물음표) 중 1번째, 순서중요
				count = pstmt.executeUpdate(); // 쿼리문 실행

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
		public List<BookVo> getbookList(){
			
			List<BookVo> bookList = new ArrayList<BookVo>(); 
			
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
					System.out.println("접속성공");
		
					// 3. SQL문 준비 / 바인딩 / 실행
					String query = ""; 
					query += " select book_id, ";
					query += "        title, ";
					query += "        pubs, ";
					query += "        pub_date, ";
					query += "        author_id ";
					query += " from book ";
					
					System.out.println(query);
					pstmt = conn.prepareStatement(query); 
					rs = pstmt.executeQuery(); 
					
					// 4.결과처리
					while(rs.next()) {
						int bookId = rs.getInt("book_id");
						String bookName = rs.getString("title");
						String bookPubs = rs.getString("pubs");
						String bookPubdate = rs.getString("pub_date");
						String bookAuthorid = rs.getString("author_id");
						
						BookVo vo = new BookVo(book_id, title, pubs, pub_date, author_id);
						bookList.add(vo);
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
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
			    // 2. Connection 얻어오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
				conn = DriverManager.getConnection(url, "webdb", "webdb");

			    // 3. SQL문 준비 / 바인딩 / 실행
			
				String query ="";
				query += " update book " ;
				query += " set title = ? ,";
				query += "     pubs = ? ,";
				query += "     pub_date = ? ,";
				query += "     author_id = ? ";
				query += " where book_id = ? ";
				
				pstmt = conn.prepareStatement(query);//쿼리로 만들기
				pstmt.setNString(1,"29년");
				pstmt.setNString(2,"비대면수업");
				pstmt.setNString(3,"20-12-29");
				pstmt.setInt(4,2);
				pstmt.setInt(5,11);
				
				System.out.println(query);
				
				
				int count = pstmt.executeUpdate();
				
			    // 4.결과처리
				System.out.println("건이 수정되었습니다.");
				
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
		
		
		
		
		}
	
}
