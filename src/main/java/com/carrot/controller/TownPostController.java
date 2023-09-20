package com.carrot.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.carrot.domain.LocationVO;
import com.carrot.domain.ReplyVO;
import com.carrot.domain.SearchVO;
import com.carrot.domain.TownPostVO;
import com.carrot.domain.UserVO;
import com.carrot.service.LocationService;
import com.carrot.service.PagingService;
import com.carrot.service.TownPostService;
import com.carrot.service.UserService;

@Controller
public class TownPostController {

	@Autowired
	private TownPostService townpostService;
	
	@Autowired
	private UserService userService;

    @Autowired
    private LocationService locationService;

	
	@Autowired
    private PagingService pagingService;
	
	// 페이지 이동
	@RequestMapping("/page/postList") //게시물 조회
	public String searchPost(SearchVO vo, Model model) {
		
		System.out.println("searchvo : " + vo);
        if (userService.isAuthenticated()) {
            UserVO user = userService.getUserInfo();
            model.addAttribute("user", user);
            model.addAttribute("list", townpostService.searchPost(pagingService.setPaging(userService.setUserLocation())));
            model.addAttribute("loc1List", locationService.loc1Set());
            model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(user.getLoc1())));
            model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(user.getLoc1(), user.getLoc2())));
            model.addAttribute("page", pagingService.getPagingInfo(userService.setUserLocation()));
        } else {
            model.addAttribute("list", townpostService.searchPost(pagingService.setPaging(new SearchVO())));
            model.addAttribute("loc1List", locationService.loc1Set());
            model.addAttribute("page", pagingService.getPagingInfo(new SearchVO()));
        }
		return "postList";
	}
	
	 @RequestMapping("/api/post/search") //게시물 검색
	    public String search(SearchVO vo, Model model) {
		 
		 System.out.print("게시물 검색 ; ");
		 System.out.println(vo);
	        model.addAttribute("list", townpostService.searchPost(vo));
	        model.addAttribute("list", townpostService.searchPost(pagingService.setPaging(vo)));
	        model.addAttribute("loc1List", locationService.loc1Set());
	        model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(vo.getLoc1())));
	        model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(vo.getLoc1(), vo.getLoc2())));
	        model.addAttribute("page", pagingService.getPagingInfo(pagingService.setPaging(vo)));
	        model.addAttribute("searchInfo", vo);
	        return "searchPost";
	    }
	
	@RequestMapping("/page/newpost") // 게시판 글 작성 페이지
	public String newpost() {
		return "postRegister";
	}
	
	@RequestMapping("/page/editpost/{id}") // 게시물 수정 페이지
	public String editPost(Model model, @PathVariable String id) throws Exception {
		TownPostVO vo = new TownPostVO();
		vo = townpostService.detailPost(id);
		model.addAttribute("postdetail", vo);
		return "postEdit";
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
		model.addAttribute("nickname", userService.getUserInfo().getNickname());
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
//		3. 게시글 삭제 시 댓글 모두 삭제
		townpostService.deleteAllReply(id);
		return "/page/postList";
	}
	
	@ResponseBody
	@RequestMapping("/api/post/insertreply") //댓글 등록 버튼
	public ReplyVO insertReply(@RequestBody ReplyVO reply) {
		ReplyVO vo = new ReplyVO();
		System.out.println("parent : " + reply.getParent());
		vo = townpostService.insertReply(reply);
		System.out.println("댓글등록할때 게시글 번호 : " + reply.getTownPostId());
		//댓글 수 증가
		townpostService.replyCount(reply.getTownPostId());
		
		System.out.println("댓글등록 반환: " + vo);
		return vo; 
	}
	
	@ResponseBody
	@RequestMapping("/api/post/deletereply") //댓글 삭제 버튼
	public boolean deleteReply(@RequestBody HashMap<String, String> map) {
		String id = map.get("id");
		String postid = map.get("postid");
//		1. 댓글 삭제 (status 0 -> 1)
		townpostService.deleteReply(id);
//		2. 게시글 댓글 수 감소
		townpostService.replyCountDelete(postid);
		return true;
	}
	
	@ResponseBody
	@RequestMapping("/api/post/rereplylist") //대댓글 조회
	public List<ReplyVO> reReplyList(@RequestBody HashMap<String, String> map) {
		String parent = map.get("parent");
		System.out.println("parent id : " + parent);
		return townpostService.rereplyList(parent);
	}
	
	@ResponseBody
	@RequestMapping("/api/image/image") // summernote에서 이미지 업로드시 img태그로 변환 (base64 -> url)
	public String SummerNoteImageFile(@RequestParam("file") MultipartFile file) {
		return townpostService.imgTag(file);
	}

}
