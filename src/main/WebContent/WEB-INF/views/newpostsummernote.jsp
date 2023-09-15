<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<title>Insert title here</title>

<!-- include bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>

<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script>
        $(document).ready(function () {
            //여기 아래 부분
            $('#summernote').summernote({
                height: 300,                 // 에디터 높이
                minHeight: null,             // 최소 높이
                maxHeight: null,             // 최대 높이
                focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
                lang: "ko-KR",					// 한글 설정
                placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
                toolbar: [
                    // [groupName, [list of button]]
                    ['fontname', ['fontname']],
                    ['fontsize', ['fontsize']],
                    ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
                    ['color', ['forecolor', 'color']],
                    ['table', ['table']],
                    ['para', ['ul', 'ol', 'paragraph']],
                    ['height', ['height']],
                    ['insert', ['picture', 'link', 'video']],
                    ['view', ['fullscreen', 'help']]
                ],
                fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체'],
                fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
                callbacks : {                                                    
					onImageUpload : function(files, editor, welEditable) {
						
						for (var i = 0; i < files.length; i++){
							sendFile(files[i], this);
						}
						
						console.log('onImageUpload');

						
					}
				}
            });
        });
        
        var header = $("meta[name='_csrf_header']").attr('content');
        var token = $("meta[name='_csrf']").attr('content');
        
        function sendFile(file, el) {
        	var form_data = new FormData();
        	form_data.append('file', file);
        	console.log(form_data);
        	$.ajax({
        		data : form_data,
        		type : "POST",
        		url : '/api/image/image',
        		cache : false,
        		contentType : false,
        		enctype : 'multipart/form-data',
        		processData : false,
        		/*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
        		beforeSend : function(xhr){
        			xhr.setRequestHeader(header, token)
        			},
        		success : function(url) {
        					console.log(url);
        					$(el).summernote('insertImage',url);
        				}
        			})
        		}

        
    </script>

<style>
#category {
	width: auto;
}
</style>

</head>

<body>

	<div class="header">
		<h1>HEADDER</h1>
	</div>

	<div class="content">
		<div class="newpost">
			<form method="post" action="/api/post/inspost">
			<input type="hidden" id="token" name="${_csrf.parameterName}" value="${_csrf.token}">
				<div class="title">
					<div class="inputitle">

						<select class="form-select" aria-label="카테고리" id="category" name="category_id">
							<option value="0" selected>카테고리</option>
							<option value="1">동네소식</option>
							<option value="2">동네질문</option>
							<option value="3">동네맛집</option>
							<option value="4">일   상</option>
							<option value="5">생활정보</option>
							<option value="6">취미생활</option>
							<option value="7">실시간 날씨</option>
							<option value="8">분실/실종센터</option>
						</select> <input type="text" placeholder="제목을 입력하세요" id="posttitle" name="posttitle" />
					</div>
				</div>

				<!-- form 안에 에디터를 사용하는 경우 (보통 이경우를 많이 사용하는듯)-->
				<textarea id="summernote" name="editordata"></textarea>
				<input type="submit" value="등록" />
				<input type="reset" value="취소" />
			</form>
			
			<!-- div에 에디터를 사용하는 경우 -->
			<!--  <div id="summernote" class="summernote"></div> -->

		</div>
	</div>
</body>
</html>