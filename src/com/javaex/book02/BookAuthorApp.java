package com.javaex.book02;

import java.util.ArrayList;
import java.util.List;

public class BookAuthorApp {

	public static void main(String[] args) {

		//작가 6명 등록
		//AuthorDao, AuthorVo 이용해서 등록
		//수정, 삭제 리스트
		
		//책 8권 등록
		//BookDao, BookVo 이용해서 등록 8권
		//(수정, 삭제, 리스트)
		
		//책을 전체 출력(작가 정보까지 나열된 책)
		//(책 -> 책정보 + 작가정보)
		//bookVo ->책정보 + 작가정보
		
			
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorVoList = new ArrayList<AuthorVo>();
		
		BookDao bookDao = new BookDao();
		List<BookVo> bookVoList = new ArrayList<BookVo>();
		
		/* 작가 6명 등록 */ //AuthorDao, AuthorVo 이용해서 등록
		AuthorVo author01 = new AuthorVo("이문열", "경북 영양");
		authorDao.authorInsert(author01);
		
		AuthorVo author02 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(author02);
		
		AuthorVo author03 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(author03);
		
		AuthorVo author04 = new AuthorVo("기안84", "기안동에서 산 84년생");
		authorDao.authorInsert(author04);
		
		AuthorVo author05 = new AuthorVo("강풀", "온라인 만화가 1세대");
		authorDao.authorInsert(author05);
		
		AuthorVo author06 = new AuthorVo("김영하", "알쓸신잡");
		authorDao.authorInsert(author06);
		
			
		/* 수정 */
		AuthorVo author07 = new AuthorVo(6, "김영하", "소설작가");
		authorDao.authorUpdate(author07);
		
		/* 삭제 */
		authorDao.authorDelete(6);
		
		//출력확인 - 리스트
		authorVoList = authorDao.getAuthorList();
		System.out.println("===============작가 명단===============");
		for(int i=0;i<authorVoList.size();i++) {
			System.out.println(authorVoList.get(i).author_id+ ". " +authorVoList.get(i).author_name+ ", " +authorVoList.get(i).author_desc);
		}
		
		
		/* 책 8권 등록 */ // BookDao, BookVo 이용해서 등록
		BookVo bookVo01 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.bookInsert(bookVo01);
		
		BookVo bookVo02 = new BookVo("삼국지", "인음사", "2002-03-01", 1);
		bookDao.bookInsert(bookVo02);
		
		BookVo bookVo03 = new BookVo("토지", "마로니에북스", "2012-08-15", 2);
		bookDao.bookInsert(bookVo03);
		
		BookVo bookVo04 = new BookVo("유시민의 글쓰기 특강", "생각의길", "2015-04-01", 3);
		bookDao.bookInsert(bookVo04);
		
		BookVo bookVo05 = new BookVo("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookDao.bookInsert(bookVo05);
		
		BookVo bookVo06 = new BookVo("순정만화", "재미주의", "2011-08-03", 5);
		bookDao.bookInsert(bookVo06);
		
		BookVo bookVo07 = new BookVo("오직두사람", "문학동네", "2017-05-04", 6);
		bookDao.bookInsert(bookVo07);
		
		BookVo bookVo08 = new BookVo("26년", "재미주의", "2012-02-04", 5);
		bookDao.bookInsert(bookVo08);
		
		
		/* 수정 */
		BookVo bookVo09 = new BookVo(1, "오직두사람", "문학동네", "2017-05-04", 1);
		bookDao.bookUpdate(bookVo09);
		
		BookVo bookVo10 = new BookVo(5, "26년 ver.2020", "재미주의", "2012-02-04", 5);
		bookDao.bookUpdate(bookVo10);
		
		
		/* 삭제 */
		bookDao.bookDelete(3);
		
		//책을 전체 출력 *(책 -> 책정보 + 작가정보)
		//BookVo -> 책정보 + 작가정보
		//출력확인 - 리스트
		bookVoList = bookDao.getbookList();
		System.out.println("===============책 리스트===============");
		for(int i=0;i<bookVoList.size(); i++) {
			System.out.println(bookVoList.get(i).book_id+ ". " +bookVoList.get(i).title+ ", " +bookVoList.get(i).pubs+ ", " +bookVoList.get(i).pub_date+ ", " +bookVoList.get(i).author_id+ ", " +bookVoList.get(i).author_name+ ", " +bookVoList.get(i).author_desc);
		}
	}
	
}

