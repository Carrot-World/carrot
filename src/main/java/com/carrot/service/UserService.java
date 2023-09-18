package com.carrot.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.carrot.domain.AuthVO;
import com.carrot.domain.SearchVO;
import com.carrot.domain.UserVO;
import com.carrot.handler.CustomUser;
import com.carrot.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private SqlSession sqlSession;
    
    @Autowired 
	BCryptPasswordEncoder encoder;
    

    public UserVO getUserInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUser customuser = (CustomUser) principal;
        return customuser.getUser();
    }

    public boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ANONYMOUS"));
    }

    public SearchVO setUserLocation() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUser customuser = (CustomUser) principal;
        UserVO user = customuser.getUser();
        return new SearchVO(user.getLoc1(), user.getLoc2(), user.getLoc3());
    }
    
    public int idCheck(String id) {
    	return sqlSession.getMapper(UserRepository.class).idCheck(id);
    }
    
    public int nicCheck(String nickname) {
    	return sqlSession.getMapper(UserRepository.class).nicCheck(nickname);
    }
    
    public int emailCheck(String email) {
    	return sqlSession.getMapper(UserRepository.class).emailCheck(email);
    }
    
    public UserVO selectById(String id) {
    	return sqlSession.getMapper(UserRepository.class).selectById(id);
    }

    public int signUp(UserVO vo) {
    	return sqlSession.getMapper(UserRepository.class).signUp(vo);
    }
    public int signUp_auth(AuthVO authVo) {
    	return sqlSession.getMapper(UserRepository.class).signUp_auth(authVo);
    	
    }
    
    public int updateUser(UserVO vo) { //회원정보수정
    	int cnt = sqlSession.getMapper(UserRepository.class).updateUser(vo);
    	UserVO savedvo = getUserInfo();
    	savedvo.setNickname(vo.getNickname());
    	savedvo.setEmail(vo.getEmail());
    	savedvo.setLoc1(vo.getLoc1());
    	savedvo.setLoc2(vo.getLoc2());
    	savedvo.setLoc3(vo.getLoc3());
    	return cnt;
    }
    
    public int updatePwd(String id, String newpassword) { //비밀번호 변경
    	String encodepw = encoder.encode(newpassword);
    	HashMap<String, String> map = new HashMap<>();
    	map.put("id", id);
    	map.put("encodepw", encodepw);
    	
    	return sqlSession.getMapper(UserRepository.class).updatePwd(map);
    }
    
    public boolean pwdCheck(String id, String password) { //비밀번호 확인
    	UserVO vo = sqlSession.getMapper(UserRepository.class).selectById(id);
    	String encodepw = encoder.encode(password);
    	String beforepwd = vo.getPassword();
    	
    	if ( encoder.matches(password, beforepwd) ) {
    		System.out.println("비밀번호 맞음!");
    		return true;
    	} else {
    		System.out.println("비밀번호 틀림!");
    		return false;
    	}
    }

    public boolean withdrawSignUp(String id) { //회원탈퇴
//    	String encodepw = encoder.encode(password);
    	int result = sqlSession.getMapper(UserRepository.class).withdrawSignUp(id);
    	if (result <= 0) {
    		return false;
    	}
    	return true;
    }
    
}