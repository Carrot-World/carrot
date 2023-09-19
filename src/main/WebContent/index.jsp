<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Document</title>
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7551008ffbd30aac5abaffdcc5a33d7f&libraries=services"></script>
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
    <link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet">
    <link rel="stylesheet" href="https://d1unjqcospf8gs.cloudfront.net/assets/home/home-8eaad34ac868e1c437dc11987fc0d19e474be153dadcb714fcb9693c1273da34.css" media="all" />

</head>
<body>
<div class="header bg-body-tertiary">
    <nav class="navbar">
        <a class="navbar-brand logo" href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/resources/image/carrot.jpeg" width="40" height="35"
                 class="align-text-top"/>
            당근나라
        </a>
        <div class="nav-button-wrapper">
            <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/page/itemRegister">물품등록</a>
            <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/page/itemList">물품목록</a>
            <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/page/postList">동네생활</a>
            <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/page/chat">채팅</a>
            <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/page/mypageSell">마이페이지</a>
            <sec:authorize access="isAuthenticated()">
                <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/logout">로그아웃</a>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <a class="navbar-brand" style="font-size: 20px; font-weight: 500" href="${pageContext.request.contextPath}/page/login">로그인</a>
            </sec:authorize>
        </div>
    </nav>
</div>
<main>
    <section id="rebranded-home-main-section-top" class="background-pale-peach">
        <div id="home-main-top">
            <div class="home-main-desc">
                <h1 class="home-main-title">당신 근처의<br>지역 생활 커뮤니티</h1>
                <p class="service-desc">
                    동네라서 가능한 모든 것<br>당근에서 가까운 이웃과 함께해요.
                </p>
                <br>
            </div>
            <div class="home-main-image-top">
                <img srcset="https://d1unjqcospf8gs.cloudfront.net/assets/home/main/3x/rebranded-image-top-e765d561ee9df7f5ab897f622b8b5a35aaa70314f734e097ea70e6c83bdd73f1.webp "
                     class="home-main-image-top" alt="중고거래, 동네생활 질문글, 알바와 동네가게"
                     src="https://d1unjqcospf8gs.cloudfront.net/assets/home/main/3x/rebranded-image-top-eb44f81acb1938b57ba029196887cdd56fbb66dc46aa5d8c6d8392a7d8c9e671.png"/>
            </div>
        </div>
    </section>
    <section class="rebranded-home-main-section" style="background-color:#f9f9f9">
        <div class="home-main-content">
            <div class="home-main-image home-main-image-01">
<%--                <img srcset="https://d1unjqcospf8gs.cloudfront.net/assets/home/main/3x/rebranded-image-1-c84dd43a3a50742bc296bd36e8b9b1c4a487b8d36aaf67e7f90ecce80201cf60.webp "--%>
<%--                     class="home-main-image-01" alt="우리동네 중고 직거래 사진"--%>
<%--                     src="https://d1unjqcospf8gs.cloudfront.net/assets/home/main/3x/rebranded-image-1-efd09cb54044140d530c6647906303c669ca80a8b51473722fc2b1747a3d9047.png"/>--%>
                <img class="home-main-image-01" alt="우리동네 중고 직거래 사진"
                     src="${pageContext.request.contextPath}/resources/image/mainTrade.png"/>
            </div>
            <div>
                <p class="service-title">
                    중고거래
                </p>
                <h1 class="home-main-title">믿을만한<br>이웃 간 중고거래</h1>
                <p class="service-desc">
                    동네 주민들과 가깝고 따뜻한 거래를<br>지금 경험해보세요.
                </p>
            </div>
        </div>
    </section>
    <section class="rebranded-home-main-section" style="background-color:#FFF8F1;">
        <div class="home-main-content home-main-reverse">
            <div class="home-main-image home-main-image-02">
                <img srcset="https://d1unjqcospf8gs.cloudfront.net/assets/home/main/3x/rebranded-image-2-78a84106155b4b2c341b6425515a90782dc08a6f443ba0bb622700ef788d2e19.webp "
                     class="home-main-image-02" loading="lazy" alt="이웃과 함께하는 동네생활"
                     src="https://d1unjqcospf8gs.cloudfront.net/assets/home/main/3x/rebranded-image-2-c99a6862a786b8f801c08ea8e76cfc0c06b5f86442c90ff7bffef0f99146321e.png"/>
            </div>
            <div>
                <p class="service-title">
                    동네생활
                </p>
                <h1 class="home-main-title">이웃만 아는<br>동네 정보와 이야기</h1>
                <p class="service-desc">
                    우리동네의 다양한 정보와 이야기를<br>공감과 댓글로 나누어요.
                </p>
                <ul class="home-story-list">
                    <li class="home-story-list-item">
                        <div class="icon-story icon-story-01"></div>
                        <div class="text-s text-bold mt-3 mb-2">동네모임</div>
                        <div class="item-desc">근처 이웃들과 동네<br>이야기를 해보세요.</div>
                    </li>
                    <li class="home-story-list-item">
                        <div class="icon-story icon-story-02"></div>
                        <div class="text-s text-bold mt-3 mb-2">동네질문</div>
                        <div class="item-desc">궁금한 게 있을 땐<br>이웃에 물어보세요.</div>
                    </li>
                    <li class="home-story-list-item">
                        <div class="icon-story icon-story-03"></div>
                        <div class="text-s text-bold mt-3 mb-2">동네분실센터</div>
                        <div class="item-desc">무언가를 잃어버렸다면<br>글을 올려보세요.</div>
                    </li>
                </ul>
            </div>
        </div>
    </section>
</main>
</body>
</html>