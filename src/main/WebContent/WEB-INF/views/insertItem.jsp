<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>물품 등록 페이지</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous"
    >
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/insertItem.css?after" rel="stylesheet">
    <script defer src="${pageContext.request.contextPath}/resources/js/insertItem.js"></script>
</head>
<body>
<div id="mainImg">
</div>
<input type="file" class="real-upload hidden" accept="image/*" required multiple/>
<div class="upload">
</div>
<button type="button" class="btn btn-primary" id="btnImgUpload"
        style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">
    이미지 추가
</button>

<div id="insertForm">
    <div class="row mb-3">
        <label for="title" class="col-sm-2 col-form-label">제목</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="title" name="title" value="${item == null ? "" : item.title}">
        </div>
    </div>

    <div class="row mb-3">
        <label for="price" class="col-sm-2 col-form-label">가격</label>
        <div class="col-sm-10">
            <input type="number" class="form-control" id="price" name="price" value="${item == null ? "" : item.price}">
        </div>
    </div>

    <div class="row mb-3">
        <label for="location" class="col-sm-2 col-form-label">위치</label>
        <div class="col-sm-10" id="location">
            <select id="loc1" name="loc1" onchange="changeLoc1Select()" ${item == null ? "" : "disabled"} class="form-select form-select-sm" aria-label="Small select example">
                <option></option>
                <c:forEach items="${loc1List}" var="location1">
                    <option ${location1 == user.loc1 ? 'selected' : ''} value="${location1}">${location1}</option>
                </c:forEach>
            </select>
            <select id="loc2" name="loc2" onchange="changeLoc2Select()" ${item == null ? "" : "disabled"} class="form-select form-select-sm" aria-label="Small select example">
                <option></option>
                <c:forEach items="${loc2List}" var="location2">
                    <option ${location2 == user.loc2 ? 'selected' : ''} value="${location2}">${location2}</option>
                </c:forEach>
            </select>
            <select id="loc3" name="loc3" class="form-select form-select-sm" ${item == null ? "" : "disabled"} aria-label="Small select example">
                <option></option>
                <c:forEach items="${loc3List}" var="location3">
                    <option ${location3 == user.loc3 ? 'selected' : ''} value="${location3}">${location3}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="row mb-3">
        <label for="categorySelect" class="col-sm-2 col-form-label">카테고리</label>
        <div class="col-sm-10" id="categorySelect">
            <select class="form-select form-select-sm" aria-label="Small select example" id="category_id" name="category_id">
                <option ${item == null ? "selected" : ""}></option>
                <option value="1" ${item.category_id == 1 ? "selected" : ""}>디지털기기</option>
                <option value="2" ${item.category_id == 2 ? "selected" : ""}>가구/인테리어</option>
                <option value="3" ${item.category_id == 3 ? "selected" : ""}>유아동</option>
                <option value="4" ${item.category_id == 4 ? "selected" : ""}>여성의류</option>
                <option value="5" ${item.category_id == 5 ? "selected" : ""}>남성패션/잡화</option>
                <option value="6" ${item.category_id == 6 ? "selected" : ""}>스포츠/레저</option>
                <option value="7" ${item.category_id == 7 ? "selected" : ""}>도서</option>
                <option value="8" ${item.category_id == 8 ? "selected" : ""}>가공식품</option>
                <option value="9" ${item.category_id == 9 ? "selected" : ""}>식물</option>
                <option value="10" ${item.category_id == 10 ? "selected" : ""}>삽니다</option>
                <option value="11" ${item.category_id == 11 ? "selected" : ""}>생활가전</option>
                <option value="12" ${item.category_id == 12 ? "selected" : ""}>생활/주방</option>
                <option value="13" ${item.category_id == 13 ? "selected" : ""}>유아도서</option>
                <option value="14" ${item.category_id == 14 ? "selected" : ""}>여성잡화</option>
                <option value="15" ${item.category_id == 15 ? "selected" : ""}>뷰티/미용</option>
                <option value="16" ${item.category_id == 16 ? "selected" : ""}>취미/게임/음반</option>
                <option value="17" ${item.category_id == 17 ? "selected" : ""}>티켓/교환권</option>
                <option value="18" ${item.category_id == 18 ? "selected" : ""}>반려동물용품</option>
                <option value="19" ${item.category_id == 19 ? "selected" : ""}>기타 중고물품</option>
            </select>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-sm-10 offset-sm-2">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="free" name="free">
                <label class="form-check-label" for="free">
                    무료나눔
                </label>
            </div>
        </div>
    </div>

    <div class="row mb-3">
        <textarea class="form-control" id="content" name="content" rows="10">${item == null ? "" : item.content}</textarea>
    </div>

    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button type="button" class="btn btn-primary" id="insertBtn" value="${item == null ? "" : item.id}" name="${item == null ? "insert" : "update"}" onclick="uploadHandler()">물품 ${item == null ? "등록" : "수정"}</button>
    </div>
    <input type="hidden" id="token" name="${_csrf.parameterName}" value="${_csrf.token}">
</div>
</body>
</html>