package com.carrot.repository;

import java.util.List;

import com.carrot.domain.AuthVO;
import com.carrot.domain.UserVO;

public interface UserRepository {

	public List<AuthVO> selectByAuth(String id);
	public int signUp(UserVO vo);
	public int signUp_auth(AuthVO authVo);
	public int idCheck(String id);
	public int nicCheck(String nickname);
	public int emailCheck(String email);
	public UserVO selectById(String id);
	
	public int updateUser(UserVO vo); //회원정보수정
	public int withdrawSignUp(String id, String password); //회원 탈퇴

}
