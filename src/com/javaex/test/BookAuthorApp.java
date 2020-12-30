package com.javaex.test;

public class BookAuthorApp {

import java.util.List;
import java.util.Scanner;
import com.javaex.book01.BookDao;

		public static void main(String[] args) {
			//작가 6명 등록
			//AuthorDao, AuthorVo 이용해서 등록을 시킴
			AuthorDao authorDao = new AuthorDao();
			List<AuthorVO> authorList = authorDao.getAuthorList();
			
			//등록(insert)
			AuthorVO author01 = new AuthorVO("김문열", "경북 영양");
			authorDao.AuthorInsert(author01);
			
			AuthorVO author02 = new AuthorVO("박경리", "경상남도 통영");
			authorDao.AuthorInsert(author02);
			
			AuthorVO author03 = new AuthorVO("유시민", "17대 국회의원");
			authorDao.AuthorInsert(author03);
			
			AuthorVO author04 = new AuthorVO("기안84", "기안동에서 산 84년생");
			authorDao.AuthorInsert(author04);
			
			AuthorVO author05 = new AuthorVO("강풀", "온라인 만화가 1세대");
			authorDao.AuthorInsert(author05);
			
			AuthorVO author06 = new AuthorVO("김영하", "알쓸신잡");
			authorDao.AuthorInsert(author06);
			
			AuthorVO author07 = new AuthorVO("테스트", "경기도 수원시");   
			authorDao.AuthorInsert(author07);                  
			
			//조회(select)
			authorList = authorDao.getAuthorList();
			
			System.out.println("===========Author 리스트===========");
			for(int i = 0; i < authorList.size(); i++) {
				AuthorVO vo = authorList.get(i);
				System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() +
									", " + vo.getAuthorDesc());
			}
		
			//(수정, 삭제 리스트)
			//수정(update)
			AuthorVO author08 = new AuthorVO(7, "김서영", "경기도 수원시 장안구");
			authorDao.authorUpdate(author08);
			//리스트 출력
			authorList = authorDao.getAuthorList();
			
			System.out.println("===========Author 리스트===========");
			for(int i = 0; i < authorList.size(); i++) {
				AuthorVO vo = authorList.get(i);
				System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() +
									", " + vo.getAuthorDesc());
			}
			
			//삭제(delete)
			authorDao.authorDelete(7);
			//리스트 출력
			authorList = authorDao.getAuthorList();
			
			System.out.println("===========Author 리스트===========");
			for(int i = 0; i < authorList.size(); i++) {
				AuthorVO vo = authorList.get(i);
				System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() +
									", " + vo.getAuthorDesc());
			}
			
			System.out.println("=======================================================");
			
			//책 8 권 등록
			//BookDao, BookVO 이용해서 등록을 시킴
			BookDao bookDao = new BookDao();
			List<BookVO> bookList = bookDao.getBookList();
			
			//저장(insert)
			BookVO book01 = new BookVO("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
			bookDao.bookInsert(book01);
			
			BookVO book02 = new BookVO("삼국지", "민음사", "2002-03-01", 1);
			bookDao.bookInsert(book02);
			
			BookVO book03 = new BookVO("토지", "마로니에북스", "2012-08-15", 2);
			bookDao.bookInsert(book03);
			
			BookVO book04 = new BookVO("유시민의 글쓰기 특강", "생각의 길", "2015-04-01", 3);
			bookDao.bookInsert(book04);
			
			BookVO book05 = new BookVO("패션왕", "중앙북스(books)", "2012-02-22", 4);
			bookDao.bookInsert(book05);
			
			BookVO book06 = new BookVO("순정만화", "재미주의", "2011-08-03", 5);
			bookDao.bookInsert(book06);
			
			BookVO book07 = new BookVO("오직두사람", "문학동네", "2017-05-04", 6);
			bookDao.bookInsert(book07);
			
			BookVO book08 = new BookVO("26년", "재미주의", "2012-02-04", 5);
			bookDao.bookInsert(book08);
			
			BookVO book09 = new BookVO("테스트책", "테스트", "2020-12-29", 3);
			bookDao.bookInsert(book09);
			
			//조회(select)
			bookList = bookDao.getBookList();
			
			System.out.println("===========Book 리스트===========");
			
			for(int i = 0; i < bookList.size(); i++) {
				BookVO bookvo = bookList.get(i);
				System.out.println(bookvo.getBook_id() + ", " + bookvo.getTitle() + ", " +
								   bookvo.getPubs() + ", " + bookvo.getPub_date() + ", " +
								   bookvo.getAuthorId());
			}
			
			//(수정, 삭제 리스트)
			//수정(update)
			BookVO book10 = new BookVO(9, "책제목이다", "김서영", "2020-12-14", 4);
			bookDao.bookUpdate(book10);
			
			//출력
			bookList = bookDao.getBookList();
			
			System.out.println("===========Book 리스트===========");
			
			for(int i = 0; i < bookList.size(); i++) {
				BookVO bookvo = bookList.get(i);
				System.out.println(bookvo.getBook_id() + ", " + bookvo.getTitle() + ", " +
								   bookvo.getPubs() + ", " + bookvo.getPub_date() + ", " +
								   bookvo.getAuthorId());
			}
			
			//삭제(delete)
			bookDao.bookDelete(9);
			
			bookList = bookDao.getBookList();
			
			System.out.println("===========Book 리스트===========");
			
			for(int i = 0; i < bookList.size(); i++) {
				BookVO bookvo = bookList.get(i);
				System.out.println(bookvo.getBook_id() + ", " + bookvo.getTitle() + ", " +
								   bookvo.getPubs() + ", " + bookvo.getPub_date() + ", " +
								   bookvo.getAuthorId());
			}
			//책을 전체를 출력
			//(책--> 책정보+작가정보)
			//BookVO --> 책정보 + 작가정보
			bookList = bookDao.getAllList();

			System.out.println("===========Book 리스트/Author 리스트===========");
			for(int i = 0; i < bookList.size(); i++) {
				BookVO bookvo = bookList.get(i);
				System.out.println(bookvo.getBook_id() + ", " + bookvo.getTitle() + ", " +
								   bookvo.getPubs() + ", " + bookvo.getPub_date() + ", " +
								   bookvo.getAuthorId() + ", " + bookvo.getAuthorName() + ", " + 
								   bookvo.getAuthorDesc());
			}
			
			//검색하기
			Scanner sc = new Scanner(System.in);
			
			System.out.print("검색할 문자를 입력하세요 : ");
			String str = sc.nextLine();

			bookList = bookDao.BookSearch(str);
			
			System.out.println("===========검색하기===========");
			for(int i = 0; i < bookList.size(); i++) {
				BookVO bookvo = bookList.get(i);
				System.out.println(bookvo.getBook_id() + ", " + bookvo.getTitle() + ", " +
							       bookvo.getPubs() + ", " + bookvo.getPub_date() + ", " +
							       bookvo.getAuthorId() + ", " + bookvo.getAuthorName() + ", " + 
							       bookvo.getAuthorDesc());
			}
			
			sc.close();
		}

	}
}
