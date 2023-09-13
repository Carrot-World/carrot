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
        var selectEl = document.querySelector("#loc2");
        selectEl.innerHTML = "";
        data.forEach((loc) => {
            selectEl.innerHTML += optionEl(loc);
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
        selectEl.innerHTML = "";
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
    var categorySelectValue = categorySelect.options[categorySelect.selectedIndex].textContent;
    var loc1Select = document.getElementById("loc1");
    var loc1SelectValue = loc1Select.options[loc1Select.selectedIndex].value;
    var loc2Select = document.getElementById("loc2");
    var loc2SelectValue = loc2Select.options[loc2Select.selectedIndex].value;
    var loc3Select = document.getElementById("loc3");
    var loc3SelectValue = loc3Select.options[loc3Select.selectedIndex].value;
    console.log(categorySelectValue);
    console.log(loc1SelectValue);
    console.log(loc2SelectValue);
    console.log(loc3SelectValue);

    $.ajax({
        url: "api/item/search.do",
        data: {
            "category_name" : categorySelectValue,
            "loc1" : loc1SelectValue,
            "loc2" : loc2SelectValue,
            "loc3" : loc3SelectValue
        },
        method: "get",
        dataType: "text"
    }).done((data) => {
        alert("통신 성공");
    })

}