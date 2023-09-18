package com.carrot.repository;

import java.util.ArrayList;
import java.util.List;

import com.carrot.domain.ReplyVO;
import com.carrot.domain.SearchVO;
import com.carrot.domain.TownPostVO;

public interface TownPostRepository {

	
	//전체 검색
	public ArrayList<TownPostVO> listByALL();
	//위치로 검색
	public ArrayList<TownPostVO> listByLoc();
	//카테고리로 검색
	public ArrayList<TownPostVO> listByCategory();
	//제목으로 검색
	public ArrayList<TownPostVO> listByTitle();
	//
	public ArrayList<TownPostVO> listBySearch(SearchVO vo);
	
	
	//게시글 등록
	public int insertPost(TownPostVO vo);
	//게시글 수정
	public int updatePost(TownPostVO vo);
	//게시글 삭제
	public int deletePost(String postid);
	//게시글 조회
	public TownPostVO detailPost(String postid);

	
	//댓글 등록
	public int insertReply(ReplyVO vo);
	//댓글 조회
	public List<ReplyVO> replList(String postid);
	//대댓글 조회
	public List<ReplyVO> rereplList(String postid, String parent);
	//댓글 삭제
	public int deleteReply(String id);
	//게시글 삭제 시 -> 댓글 전체 삭제
	public int deleteAllReply(String postid);
	//원댓글 삭제시 -> 대댓글 삭제
	public int deleteReReply(String parent);
	
	//조회수 증가
	public int readCount(String postid);
	//댓글 수 증가
	public int replyCount(int postid);
	//댓글 수 삭제
	public int replyCountDelete(String postid);

	//회원탈퇴시 모든 글, 모든 댓글 삭제
	public int withdrawPost(String writer);
	public int withdrawReply(String writer);
	

}
