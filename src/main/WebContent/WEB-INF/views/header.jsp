<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
  <head>
    <meta id="_csrf" name="_csrf" content="${_csrf.token}" />
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />
  </head>
</html>
<div class="header bg-body-tertiary">
    <nav class="navbar">
        <a class="navbar-brand logo" href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/resources/image/carrot.jpeg" width="40" height="35"
                 class="align-text-top"/>
            당근나라
        </a>
        <div class="nav-button-wrapper">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/page/itemRegister">물품등록</a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/page/itemList">물품목록</a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/page/postList">동네생활</a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/page/chat">채팅</a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/page/mypageSell">마이페이지</a>
            <sec:authorize access="isAuthenticated()">
               <a class="navbar-brand" onclick="pageLogout()">로그아웃</a>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/page/login">로그인</a>
            </sec:authorize>
        </div>
    </nav>
</div>
<script>
function pageLogout() {
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	
	$.ajax({
		type: 'POST',
		url: '/logout',
		beforeSend: function (xhr) {
			xhr.setRequestHeader("content-type","application/json");
			xhr.setRequestHeader(header, token);
		}
	}).done(() => {
        window.location = "/";
    })
}	
</script>
