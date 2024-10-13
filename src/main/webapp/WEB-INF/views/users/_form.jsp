<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_USER.getValue()}" />
<c:set var="sommIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:if test="${errors != null }">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            <c:out value="${error }" /><br />
        </c:forEach>
    </div>
</c:if>

<label for="${AttributeConst.USER_ID.getValue()}">ID</label><br />
<input type="text" name="${AttributeConst.USER_ID.getValue()}" id="${AttributeConst.USER_ID.getValue()}" value="${user.id }" />
<br /><br />

<label for="${AttributeConst.USER_PASS.getValue()}">password</label><br />
<input type="password" name="${AttributeConst.USER_PASS.getValue()}" id="${AttributeConst.USER_PASS.getValue()}" value="${user.password}" />
<br /><br />

<input type="hidden" name="${AttributeConst.USER_ID.getValue()}" value="${user.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>