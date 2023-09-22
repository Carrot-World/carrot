/* myTradePage */

$(window).scroll(function () {
    $('.header').css('left', 0 - $(this).scrollLeft());
});

var header = $("meta[name='_csrf_header']").attr('content');
var token = $("meta[name='_csrf']").attr('content');

// 쓰로틀
// 물품목록 4개씩 고정하기 위해
function rescaleCard() {
    const wrapperWidth = document.querySelector("div.items").offsetWidth;
    const offset = 30;
    const cardWidth = Math.ceil(wrapperWidth * 270 / 1336);

    const cards = document.querySelectorAll("div.card");
    const cardImg = document.querySelectorAll("img.card-img-top");
    for (let i = 0; i < cards.length; i++) {
        cards[i].style.width = `${cardWidth}px`;
        cardImg[i].style.height = `${cardWidth}px`;
    }
}

function throttle(fn, delay) {
    let waiting = false;
    return () => {
        if (!waiting) {
            fn.apply(this);
            waiting = true;
            setTimeout(() => {
                waiting = false;
            }, delay);
        }
    }
}

window.addEventListener("resize", throttle(rescaleCard, 100));

const updateModal = new bootstrap.Modal("#updateModal");
const passwordModal = new bootstrap.Modal("#passwordModal");

function passwordModalOpen() {
    updateModal.hide();
    passwordModal.show();
}

function updateModalOpen() {
    passwordModal.hide();
    updateModal.show();
}





function updateInfo(e) {
    /* var target = document.getElementById('btnUdpateInfo');
    const e = document.querySelector('#userid').value;

    target.addEventListener('click', function(e){ */
    const nickname = document.querySelector("#newnickname").value;
    const email = document.querySelector("#newemail").value;
    const loc1 = document.querySelector("#loc1").value;
    const loc2 = document.querySelector("#loc2").value;
    const loc3 = document.querySelector("#loc3").value;

    let data = {
        'id': e,
        'nickname': nickname,
        'email': email,
        'loc1': loc1,
        'loc2': loc2,
        'loc3': loc3
    };

    $.ajax({
        url: '/api/myPage/updateinfo',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(data),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: (result) => {
            console.log(result);
            location.reload();
        },
        error: () => {
            alert('수정 실패')
            location.reload();
        }
    })

}
/* ) */

function updatePassword(e) {

  const password = document.querySelector("#password").value.replaceAll('<', '&lt;').replaceAll('>', '&gt;');;
  const newpassword1 = document.querySelector("#newpassword1").value.replaceAll('<', '&lt;').replaceAll('>', '&gt;');;
  const newpassword = document.querySelector("#newpassword").value.replaceAll('<', '&lt;').replaceAll('>', '&gt;');;
 
  var passwordTest =
   /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{6,12}$/;

  
	if (!passwordTest.test(newpassword1)) {
	alert(
	  "비밀번호는 영어, 숫자, 특수문자 1개 이상씩 사용하여 6~12자로 적어주세요."
	);
	return false;
  }
  
  if (newpassword1 !== newpassword) {
    alert("비밀번호 확인을 해주세요.");
    return false;
  }

    let data = {
        'id': e,
        'password': password,
        'newpassword': newpassword
    };

    $.ajax({
        url: '/api/myPage/updatepassword',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(data),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: (result) => {
            console.log(result);
            logout();
        },
        error: () => {
            alert('변경 실패 비밀번호를 다시 확인해 주세요')
            location.reload();
        }
    })

}


function btnWithdraw(e) {

    let userid = { 'id': e };

    $.ajax({
        url: '/api/myPage/withdraw',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(userid),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        },
        success: (result) => {
            console.log(result);
            logout();
        },
        error: () => {
            alert('탈퇴 실패')
            location.reload();
        }

    })
}

function logout() {

    $.ajax({
        url: '/logout',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token)
        }, success: (result) => {
            console.log("logout");
            console.log(result);
            location.reload();
        }
    })
}

/* function changeLoc1Select() {
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
      var selectEl = document.querySelector("#loc3");
      selectEl.innerHTML = "";
      data.forEach((loc) => {
        selectEl.innerHTML += optionEl(loc);
      });
    });
  }
  
  function optionEl(loc) {
    return '<option value="' + loc + '">' + loc + "</option>";
  } */

function changeLoc1Select() {
    var loc1Select = document.getElementById("loc1");
    var loc1SelectValue = loc1Select.options[loc1Select.selectedIndex].value;
    var loc2El = $("#loc2");
    var loc3El = $("#loc3");

    if (loc1SelectValue === "도시 선택") {
        loc2El.find("option:eq(0)").prop("selected", true);
        loc3El.find("option:eq(0)").prop("selected", true);

        loc2El.prop("disabled", true);
        loc3El.prop("disabled", true);
    } else {
        $.ajax({
            url: "/api/loc/get2",
            data: {
                "loc1": loc1SelectValue
            },
            method: "get",
            dataType: "json"
        }).done((data) => {
            var selectLoc2El = document.querySelector("#loc2");
            var selectLoc3El = document.querySelector("#loc3");
            selectLoc2El.innerHTML = `<option value="지역 선택">지역 선택</option>`;
            selectLoc3El.innerHTML = `<option value="동네 선택">동네 선택</option>`;
            loc2El.prop("disabled", false);
            loc3El.prop("disabled", true);
            data.forEach((loc) => {
                selectLoc2El.innerHTML += optionEl(loc);
            })
        });
    }
}

function changeLoc2Select() {
    var loc1Select = document.getElementById("loc1");
    var loc1SelectValue = loc1Select.options[loc1Select.selectedIndex].value;
    var loc2Select = document.getElementById("loc2");
    var loc2SelectValue = loc2Select.options[loc2Select.selectedIndex].value;
    var loc3El = $("#loc3");

    if (loc1SelectValue === "도시 선택" || loc2SelectValue === "지역 선택") {
        loc3El.find("option:eq(0)").prop("selected", true);

        loc3El.prop("disabled", true);
    } else {
        $.ajax({
            url: "/api/loc/get3",
            data: {
                "loc1": loc1SelectValue,
                "loc2": loc2SelectValue
            },
            method: "get",
            dataType: "json"
        }).done((data) => {
            var selectEl = document.querySelector("#loc3");
            selectEl.innerHTML = `<option value="동네 선택">동네 선택</option>`;
            loc3El.prop("disabled", false);
            data.forEach((loc) => {
                selectEl.innerHTML += optionEl(loc);
            })
        });
    }
}

function optionEl(loc) {
    return '<option value="' + loc + '">' + loc + "</option>";
}