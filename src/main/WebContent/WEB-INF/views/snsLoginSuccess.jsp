<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>snsLoginSuccess</title>
<script>
console.log('${req_locRegist}');
if('${req_locRegist}' == "loc_isNull"){
		alert("지역 등록되어있지 않슴다 등록해주세요, 임시로그인석세스거쳐서 인서트아이템으로");
		window.location.href = "/page/insertItem";
}
window.location.href = "/page/listItem"; 
</script>
</head>
<body>

</body>
</html>