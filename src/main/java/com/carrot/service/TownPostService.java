package com.carrot.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.carrot.domain.TownPostVO;
import com.carrot.domain.UserVO;
import com.carrot.repository.TownPostRepository;

@Service
public class TownPostService {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AWSS3 AWSS3;

	public int insertPost(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception { //게시글 등록
		
		TownPostVO vo = new TownPostVO();
		vo.setCategory_id(Integer.parseInt(request.getParameter("category_id")));
		UserVO user = userService.getUserInfo();
		vo.setTitle(request.getParameter("posttitle"));
		vo.setContent(request.getParameter("editordata"));
		vo.setWriter(user.getNickname());
		vo.setLoc1(user.getLoc1());
		vo.setLoc2(user.getLoc2());
		vo.setLoc3(user.getLoc3());
		return sqlSession.getMapper(TownPostRepository.class).insertPost(vo);
	}
	
	public String imgTag(MultipartFile file) { //imgTag 변환
		
		String fileName;
		try {
			fileName = AWSS3.getImgTag(file);
			System.out.println(fileName);
			if (fileName != null) {
				String url = "https://carrot-world.s3.ap-northeast-2.amazonaws.com/";
				return url + fileName;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
