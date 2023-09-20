package com.carrot.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

	    String loginFailureMessage = "로그인에 실패하였습니다."; // 기본 메시지

	    if (exception instanceof AuthenticationServiceException) {
	        loginFailureMessage = "죄송합니다. 시스템에 오류가 발생했습니다.";
	    } else if (exception instanceof BadCredentialsException) {
	        loginFailureMessage = "아이디 또는 비밀번호가 일치하지 않습니다.";
	    } else if (exception instanceof DisabledException) {
	        loginFailureMessage = "현재 사용할 수 없는 계정입니다.";
	    } else if (exception instanceof LockedException) {
	        loginFailureMessage = "현재 잠긴 계정입니다.";
	    } else if (exception instanceof AccountExpiredException) {
	        loginFailureMessage = "이미 만료된 계정입니다.";
	    } else if (exception instanceof CredentialsExpiredException) {
	        loginFailureMessage = "비밀번호가 만료된 계정입니다.";
	    }

	    // 세션에 로그인 실패 메시지 저장
	    request.getSession().setAttribute("loginFailureMessage", loginFailureMessage);

	    // 로그인 페이지로 포워드
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/api/loginfail");
	    dispatcher.forward(request, response);
	}
}
