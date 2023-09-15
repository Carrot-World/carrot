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

    if (loc1SelectValue === "") {
        $("#loc2").find("option:eq(0)").prop("selected", true);
        $("#loc3").find("option:eq(0)").prop("selected", true);

        $.ajax({
            url: "/api/item/search",
            data: {
                "category_id": categorySelectId
            },
            method: "get",
            dataType: "text"
        }).done((text) => {
            $("div#component").html(text);
        })
    } else if (loc2SelectValue === "") {
        $("#loc3").find("option:eq(0)").prop("selected", true);

        $.ajax({
            url: "/api/item/search",
            data: {
                "category_id": categorySelectId,
                "loc1": loc1SelectValue
            },
            method: "get",
            dataType: "text"
        }).done((text) => {
            $("div#component").html(text);
        })

    } else if (loc3SelectValue === "") {

        $.ajax({
            url: "/api/item/search",
            data: {
                "category_id": categorySelectId,
                "loc1": loc1SelectValue,
                "loc2": loc2SelectValue
            },
            method: "get",
            dataType: "text"
        }).done((text) => {
            $("div#component").html(text);
        })

    } else {
        $.ajax({
            url: "/api/item/search",
            data: {
                "category_id": categorySelectId,
                "loc1": loc1SelectValue,
                "loc2": loc2SelectValue,
                "loc3": loc3SelectValue
            },
            method: "get",
            dataType: "text"
        }).done((text) => {
            $("div#component").html(text);
        })
    }
}

function pageMove(url) {
    $.ajax({
        url,
        dataType: "text"
    }).done((text) => {
        $("div#component").html(text);
    })
}