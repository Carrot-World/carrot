<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous" />
<link
	href="${pageContext.request.contextPath}/resources/html/postList/style.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>

</head>
<body>
	<!-- <div class="min-width"></div> -->
	<div class="header bg-body-tertiary">
		<nav class="navbar">
			<a class="navbar-brand logo" href="#"> <img
				src="../image/carrot.jpeg" width="40" height="35"
				class="align-text-top" /> 당근나라
			</a>
			<div class="nav-button-wrapper">
				<a class="navbar-brand" href="#">물품등록</a> <a class="navbar-brand"
					href="#">물품목록</a> <a class="navbar-brand" href="#">동네생활</a> <a
					class="navbar-brand" href="#">채팅</a> <a class="navbar-brand"
					href="#">마이페이지</a> <a class="navbar-brand" href="#">로그인</a>
			</div>

			</a>
		</nav>
	</div>
	<div class="section">
		<div class="img-wrapper">
			<img src="../image/postmain.png" width="100%">
		</div>
		<div class="section-header">
			<div class="search">
				<c:if test="${ user.loc1 == null}">
					<h3 class="curr-location">동네생활</h3>
				</c:if>
				<c:if test="${ user.loc1 != null }">
					<h3 class="curr-location">${ user.loc1 }${ user.loc2 } ${ user.loc3 }의 동네생활</h3>
				</c:if>
				<div class="search-location">
					<select class="form-select">
						<option selected>도, 시</option>
						<option value="1">서울</option>
						<option value="2">인천</option>
						<option value="3">경기</option>
					</select> <select class="form-select">
						<option selected>구, 시</option>
						<option value="1">One</option>
						<option value="2">Two</option>
						<option value="3">Three</option>
					</select> <select class="form-select">
						<option selected>동, 면, 읍</option>
						<option value="1">One</option>
						<option value="2">Two</option>
						<option value="3">Three</option>
					</select>
					<button class="btn" id="currBtn">현재위치</button>
				</div>
				<div class="search-keyword">
					<select class="form-select">
						<option value="0">전체검색</option>
						<option value="1">동네소식</option>
						<option value="2">동네질문</option>
						<option value="3">동네맛집</option>
						<option value="4">일 상</option>
						<option value="5">생활정보</option>
						<option value="6">취미생활</option>
						<option value="7">실시간 날씨</option>
						<option value="8">분실/실종센터</option>
					</select> <input type="text" placeholder="검색어 입력" class="form-control"
						id="searchKeyword">
					<button class="btn" id="searchBtn">검색</button>
				</div>
			</div>

		</div>
		<div class="section-content">
			<div class="post-container">
				<c:forEach items="${ list }" var="list">
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
						<tr>
							<td>${ list.categoryName }</td>
							<td onclick="location.href='/page/detailpost/${list.id}'">${ list.title }</td>
							<td>${ list.writer }</td>
							<td>${ list.created_at }</td>
							<td>${ list.read_cnt }</td>
							<td>${ list.reply_cnt }</td>
						</tr>
					</table>
				</c:forEach>
			</div>
		</div>

		<div class="section-footer">

			<nav aria-label="...">
				<ul class="pagination">
					<c:if test="${page.start != 1}">
						<li class="page-item"><a class="page-link"
							onclick="pageMove('/api/post/search?category_id=0&loc1=${user.loc1}&loc2=${user.loc2}&loc3=${user.loc3}&pageNo=${page.start - 1}')">
								&laquo;</a></li>
					</c:if>
					<c:forEach var="i" begin="${page.start}" end="${page.end}">
						<li class="page-item ${i == page.current ? 'active' : ''}"><a
							class="page-link"
							onclick="pageMove('/api/post/search?category_id=0&loc1=${user.loc1}&loc2=${user.loc2}&loc3=${user.loc3}&pageNo=${i}')">
								${i} </a></li>
					</c:forEach>
					<c:if test="${page.end != page.total}">
						<li class="page-item"><a class="page-link"
							onclick="pageMove('/api/post/search?category_id=0&loc1=${user.loc1}&loc2=${user.loc2}&loc3=${user.loc3}&pageNo=${page.end + 1}')">
								&raquo;</a></li>
					</c:if>
				</ul>
			</nav>


		</div>
	</div>
	<script>
      $(window).scroll(function(){
        $('.header').css('left', 0-$(this).scrollLeft());
      });
      
      
      function searchBtnHandler() {
  	    var categorySelect = document.getElementById("categorySelect");
  	    var categorySelectId = categorySelect.options[categorySelect.selectedIndex].value;
  	    var loc1Select = document.getElementById("loc1");
  	    var loc1SelectValue = loc1Select.options[loc1Select.selectedIndex].value;
  	    var loc2Select = document.getElementById("loc2");
  	    var loc2SelectValue = loc2Select.options[loc2Select.selectedIndex].value;
  	    var loc3Select = document.getElementById("loc3");
  	    var loc3SelectValue = loc3Select.options[loc3Select.selectedIndex].value;
  	    var titleValue = document.getElementById("searchKeyword").value.trim;

  	    if (loc1SelectValue === "") {
  	        $("#loc2").find("option:eq(0)").prop("selected", true);
  	        $("#loc3").find("option:eq(0)").prop("selected", true);

  	        $.ajax({
  	            url: "/api/item/search",
  	            data: {
  	                "category_id": categorySelectId
  	            },
  	            method: "get",
  	            dataType: "text"
  	        }).done((text) => {
  	            $("div#component").html(text);
  	        })
  	    } else if (loc2SelectValue === "") {
  	        $("#loc3").find("option:eq(0)").prop("selected", true);

  	        $.ajax({
  	            url: "/api/item/search",
  	            data: {
  	                "category_id": categorySelectId,
  	                "loc1": loc1SelectValue
  	            },
  	            method: "get",
  	            dataType: "text"
  	        }).done((text) => {
  	            $("div#component").html(text);
  	        })

  	    } else if (loc3SelectValue === "") {

  	        $.ajax({
  	            url: "/api/item/search",
  	            data: {
  	                "category_id": categorySelectId,
  	                "loc1": loc1SelectValue,
  	                "loc2": loc2SelectValue
  	            },
  	            method: "get",
  	            dataType: "text"
  	        }).done((text) => {
  	            $("div#component").html(text);
  	        })

  	    } else {
  	        $.ajax({
  	            url: "/api/item/search",
  	            data: {
  	                "category_id": categorySelectId,
  	                "loc1": loc1SelectValue,
  	                "loc2": loc2SelectValue,
  	                "loc3": loc3SelectValue,
  	                "title" : titleValue
  	            },
  	            method: "get",
  	            dataType: "text"
  	        }).done((text) => {
  	            $("div#component").html(text);
  	        })
  	    }
  	}

  	function pageMove(url) {
  	    $.ajax({
  	        url,
  	        dataType: "text"
  	    }).done((text) => {
  	        $("div#component").html(text);
  	    })
  	}
      
    </script>
</body>
</html>
