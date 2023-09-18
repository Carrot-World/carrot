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
import org.springframework.web.bind.annotation.RequestParam;
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
	@RequestMapping("/page/mypageSell") //마이페이지 (판매내역)
	public String myPageSell(Model model) {
		UserVO user = userService.getUserInfo();
        model.addAttribute("userinfo", user);
        model.addAttribute("list", itemPostService.selectByWriter(user.getId()));
        
		return "mySellPage";
	}
	
	@RequestMapping("/page/mypageBuy") //마이페이지 (구매내역)
	public String myPageBuy(Model model) {
		UserVO user = userService.getUserInfo();
        model.addAttribute("userinfo", user);
        
		return "myBuyPage";
	}
	
	@RequestMapping("/page/mypageReply") //마이페이지 (거래후기)
	public String myPageReply(Model model) {
		UserVO user = userService.getUserInfo();
        model.addAttribute("userinfo", user);
        
		return "myReplyPage";
	}
	
	//기능
	@ResponseBody
	@RequestMapping("api/myPage/updateinfo") //회원 정보 수정
	public boolean updateInfo(@RequestBody UserVO vo) {
		System.out.println("update : " + vo);
		userService.updateUser(vo);
		return true;
	}
	
	@ResponseBody
	@RequestMapping("api/myPage/updatepassword") //비밀번호 변경
	public boolean updatePassword(@RequestBody HashMap<String, String> map) {
		UserVO vo = new UserVO();
		vo = userService.getUserInfo();
		String id = vo.getId();
		String password = map.get("password");
		String newpassword = map.get("newpassword");
		System.out.println("password : " + password);
		System.out.println("newpassword : " + newpassword);
		System.out.println("id : " + id);
		
		if ( userService.pwdCheck(id, password) ) {
			userService.updatePwd(id, newpassword);
		} else {
			return false;
		}
		return true;
	}
	
	
	@ResponseBody
	@RequestMapping("api/myPage/withdraw") //회원탈퇴
	public String withdraw(@RequestBody String id ) {
		System.out.println(id);
		
		if ( !userService.withdrawSignUp(id) ) {
			return "redirect:page/myPage";
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
