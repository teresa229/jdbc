package com.javaex.test;

public class BookVO extends AuthorVO{
	
		//필드
		public int book_id;
		public String title;
		public String pubs;
		public String pub_date;
		public int authorId;
		
		
		//생성자
		public BookVO() {}
	
		public BookVO(int book_id, String title, String pubs, String pub_date, int authorId, String author_name, String author_desc) {
			super.authorName = author_name;
			super.authorDesc = author_desc;
			this.book_id = book_id;
			this.title = title;
			this.pubs = pubs;
			this.pub_date = pub_date;
			this.authorId = authorId;
		}
	
	
		public BookVO(String title, String pubs, String pub_date, int authorId) {
			this.title = title;
			this.pubs = pubs;
			this.pub_date = pub_date;
			this.authorId = authorId;
		}
	
		public BookVO(int book_id, String title, String pubs, String pub_date, int authorId) {
			this.book_id = book_id;
			this.title = title;
			this.pubs = pubs;
			this.pub_date = pub_date;
			this.authorId = authorId;
		}
	
		
		//메소드-g/s
		public int getBook_id() {
			return book_id;
		}
	
		public void setBook_id(int book_id) {
			this.book_id = book_id;
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
	
		public int getAuthorId() {
			return authorId;
		}
	
		public void setAuthorId(int authorId) {
			this.authorId = authorId;
		}
	
		//메소드-일반
		@Override
		public String toString() {
			return "BookVO [book_id=" + book_id + ", title=" + title + ", pubs=" + pubs + ", pub_date=" + pub_date
					+ ", authorId=" + authorId + "]";
		}	
		
}



