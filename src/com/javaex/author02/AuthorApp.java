package com.javaex.author02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList = authorDao.getAuthorList();
		
		//int count = authorDao.authorInsert("이문열", "경북영양");
		//System.out.println(count + "건 등록되었습니다.");
		
		//등록 (insert)
		AuthorVo authorVo1 = new AuthorVo("이문열", "경북영양");
		authorDao.authorInsert(authorVo1); 
		
		AuthorVo authorVo2 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(authorVo2); 
		
		AuthorVo authorVo3 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(authorVo3); 
		
		//리스트 (select)
		authorList = authorDao.getAuthorList(); //AuthorList 지은 이름 //List<AuthorVo> 상단에 정리
		
		//리스트 전체 출력
		System.out.println("========작가 리스트==========");
		for(int i = 0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() +". " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		
		//작가 삭제 (delete)
		authorDao.authorDelete(3); //3번째 아이디 지워보기
		
		//리스트 출력
		authorList = authorDao.getAuthorList(); // 리스트 갱신해야한다. 그래야 2번 지워짐
		
		System.out.println("========작가 리스트==========");
		for(int i = 0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i); 
			System.out.println(vo.getAuthorId() +". " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		
		//작가 수정(update)
		AuthorVo authorVo4 = new AuthorVo(2,"김경리","제주도");
		authorDao.authorUpdate(authorVo4);
        
		//리스트 출력(select)
		authorList = authorDao.getAuthorList(); // 리스트 갱신해야한다. 그래야 2번 지워짐
		
		System.out.println("========작가 리스트==========");
		for(int i = 0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i); 
			System.out.println(vo.getAuthorId() +". " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
	}
    
}

		