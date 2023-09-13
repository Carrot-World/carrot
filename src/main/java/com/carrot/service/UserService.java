package com.carrot.service;

import com.carrot.domain.UserVO;
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
        return sqlSession.getMapper(UserRepository.class).selectById(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public boolean isAnonymous() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ANONYMOUS"));
    }
}
