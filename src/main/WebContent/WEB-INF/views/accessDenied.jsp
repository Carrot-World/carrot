<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>권한 접근 금지</title>
</head>
<body>
      <br><br>
      <h1 style="text-align:center">접근 권한이 없습니다.</h1><br>
      <h1 style="text-align:center">관리자에게 문의해주세요.</h1><br>
      <a href="/page/main">홈으로</a>
    <form action="logout" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <span style="text-align:center"><button >로그아웃</button></span>
    </form>
    
</body>
</html>