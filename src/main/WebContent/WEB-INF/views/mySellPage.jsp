<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
			<!DOCTYPE html>
			<html lang="en">

			<head>
				<meta charset="UTF-8" />
				<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
				<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />
				<meta name="viewport" content="width=device-width, initial-scale=1.0" />
				<title>Document</title>
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
					integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
					crossorigin="anonymous" />
				<link href="${pageContext.request.contextPath}/resources/html/mypage/style(sell).css"
					rel="stylesheet" />
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
					crossorigin="anonymous"></script>
				<script src="https://code.jquery.com/jquery-3.7.1.min.js"
					integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
			</head>

			<body>
			<jsp:include page="/WEB-INF/views/header.jsp"/>
				<div class="section">
					<div class="section-header">
						<div class="user-info-wrapper">
							<div id="user-info">
								<h4 id="username">${ userinfo.nickname }</h4>
								<span id="location">${ userinfo.loc1 } ${ userinfo.loc2 } ${ userinfo.loc3 }</span>
								<p id="dealCnt">거래횟수: ${ userinfo.completed_cnt }회</p>
							</div>
							<div class="button-wrapper">
								<button class="btn update" data-bs-target="#updateModal"
									data-bs-toggle="modal">내정보수정</button>
							</div>
						</div>
					</div>
					<div class="section-content">
						<div class="section-tab">
							<ul class="nav nav-pills">
								<li class="nav-item"><a class="nav-link active" aria-current="page"
										href="mypageSell">판매물품</a></li>
								<li class="nav-item"><a class="nav-link" href="mypageBuy">구매물품</a></li>
								<li class="nav-item"><a class="nav-link" href="mypageReply">거래후기</a></li>
							</ul>
						</div>

						<div>
							<div class="items">
								<c:forEach items="${list}" var="item">
									<c:if test="${ item.status == 0 || item.status == 1 }">
										<div class="card" onclick="location.href='/page/detail?id=${item.id}'">
											<c:if test="${item.imageList != null}">
												<c:forEach items="${item.imageList}" var="image" end="0">
													<img src="${pageContext.request.contextPath}/resources/html/image/img.jpg"
														class="card-img-top">
												</c:forEach>
											</c:if>
											<c:if test="${item.imageList == null}">
												<img src="${pageContext.request.contextPath}/resources/html/image/noImage.png"
													class="card-img-top">
											</c:if>
											<div class="card-body">
												<h5 class="title">${ item.title }</h5>
												<p class="price">
													<fmt:formatNumber value="${item.price}" pattern="#,###" />
													원
												</p>
												<p class="location">${item.loc1} ${item.loc2} ${item.loc3}</p>
												<p class="count">찜 ${item.hart_cnt} ∙ 채팅 ${item.chat_cnt}</p>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>

					<div class="section-footer">

						<nav>
							<ul class="pagination">
								<li class="page-item"><a class="page-link" href="#"> <span
											aria-hidden="true">&laquo;</span>
									</a></li>
								<li class="page-item"><a class="page-link active" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#"> <span
											aria-hidden="true">&raquo;</span>
									</a></li>
							</ul>
						</nav>

					</div>
				</div>

				<!-- 회원정보 수정 모달 -->
				<div class="modal fade" id="updateModal" tabindex="-1" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="updateModalLabel">내정보수정</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div class="input-wrapper">
									<div class="input-row">
										<div class="label">ID:</div>
										<div class="input">
											<input class="form-control" type="text" value="${ userinfo.id }" id="userid"
												readonly="readonly">
										</div>
									</div>

									<div class="input-row">
										<div class="label">이메일:</div>
										<div class="input">
											<input class="form-control" type="text" value="${ userinfo.email }"
												id="newemail"> <input type="button" value="이메일인증" id="btn-emailCheck"
												onclick="emailModalOpen()">
										</div>
									</div>

									<div class="input-row">
										<div class="label">닉네임:</div>
										<div class="input">
											<input class="form-control" type="text" value="${ userinfo.nickname }"
												id="newnickname">
										</div>
									</div>

									<div class="input-row">
										<div class="label">지역:</div>
										<div class="input">
											<select class="form-select" id="newloc1">
												<option selected>도, 시</option>
												<option value="1">서울</option>
												<option value="2">인천</option>
												<option value="3">경기</option>
											</select> <select class="form-select" id="newloc2">
												<option selected>구, 시</option>
												<option value="1">One</option>
												<option value="2">Two</option>
												<option value="3">Three</option>
											</select> <select class="form-select" id="newloc3">
												<option selected>동, 면, 읍</option>
												<option value="1">One</option>
												<option value="2">Two</option>
												<option value="3">Three</option>
											</select>
											<button class="btn" id="currBtn">현재위치</button>
										</div>
									</div>

								</div>
							</div>
							<div class="modal-footer">
								<button class="btn orange-btn" onclick="updateInfo('${ userinfo.id }')">회원정보 수정</button>
								<!-- <button class="btn orange-btn" id="btnUdpateInfo" >회원정보 수정</button> -->
								<button class="btn orange-btn" onclick="passwordModalOpen()">비밀번호
									변경</button>
								<button class="btn btn-danger" onclick="btnWithdraw('${ userinfo.id }')">회원탈퇴</button>
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
							</div>
						</div>
					</div>
				</div>

				<!-- 비밀번호 변경 모달 -->
				<div class="modal fade" id="passwordModal" tabindex="-1" aria-hidden="true">
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
											<input class="form-control" type="password">
										</div>
									</div>

									<div class="input-row">
										<div class="label">비밀번호 확인:</div>
										<div class="input">
											<input class="form-control" type="password" id="newpassword">
										</div>
									</div>

								</div>
							</div>
							<div class="modal-footer">
								<button class="btn orange-btn" onclick="updateModalOpen()">회원정보
									수정</button>
								<button class="btn orange-btn" onclick="updatePassword('${ userinfo.id }')">비밀번호
									변경</button>
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
							</div>
						</div>
					</div>
				</div>


				<!-- 이메일 인증 Modal -->
				<div class="modal fade" id="#staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"
					tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog ">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="staticBackdropLabel">이메일 인증</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body row">

								<div id="custom-modal-row" class="mb-3">
									<input type="text" id="modal-text-email" class="modal-text" readonly="readonly" />
									<input type="button" id="request-authnum" value="인증 요청">
								</div>
								<div id="custom-modal-row" class="mb-3">
									<input type="text" id="res-authnum-text" class="modal-text" /> <input type="button"
										id="res-authnum" value="인증 확인">
									<!--  <input type="text" id="time-limit" value="유효시간" size="6"> -->
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" id="submit-email-auth">확인</button>
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
							</div>
						</div>
					</div>
				</div>


				<script>
					$(window).scroll(function () {
						$('.header').css('left', 0 - $(this).scrollLeft());
					});

					var header = $("meta[name='_csrf_header']").attr('content');
	        		var token = $("meta[name='_csrf']").attr('content');

					// 쓰로틀
					// 물품목록 4개씩 고정하기 위해
					function rescaleCard() {
						const wrapperWidth = document.querySelector("div.items").offsetWidth;
						const offset = 30;
						const cardWidth = Math.ceil(wrapperWidth * 270 / 1336);

						const cards = document.querySelectorAll("div.card");
						const cardImg = document.querySelectorAll("img.card-img-top");
						for (let i = 0; i < cards.length; i++) {
							cards[i].style.width = `${cardWidth}px`;
							cardImg[i].style.height = `${cardWidth}px`;
						}
					}

					function throttle(fn, delay) {
						let waiting = false;
						return () => {
							if (!waiting) {
								fn.apply(this);
								waiting = true;
								setTimeout(() => {
									waiting = false;
								}, delay);
							}
						}
					}

					window.addEventListener("resize", throttle(rescaleCard, 100));

					const updateModal = new bootstrap.Modal("#updateModal");
					const passwordModal = new bootstrap.Modal("#passwordModal");

					function passwordModalOpen() {
						updateModal.hide();
						passwordModal.show();
					}

					function updateModalOpen() {
						passwordModal.hide();
						updateModal.show();
					}


					
					
					
					function updateInfo(e) {
					/* var target = document.getElementById('btnUdpateInfo');
					const e = document.querySelector('#userid').value;

					target.addEventListener('click', function(e){ */
						const nickname = document.querySelector("#newnickname").value;
						const email = document.querySelector("#newemail").value;
						const loc1 = document.querySelector("#newloc1").value;
						const loc2 = document.querySelector("#newloc2").value;
						const loc3 = document.querySelector("#newloc3").value;

						let data = {
							'id': e,
							'nickname': nickname,
							'email' : email,
							'loc1' : loc1,
							'loc2' : loc2,
							'loc3' : loc3
						};

						$.ajax({
							url: '/api/myPage/updateinfo',
							type: 'POST',
							dataType: 'json',
							contentType: 'application/json',
							data: JSON.stringify(data),
							beforeSend: function (xhr) {
								xhr.setRequestHeader(header, token)
							},
							success: (result) => {
								console.log(result);
								location.reload();
							},
							error: () => {
								alert('수정 실패')
								location.reload();
							}
						})

					}
					/* ) */

					function updatePassword(e) {

						const password = document.querySelector("#password").value;
						const newpassword = document.querySelector("#newpassword").value;
						
						let data = {
							'id' : e,
							'password': password,
							'newpassword': newpassword
						};

						$.ajax({
							url: '/api/myPage/updatepassword',
							type: 'POST',
							dataType: 'json',
							contentType: 'application/json',
							data: JSON.stringify(data),
							beforeSend: function (xhr) {
								xhr.setRequestHeader(header, token)
							},
							success: (result) => {
								console.log(result);
								logout();
							},
							error: () => {
								alert('변경 실패 비밀번호를 다시 확인해 주세요')
								location.reload();
							}
						})

					}
					
					/* function logout(){
						$.ajax({
							url: 'logout',
							type: 'POST', 
							contentType: 'x-www-form-urlencoded',
							
						})
						
					} */

					function btnWithdraw(e) {

						let userid = { 'id' :  e };

						$.ajax({
							url: '/api/myPage/withdraw',
							type: 'POST',
							dataType : 'json',
							contentType:'application/json',
							data: JSON.stringify(userid),
							beforeSend: function (xhr) {
								xhr.setRequestHeader(header, token)
							},
							success: (result) => {
								console.log(result);
								logout();
							},
							error: () => {
								alert('탈퇴 실패')
								location.reload();
							}

						})
					}

					function logout() {

						$.ajax({
							url: '/logout',
							type: 'POST',
							dataType : 'json',
							contentType:'application/json',
							beforeSend: function (xhr) {
								xhr.setRequestHeader(header, token)
							}
						})
					}

					$("#btn-emailCheck").click(function (e) {
						e.preventDefault();

						// alert("Button 이메일 인증버튼 Clicked!");

						var email = $("#email").val();
						var tokenInput = $("#token");
						var data = { email: email };
						data[tokenInput.attr("name")] = tokenInput.val();
						//   var str = email;
						//   str += "은 사용 가능합니다.";

						$.ajax({
							async: true,
							type: "POST",
							data,
							url: "/api/signup/emailcheck",
							dataType: "json",
							contentType: "application/x-www-form-urlencoded",
							success: function (cnt) {
								if (cnt > 0) {
									alert("해당 이메일 존재");
									$("#submit").attr("disabled", "disabled");
									$("#eamil").focus();
								} else {
									$("#submit").removeAttr("disabled");
									$("#modal-text-email").val(email);
									$("#res-authnum-text").val("");
									$("#staticBackdrop").modal("show");
									sendEmail();
								}
							},
							error: function (error) {
								alert("이메일을 재입력해주세요.");
							},
						});
					});

					function sendEmail() {
						var email_auth_cd = "";
						var eamil_auth_compl = false;

						$("#res-authnum").click(function () {
							if ($("#res-authnum-text").val() != email_auth_cd) {
								alert("인증번호가 일치하지 않습니다.");
								eamil_auth_compl = false;
							}
							if ($("#res-authnum-text").val() == email_auth_cd) {
								alert("인증번호가 일치 합니다.");
								eamil_auth_compl = true;
							}
						});

						$("#request-authnum").click(function () {
							eamil_auth_compl = false;
							var email = $("#email").val();
							var tokenInput = $("#token");
							var data = { email: email };
							data[tokenInput.attr("name")] = tokenInput.val();

							$.ajax({
								type: "POST",
								url: "/api/sendemail",
								data,
								success: function (data) {
									alert("인증번호가 발송되었습니다.");
									email_auth_cd = data;
								},
								error: function (data) {
									alert("메일 발송에 실패했습니다.");
								},
							});
						});
						$("#submit-email-auth").click(function () {
							if (eamil_auth_compl == true) {
								alert("인증이 완료되었습니다.");
								$("#email").prop("readonly", true);
								$("#email").attr("style", "background-color:#80808021;");
								$("#staticBackdrop").modal("hide");
							}
							if (eamil_auth_compl == false) {
								alert("인증이 완료되지 않았습니다.");
							}
						});
					}

				</script>
			</body>

			</html>