<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />
<title>${ userinfo.nickname }의거래 후기 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous" />
<link
	href="${pageContext.request.contextPath}/resources/css/mypagetrade.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7551008ffbd30aac5abaffdcc5a33d7f&libraries=services"></script>
	<script defer src="${pageContext.request.contextPath}/resources/js/myTradePage.js" defer></script>
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

				<!-- 본인일 경우 버튼 활성화 -->

				<div class="button-wrapper">
					<button class="btn update" data-bs-target="#updateModal"
						data-bs-toggle="modal">내 정보수정</button>
				</div>
			</div>
		</div>
		<div class="section-content">
			<div class="section-tab">
				<ul class="nav nav-pills">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="mypageSell">판매물품</a></li>
					<li class="nav-item"><a class="nav-link" href="mypageBuy">구매물품</a></li>
					<li class="nav-item"><a class="nav-link" href="mypageHeart">찜 목록</a></li>
					<li class="nav-item"><a class="nav-link active" href="mypageTrade">거래후기</a></li>
				</ul>
			</div>
			<!-- 테스트용..!! 임시입니다. 바뀔거에요 -->
			<div class="reviews">
			<c:forEach items="${tradeList}" var="trade">
				<div class="review card">
					<div class="review-header">
						<h4>
							<c:if test="${trade.sellerName == userinfo.nickname}">
								${trade.buyerName}
							</c:if>
							<c:if test="${trade.sellerName != userinfo.nickname}">
								${trade.sellerName}
							</c:if>
						</h4>
						<span>-
							<c:if test="${trade.sellerName == userinfo.nickname}">
								${trade.bLoc1} ${trade.bLoc2} ${trade.bLoc3}
							</c:if>
							<c:if test="${trade.sellerName != userinfo.nickname}">
								${trade.sLoc1} ${trade.sLoc2} ${trade.sLoc3}
							</c:if>
						</span>
					</div>
					<div class="post-title" onclick="location.href='${pageContext.request.contextPath}/page/detail?id=${trade.item_post_id}'">
						물품글: ${trade.title}
					</div>
					<c:if test="${trade.sellerName == userinfo.nickname and trade.buyer_content != null}">
						<div class="review-content">
							${trade.buyer_content}
						</div>
					</c:if>
					<c:if test="${trade.buyerName == userinfo.nickname and trade.seller_content != null}">
						<div class="review-content">
								${trade.seller_content}
						</div>
					</c:if>
					<div class="review-footer">
							${trade.time} 거래완료
					</div>
					<c:if test="${(trade.buyerName == userinfo.nickname && trade.buyer_content == null)
					or (trade.sellerName == userinfo.nickname && trade.seller_content == null)}">
						<div class="review-content">
							<button class="btn orange-btn write-btn" onclick="writeModalOpen(${trade.id})">후기작성</button>
						</div>
					</c:if>
				</div>
			</c:forEach>
			</div>
		</div>
		<div class="section-footer"></div>
	</div>

	<!-- 회원정보 수정 모달 -->
<div class="modal fade" id="updateModal" tabindex="-1"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="updateModalLabel">내 정보수정</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="input-wrapper">
					<div class="input-row">
						<div class="label">ID:</div>
						<div class="input">
							<input class="form-control" type="text" value="${ userinfo.id }" id="userid" readonly="readonly"/>
				</div>
			</div>

			<div class="input-row">
				<div class="label">이메일:</div>
				<div class="input">
					<input class="form-control" type="text"
						value="${ userinfo.email }" id="newemail"> <input
						type="button" value="이메일인증" id="btn-emailCheck"
						onclick="emailModalOpen()">
				</div>
			</div>

			<div class="input-row">
				<div class="label">닉네임:</div>
				<div class="input">
					<input class="form-control" type="text"
						value="${ userinfo.nickname }" id="newnickname">
				</div>
			</div>

			<div class="input-row">
				<div class="label">지역:</div>
				<div class="input">
					<select class="form-select" id="loc1" name="loc1"
						onchange="changeLoc1Select()">
						<option value="도시 선택">도시 선택</option>
						<c:forEach items="${loc1List}" var="location1">
							<option ${location1 == userinfo.loc1 ? 'selected' : ''}
								value="${location1}">${location1}</option>
						</c:forEach>
					</select> <select class="form-select" id="loc2" name="loc2"
						onchange="changeLoc2Select()">
						<option value="지역 선택">지역 선택</option>
						<c:forEach items="${loc2List}" var="location2">
							<option ${location2 == userinfo.loc2 ? 'selected' : ''}
								value="${location2}">${location2}</option>
						</c:forEach>
					</select> <select class="form-select" id="loc3" name="loc3">
						<option value="동네 선택">동네 선택</option>
						<c:forEach items="${loc3List}" var="location3">
							<option ${location3 == userinfo.loc3 ? 'selected' : ''}
								value="${location3}">${location3}</option>
						</c:forEach>
					</select>

					<button class="btn" id="currBtn" onclick="currLocBtnHandler()">현재위치</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal-footer">
		<button class="btn orange-btn"
			onclick="updateInfo('${ userinfo.id }')">회원정보 수정</button>
		<!-- <button class="btn orange-btn" id="btnUdpateInfo" >회원정보 수정</button> -->
		<button class="btn orange-btn" onclick="passwordModalOpen()">비밀번호
			변경</button>
		<button class="btn btn-danger"
			onclick="btnWithdraw('${ userinfo.id }')">회원탈퇴</button>
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<!-- 비밀번호 변경 모달 -->
<div class="modal fade" id="passwordModal" tabindex="-1"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="passwordModalLabel">비밀번호 변경</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="input-wrapper">
					<div class="input-row">
						<div class="label">현재비밀번호:</div>
						<div class="input">
							<input class="form-control" type="password" id="password">
						</div>
					</div>

					<div class="input-row">
						<div class="label">새 비밀번호:</div>
						<div class="input">
							<input class="form-control" type="password" id="newpassword1" placeholder="영어, 숫자, 특수문자 1개 이상씩 사용하여 6~12자">
						</div>
					</div>

					<div class="input-row">
						<div class="label">비밀번호 확인:</div>
						<div class="input">
							<input class="form-control" type="password" id=newpassword>
						</div>
					</div>

				</div>
			</div>
			<div class="modal-footer">
				<button class="btn orange-btn" onclick="updateModalOpen()">회원정보
					수정</button>
				<button class="btn orange-btn" onclick="updatePassword('${userinfo.id}')">비밀번호 변경</button>
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<!-- 후기남기기 모달 -->
<div class="modal fade" id="writeModal" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="writeModalLabel">후기작성</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<label for="writeForm">OOO님의 후기작성</label>
				<textarea class="form-control write-btn" id="writeForm"></textarea>
			</div>
			<div class="modal-footer">
				<button class="btn orange-btn write-btn" onclick="">후기등록</button>
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/kakaoGeocoder.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/tradeWriteModal.js"></script>
</body>
</html>
