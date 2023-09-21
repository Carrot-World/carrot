<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal-header">
  <h1 class="modal-title fs-5" id="writeModalLabel">후기작성</h1>
  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
</div>
<div class="modal-body">
  <label for="writeForm">
    <c:if test="${userInfo.id == trade.seller}">
      ${trade.buyer}
    </c:if>
    <c:if test="${userInfo.id == trade.buyer}">
      ${trade.seller}
    </c:if>
    님의 후기작성
  </label>
  <textarea class="form-control" id="writeForm"></textarea>
</div>
<div class="modal-footer">
  <button class="btn orange-btn write-btn" onclick="updateTrade(${trade.id},
  <c:if test="${userInfo.id == trade.seller}">
  'seller'
  </c:if>
  <c:if test="${userInfo.id == trade.buyer}">
  'buyer'
  </c:if>
  )">후기등록</button>
  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
</div>