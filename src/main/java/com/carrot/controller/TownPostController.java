package com.carrot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.carrot.service.TownPostService;

@Controller
public class TownPostController {

	@Autowired
	SqlSession sqlSession;

	@Autowired
	private TownPostService townpostService;

	// 페이지 이동
	@RequestMapping("/page/newpost") // 게시판 글 작성 페이지
	public String newpost() {
		return "newpostsummernote";
	}

	@RequestMapping("/api/post/inspost") // 게시물 등록
	public String insertPost(MultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		townpostService.insertPost(file, request, response);
		return "redirect:/page/postList";
	}

	@ResponseBody
	@RequestMapping("/api/image/image") // summernote에서 이미지 업로드시 img태그로 변환 (base64 -> url)
	public String SummerNoteImageFile(@RequestParam("file") MultipartFile file) {
		return townpostService.imgTag(file);
	}

}
