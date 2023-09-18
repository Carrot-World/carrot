$(window).scroll(function () {
    $('.header').css('left', 0 - $(this).scrollLeft());
});

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
        if(cardImg[i]) {
        	cardImg[i].style.height = `${cardWidth}px`;
       	}
    }
}

rescaleCard();

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

window.addEventListener("resize", throttle(rescaleCard, 100))

function changeLoc1Select() {
    var loc1Select = document.getElementById("loc1");
    var loc1SelectValue = loc1Select.options[loc1Select.selectedIndex].value;

    $.ajax({
        url: "/api/loc/get2",
        data: {
            "loc1": loc1SelectValue
        },
        method: "get",
        dataType: "json"
    }).done((data) => {
        console.log(data);
        var selectLoc2El = document.querySelector("#loc2");
        var selectLoc3El = document.querySelector("#loc3");
        selectLoc2El.innerHTML = "<option></option>";
        selectLoc3El.innerHTML = "<option></option>";
        data.forEach((loc) => {
            selectLoc2El.innerHTML += optionEl(loc);
        })
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
            "loc1": loc1SelectValue,
            "loc2": loc2SelectValue
        },
        method: "get",
        dataType: "json"
    }).done((data) => {
        console.log(data);
        var selectEl = document.querySelector("#loc3");
        selectEl.innerHTML = "<option></option>";
        data.forEach((loc) => {
            selectEl.innerHTML += optionEl(loc);
        })
    });
}

function optionEl(loc) {
    return '<option value="' + loc + '">' + loc + '</option>'
}

function searchBtnHandler() {
    var categorySelect = document.getElementById("categorySelect");
    var categorySelectId = categorySelect.options[categorySelect.selectedIndex].value;
    var loc1Select = document.getElementById("loc1");
    var loc1SelectValue = loc1Select.options[loc1Select.selectedIndex].value;
    var loc2Select = document.getElementById("loc2");
    var loc2SelectValue = loc2Select.options[loc2Select.selectedIndex].value;
    var loc3Select = document.getElementById("loc3");
    var loc3SelectValue = loc3Select.options[loc3Select.selectedIndex].value;
    var title = document.getElementById("title").value;

    var search = new URLSearchParams();

    if (loc1SelectValue === "") {
        $("#loc2").find("option:eq(0)").prop("selected", true);
        $("#loc3").find("option:eq(0)").prop("selected", true);

        search.append("pageNo", 1);
        search.append("title", title);
        search.append("category_id", categorySelectId);
        location.search = search.toString();

    } else if (loc2SelectValue === "") {
        $("#loc3").find("option:eq(0)").prop("selected", true);

        search.append("pageNo", 1);
        search.append("title", title);
        search.append("category_id", categorySelectId);
        search.append("loc1", loc1SelectValue);
        location.search = search.toString();

    } else if (loc3SelectValue === "") {

        search.append("pageNo", 1);
        search.append("title", title);
        search.append("category_id", categorySelectId);
        search.append("loc1", loc1SelectValue);
        search.append("loc2", loc2SelectValue);
        location.search = search.toString();

    } else {

        search.append("pageNo", 1);
        search.append("title", title);
        search.append("category_id", categorySelectId);
        search.append("loc1", loc1SelectValue);
        search.append("loc2", loc2SelectValue);
        search.append("loc3", loc3SelectValue);
        location.search = search.toString();
    }
}