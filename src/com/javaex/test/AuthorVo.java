package com.javaex.test;

public class AuthorVo {
	
		//필드
		public int authorId;
		public String authorName;
		public String authorDesc;
		
		//생성자
		public AuthorVO() {}
		
		public AuthorVO(String authorName, String authorDesc) {
			this.authorName = authorName;
			this.authorDesc = authorDesc;
		}

		public AuthorVO(int authorId, String authorName, String authorDesc) {
			this.authorId = authorId;
			this.authorName = authorName;
			this.authorDesc = authorDesc;
		}

		//메소드-g/s
		public int getAuthorId() {
			return authorId;
		}

		public void setAuthorId(int authorId) {
			this.authorId = authorId;
		}

		public String getAuthorName() {
			return authorName;
		}

		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}

		public String getAuthorDesc() {
			return authorDesc;
		}

		public void setAuthorDesc(String authorDesc) {
			this.authorDesc = authorDesc;
		}

		//메소드-일반
		@Override
		public String toString() {
			return "AuthorVO [authorId=" + authorId + ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
		}	
		
	}
}
