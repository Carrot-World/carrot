<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta id="_csrf" name="_csrf" content="${_csrf.token}" />
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />
    <title>Document</title>
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7551008ffbd30aac5abaffdcc5a33d7f&libraries=services"></script>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous"
    ></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<%--<div class="header bg-body-tertiary">--%>
<%--    <nav class="navbar">--%>
<%--        <a class="navbar-brand logo" href="${pageContext.request.contextPath}/index.jsp">--%>
<%--            <img src="${pageContext.request.contextPath}/resources/image/carrot.jpeg" width="40" height="35"--%>
<%--                 class="align-text-top"/>--%>
<%--            당근나라--%>
<%--        </a>--%>
<%--        <div class="nav-button-wrapper">--%>
<%--            <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/page/itemRegister">물품등록</a>--%>
<%--            <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/page/itemList">물품목록</a>--%>
<%--            <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/page/postList">동네생활</a>--%>
<%--            <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/page/chat">채팅</a>--%>
<%--            <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/page/mypageSell">마이페이지</a>--%>
<%--            <sec:authorize access="isAuthenticated()">--%>
<%--                <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="" onclick="pageLogout()">로그아웃</a>--%>
<%--            </sec:authorize>--%>
<%--            <sec:authorize access="isAnonymous()">--%>
<%--                <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/page/login">로그인</a>--%>
<%--            </sec:authorize>--%>
<%--        </div>--%>
<%--    </nav>--%>
<%--</div>--%>
<div class="section">
    <div class="img1">
        <img src="${pageContext.request.contextPath}/resources/image/mainImg1.png" width="100%"/>
    </div>
    <div class="img2">
        <img src="${pageContext.request.contextPath}/resources/image/mainImg2.png" width="100%"/>
    </div>
    <div class="img3">
        <img src="${pageContext.request.contextPath}/resources/image/mainImg3.png" width="100%"/>
    </div>
</div>
<script>
$(window).scroll(function(){
    $('.header').css('left', 0-$(this).scrollLeft());
});
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
	})
}	
</script>
</body>
</html>