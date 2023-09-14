var imgCnt = 0;
var uploadFiles = [];

function uploadHandler() {
    const title = document.querySelector("input#title").value;
    const price = document.querySelector("input#price").value;
    const addr1 = document.querySelector("select#loc1").value;
    const addr2 = document.querySelector("select#loc2").value;
    const addr3 = document.querySelector("select#loc3").value;
    const categoryEl = document.querySelector("select#category_id");
    const categoryId = categoryEl.options[categoryEl.selectedIndex].value;
    const categoryName = categoryEl.options[categoryEl.selectedIndex].textContent;
    const content = document.querySelector("textarea#content").value;
    const token = document.querySelector("input#token");

    const formData = new FormData();
    const files = document.querySelector("input[type=file]").files;
    for (const file of uploadFiles) {
        formData.append("images", file);
    }
    formData.append("title", title);
    formData.append("price", price);
    formData.append("loc1", addr1);
    formData.append("loc2", addr2);
    formData.append("loc3", addr3);
    formData.append("category_id", categoryId);
    formData.append("category_name", categoryName);
    formData.append("content", content);
    formData.append(token.getAttribute("name"), token.value);


    for (let key of formData.keys()) {
        console.log(key);
    }

    for (let value of formData.values()) {
        console.log(value);
    }

    const insertBtnEl = $("#insertBtn");
    const insertBtnName = insertBtnEl.attr("name");
    console.log("버튼이름: " + insertBtnName);
    if (insertBtnName === "insert") {
        $.ajax({
            url: "/api/item/insert",
            data: formData,
            enctype: "multipart/form-data",
            processData: false,
            contentType: false,
            method: "post",
            success: function () {
                if (window.confirm("등록 되었습니다.")) {
                    window.location = '/page/listItem';
                } else {
                    window.location = '/page/listItem';
                }
            },
            error: function () {
                alert("애러 발생 ㅜㅜ");
            }
        });
    } else if (insertBtnName === "update") {
        const id = insertBtnEl.attr("value");
        formData.append("id", id);
        $.ajax({
            url: "/api/item/update",
            data: formData,
            enctype: "multipart/form-data",
            processData: false,
            contentType: false,
            method: "post",
            dataType: "text"
        }).done((result) => {
            window.location = result;
        })
    }

}

function getImageFiles(e) {
    const files = e.currentTarget.files;
    const imagePreview = document.querySelector('.upload');
    console.log(typeof files, files);

    if ([...files].length >= 6) {
        alert('이미지는 최대 5개 까지 업로드가 가능합니다.');
        return;
    }

    [...files].forEach(file => {
        if (!file.type.match("image/.*")) {
            alert('이미지 파일만 업로드가 가능합니다.');
            return;
        }

        // 파일 갯수 검사
        if (imgCnt < 5) {
            uploadFiles.push(file);
            const reader = new FileReader();
            reader.onload = (e) => {
                const preview = createElement(e, file);
                imagePreview.appendChild(preview);
            };
            reader.readAsDataURL(file);
            imgCnt++;
        } else {
            alert('이미지는 최대 5개 까지 업로드가 가능합니다.');
        }
    });
}

function createElement(e, file) {
    const img = document.createElement('img');
    img.setAttribute('src', e.target.result);
    img.setAttribute('data-file', file.name);
    img.setAttribute('class', 'img-thumbnail');
    return img;
}

const realUpload = document.querySelector('.real-upload');
const btnUpload = document.querySelector('#btnImgUpload');

btnUpload.addEventListener('click', () => realUpload.click());
realUpload.addEventListener('change', getImageFiles);

//주소값 받아오기
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