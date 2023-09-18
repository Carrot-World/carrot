package com.carrot.service;

import com.carrot.domain.AuthVO;
import com.carrot.domain.SearchVO;
import com.carrot.domain.UserVO;
import com.carrot.handler.CustomUser;
import com.carrot.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private SqlSession sqlSession;

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
}