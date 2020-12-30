package com.javaex.book01;

import java.sql.Date;

public class BookVo {

	//필드
	public int book_id, author_id;
	public String author_name, author_desc, title, pubs, pub_date;

	
	//생성자
	public BookVo() {}
		
	public BookVo(String title, String pubs, String pub_date, int author_id) {
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.author_id = author_id;
	}

	public BookVo(int book_id, String title, String pubs, String pub_date, int author_id) {
		this.book_id = book_id;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.author_id = author_id;
	}

	public BookVo(int book_id, String title, String pubs, String pub_date, int author_id, String author_name, String author_desc) {
		this.book_id = book_id;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.author_id = author_id;
		this.author_name = author_name;
		this.author_desc = author_desc;
	}

	//메소드 g/s
	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getAuthor_desc() {
		return author_desc;
	}

	public void setAuthor_desc(String author_desc) {
		this.author_desc = author_desc;
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

	
}

