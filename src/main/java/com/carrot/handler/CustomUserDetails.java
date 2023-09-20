package com.carrot.handler;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.carrot.domain.AuthVO;
import com.carrot.domain.UserVO;
import com.carrot.repository.UserRepository;


public class CustomUserDetails implements UserDetailsService{
    
    
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//userName means userid
	
		 UserRepository mapper = sqlSession.getMapper(UserRepository.class);
		 UserVO vo = mapper.selectById(username);
		 System.out.println("vo: "+vo);
		 System.out.println("userid: " +username);
		 List <AuthVO> list = mapper.selectByAuth(username);
		 System.out.println("권한 리스트: "+list);
		 if(list != null) {
			 vo.setAuthList(list);
		 }
		 System.out.println("authlist세팅잘됏니: "+vo.getAuthList());
		return vo == null ? null : new CustomUser(vo);
	}

}
