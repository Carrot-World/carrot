<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>

<script
 	type="text/javascript"
 	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7551008ffbd30aac5abaffdcc5a33d7f&libraries=services"></script>

<link href="${pageContext.request.contextPath}/resources/css/postList.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/resources/js/postList.js" defer></script>
</head>

<body>
<jsp:include page="/WEB-INF/views/header.jsp" />
<div class="section">
	<div class="img-wrapper">
		<img src="${pageContext.request.contextPath}/resources/image/postmain.png" width="100%">
	</div>
	<div class="section-header">
		<div class="search">
            <c:if test="${searchInfo == null}">
                <h3 class="curr-location">${user.nickname}님 동네(${user.loc1} ${user.loc2} ${user.loc3}) 의 동네생활</h3>
            </c:if>
            <c:if test="${searchInfo != null}">
                <h3 class="curr-location">검색 결과 : ${searchInfo.loc1} ${searchInfo.loc2} ${searchInfo.loc3}의 동네생활</h3>
            </c:if>
            <div class="search-location">
                <select class="form-select" id="loc1" name="loc1" onchange="changeLoc1Select()">
                    <option value="도시 선택">도시 선택</option>
                    <c:if test="${searchInfo == null}">
                        <c:forEach items="${loc1List}" var="location1">
                            <option ${location1 == user.loc1 ? 'selected' : ''}
                                    value="${location1}">${location1}</option>
                        </c:forEach>
                    </c:if>
                    <c:if test="${searchInfo != null}">
                        <c:forEach items="${loc1List}" var="location1">
                            <option ${location1 == searchInfo.loc1 ? 'selected' : ''}
                                    value="${location1}">${location1}</option>
                        </c:forEach>
                    </c:if>
                </select>
                <c:if test="${searchInfo == null}">
                    <select class="form-select" id="loc2" name="loc2" onchange="changeLoc2Select()">
                        <option value="지역 선택">지역 선택</option>
                        <c:forEach items="${loc2List}" var="location2">
                            <option ${location2 == user.loc2 ? 'selected' : ''}
                                    value="${location2}">${location2}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${searchInfo != null}">
                    <select class="form-select" id="loc2" name="loc2" ${searchInfo.loc1 == null ? "disabled" : ""}
                            onchange="changeLoc2Select()">
                        <option value="지역 선택">지역 선택</option>
                        <c:forEach items="${loc2List}" var="location2">
                            <option ${location2 == searchInfo.loc2 ? 'selected' : ''}
                                    value="${location2}">${location2}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${searchInfo == null}">
                    <select class="form-select" id="loc3" name="loc3">
                        <option value="동네 선택">동네 선택</option>
                        <c:forEach items="${loc3List}" var="location3">
                            <option ${location3 == user.loc3 ? 'selected' : ''}
                                    value="${location3}">${location3}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${searchInfo != null}">
                    <select class="form-select" id="loc3" name="loc3" ${searchInfo.loc2 == null ? "disabled" : ""}>
                        <option value="동네 선택">동네 선택</option>
                        <c:forEach items="${loc3List}" var="location3">
                            <option ${location3 == searchInfo.loc3 ? 'selected' : ''}
                                    value="${location3}">${location3}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <button class="btn" id="currBtn" onclick="currLocBtnHandler()">현재위치</button>
            </div>
            
			<div class="search-keyword">
				<select class="form-select" id="categorySelect" name="categorySelect">
					<option value="0" ${searchInfo == null ? "selected" : ""}>전체검색</option>
					<option value="1" ${searchInfo.category_id==1 ? "selected" : "" }>동네소식</option>
					<option value="2" ${searchInfo.category_id==2 ? "selected" : "" }>동네질문</option>
					<option value="3" ${searchInfo.category_id==3 ? "selected" : "" }>동네맛집</option>
					<option value="4" ${searchInfo.category_id==4 ? "selected" : "" }>일 상</option>
					<option value="5" ${searchInfo.category_id==5 ? "selected" : "" }>생활정보</option>
					<option value="6" ${searchInfo.category_id==6 ? "selected" : "" }>취미생활</option>
					<option value="7" ${searchInfo.category_id==7 ? "selected" : "" }>실시간 날씨 </option>
					<option value="8" ${searchInfo.category_id==8 ? "selected" : "" }>분실/실종센터 </option>
				</select> <input type="text" placeholder="검색어 입력" class="form-control"
					id="title" value="${searchInfo != null ? searchInfo.title : ''}">
				<button class="btn" id="searchBtn" onclick="searchBtnHandler()">검색</button>
				<button class="btn" id="btnInsertPost" onclick="location.href='/page/newpost'">글
					쓰기</button>
			</div>
		</div>

	</div>
	<div class="section-content" id="component">
		<c:if test="${list == null}">
            <h3> 게시글이 없습니다.</h3>
        </c:if>
        <c:if test="${list != null}">
		<div class="post-container">
			<table class="table table-hover post-table">
				<thead>
					<tr class="table-secondary">
						<th>카테고리</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>댓글</th>
					</tr>
				</thead>
				<c:forEach items="${ list }" var="list">
					<tr>
						<td>${ list.categoryName }</td>
						<td onclick="location.href='/page/detailpost/${list.id}'" style="cursor:pointer">${ list.title }
						</td>
						<td onclick="location.href='/page/userpageSell?id=${list.writer}'" style="cursor:pointer">${ list.nickname }</td>
						<td>
							<fmt:formatDate pattern="MM/dd hh:mm" value="${list.created_at}" />
						</td>
						<td>${ list.read_cnt }</td>
						<td>${ list.reply_cnt }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</c:if>
	</div>


	<c:if test="${page.total == page.current}">
		<c:forEach var="i" begin="1" end="${page.currPageCnt}">
			<div class="card hidden" id="${i}">
			</div>
		</c:forEach>
	</c:if>
