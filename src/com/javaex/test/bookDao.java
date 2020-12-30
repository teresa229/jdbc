package com.javaex.test;

public class bookDao {

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

		//필드
		private String driver = "oracle.jdbc.driver.OracleDriver";
		private String url = "jdbc:oracle:thin:@localhost:1521:xe";
		private String id = "webdb";
		private String pw = "webdb";
		//생성자
		//메소드-g/s
		//메소드-일반
		
		//검색하기
		public List<BookVO> BookSearch(String str) {
			List<BookVO> bookList = new ArrayList<BookVO>();
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				// 1. JDBC 드라이버 (Oracle) 로딩                      
				Class.forName(driver);                           
				                                                 
				// 2. Connection 얻어오기                            
				conn = DriverManager.getConnection(url, id, pw); 
				
			    // 3. SQL문 준비 / 바인딩 / 실행
				/*
				select  book.book_id,
				        book.title,
				        book.pubs,
				        book.pub_date,
				        book.author_id,
				        author.author_name,
				        author.author_desc
				from author, book
				where author.author_id = book.author_id
				and (book.title like '%문%'
				or book.pubs like '%문%'
				or author.author_name like '%문%');
				*/
				
				String query = "";
				query += " select  book.book_id,  ";
				query += "         book.title,    ";
				query += "         book.pubs,     ";
				query += "         to_char(book.pub_date, 'YYYY-MM-DD') pub_date, ";
				query += "         book.author_id, ";
				query += "         author.author_name, ";
				query += "         author.author_desc ";
				query += " from author, book ";
				query += " where author.author_id = book.author_id ";
				query += " and (book.title like ? ";
				query += " or book.pubs like ? ";
				query += " or author.author_name like ?) ";
				
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				
				//str = "%" + str + "%";
				
				pstmt.setString(1, "%" + str + "%");
				pstmt.setString(2, "%" + str + "%");
				pstmt.setString(3, "%" + str + "%");
				
				rs = pstmt.executeQuery();

			    // 4.결과처리
				while(rs.next()) {
					int book_id = rs.getInt("book_id");
					String title = rs.getString("title");
					String pubs = rs.getString("pubs");
					String pub_date = rs.getString("pub_date");
					int author_id = rs.getInt("author_id");
					String author_name = rs.getString("author_name");
					String author_desc = rs.getString("author_desc");
					
					//BookVO bookVo = new BookVO(book_id, title, pubs, pub_date, author_id);
					BookVO bookVo = new BookVO(book_id, title, pubs, pub_date, author_id, author_name, author_desc);
					bookList.add(bookVo);
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

			return bookList;
		}
		
		//책 전체를 출력
		public List<BookVO> getAllList() {
			List<BookVO> bookList = new ArrayList<BookVO>();
			
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				// 1. JDBC 드라이버 (Oracle) 로딩                      
				Class.forName(driver);                           
				                                                 
				// 2. Connection 얻어오기                            
				conn = DriverManager.getConnection(url, id, pw); 
				
			    // 3. SQL문 준비 / 바인딩 / 실행
				/*
				select  book.book_id,
				        book.title,
				        book.pubs,
				        book.pub_date,
				        book.author_id,
				        author.author_name,
				        author.author_desc
				from author, book
				where author.author_id = book.author_id;
				*/
				
				String query = "";
				query += " select  book.book_id,  ";
				query += "         book.title,    ";
				query += "         book.pubs,     ";
				query += "         to_char(book.pub_date, 'YYYY-MM-DD') pub_date, ";
				query += "         book.author_id, ";
				query += "         author.author_name, ";
				query += "         author.author_desc ";
				query += " from author, book ";
				query += " where author.author_id = book.author_id ";
				
				System.out.println(query);
				
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
					String author_desc = rs.getString("author_desc");
					
					//BookVO bookVo = new BookVO(book_id, title, pubs, pub_date, author_id);
					BookVO bookVo = new BookVO(book_id, title, pubs, pub_date, author_id, author_name, author_desc);
					bookList.add(bookVo);
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
			return bookList;
		}
		
		
		//삭제(delete)
		public int bookDelete(int bookId) {
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			int count = 0;

			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);

				// 2. Connection 얻어오기
				conn = DriverManager.getConnection(url, id, pw);

			    // 3. SQL문 준비 / 바인딩 / 실행
			    /*
			    delete from book
				where book_id = 9;
			    */
				String query = "";
				query += "delete from book ";
				query += "where book_id = ?";
				
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, bookId);
				
