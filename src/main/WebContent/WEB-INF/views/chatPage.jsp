<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>채팅</title>
    <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
    crossorigin="anonymous"
    />
    <link href="${pageContext.request.contextPath}/resources/css/chatPage.css" rel="stylesheet" />
    <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
    crossorigin="anonymous"
    ></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
  </head>
  <body>
    <div class="header bg-body-tertiary">
      <nav class="navbar">
        <a class="navbar-brand logo" href="#">
          <img src="../image/carrot.jpeg" width="40" height="35" class="align-text-top" />
          당근나라
        </a>
        <div class="nav-button-wrapper">
          <a class="navbar-brand" href="#">물품등록</a>
          <a class="navbar-brand" href="#">물품목록</a>
          <a class="navbar-brand" href="#">동네생활</a>
          <a class="navbar-brand" href="#">채팅</a>
          <a class="navbar-brand" href="#">마이페이지</a>
          <a class="navbar-brand" href="#">로그인</a>
        </div>

      </nav>
    </div>
    <div class="section">
      <div class="room-container" id="roomArea">
        <h2 class="username" id="userName">${username}</h2>
        <c:forEach items="${rooms}" var="room">
          <div class="chat-room" roomId="${room.id}">
            <div class="chat-room-header">
              <span class="chat-user">
                <c:choose>
                  <c:when test="${username eq room.buyer}">${room.seller}</c:when>
                  <c:when test="${username eq room.seller}">${room.buyer}</c:when>
                </c:choose>
              </span>
              <span class="last-message-time">
                <fmt:formatDate pattern="MM/dd hh:mm" value="${room.lastMessage.createdAt}"/>
              </span>
            </div>
            <div class="chat-room-content">
              <span class="last-message">${room.lastMessage.content}</span>
              <c:if test="${room.unReadCnt != 0}">
                <span class="badge bg-danger float-end">${room.unReadCnt}</span>
              </c:if>
            </div>
          </div>
        </c:forEach>
      </div>
      <div class="chat-container" roomId="${rooms[0].id}">
        <div class="chat-header">
          <h2>
            <c:choose>
              <c:when test="${username eq rooms[0].seller}">${rooms[0].buyer}</c:when>
              <c:when test="${username eq rooms[0].buyer}">${rooms[0].seller}</c:when>
            </c:choose>
          </h2>
          <button class="btn red-btn">나가기</button>
        </div>

        <div class="chat-content" id="messageArea">
          <c:forEach items="${messages}" var="message">
            <c:choose>
              <c:when test="${username eq message.writer}">
                <div class="message-wrapper my">
              </c:when>
              <c:when test="${username ne message.writer}">
                <div class="message-wrapper">
              </c:when>
            </c:choose>
              <div class="card">
                <div class="card-body">
                  <div class="card-title">${message.writer}</div>
                  <div class="card-text">${message.content}</div>
                  <div class="card-time">
                    <fmt:formatDate pattern="MM/dd hh:mm" value="${message.createdAt}"/>
                  </div>

                </div>
              </div>
            </div>
          </c:forEach>
        </div>

        <div class="chat-input">
          <textarea class="form-control" id="chatInput"></textarea>
          <button class="btn orange-btn" id="send">전송</button>
        </div>
      </div>
    </div>
    </div>
    <script>
      $(window).scroll(function(){
        $('.header').css('left', 0-$(this).scrollLeft());
      });
    </script>
    <script src="${pageContext.request.contextPath}/resources/js/chat.js"></script>
  </body>
</html>

