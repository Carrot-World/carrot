<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<script>
	var locRegist = "${req_locRegist}";
	console.log(locRegist); 
	
if('${req_locRegist}' == "loc_isNull"){
		alert("지역 등록되어있지 않슴다 등록해주세요, 임시로그인석세스거쳐서 인서트아이템으로");
		window.location.href = "/page/insertItem";
}
	//window.location.href = "/page/login"; 

</script>

<meta charset="UTF-8">
<title>임시 로그인 성공페이지</title>
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
	
	<br/><br/>
   <form action="/logout" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button>로그아웃 버튼 입니다....</button>
   </form>
    <hr>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<button onclick='location.href="https://www.podo-dev.com/blogs/19"'>내 이름은 어드민~ </button> <!-- 권한 가진애들만 이 버튼보임 -->
</sec:authorize>

<sec:authorize access="hasRole('ROLE_MEMBER')">
	<button onclick='location.href="https://www.podo-dev.com/blogs/19"'>내 이름은 롤멤버~ </button>
</sec:authorize>

<sec:authentication property="principal" var="user2"/> 
타입 : ${user2} <br>
ID : ${user2.username} <br>
PW : ${user2.password}
	

</body>
</html>