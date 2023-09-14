<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>권한 접근 금지</title>
</head>
<body>
    <h1>권한이 없습니다.</h1>
    <a href="/page/listItem">홈으로</a>
    <a href="/page/login">로그인으로</a>
    
    <form action="logout" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button>로그아웃 버튼 입니다....</button>
    </form>
    
</body>
</html>