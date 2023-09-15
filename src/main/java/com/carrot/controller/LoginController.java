package com.carrot.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carrot.domain.AuthVO;
import com.carrot.domain.UserVO;
import com.carrot.handler.CustomUser;
import com.carrot.repository.UserRepository;
import com.carrot.service.UserService;

@Controller
public class LoginController {
    
	@Autowired
	UserService userService; 
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired 
	BCryptPasswordEncoder encoder;
	
	
	@GetMapping("/page/login")
    public String login() {
		System.out.println("로그인페이지로 이동");
        return "login";
    }

    @GetMapping("/page/signup")
    public String signUp() {
    	System.out.println("회원가입페이지 이동");
        return "signUp";
    }
    
    @PostMapping("/api/signup")
    public String signUp(String id, String nickname, String password, String email, String loc1, String loc2, String loc3, RedirectAttributes redirectAttr) {
		
    	System.out.println("api/signUp");
    	String encodepw = encoder.encode(password);
    	
    	UserVO vo = new UserVO(id, nickname, encodepw, email, loc1, loc2, loc3);
    	AuthVO authVo = new AuthVO(id);
		UserRepository mapper = sqlSession.getMapper(UserRepository.class);

		int result = mapper.signUp(vo);
		result += mapper.signUp_auth(authVo);
    	
    	if(result==2) {
    		redirectAttr.addFlashAttribute("msg", "success");
            return "redirect:/page/signup_res";
    		
    	}
    	redirectAttr.addFlashAttribute("msg", "fail");
    	return "redirect:/page/signup_res";
    }
    
	
	@GetMapping("/page/signup_res")
	public String loginResult() {
		System.out.println("회원가입 결과창 들렸음");
		return "signUpResult";
	}
    
    @GetMapping("/api/success")
    public String success(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserRepository mapper = sqlSession.getMapper(UserRepository.class);
		UserVO vo = mapper.selectById(authentication.getName());
		System.out.println("auth: "+authentication);
		System.out.println("authentication.getAuthorities().getClass():"+authentication.getAuthorities().getClass());
		System.out.println("authentication.getAuthorities():"+authentication.getAuthorities());
		model.addAttribute("user",vo);
		model.addAttribute("msg",authentication.getName()+"님 어서오세요");

        return "listItem";
    }
    
    @GetMapping("/access_denied")
    public String deny() {

        return "accessDenied";
    }
    
    @GetMapping("/principal")
    public String cipal() { //로그인 한 이후에 가보셔야해요

	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	CustomUser customuser = (CustomUser)principal;
	//customuser.getUser() vo에 담고 있는 유저정보
	
	System.out.println(customuser);
	System.out.println(customuser.getUser());
	
	return "imsiLoginSuccess";
	}
    
    @ResponseBody
    @PostMapping("/api/signup/idcheck")
    public int idCheck(@RequestParam String id) {
    	int cnt = 0;
    	cnt = userService.idCheck(id);
    	
    	return cnt;
    }
    
    @ResponseBody
    @PostMapping("/api/signup/niccheck")
    public int nicCheck(@RequestParam String nickname) {
    	int cnt = 0;
    	cnt = userService.nicCheck(nickname);
    	
    	return cnt;
    }
    
    @ResponseBody
    @PostMapping("/api/signup/emailcheck")
    public int emailCheck(@RequestParam String email) {
    	int cnt = 0;
    	cnt = userService.emailCheck(email);
    	
    	return cnt;
    }

}
