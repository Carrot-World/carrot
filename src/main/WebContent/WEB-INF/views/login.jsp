<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <!-- 부트스트랩 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <!-- 부트스트랩 -->
    <link href="${pageContext.request.contextPath }/resources/css/login.css?after" rel="stylesheet">
    <!-- 사용자 css -->
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>   
    <script defer src="../resources/js/login.js" ></script>
    
<title>로그인 페이지</title>
</head>
<body>

  <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card border-0 shadow rounded-3 my-5">
          <div class="card-body p-4 p-sm-5">
            <h5 class="card-title text-center mb-5 fw-light fs-5">로그인</h5>
             <form action="/login" method="post">
              <div class="form-floating mb-3">
                <input type="id" name="username" class="form-control login-id-input" id="floatingInput" placeholder="ID">
                <label for="floatingInput">아이디</label>
              </div>
              <div class="form-floating mb-3">
                <input type="password" name="password" class="form-control login-pw-input" id="floatingPassword" placeholder="Password">
                <label for="floatingPassword">비밀번호</label>
              </div>
							<div>
								<input type="hidden" id="token" name="${_csrf.parameterName}" value="${_csrf.token}">
							</div>
              <div class="form-check mb-3">
                <input class="form-check-input" type="checkbox" value="" id="rememberPasswordCheck">
                <label class="form-check-label" for="rememberPasswordCheck">
                  아이디 기억하기
                </label>
              </div>
              <div class="d-grid">
                <button class="btn btn-primary btn-loginType btn-loginCheck fw-bold" type="submit">로그인
                 </button>
              </div>
           	 </form>	
              <hr class="my-4">
              <div class="d-grid mb-2">
                <button class="btn btn-email btn-loginType fw-bold">
                  <div>
                		<img class="btn-logo-size" src="../resources/image/email.png">
                		<span> 이메일로 시작하기</span>
                	</div>
                </button>
              </div>
              <div class="d-grid mb-2">
                <button class="btn btn-google btn-loginType fw-bold" >
                	<div>
                		<img class="btn-logo-size" src="../resources/image/google.png">
                		<span> 구글로 시작하기</span>
                	</div>
                </button>
              </div>
              
              <div class="d-grid mb-2">
                <button class="btn btn-kakao btn-loginType fw-bold" >
                  <div>
                		<img class="btn-logo-size" src="../resources/image/kakao.png">
                		<span> 카카오로 시작하기</span>
                	</div>
                </button>
              </div>

              <div class="d-grid">
                <button class="btn btn-naver btn-loginType fw-bold" >
                	<div class="logo-len">
                		<img class="btn-logo-size" src="../resources/image/naver.png">
                		<span> 네이버로 시작하기</span>
                	</div>
                </button>
              </div>

           
          </div>
        </div>
      </div>
    </div>
  </div>

</body>
</html>