</div>
<div class="section-footer">
	<nav>
		<c:if test="${searchInfo == null}">
			<ul class="pagination">
				<c:if test="${page.start != 1}">
					<li class="page-item">
						<a class="page-link"
							onclick="window.location='/page/postList?category_id=0&loc1=${user.loc1}&loc2=${user.loc2}&loc3=${user.loc3}&pageNo=${page.start - 1}'">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
				</c:if>
				<c:forEach var="i" begin="${page.start}" end="${page.end}">
					<li class="page-item ${i == page.current ? 'active' : ''}">
						<a class="page-link"
							onclick="window.location='/page/postList?category_id=0&loc1=${user.loc1}&loc2=${user.loc2}&loc3=${user.loc3}&pageNo=${i}'">
							${i}
						</a>
					</li>
				</c:forEach>
				<c:if test="${page.end != page.total}">
					<li class="page-item">
						<a class="page-link"
							onclick="window.location='/page/postList?category_id=0&loc1=${user.loc1}&loc2=${user.loc2}&loc3=${user.loc3}&pageNo=${page.end + 1}'">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:if>
			</ul>
		</c:if>
		<c:if test="${searchInfo != null}">
			<ul class="pagination">
				<c:if test="${page.start != 1}">
					<li class="page-item">
						<a class="page-link"
							onclick="window.location='/page/postList?category_id=${searchInfo.category_id}&loc1=${searchInfo.loc1}&loc2=${searchInfo.loc2}&loc3=${searchInfo.loc3}&title=${searchInfo.title}&pageNo=${page.start - 1}'">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
				</c:if>
				<c:forEach var="i" begin="${page.start}" end="${page.end}">
					<li class="page-item ${i == page.current ? 'active' : ''}">
						<a class="page-link"
							onclick="window.location='/page/postList?category_id=${searchInfo.category_id}&loc1=${searchInfo.loc1}&loc2=${searchInfo.loc2}&loc3=${searchInfo.loc3}&title=${searchInfo.title}&pageNo=${i}'">
							${i}
						</a>
					</li>
				</c:forEach>
				<c:if test="${page.end != page.total}">
					<li class="page-item">
						<a class="page-link"
							onclick="window.location='/page/postList?category_id=${searchInfo.category_id}&loc1=${searchInfo.loc1}&loc2=${searchInfo.loc2}&loc3=${searchInfo.loc3}&title=${searchInfo.title}&pageNo=${page.end + 1}'">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:if>
			</ul>
		</c:if>
	</nav>
</div>
<script src="${pageContext.request.contextPath}/resources/js/kakaoGeocoder.js"></script>
</body>
</html>