package com.carrot.handler;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.carrot.domain.AuthVO;
import com.carrot.domain.UserVO;
import com.carrot.service.UserService;


public class CustomUserDetails implements UserDetailsService{
    
    
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//userName means userid
	
		 UserService mapper = sqlSession.getMapper(UserService.class);
		 UserVO vo = mapper.selectById(username);
		 System.out.println(vo);
		 System.out.println("userid:" +username);
		 List <AuthVO> list = mapper.selectByAuth(username);
		 System.out.println("¸®½ºÆ®: "+list);
		 vo.setAuthList(list);
		
		return vo == null ? null : new CustomUser(vo);
	}

}
