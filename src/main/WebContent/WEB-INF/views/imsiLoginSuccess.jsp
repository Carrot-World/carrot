<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>임시 로그인 성공 페이지입니다.</h3><br/>
	메세지: ${msg}<hr/><br/>
	이 아래는 UserVO<br/>
	아이디: ${user.id}<br/>
	닉네임: ${user.nickname}<br/>
	이메일: ${user.email}<br/>
	시/도: ${user.loc1}<br/>
	시/군/구: ${user.loc2}<br/>
	읍/면/동: ${user.loc3}<br/>
</body>
</html>