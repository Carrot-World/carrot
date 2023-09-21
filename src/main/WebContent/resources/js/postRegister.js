/* postRegister */

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
      
 

 function Checkform() { //제목 검사
   let title = postform.title.value;
   postform.title.value = title.replaceAll('<', '&lt;').replaceAll('>', '&gt;');
   console.log(postform)
   console.log(title)
   return true;
}
