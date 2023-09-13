<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
            crossorigin="anonymous"/>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous">
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>
        .card {
            width: 200px;
        }


        img {
            width: 198px;
            height: 198px;
        }

        #content {
            width: 1000px;
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 0 auto;
        }

        .search {
            width: 1000px;
            display: flex;
            flex-direction: row;
            justify-content: flex-end;
            margin-bottom: 30px;
        }
        #searchLabel {
            margin-right: 30px;
        }

        #searchLocation {
            width: 600px;
            display: flex;
            flex-direction: row;
            justify-content: space-evenly;
        }

        #searchLocation > select {
            width: 27%;
        }

        .card-title {
            font-size: 19px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        p {
            margin-bottom: 1px;
        }

        .price {
            font-weight: bold;
        }

        .location {
            font-size: 13px;
        }

        .count {
            font-size: 12px;
            font-weight: 300;
        }

        .itemList {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-evenly;
            gap: 30px;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
<div id="content">
    <div class="search">
        <label for="searchLocation" id="searchLabel" class="col-form-label">위치</label>
        <div id="searchLocation">
            <select id="loc1" name="loc1" onchange="changeLoc1Select()" class="form-select form-select-sm" aria-label="Small select example">
                <option selected disabled>도, 시</option>
                <option value="강원특별자치도">강원특별자치도</option>
                <option value="경기도">경기도</option>
                <option value="경상남도">경상남도</option>
                <option value="경상북도">경상북도</option>
                <option value="광주광역시">광주광역시</option>
                <option value="대구광역시">대구광역시</option>
                <option value="대전광역시">대전광역시</option>
                <option value="부산광역시">부산광역시</option>
                <option value="서울특별시">서울특별시</option>
                <option value="세종특별자치시">세종특별자치시</option>
                <option value="울산광역시">울산광역시</option>
                <option value="인천광역시">인천광역시</option>
                <option value="전라남도">전라남도</option>
                <option value="전라북도">전라북도</option>
                <option value="제주특별자치도">제주특별자치도</option>
                <option value="충청남도">충청남도</option>
                <option value="충청북도">충청북도</option>
            </select>
            <select id="loc2" name="loc2" onchange="changeLoc2Select()" class="form-select form-select-sm" aria-label="Small select example">
                <option selected disabled>구, 시</option>
            </select>
            <select id="loc3" name="loc3" class="form-select form-select-sm" aria-label="Small select example">
                <option selected>동, 면, 읍</option>
            </select>
        </div>
    </div>
    <div class="itemList">
        <c:forEach items="${list}" var="item">
            <div class="card" onclick="location.href='/page/detail?id=${item.id}'">
                <c:if test="${item.imageList == null}">
                    <img src="../../resources/image/noImage.png" class="card-img-top" alt="...">
                </c:if>
                <c:if test="${item.imageList != null}">
                    <c:forEach items="${item.imageList}" var="image" end="0">
                        <img src="${image.url}" class="card-img-top" alt="...">
                    </c:forEach>
                </c:if>
                <div class="card-body">
                    <h5 class="card-title">${item.title}</h5>
                    <p class="price"><fmt:formatNumber value="${item.price}" pattern="#,###"/>원</p>
                    <p class="location">${item.loc1} ${item.loc2} ${item.loc3}</p>
                    <p class="count">찜 5 ∙ 채팅 ${item.chat_cnt}</p>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="footer">
        <nav aria-label="...">
            <ul class="pagination pagination-sm justify-content-center">
                <li class="page-item disabled">
                    <a class="page-link">&laquo;</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item active" aria-current="page">
                    <a class="page-link" href="#">2</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">&raquo;</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<script>
    function changeLoc1Select() {
        var loc1Select = document.getElementById("loc1");
        var loc1SelectValue = loc1Select.options[loc1Select.selectedIndex].value;

        $.ajax({
            url: "/api/loc/get2",
            data: {
                "loc1" : loc1SelectValue},
            method: "post",
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
            method: "post",
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
</script>
</body>
</html>
