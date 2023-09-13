<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <!-- 부트스트랩 -->
<!--     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
 -->    <!-- 부트스트랩 -->
    <link href="../resources/css/join.css?after" rel="stylesheet">
    <!-- 사용자 css -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>   
    <script src="../resources/js/signUp.js" ></script>
    
<title>회원가입 페이지</title>
</head>
<body>
 <div class="container">
 	<form  action="/api/signUp" method="post" accept-charset="UTF-8">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
       <div class="row">
        <h4 class="mb-3">회원가입</h4>
          <div class="col-md-8 mb-3">
            <label for="id">아이디</label>
            <input type="text" class="form-control" id="id" name="id" placeholder="" value="" required>
            <div class="invalid-feedback">
              아이디를 입력해주세요.
            </div>
          </div>
 	          <div class="col-md-4 mb-8">
          		<input type="button" id="btn-idCheck" class="btn btn-primary btn-lg btn-block" value="중복체크"/>
	          </div>
         </div>
	          <label for="nickname">닉네임</label>
	          <div class="col-md-8 mb-3">
	            <input type="text" class="form-control" id="nickname" name="nickname" placeholder="" value="" required>
	            <div class="invalid-feedback">
	              닉네임을 입력해주세요.
	            </div>
	          </div>

          <div class="row">
	         	<div class="col-md-6 mb-3">
	            <label for="password">비밀번호 입력</label>
	            <input type="text" class="form-control" id="password" name="password" placeholder="" value="" required>
	            <div class="invalid-feedback">
	              비밀번호를 입력해주세요.
	            </div>
	          </div>
	          <div class="col-md-6 mb-3">
	            <label for="passwordChk">비밀번호 확인</label>
	            <input type="text" class="form-control" id="passwordChk" name="passwordChk" placeholder="" value="" required>
	            <div class="invalid-feedback">
	              비밀번호를 재입력해주세요
	            </div>
	          </div>
          </div>

          <div class="mb-3">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="id@example.com" required>
            <div class="invalid-feedback">
              이메일을 입력해주세요.
            </div>
          </div>

					<div class="row">
            <label for="location">위치</label>
	          <div class="col-md-3 mb-3">
    	        <select id="loc1" class="form-select" name="loc1" onchange="changeLoc1Select()">
    	        	<option selected disabled>도, 시</option>
                <option value="강원특별자치도">강원특별자치도</option>
                <option value="경기도">경기도</option>
                <option value="경상남도">경상남도</option>
                <option value="경상북도">경상북도</option>
                <option value="광주광역시">광주광역시</option>
                <option value="대구광역시">대구광역시</option>
                <option value="대전광역시">대전광역시</option>
                <option value="부산광역시">부산광역시</option>
                <option value="서울특별시">서울특별시</option>
                <option value="세종특별자치시">세종특별자치시</option>
                <option value="울산광역시">울산광역시</option>
                <option value="인천광역시">인천광역시</option>
                <option value="전라남도">전라남도</option>
                <option value="전라북도">전라북도</option>
                <option value="제주특별자치도">제주특별자치도</option>
                <option value="충청남도">충청남도</option>
                <option value="충청북도">충청북도</option>
            	</select>
 	            <div class="invalid-feedback">
	              선택해주세요.
	            </div>
		        </div>
		        <div class="col-md-3 mb-3">
     	        <select id="loc2" class="form-select" name="loc2" onchange="changeLoc2Select()" >
    	        	<option selected disabled>구, 시</option>

            	</select>
 	            <div class="invalid-feedback">
	              선택해주세요.
	            </div>
            </div>
            <div class="col-md-3 mb-3">
     	        <select id="loc3" class="form-select" name="loc3">
    	        	<option selected disabled>동, 면, 읍</option>

            	</select>
	            <div class="invalid-feedback">
	              선택해주세요.
	            </div>
            </div>
          </div>
					<input type="hidden" id="token" name="${_csrf.parameterName}" value="${_csrf.token}">
          <hr class="mb-4">
          <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="aggrement" required>
            <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
          </div>
          <div class="mb-4"></div>
          <button class="btn btn-primary btn-lg btn-block" type="submit">가입</button>
          <input type="button" id="btn-cancel" class="btn btn-warning btn-lg btn-block" value="취소" />
      </div>
    </div>
   </form>
    <footer class="my-3 text-center text-small">
      <p class="mb-1">&copy; (주) 공윤배</p>
    </footer>
  </div>
  
  
  
<script>
let form2;
  window.addEventListener('load', () => {
    const forms = document.getElementsByClassName('validation-form');

    Array.prototype.filter.call(forms, (form) => {
    	form2 = form;
      form.addEventListener('submit', function (event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }

        form.classList.add('was-validated');
      }, false);
    });
  }, false);
</script>
	
</body>
</html>