				count = pstmt.executeUpdate();
				
			    // 4.결과처리
				System.out.println(count + " 건 삭제");

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
			return count;
		}
		
		
		//수정(update)
		public int bookUpdate(BookVO bookVo) {
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
		
			int count = 0;
			
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩                      
				Class.forName(driver);                           
				                                                 
				// 2. Connection 얻어오기                            
				conn = DriverManager.getConnection(url, id, pw); 
		
			    // 3. SQL문 준비 / 바인딩 / 실행
				/*
				update book
				set title = '책제목이다',
				    pubs = '김서영',
				    pub_date = '2020-12-14',
				    author_id = 4
				where author_id = 9;
				*/
				String query = "";
				query += " update book         ";
				query += " set title = ? ,     ";
				query += "     pubs = ? ,      ";
				query += "     pub_date = ? ,  ";
				query += "     author_id = ?   ";
				query += " where book_id = ? ";
			    
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, bookVo.getTitle());
				pstmt.setString(2, bookVo.getPubs());
				pstmt.setString(3, bookVo.getPub_date());
				pstmt.setInt(4, bookVo.getAuthorId());
				pstmt.setInt(5, bookVo.getBook_id());
				
				count = pstmt.executeUpdate();
			    // 4.결과처리
				System.out.println("[DAO] : " + count + " 건 수정");
				
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
			return count;
		}
		//조회(select)
		public List<BookVO> getBookList() {
			List<BookVO> bookList = new ArrayList<BookVO>();
			
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				// 1. JDBC 드라이버 (Oracle) 로딩                      
				Class.forName(driver);                           
				                                                 
				// 2. Connection 얻어오기                            
				conn = DriverManager.getConnection(url, id, pw); 
				
			    // 3. SQL문 준비 / 바인딩 / 실행
				/*
				select  book_id,
				        title,
				        pubs,
				        to_char(pub_date, 'YYYY-MM-DD') pub_date,
				        author_id
				from book;
				*/
				
				String query = "";
				query += " select  book_id,  ";
				query += "         title,    ";
				query += "         pubs,     ";
				query += "         to_char(pub_date, 'YYYY-MM-DD') pub_date, ";
				query += "         author_id ";
				query += " from book ";
				
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				
				rs = pstmt.executeQuery();

			    // 4.결과처리
				while(rs.next()) {
					int book_id = rs.getInt("book_id");
					String title = rs.getString("title");
					String pubs = rs.getString("pubs");
					String pub_date = rs.getString("pub_date");
					int author_id = rs.getInt("author_id");
					
					BookVO bookVo = new BookVO(book_id, title, pubs, pub_date, author_id);
					bookList.add(bookVo);
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
			return bookList;
		}
		
		
		//저장(insert)
		public int bookInsert(BookVO bookVo) {
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			int count = 0;
			
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩                      
				Class.forName(driver);                           
				                                                 
				// 2. Connection 얻어오기                            
				conn = DriverManager.getConnection(url, id, pw); 
				
			    // 3. SQL문 준비 / 바인딩 / 실행
				/*
				insert into book
				values (seq_book_id.nextval, '우리들의 일그러진 영웅', '다림', '1998-02-22', 1);
				*/
				
				String query = "";
				query += " insert into book ";
				query += " values (seq_book_id.nextval, ?, ?, ?, ?)";
				
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
			    pstmt.setString(1, bookVo.getTitle());
			    pstmt.setString(2, bookVo.getPubs());
			    pstmt.setString(3, bookVo.getPub_date());
			    pstmt.setInt(4, bookVo.getAuthorId());
			    
			    count = pstmt.executeUpdate();
				
			    // 4.결과처리
			    System.out.println("[DAO] : " + count + " 건 저장");

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
			return count;
		}
	}

}
