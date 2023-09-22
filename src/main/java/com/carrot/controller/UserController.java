package com.carrot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carrot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.carrot.domain.ItemPostVO;
import com.carrot.domain.LocationVO;
import com.carrot.domain.TradeVO;
import com.carrot.domain.UserVO;

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

	@Autowired
	private TradeService tradeService;

	//페이징 이동
	@RequestMapping("/page/mypageSell") //마이페이지 (판매내역)
	public String myPageSell(Model model) {
		UserVO user = userService.getUserInfo();

        model.addAttribute("userinfo", userService.selectById(user.getId()));
        List<ItemPostVO> list = itemPostService.selectByWriter(user.getId());
        model.addAttribute("list", list);
        
        if ( list != null) {
            model.addAttribute("itemcnt", list.size());
            }
        
        model.addAttribute("loc1List", locationService.loc1Set());
        model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(user.getLoc1())));
        model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(user.getLoc1(), user.getLoc2())));

		return "mySellPage";
	}

	@RequestMapping("/page/mypageBuy") //마이페이지 (구매내역)
	public String myPageBuy(Model model) {
		UserVO user = userService.getUserInfo();
        model.addAttribute("userinfo", userService.selectById(user.getId()));
        List<ItemPostVO> list = itemPostService.selectByBuyer(user.getId());
        model.addAttribute("list", list);
        
        if ( list != null) {
            model.addAttribute("itemcnt", list.size());
            }
        
        model.addAttribute("loc1List", locationService.loc1Set());
        model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(user.getLoc1())));
        model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(user.getLoc1(), user.getLoc2())));

		return "myBuyPage";
	}

	@RequestMapping("/page/mypageHeart") //마이페이지 (찜 목록)
	public String myPageHeart(Model model) {
		UserVO user = userService.getUserInfo();
		model.addAttribute("userinfo", userService.selectById(user.getId()));
		List<ItemPostVO> list = itemPostService.selectHeartById(user.getId());
		model.addAttribute("list", list);
		
		if ( list != null) {
			model.addAttribute("itemcnt", list.size());
		}
		
		model.addAttribute("loc1List", locationService.loc1Set());
		model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(user.getLoc1())));
		model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(user.getLoc1(), user.getLoc2())));
		
		return "myHeartPage";
	}
	
	@RequestMapping("/page/mypageTrade") //마이페이지 (거래후기)
	public String myPageTrade(Model model) {
		UserVO user = userService.getUserInfo();
        model.addAttribute("userinfo", userService.selectById(user.getId()));
        
        List<TradeVO> list = itemPostService.selectTradeById(user.getId());
        model.addAttribute("list", list);
        System.out.println("마이페이지 (거래후기)" + list);
        
        model.addAttribute("loc1List", locationService.loc1Set());
        model.addAttribute("loc2List", locationService.loc2Set(new LocationVO(user.getLoc1())));
        model.addAttribute("loc3List", locationService.loc3Set(new LocationVO(user.getLoc1(), user.getLoc2())));
		model.addAttribute("tradeList", tradeService.getListByUserId2(user.getId()));

		return "myTradePage";
	}

	@RequestMapping("/page/userpageSell") //유저페이지 (판매내역)
	public String userPageSell(Model model, @RequestParam String id) {
		System.out.println("id값 : " +  id);
		UserVO login = userService.getUserInfo();
		UserVO user = userService.selectById(id);
		model.addAttribute("userinfo", user);
		
		if ( login.getId().equals(user.getId())) {
			return "redirect:/page/mypageSell";
		}
		
		List<ItemPostVO> list = itemPostService.selectByWriter(user.getId());
        model.addAttribute("list", list);
        if ( list != null) {
        model.addAttribute("itemcnt", list.size());
        }
		return "userSellPage";
	}

	@RequestMapping("/page/userpageTrade") //유저페이지 (거래후기)
	public String userPageTrade(Model model, @RequestParam String id) {
		UserVO login = userService.getUserInfo();
		if ( login.getId().equals(id)) {
			return "redirect:/page/mypageTrade";
		}

		UserVO user = userService.selectById(id);
		model.addAttribute("userinfo", user);
		model.addAttribute("tradeList", tradeService.getListByUserId(id));

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
	public String withdraw(@RequestBody HashMap<String, String> map ) {
		System.out.println(map);
		String id = map.get("id");
		System.out.println("id:" + id);

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

	@ResponseBody
	@PostMapping("/api/mypage/emailcheck")
	public int emailCheck(@RequestParam String email) {
		System.out.println("이메일체크옴");
		int cnt = 0;
		cnt = userService.emailCheck(email);
		System.out.println("실행됏냐: "+cnt);
		return cnt;
	}

	@ResponseBody
	@PostMapping("/api/mypage/niccheck")
	public int nicCheck(@RequestParam String nickname) {
		int cnt = 0;
		cnt = userService.nicCheck(nickname);

		return cnt;
	}

	@RequestMapping("/api/trade/writeModal/{tradeId}")
	public String writeModal(@PathVariable int tradeId, Model model) {
		model.addAttribute("userInfo", userService.getUserInfo());
		model.addAttribute("trade", tradeService.selectById(tradeId));
		return "tradeWriteModal";
	}

	@ResponseBody
	@RequestMapping("/api/trade/updateTrade")
	public void updateTrade(@RequestBody Map<String, Object> data) {
		String content = (String)data.get("content");
		String type = (String)data.get("type");
		int tradeId = (int) data.get("tradeId");
		String colName = type + "_content";
		tradeService.writeContent(tradeId, colName, content);

	}
}
