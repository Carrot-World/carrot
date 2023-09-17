<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <!-- 부트스트랩 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <!-- 부트스트랩 -->
    <link href="../resources/css/findId.css?after" rel="stylesheet">
    <!-- 사용자 css -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>   
    <!-- <script defer src="../resources/js/signUp.js" ></script> -->

<title>아이디 찾기</title>
</head>
 <body class="text-center">
    
    <!--  html 전체 영역을 지정하는 container -->
    <div id="container">
      <!--  login 폼 영역을 : loginBox -->
      <div id="loginBox">
      
        <!-- 로그인 페이지 타이틀 -->
        <div id="loginBoxTitle">CodeZone Login</div>
        <!-- 아이디, 비번, 버튼 박스 -->
        <div id="inputBox">
          <div class="input-form-box"><span>아이디 </span><input type="text" name="uid" class="form-control"></div>
          <div class="input-form-box"><span>비밀번호 </span><input type="password" name="upw" class="form-control"></div>
          <div class="button-login-box" >
            <button type="button" class="btn btn-primary btn-xs" style="width:100%">로그인</button>
          </div>
        </div>
        
      </div>
    </div>
</body>
</html>