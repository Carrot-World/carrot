package com.carrot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carrot.domain.ItemPostVO;
import com.carrot.domain.UserVO;
import com.carrot.service.ItemPostService;
import com.carrot.service.LocationService;
import com.carrot.service.PagingService;
import com.carrot.service.TownPostService;
import com.carrot.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemPostService itemPostService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private PagingService pagingService;
	
	@Autowired
	private TownPostService townpostService;
	
	//페이징 이동
	
	
	
	
	//기능
	@ResponseBody
	@RequestMapping("api/myPage/updateinfo") //회원 정보 수정
	public boolean updateInfo(UserVO vo) {
		System.out.println("update : " + vo);
		userService.updateUser(vo);
		return true;
	}
	
	
	@ResponseBody
	@RequestMapping("api/myPage/withdraw") //회원탈퇴
	public String withdraw(@RequestBody HashMap<String, String> map) {
		String id = getId();
		String password = map.get("password");
		if ( !userService.withdrawSignUp(id, password) ) {
			return "redirect:page/mypage1";
		}
//		itemPostService.withdrawItem(id);
		townpostService.withdrawPost(id);
		townpostService.withdrawReply(id);
		
		return "redirect:page/login";
	}
	
	public String getId() { //로그인 id 얻기
		UserVO vo = new UserVO();
		vo = userService.getUserInfo();
		return vo.getId();
	}
	
	
	

}
