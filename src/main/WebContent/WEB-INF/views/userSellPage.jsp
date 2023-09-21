<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>${ userinfo.nickname }의판매내역 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous" />
<link
	href="${pageContext.request.contextPath}/resources/css/mypagesell.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/userSellPage.js" defer></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
	<div class="section">
		<div class="section-header">
			<div class="user-info-wrapper">
				<div id="user-info">
					<h4 id="username">${ userinfo.nickname }</h4>
					<span id="location">${ userinfo.loc1 } ${ userinfo.loc2 } ${ userinfo.loc3 }</span>
					<p id="dealCnt">거래횟수: ${ userinfo.completed_cnt }회</p>
				</div>
			</div>
		</div>
		<div class="section-content">
			<div class="section-tab">
				<ul class="nav nav-pills">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="userpageSell?id=${userinfo.id}">판매물품</a></li>
					<li class="nav-item"><a class="nav-link"
						href="userpageTrade?id=${userinfo.id}">거래후기</a></li>
				</ul>
			</div>

			<div>
				<div class="items">
					<c:forEach items="${list}" var="item">
						<c:if test="${ item.status == 0 }">
							<div class="card"
								onclick="location.href='/page/detail?id=${item.id}'">
								<c:if test="${item.imageList != null}">
									<c:forEach items="${item.imageList}" var="image" end="0">
										<img src="${image.url}" class="card-img-top">
									</c:forEach>
								</c:if>
								<c:if test="${item.imageList == null}">
									<img
										src="${pageContext.request.contextPath}/resources/imgae/noImage.png"
										class="card-img-top">
								</c:if>
								<div class="card-body">
									<h5 class="title">${ item.title }</h5>
									<p class="price">
										<fmt:formatNumber value="${item.price}" pattern="#,###" />
										원
									</p>
									<p class="location">${item.loc1}${item.loc2}${item.loc3}</p>
									<p class="count">찜 ${item.hart_cnt} ∙ 채팅 ${item.chat_cnt}</p>
								</div>
							</div>
						</c:if>
					</c:forEach>

					<c:forEach items="${list}" var="item">
						<c:if test="${ item.status == 1 }">
							<div class="card"
								onclick="location.href='/page/detail?id=${item.id}'">
								<c:if test="${item.imageList != null}">
									<c:forEach items="${item.imageList}" var="image" end="0">
										<img src="${image.url}" class="card-img-top" style="opacity: 0.5">
									</c:forEach>
								</c:if>
								<c:if test="${item.imageList == null}">
									<img
										src="${pageContext.request.contextPath}/resources/image/noImage.png"
										class="card-img-top" style="opacity: 0.5">
								</c:if>
								<div class="card-body">
									<h5 class="title">${ item.title }</h5>
									<p class="price">
										<fmt:formatNumber value="${item.price}" pattern="#,###" />
										원
									</p>
									<p class="location">${item.loc1}${item.loc2}${item.loc3}</p>
									<p class="count">찜 ${item.hart_cnt} ∙ 채팅 ${item.chat_cnt}</p>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="section-footer"></div>
	</div>
</body>
</html>
