package com.javaex.book01;

import java.sql.Date;

public class BookVo {

	//필드
	public int bookId, authorId;
	public String title, pubs;
	public Date pub_date;

	
	//생성자
	public BookVo() {}
		
	public BookVo(String title, String pubs, String pub_date, int authorId) {
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.authorId = authorId;
	}

	public BookVo(int bookId, String title, String pubs, String pub_date, String authorName) {
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.authorName = authorName;
	}

	public BookVo(int bookId, String title, String pubs, String pub_date, int author_id) {
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.authorId = authorId;
	}
	
	public BookVo(int bookId, String title, String pubs, Date pub_date, int authorId, String authorName, String authorDesc) {
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	//메소드 g/s
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubs() {
		return pubs;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public String getPub_date() {
		return pub_date;
	}

	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}

	public String getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}

	//메소드
	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", title=" + title + ", pubs=" + pubs + ", pub_date=" + pub_date
				+ ", author_id=" + author_id + "]";
	}

}
