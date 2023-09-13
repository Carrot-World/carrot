package com.carrot.service;

import java.util.List;

import com.carrot.domain.AuthVO;
import com.carrot.domain.UserVO;

public interface UserService {
	public UserVO selectById(String id);
	public List<AuthVO> selectByAuth(String id);
	public int signUp(UserVO vo);
	public int signUp_auth(AuthVO authVo);
}
