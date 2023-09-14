<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 결과출력</title>
</head>
<body>

<script> 
var msg = "${msg}";
if(msg === "success") {
    alert("회원가입 성공했습니다.");
    location.href="/page/login"
}
if(msg === "fail") {
    alert("회원가입 실패했습니다.");
    location.href="/page/signup;"
}
location.href="/page/login"
</script>
</body>
</html>