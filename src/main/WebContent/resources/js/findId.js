$("#btn-emailCheck").click(function (e) {
  e.preventDefault();

  var email = $("#email").val();
  var tokenInput = $("#token");
  var data = { email: email };
  data[tokenInput.attr("name")] = tokenInput.val();

  if (email.search(/\s/) !== -1 || email.trim().length === 0) {
    alert("이메일에는 공백이 들어갈 수 없습니다.");
  } else {
    $.ajax({
      async: true,
      type: "POST",
      data,
      url: "/api/login/findid",
      dataType: "json",
      contentType: "application/x-www-form-urlencoded",
      success: function (cnt) {
        if (cnt > 0) {
          alert("해당 이메일 존재");
          $("#eamil").focus();
        } else {
          $("#modal-text-email").val(email);
          $("#res-authnum-text").val("");
          $("#emailCertifyModal").modal("show");
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
  var eamil_auth_compl = false;

  $("#res-authnum").click(function () {
    if ($("#res-authnum-text").val() !== email_auth_cd) {
      alert("인증번호가 일치하지 않습니다.");
      eamil_auth_compl = false;
    }
    if ($("#res-authnum-text").val() === email_auth_cd) {
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
    if (eamil_auth_compl === true) {
      alert("인증이 완료되었습니다.");
      $("#email").prop("readonly", true);
      $("#email").attr("style", "background-color:#80808021;");
      $("#emailCertifyModal").modal("hide");
      $("#emailStatus").val("1");
    }
    if (eamil_auth_compl === false) {
      alert("인증이 완료되지 않았습니다.");
    }
  });
}