package com.carrot.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	public int updatePwd(HashMap<String, String> map); //비밀번호 변경
	public int withdrawSignUp(String id); //회원 탈퇴
	public String findId(String email);
	public int findPassword(@Param("id")String id, @Param("email")String email);
	public int updatePassword(@Param("id")String id, @Param("password")String password);

}
