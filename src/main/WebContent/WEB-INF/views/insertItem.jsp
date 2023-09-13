<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <input type="text" class="form-control" id="title" name="title">
        </div>
    </div>

    <div class="row mb-3">
        <label for="price" class="col-sm-2 col-form-label">가격</label>
        <div class="col-sm-10">
            <input type="number" class="form-control" id="price" name="price">
        </div>
    </div>

    <div class="row mb-3">
        <label for="location" class="col-sm-2 col-form-label">위치</label>
        <div class="col-sm-10" id="location">
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
                <option selected>구, 시</option>
            </select>
            <select id="loc3" name="loc3" class="form-select form-select-sm" aria-label="Small select example">
                <option selected>동, 면, 읍</option>
            </select>
        </div>
    </div>

    <div class="row mb-3">
        <label for="categorySelect" class="col-sm-2 col-form-label">카테고리</label>
        <div class="col-sm-10" id="categorySelect">
            <select class="form-select form-select-sm" aria-label="Small select example" id="category_id" name="category_id">
                <option selected>카테고리</option>
                <option value="1">디지털기기</option>
                <option value="2">가구/인테리어</option>
                <option value="3">유아동</option>
                <option value="4">여성의류</option>
                <option value="5">남성패션/잡화</option>
                <option value="6">스포츠/레저</option>
                <option value="7">도서</option>
                <option value="8">가공식품</option>
                <option value="9">식물</option>
                <option value="10">삽니다</option>
                <option value="11">생활가전</option>
                <option value="12">생활/주방</option>
                <option value="13">유아도서</option>
                <option value="14">여성잡화</option>
                <option value="15">뷰티/미용</option>
                <option value="16">취미/게임/음반</option>
                <option value="17">티켓/교환권</option>
                <option value="18">반려동물용품</option>
                <option value="19">기타 중고물품</option>
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
        <textarea class="form-control" id="content" name="content" rows="10"></textarea>
    </div>

    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button type="button" class="btn btn-primary" onclick="uploadHandler()">물품 등록</button>
    </div>
</div>
</body>
</html>