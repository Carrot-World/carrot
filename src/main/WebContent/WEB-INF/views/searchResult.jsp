<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${list == null}">
    <h3>조건에 맞는 검색 결과가 없습니다.</h3>
</c:if>
<c:if test="${list != null}">
    <div class="itemList">
        <c:forEach items="${list}" var="item">
            <div class="card" onclick="location.href='/page/detail?id=${item.id}'">
                <c:if test="${item.imageList == null}">
                    <img src="${pageContext.request.contextPath}/resources/image/noImage.png" class="card-img-top"
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
    <c:if test="${page != null}">
        <div class="footer">
            <nav aria-label="...">
                <ul class="pagination pagination-sm justify-content-center">
                    <c:if test="${page.start != 1}">
                        <li class="page-item">
                            <a class="page-link"
                               onclick="pageMove('/api/item/search?category_id=${searchInfo.category_id}&loc1=${searchInfo.loc1}&loc2=${searchInfo.loc2}&loc3=${searchInfo.loc3}&pageNo=${page.start - 1}')">
                                &laquo;</a>
                        </li>
                    </c:if>
                    <c:forEach var="i" begin="${page.start}" end="${page.end}">
                        <li class="page-item ${i == page.current ? 'active' : ''}">
                            <a class="page-link"
                               onclick="pageMove('/api/item/search?category_id=${searchInfo.category_id}&loc1=${searchInfo.loc1}&loc2=${searchInfo.loc2}&loc3=${searchInfo.loc3}&pageNo=${i}')">
                                    ${i}
                            </a>
                        </li>
                    </c:forEach>
                    <c:if test="${page.end != page.total}">
                        <li class="page-item">
                            <a class="page-link"
                               onclick="pageMove('/api/item/search?category_id=${searchInfo.category_id}&loc1=${searchInfo.loc1}&loc2=${searchInfo.loc2}&loc3=${searchInfo.loc3}&pageNo=${page.end + 1}')">
                                &raquo;</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </c:if>
</c:if>
