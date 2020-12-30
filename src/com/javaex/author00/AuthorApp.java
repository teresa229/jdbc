package com.javaex.author00;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		
		authorDao.authorInsert("이문열"," 경북영양");
		authorDao.authorInsert("박경리"," 경상남도 통영");
		
		
	}

}
