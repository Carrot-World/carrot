package com.carrot.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.carrot.domain.CategoryVO;
import com.carrot.domain.ReplyVO;
import com.carrot.domain.SearchVO;
import com.carrot.domain.TownPostVO;
import com.carrot.domain.UserVO;
import com.carrot.repository.CategoryRepository;
import com.carrot.repository.TownPostRepository;

@Service
public class TownPostService {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AWSS3 AWSS3;
	
	private static HashMap <Integer, String> categoryNameMap;
	private static boolean isSetCategory;
	
	public ArrayList<TownPostVO> listPost(){ //게시글 전체 조회

		if (!isSetCategory) {
			setCategoryName();
        }
		
		ArrayList<TownPostVO> list = sqlSession.getMapper(TownPostRepository.class).listByALL();
		
		for ( TownPostVO townpostvo : list ) {
			townpostvo.setCategoryName(categoryNameMap.get(townpostvo.getId()));
		}
		return list;
	}
	
	public ArrayList<TownPostVO> searchPost(SearchVO vo){ //게시글 검색 조회
		
		System.out.println("service searchvo : " + vo);
		
		if (!isSetCategory) {
			setCategoryName();
        }
		
		ArrayList<TownPostVO> list = sqlSession.getMapper(TownPostRepository.class).listBySearch(vo);
		
		for ( TownPostVO townpostvo : list ) {
			townpostvo.setCategoryName(categoryNameMap.get(townpostvo.getId()));
		}
		
		return list;
	}
	

	public int insertPost(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception { //게시글 등록
		TownPostVO vo = new TownPostVO();
		System.out.println(request.getParameter("category_id"));
		vo.setCategory_id(Integer.parseInt(request.getParameter("category_id")));
		UserVO user = userService.getUserInfo();
		Date created_at = new Date(System.currentTimeMillis());
		vo.setCreated_at(created_at);
		vo.setTitle(request.getParameter("posttitle"));
		vo.setContent(request.getParameter("editordata"));
		vo.setWriter(user.getId());
		vo.setLoc1(user.getLoc1());
		vo.setLoc2(user.getLoc2());
		vo.setLoc3(user.getLoc3());
		return sqlSession.getMapper(TownPostRepository.class).insertPost(vo);
	}
	
	public TownPostVO detailPost(String id) { //게시글 상세보기
		TownPostVO vo = new TownPostVO();
		vo = sqlSession.getMapper(TownPostRepository.class).detailPost(id);
		
//		댓글 조회
		vo.setReplylist( replyList(id) );
		
		UserVO user = userService.selectById(vo.getWriter());
		vo.setWriterNickname(user.getNickname());
		System.out.println("townpostVO : " + vo);
		return vo;
	}
	
	public int readCount(String id) { //조회수
		return sqlSession.getMapper(TownPostRepository.class).readCount(id);
	}
	
	public int updatePost(MultipartFile file, HttpServletRequest request, HttpServletResponse response, String id) throws Exception { //게시글 수정
		TownPostVO vo = new TownPostVO();
		vo.setTitle(request.getParameter("posttitle"));
		vo.setContent(request.getParameter("editordata"));
		vo.setId(Integer.parseInt(id));
	return sqlSession.getMapper(TownPostRepository.class).updatePost(vo);
	}
	
	public int deletePost(String id) { //게시글 삭제
		return sqlSession.getMapper(TownPostRepository.class).deletePost(id);
	}
	
	public int replyCount(int id) { //댓글 수
		return sqlSession.getMapper(TownPostRepository.class).replyCount(id);
	}

	public ReplyVO insertReply(ReplyVO vo) { //댓글 등록
		UserVO uservo = new UserVO();
		uservo = userService.getUserInfo();
		
		String writerId = uservo.getId();
		String writerNickname = uservo.getNickname();
		Date created_at = new Date(System.currentTimeMillis());
		
		vo.setWriter(writerId);
		vo.setNickname(writerNickname);
		vo.setCreated_at(created_at);
		vo.setParent("");
		sqlSession.getMapper(TownPostRepository.class).insertReply(vo);
		
		ReplyVO reply = new ReplyVO();
		UserVO user = userService.selectById(writerId);
		reply.setCreated_at(created_at);
		reply.setNickname(user.getNickname());
		return reply;
	}
	
	public List<ReplyVO> replyList(String id) { //댓글 조회
		List<ReplyVO> selectReplyList = sqlSession.getMapper(TownPostRepository.class).replList(id);
		System.out.println("selectReplyList : " + selectReplyList);
		return selectReplyList;
	}
	
	public int deleteReply(String id) { //댓글 삭제
		return sqlSession.getMapper(TownPostRepository.class).deleteReply(id);
	}
	
	public int deleteReReply(String parent) { //원댓글 삭제시 대댓글 함께 삭제
		return sqlSession.getMapper(TownPostRepository.class).deleteReReply(parent);
	}
	
	public int deleteAllReply(String postid) { //게시글 삭제 시 댓글 전체 삭제
		return sqlSession.getMapper(TownPostRepository.class).deleteAllReply(postid);
	}
	
	public int withdrawPost(String writer) { //회원탈퇴시 게시글 모두 삭제
		return sqlSession.getMapper(TownPostRepository.class).withdrawPost(writer);
	}
	public int withdrawReply(String writer) { //회원탈퇴시 게시글 댓글 모두 삭제
		return sqlSession.getMapper(TownPostRepository.class).withdrawReply(writer);
	}
	
	
	
	public String imgTag(MultipartFile file) { //imgTag 변환
		
		String fileName;
		try {
			fileName = AWSS3.getImgTag(file);
			if (fileName != null) {
				String url = "https://carrot-world.s3.ap-northeast-2.amazonaws.com/";
				return url + fileName;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//카테고리 id -> name 얻기
	public void setCategoryName() {
		
		List<CategoryVO> list = new ArrayList<>();
		list = sqlSession.getMapper(CategoryRepository.class).selecttownpostAll();
		categoryNameMap = new HashMap();
		for ( CategoryVO vo : list ) {
			categoryNameMap.put(vo.getId(), vo.getName());
		}
		isSetCategory = true;
	}

}
