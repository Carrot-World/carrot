<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
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
    <link href="${pageContext.request.contextPath}/resources/css/listItem.css?after" rel="stylesheet">
    <script defer src="${pageContext.request.contextPath}/resources/js/listItem.js"></script>
</head>
<body>
<div id="content">
    <c:if test="${user != null}">
        <h3>${user.nickname}님 동네 물건들</h3>
    </c:if>
    <div class="search">
        <label for="searchCategory" id="categoryLabel" class="col-form-label">카테고리</label>
        <div id="searchCategory">
            <select id="categorySelect" name="categorySelect" class="form-select form-select-sm"
                    aria-label="Small select example">
                <option selected value="0"></option>
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
        <label for="searchLocation" id="searchLabel" class="col-form-label">위치</label>
        <div id="searchLocation">
            <select id="loc1" name="loc1" onchange="changeLoc1Select()" class="form-select form-select-sm"
                    aria-label="Small select example">
                <option></option>
                <c:if test="${user != null}">
                    <c:forEach items="${loc1List}" var="location1">
                        <option ${location1 == user.loc1 ? 'selected' : ''} value="${location1}">${location1}</option>
                    </c:forEach>
                </c:if>
                <c:if test="${user == null}">
                    <c:forEach items="${loc1List}" var="location1">
                        <option value="${location1}">${location1}</option>
                    </c:forEach>
                </c:if>
            </select>
            <select id="loc2" name="loc2" onchange="changeLoc2Select()" class="form-select form-select-sm"
                    aria-label="Small select example">
                <option></option>
                <c:if test="${user != null}">
                    <c:forEach items="${loc2List}" var="location2">
                        <option ${location2 == user.loc2 ? 'selected' : ''} value="${location2}">${location2}</option>
                    </c:forEach>
                </c:if>
            </select>
            <select id="loc3" name="loc3" class="form-select form-select-sm" aria-label="Small select example">
                <option></option>
                <c:if test="${user != null}">
                    <c:forEach items="${loc3List}" var="location3">
                        <option ${location3 == user.loc3 ? 'selected' : ''} value="${location3}">${location3}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <button id="searchBtn" onclick="searchBtnHandler()" type="button" class="btn btn-primary btn-sm">검색</button>
    </div>
    <div id="component">
        <c:if test="${list == null}">
            <h3> 동네에 등록된 물건이 없네용 ㅜ</h3>
        </c:if>
        <c:if test="${list != null}">
            <div class="itemList">
                <c:forEach items="${list}" var="item">
                    <div class="card" onclick="location.href='/page/detail?id=${item.id}'">
                        <c:if test="${item.imageList == null}">
                            <img src="${pageContext.request.contextPath}/resources/image/noImage.png"
                                 class="card-img-top"
                                 alt="...">
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
                            <p class="count">찜 ${item.hart_cnt} ∙ 채팅 ${item.chat_cnt}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="footer">
                <nav aria-label="...">
                    <ul class="pagination pagination-sm justify-content-center">
                        <c:if test="${page.start != 1}">
                            <li class="page-item">
                                <a class="page-link"
                                   onclick="pageMove('/api/item/search?category_id=0&loc1=${user.loc1}&loc2=${user.loc2}&loc3=${user.loc3}&pageNo=${page.start - 1}')">
                                    &laquo;</a>
                            </li>
                        </c:if>
                        <c:forEach var="i" begin="${page.start}" end="${page.end}">
                            <li class="page-item ${i == page.current ? 'active' : ''}">
                                <a class="page-link"
                                   onclick="pageMove('/api/item/search?category_id=0&loc1=${user.loc1}&loc2=${user.loc2}&loc3=${user.loc3}&pageNo=${i}')">
                                        ${i}
                                </a>
                            </li>
                        </c:forEach>
                        <c:if test="${page.end != page.total}">
                            <li class="page-item">
                                <a class="page-link"
                                   onclick="pageMove('/api/item/search?category_id=0&loc1=${user.loc1}&loc2=${user.loc2}&loc3=${user.loc3}&pageNo=${page.end + 1}')">
                                    &raquo;</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
