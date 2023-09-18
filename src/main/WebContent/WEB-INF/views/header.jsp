<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="header bg-body-tertiary">
    <nav class="navbar">
        <a class="navbar-brand logo" href="${pageContext.request.contextPath}/page/itemList">
            <img src="${pageContext.request.contextPath}/resources/image/carrot.jpeg" width="40" height="35"
                 class="align-text-top"/>
            당근나라
        </a>
        <div class="nav-button-wrapper">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/page/itemRegister">물품등록</a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/page/itemList">물품목록</a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/page/postList">동네생활</a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/page/chat">채팅</a>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/page/mypage">마이페이지</a>
            <sec:authorize access="isAuthenticated()">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/logout">로그아웃</a>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/page/login">로그인</a>
            </sec:authorize>
        </div>
    </nav>
</div>