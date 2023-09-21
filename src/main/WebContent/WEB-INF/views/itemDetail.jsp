<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>${item.writer} 님의 물품</title>
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
    ></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/resources/css/itemDetail.css" rel="stylesheet">
    <script defer src="${pageContext.request.contextPath}/resources/js/itemDetail.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="section">
    <div class="section-header">

    </div>
    <div class="section-content">
        <div id="imageCarousel" class="carousel slide">
            <div class="carousel-indicators">
                <c:if test="${item.imageList == null}">
                    <button type="button" data-bs-target="#imageCarousel" data-bs-slide-to="0" class="active"
                            aria-current="true"></button>
                </c:if>
                <c:if test="${item.imageList != null}">
                    <c:forEach items="${item.imageList}" var="image" end="0" varStatus="status">
                        <button type="button" data-bs-target="#imageCarousel" data-bs-slide-to="${status.index}"
                                class="active" aria-current="true"></button>
                    </c:forEach>
                    <c:forEach items="${item.imageList}" var="image" begin="1" varStatus="status">
                        <button type="button" data-bs-target="#imageCarousel"
                                data-bs-slide-to="${status.index}"></button>
                    </c:forEach>
                </c:if>
            </div>
            <div class="carousel-inner">
                <c:if test="${item.imageList == null}">
                    <div class="carousel-item active">
                        <img src="${pageContext.request.contextPath}/resources/image/noImage.png" class="d-block w-100"
                             alt="..."/>
                    </div>
                </c:if>
                <c:if test="${item.imageList != null}">
                    <c:forEach items="${item.imageList}" var="image" end="0">
                        <div class="carousel-item active">
                            <img src="${image.url}" class="d-block w-100" alt="..."/>
                        </div>
                    </c:forEach>
                    <c:forEach items="${item.imageList}" var="image" begin="1">
                        <div class="carousel-item">
                            <img src="${image.url}" class="d-block w-100" alt="..."/>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <button
                    class="carousel-control-prev"
                    type="button"
                    data-bs-target="#imageCarousel"
                    data-bs-slide="prev"
            >
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            </button>
            <button
                    class="carousel-control-next"
                    type="button"
                    data-bs-target="#imageCarousel"
                    data-bs-slide="next"
            >
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
            </button>
        </div>
        <div class="user-info">
            <div class="user-info-wrapper">
                <div id="user-info"
                     onclick="location.href='${pageContext.request.contextPath}/page/userpageSell?id=${item.writer}'" style="cursor:pointer">
                    <h4 id="username">${item.writer_nickname}</h4>
                    <span id="location">${item.loc1} ${item.loc2} ${item.loc3}</span>
                    <p id="dealCnt">거래횟수: ${item.writer_trade_cnt}회</p>
                </div>
                <div class="button-wrapper">

                </div>
            </div>
        </div>
        <div class="post-wrapper">
            <div class="post-title">${item.title}</div>
            <div class="post-category">${item.category_name} ∙ <fmt:formatDate value="${item.created_at}"
                                                                               pattern="MM/dd HH:mm"/></div>
            <div class="post-content">
                <pre>${item.content}</pre>
            </div>
            <div class="post-like">관심 ${item.hart_cnt} ∙ 채팅 ${item.chat_cnt}</div>
        </div>
    </div>


    <div class="section-footer">
        <div class="price-card">
            <span class="price"><fmt:formatNumber value="${item.price}" pattern="#,###"/>원</span>
            <div class="button-wrapper">
                <!-- 다른 사람 -->
                <c:if test="${item.writer != user.id}">
                    <button class="btn" type="button" id="hartBtn" value="${item.id}" value1="${item.hart_cnt}"
                            name="${isHart == null ? "plus" : "minus"}"
                            onclick="hartBtnHandler()">${isHart == null ? "찜하기" : "찜 취소"}</button>
                    <form action="/page/chat" method="post" class="button-wrapper">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="itemId" value="${item.id}"/>
                        <button class="btn">채팅하기</button>
                    </form>
                </c:if>
                <!-- 등록한 사람 -->
                <c:if test="${item.writer == user.id}">
                    <c:if test="${item.status == 0}">
                        <button class="btn" id="completeBtn" value="${item.id}"
                                onclick="completeBtnHandler('${item.writer}')">판매완료하기</button>
                        <form action="/api/item/delete" method="post" class="button-wrapper">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="id" value="${item.id}"/>
                            <input type="hidden" name="writer" value="${item.writer}"/>
                            <button class="btn">삭제</button>
                        </form>
                    </c:if>
                    <!-- 판매완료 -->
                    <c:if test="${item.status == 1}">
                        <button class="btn disabled">판매완료</button>
                    </c:if>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>