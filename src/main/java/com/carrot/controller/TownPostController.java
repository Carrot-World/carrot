package com.carrot.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.carrot.domain.ReplyVO;
import com.carrot.domain.TownPostVO;
import com.carrot.domain.UserVO;
import com.carrot.service.TownPostService;
import com.carrot.service.UserService;

@Controller
public class TownPostController {

	@Autowired
	SqlSession sqlSession;

	@Autowired
	private TownPostService townpostService;
	
	@Autowired
	private UserService userService;

	// 페이지 이동
	@RequestMapping("/page/postList") // 게시물 목록조회 (전체조회)
	public String listPost(Model model, String searchCondition, Object searchKeyword) {
		List<TownPostVO> list = townpostService.listPost();
		model.addAttribute("postlist", list);
		return "townpostlist";
	}
	
	@RequestMapping("/page/newpost") // 게시판 글 작성 페이지
	public String newpost() {
		return "newpostsummernote";
	}
	
	@RequestMapping("/page/editpost/{id}") // 게시물 수정 페이지
	public String editPost(Model model, @PathVariable String id) throws Exception {
		TownPostVO vo = new TownPostVO();
		vo = townpostService.detailPost(id);
		model.addAttribute("postdetail", vo);
		return "editpostsummernote";
	}	

	
	// 기능
	@RequestMapping("/api/post/inspost") // 게시물 등록
	public String insertPost(MultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		townpostService.insertPost(file, request, response);
		return "redirect:/page/postList";
	}
	
	@RequestMapping("/page/detailpost/{id}") // 게시물 상세보기 , 조회수 증가
	public String detailPost(Model model, @PathVariable String id) throws Exception {
		TownPostVO vo = new TownPostVO();
		vo = townpostService.detailPost(id);
		townpostService.readCount(id); //조회수 증가
		model.addAttribute("postdetail", vo);
		return "postDetail";
	}
	

	@RequestMapping("/api/post/updatepost/{id}") // 게시물 수정 버튼
	public String updatePost(MultipartFile file, HttpServletRequest request, HttpServletResponse response,
			@PathVariable String id) throws Exception {
		townpostService.updatePost(file, request, response, id);
		return "redirect:/page/detailpost/" + id;
	}
	
	@ResponseBody
	@RequestMapping("/api/post/delete") //게시물 삭제 버튼
	public String delectPost(String id) {
		townpostService.deletePost(id);
		return "/page/postList";
	}
	
	@ResponseBody
	@RequestMapping("/api/post/insertreply") //댓글 등록 버튼
	public ReplyVO insertReply(@RequestBody ReplyVO reply) {
		ReplyVO vo = new ReplyVO();
		vo = townpostService.insertReply(reply);
		townpostService.replyCount(reply.getTownPostId());
		return vo; 
	}
	

	@ResponseBody
	@RequestMapping("/api/image/image") // summernote에서 이미지 업로드시 img태그로 변환 (base64 -> url)
	public String SummerNoteImageFile(@RequestParam("file") MultipartFile file) {
		return townpostService.imgTag(file);
	}

}
