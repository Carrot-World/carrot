<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal-content">
  <div class="modal-header">
    <h1 class="modal-title fs-5" id="passwordModalLabel">판매완료</h1>
    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
  </div>
  <div class="modal-body">
    <c:if test="${fn:length(buyerList) == 0}">
      <div class="input-row">
        <label style="width: 100%; font-weight: 500;">생성된 채팅방이 없습니다.</label>
      </div>
    </c:if>
    <c:if test="${fn:length(buyerList) != 0}">
      <div class="input-row">
        <label for="buyerSelector">구매자 이름: </label>
        <select class="form-select" id="buyerSelector">
          <option value="">구매자 이름을 선택해주세요.</option>
          <c:forEach items="${buyerList}" var="buyer">
            <option value="${buyer}">${buyer}</option>
          </c:forEach>
        </select>
      </div>
    </c:if>
  </div>
  <div class="modal-footer">
    <c:if test="${fn:length(buyerList) != 0}">
      <button class="btn orange-btn" onclick="submitBtnHandler(${postId})">판매완료</button>
    </c:if>
    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
  </div>
</div>