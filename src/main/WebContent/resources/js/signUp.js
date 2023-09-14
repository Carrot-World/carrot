$("#btn-idCheck").click(function () {
  //    e.preventDefault();
  var username = $("#id").val();
  var tokenInput = $("#token");
  var data = { id: username };
  data[tokenInput.attr("name")] = tokenInput.val();

  console.log("ajax이전");
  //alert("Button 아이디체크 Clicked!");
  if (username.search(/\s/) != -1) {
    alert("아이디에는 공백이 들어갈 수 없습니다.");
  } else {
    if (username.trim().length != 0) {
      $.ajax({
        async: true,
        type: "POST",
        data,
        url: "/api/signup/idcheck",
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success: function (cnt) {
          if (cnt > 0) {
            alert("해당 아이디 존재");
            $("#submit").attr("disabled", "disabled");
            $("#id").focus();
          } else {
            alert("사용가능 아이디");
            $("#submit").removeAttr("disabled");
            $("#nickname").focus();
          }
        },
        error: function (error) {
          alert("아이디를 입력해주세요.");
        },
      });
    } else {
      alert("아이디를 입력해주세요.");
    }
  }
});

$("#btn-nicCheck").click(function (e) {
  e.preventDefault();
  //alert("Button 닉네임체크 Clicked!");
  var nickname = $("#nickname").val();
  var tokenInput = $("#token");
  var data = { nickname: nickname };
  data[tokenInput.attr("name")] = tokenInput.val();

  console.log("ajax이전");
  //alert("Button 아이디체크 Clicked!");
  if (nickname.search(/\s/) != -1) {
    alert("닉네임에는 공백이 들어갈 수 없습니다.");
  } else {
    if (nickname.trim().length != 0) {
      $.ajax({
        async: true,
        type: "POST",
        data,
        url: "/api/signup/niccheck",
        dataType: "json",
        contentType: "application/x-www-form-urlencoded",
        success: function (cnt) {
          if (cnt > 0) {
            alert("해당 닉네임 존재");
            $("#submit").attr("disabled", "disabled");
            $("#nickname").focus();
          } else {
            alert("사용가능 닉네임");
            $("#submit").removeAttr("disabled");
            $("#password").focus();
          }
        },
        error: function (error) {
          alert("닉네임을 입력해주세요.");
        },
      });
    } else {
      alert("닉네임을 입력해주세요.");
    }
  }
});

$("#btn-emailCheck").click(function (e) {
  e.preventDefault();
  alert("Button 이메일 인증버튼 Clicked!");
});

//DB 주소값 받아오기
function changeLoc1Select() {
  console.log("change");
  var loc1Select = document.getElementById("loc1");
  var loc1SelectValue = loc1Select.options[loc1Select.selectedIndex].value;

  $.ajax({
    url: "/api/loc/get2",
    data: {
      loc1: loc1SelectValue,
    },
    method: "get",
    dataType: "json",
  }).done((data) => {
    console.log(data);
    var selectEl = document.querySelector("#loc2");
    selectEl.innerHTML = "";
    data.forEach((loc) => {
      selectEl.innerHTML += optionEl(loc);
    });
  });
}
function changeLoc2Select() {
  var loc1Select = document.getElementById("loc1");
  var loc1SelectValue = loc1Select.options[loc1Select.selectedIndex].value;
  var loc2Select = document.getElementById("loc2");
  var loc2SelectValue = loc2Select.options[loc2Select.selectedIndex].value;

  $.ajax({
    url: "/api/loc/get3",
    data: {
      loc1: loc1SelectValue,
      loc2: loc2SelectValue,
    },
    method: "get",
    dataType: "json",
  }).done((data) => {
    console.log(data);
    var selectEl = document.querySelector("#loc3");
    selectEl.innerHTML = "";
    data.forEach((loc) => {
      selectEl.innerHTML += optionEl(loc);
    });
  });
}

function optionEl(loc) {
  return '<option value="' + loc + '">' + loc + "</option>";
}

$("#btn-myLoc").click(function (e) {
  e.preventDefault();
  alert("Button 내 위치 불러오기 버튼 Clicked!");
});

$("#btn-cancel").click(function (e) {
  e.preventDefault();
  alert("Button 아이디 취소버튼 Clicked!");
});
