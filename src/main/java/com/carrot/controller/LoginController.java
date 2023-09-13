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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carrot.domain.AuthVO;
import com.carrot.domain.UserVO;
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

    @GetMapping("/page/signUp")
    public String signUp() {
    	System.out.println("회원가입페이지 이동");
        return "signUp";
    }
    
    @PostMapping("/api/signUp")
    public String signUp(String id, String nickname, String password, String email, String loc1, String loc2, String loc3, RedirectAttributes redirectAttr) {
		
    	System.out.println("api/signUp");
    	String encodepw = encoder.encode(password);
    	
    	UserVO vo = new UserVO(id, nickname, encodepw, email, loc1, loc2, loc3);
    	AuthVO authVo = new AuthVO(id);
    	System.out.println(loc1);
    	System.out.println(loc2);
    	System.out.println(loc3);
		UserRepository mapper = sqlSession.getMapper(UserRepository.class);

		int result = mapper.signUp(vo);
		result += mapper.signUp_auth(authVo);
    	
    	if(result==2) {
    		redirectAttr.addFlashAttribute("msg", "success");
            return "redirect:/page/signUpResult";
    		
    	}
    	redirectAttr.addFlashAttribute("msg", "fail");
    	return "redirect:/page/signUpResult";
    }
    
	
	@GetMapping("/page/signUpResult")
	public String loginResult() {
		System.out.println("회원가입 결과창 들렸음");
		return "signUpResult";
	}
    
    @GetMapping("/api/success")
    public String success(Model model) {
    	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserRepository mapper = sqlSession.getMapper(UserRepository.class);
		UserVO vo = mapper.selectById(authentication.getName());
		
		model.addAttribute("user",vo);
		model.addAttribute("msg",authentication.getName()+"님 어서오세요");
    	
        return "imsiLoginSuccess";
    }
    
    @GetMapping("/accessDenied")
    public String deny() {

        return "accessDenied";
    }

}
