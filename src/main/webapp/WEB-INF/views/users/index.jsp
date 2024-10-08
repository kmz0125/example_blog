<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actUser" value="${ForwardConst.ACT_LOGIN.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW_LOGIN.getValue() }" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue() }" />

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null }">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>ユーザー登録</h2>
    </c:param>
</c:import>