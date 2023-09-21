$(".btn-email").click(function () {
  location.href = "/page/signup";
});

$("#idFind").click(function (e) {
  e.preventDefault();
  $("#idFinderModal").modal("show");
});

$("#idFinderModal_req").click(function (e) {
  var email = $("#idFinderModal_email").val();
  var tokenInput = $("#token");
  var data = { email: email };
  data[tokenInput.attr("name")] = tokenInput.val();

  if (email.search(/\s/) !== -1 || email.trim().length === 0) {
    alert("이메일에는 공백이 들어갈 수 없습니다.");
  } else {
    $.ajax({
      async: true,
      type: "POST",
      data: data,
      url: "/api/signup/emailcheck",
      dataType: "json",
      contentType: "application/x-www-form-urlencoded",
      success: function (cnt) {
        if (cnt < 1) {
          alert("해당 정보로 가입된 아이디가 없습니다.");
        } else {
          sendEmail();
        }
      },
      error: function (error) {
        alert("이메일을 재입력해주세요.");
      },
    });
  }
});

function sendEmail() {
  var email_auth_cd = "";
  var email = $("#idFinderModal_email").val();
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

  $("#idFinderModal_compl").click(function () {
    if ($("#idFinderModal_num").val() !== email_auth_cd) {
      $("#idFinderModal_msg").html("※ 인증번호가 일치하지 않습니다.");
    }
    if ($("#idFinderModal_num").val() === email_auth_cd) {
      $.ajax({
        async: true,
        type: "POST",
        data: data,
        url: "/api/finduser/id",
        // dataType: "json",
        contentType: "application/x-www-form-urlencoded",

        success: function (id, response) {
          alert("해당 아이디는" + id + "입니다");
        },
        error: function (jqXHR, textStatus, errorThrown) {
          alert("재실행 해주세요.");
        },
      });
    }
  });
}

$("#passwordFind").click(function (e) {
  $("#pwdFinderModal").modal("show");
});

$("#pwdFinderModal_req").click(function (e) {
  console.log("모달리퀘");
  var id = $("#pwdFinderModal_id").val();
  var email = $("#pwdFinderModal_email").val();
  console.log(id + " 아이디/이메일 " + email);
  var tokenInput = $("#token");
  var data = { id: id, email: email };
  data[tokenInput.attr("name")] = tokenInput.val();

  if (id.search(/\s/) !== -1 || id.trim().length === 0) {
    alert("이메일에는 공백이 들어갈 수 없습니다.");
    return false;
  }
  if (email.search(/\s/) !== -1 || email.trim().length === 0) {
    alert("이메일에는 공백이 들어갈 수 없습니다.");
    return false;
  }

  $.ajax({
    async: true,
    type: "POST",
    data,
    url: "/api/finduser/password",
    dataType: "json",
    contentType: "application/x-www-form-urlencoded",
    success: function (cnt) {
      if (cnt < 1) {
        alert("해당 정보로 가입된 아이디가 없습니다.");
      } else {
        sendEmail();
      }
    },
    error: function (error) {
      console.log(error);
      alert("다시 시작해주세요.");
    },
  });
});

function sendEmail() {
  var email_auth_cd = "";
  var id = $("#pwdFinderModal_id").val();
  var email = $("#pwdFinderModal_email").val();
  var tokenInput = $("#token");
  var data = { id: id, email: email };
  data[tokenInput.attr("name")] = tokenInput.val();

  $.ajax({
    type: "POST",
    url: "/api/sendemail",
    data: data,
    success: function (data) {
      alert("인증번호가 발송되었습니다.");
      email_auth_cd = data;
    },
    error: function (data) {
      alert("메일 발송에 실패했습니다.");
    },
  });

  $("#pwdFinderModal_compl").click(function () {
    if ($("#pwdFinderModal_num").val() !== email_auth_cd) {
      $("#pwdFinderModal_msg").html("※ 인증번호가 일치하지 않습니다.");
    }
    if ($("#pwdFinderModal_num").val() === email_auth_cd) {
      $("#pwdFinderModal").modal("hide");
      $("#pwdUpdateModal").modal("show");
    }
  }); // 이 부분에 누락된 세미콜론 추가

  $("#pwdUpdateModal_compl").click(function () {
    var id = $("#pwdFinderModal_id").val();
    var password = $("#pwdUpdateModal_pwd").val();
    var passwordChk = $("#pwdUpdateModal_pwdChk").val();
    var tokenInput = $("#token");
    var passwordTest =
      /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{6,12}$/;
    console.log("id: " + id, "password: " + password);
    var data2 = { id: id, password: password };
    data2[tokenInput.attr("name")] = tokenInput.val();
    console.log("데이터2:" + data2);
    if (!passwordTest.test(password)) {
      alert(
        "비밀번호는 영어, 숫자, 특수문자 1개 이상씩 사용하여 6~12자로 적어주세요."
      );
      return false;
    }

    if (password !== passwordChk) {
      alert("비밀번호를 확인해주세요");
    }
    $.ajax({
      async: true,
      type: "POST",
      data: data2,
      url: "/api/finduser/newpwd",
      dataType: "json",
      contentType: "application/x-www-form-urlencoded",
      success: function (cnt, response) {
        if (cnt > 0) {
          alert("비밀번호 변경에 성공하셨습니다.");
          $("#pwdUpdateModal").modal("hide");
        } else {
          alert("비밀번호 재설정에 실패하셨습니다.");
        }
      },
      error: function (jqXHR, textStatus, errorThrown) {
        alert("비밀번호 재설정에 실패했습니다.");
      },
    });
  }); // 이 부분에 누락된 세미콜론 추가
}
