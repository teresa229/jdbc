package com.javaex.test;

public class AuthorDao {

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
		//디폴트 생성자 생략(다른 생성자 없음)
		
		
		//메소드 - 일반
		//작가 수정하기
		public int authorUpdate(AuthorVO authorVo) {
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
			    update author
				set author_name = '김서영',
				    author_desc = '경기도 수원시'
				where author_id = 1;
			    */
				String query = "";
				query += " update author         ";
				query += " set author_name = ? , ";
				query += "     author_desc = ?   ";
				query += " where author_id = ?   ";
				
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, authorVo.getAuthorName());
				pstmt.setString(2, authorVo.getAuthorDesc());
				pstmt.setInt(3, authorVo.getAuthorId());
				
				count = pstmt.executeUpdate();
				
			    // 4.결과처리
				System.out.println(count + " 건 수정");
		
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
		
		
		//작가 삭제하기
		public int authorDelete(int authorId) {
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
			    delete from author
				where author_id = 1;
			    */
				String query = "";
				query += "delete from author ";
				query += "where author_id = ?";
				
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, authorId);
				
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
		
		//작가테이블 리스트 가져오기
		public List<AuthorVO> getAuthorList() {
			
			List<AuthorVO> authorList = new ArrayList<AuthorVO>();
			
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
				String query = "";
				query += " select  author_id,   ";
				query += " 		   author_name, ";
				query += "         author_desc  ";
				query += " from author          ";
				
				System.out.println(query);

				pstmt = conn.prepareStatement(query);
				
				rs = pstmt.executeQuery();
			    
			    // 4.결과처리
				//rs에 있는 데이터를 List에 담아줘야함.(List<AuthorVO>로 구성)
				while(rs.next()) {
					int authorId = rs.getInt("author_id");
					String authorName = rs.getString("author_name");
					String authorDesc = rs.getString("author_desc");
					
					AuthorVO vo = new AuthorVO(authorId, authorName, authorDesc);
					authorList.add(vo);
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
			return authorList;
		}
		
		//작가 저장 기능
		public int AuthorInsert(AuthorVO aurhorVo) {
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
				insert into author 
				values (SEQ_AUTHOR_ID.nextval, '김서영', '경기도 수원시'); 
				*/
				
				String query = "";
				query += " insert into author ";
				query += " values (SEQ_AUTHOR_ID.nextval, ?, ?) ";
				
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, aurhorVo.getAuthorName());
				pstmt.setString(2, aurhorVo.getAuthorDesc());
				
				count = pstmt.executeUpdate();
			    
			    // 4.결과처리
				System.out.println("[DAO] : " + count + " 건이 저장");

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
