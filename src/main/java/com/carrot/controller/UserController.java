package com.carrot.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carrot.domain.LocationVO;
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
        
    	System.out.println("1. user : " + user);
		System.out.println("2. loc1List : " + locationService.loc1Set());
        model.addAttribute("loc1List", locationService.loc1Set());
        model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(user.getLoc1())));
        model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(user.getLoc1(), user.getLoc2())));
        
		return "mySellPage";
	}
	
	@RequestMapping("/page/mypageBuy") //마이페이지 (구매내역)
	public String myPageBuy(Model model) {
		UserVO user = userService.getUserInfo();
		System.out.println("1. user : " + user);
		System.out.println("2. loc1List : " + locationService.loc1Set());
        model.addAttribute("userinfo", user);
        model.addAttribute("loc1List", locationService.loc1Set());
        model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(user.getLoc1())));
        model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(user.getLoc1(), user.getLoc2())));
        
		return "myBuyPage";
	}
	
	@RequestMapping("/page/mypageTrade") //마이페이지 (거래후기)
	public String myPageTrade(Model model) {
		UserVO user = userService.getUserInfo();
        model.addAttribute("userinfo", user);
        model.addAttribute("loc1List", locationService.loc1Set());
        model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(user.getLoc1())));
        model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(user.getLoc1(), user.getLoc2())));
        
		return "myTradePage";
	}
	
	@RequestMapping("/page/userpageSell") //유저페이지 (판매내역)
	public String userPageSell(Model model, String id) {
		UserVO login = userService.getUserInfo();
		UserVO user = userService.selectById(id);
		model.addAttribute("userinfo", user);
		if ( login.getId().equals(user.getId())) {
			return "redirect://page/mypageSell";
		}
		model.addAttribute("list", itemPostService.selectByWriter(user.getId()));
		return "userSellPage";
	}
	
	@RequestMapping("/page/userpageTrade") //유저페이지 (거래후기)
	public String userPageTrade(Model model, String id) {
		UserVO login = userService.getUserInfo();
		UserVO user = userService.selectById(id);
		model.addAttribute("userinfo", user);
		
		
		
		if ( login.getId().equals(user.getId())) {
			return "redirect://page/mypageTrade";
		}
		
		return "userTradePage";
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
		String userid = map.get("id");
		String password = map.get("password");
		String newpassword = map.get("newpassword");
	
		if ( userService.pwdCheck(id, password) ) {
			int result = userService.updatePwd(id, newpassword);
			System.out.println("result : " + result);
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
