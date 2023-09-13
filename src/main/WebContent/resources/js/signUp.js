$("#btn-idCheck").click(function() {
//    e.preventDefault(); 
    alert("Button 아이디체크 Clicked!");
});

$("#btn-cancel").click(function (e) {
	e.preventDefault(); 
	alert("Button 아이디 취소버튼 Clicked!");
	
});


$(".btn-cancel").click(function (e) {
	e.preventDefault(); 
	alert("Button 클래스 취소버튼 Clicked!");
	
});
    //주소값 받아오기
    function changeLoc1Select() {
    		console.log("change");
        var loc1Select = document.getElementById("loc1");
        var loc1SelectValue = loc1Select.options[loc1Select.selectedIndex].value;

        $.ajax({
            url: "/api/loc/get2",
            data: {
                "loc1" : loc1SelectValue},
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
                "loc1" : loc1SelectValue,
                "loc2" : loc2SelectValue},
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
        return '<option value="'+ loc + '">' + loc + '</option>'
    }