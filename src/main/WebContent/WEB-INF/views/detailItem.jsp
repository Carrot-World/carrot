<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${item.writer} 님의 물품</title>
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
    <link href="${pageContext.request.contextPath}/resources/css/detailItem.css?after" rel="stylesheet">
    <script defer src="${pageContext.request.contextPath}/resources/js/detailItem.js"></script>
</head>
<body>
<div id="content">
    <div id="carouselExampleIndicators" class="carousel slide">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3"
                    aria-label="Slide 4"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="4"
                    aria-label="Slide 5"></button>
        </div>
        <div class="carousel-inner">
            <c:if test="${item.imageList == null}">
                <div class="carousel-item active">
                    <img src="../../resources/image/noImage.png" class="d-block w-100" alt="...">
                </div>
            </c:if>
            <c:if test="${item.imageList != null}">
                <c:forEach items="${item.imageList}" var="image" end="0">
                    <div class="carousel-item active">
                        <img src="${image.url}" class="d-block w-100" alt="...">
                    </div>
                </c:forEach>
                <c:forEach items="${item.imageList}" var="image" begin="1">
                    <div class="carousel-item">
                        <img src="${image.url}" class="d-block w-100" alt="...">
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    <hr>
    <div class="detail">
        <h3>${item.title}</h3>
        <p class="article-info">${item.category_name} ∙ <fmt:formatDate value="${item.created_at}"
                                                                        pattern="yyyy-MM-dd HH:mm"/></p>
        <p id="price"><fmt:formatNumber value="${item.price}" pattern="#,###"/>원</p>
        <p id="article-content">
            ${fn:replace(item.content, replaceChar, "<br/>")}
        </p>
        <p class="article-info">찜 5 ∙ 채팅 ${item.chat_cnt}</p>
    </div>
    <hr>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end btnDetail">
        <button class="btn btn-primary" type="button">채팅하기</button>
        <button class="btn btn-primary" type="button">찜하기</button>
        <button class="btn btn-primary" type="button">신고하기</button>
    </div>
</div>
</body>
</html>