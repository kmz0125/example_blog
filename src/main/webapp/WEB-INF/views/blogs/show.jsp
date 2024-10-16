<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actBlo" value="${ForwardConst.ACT_BLOG.getValue()}" />
<c:set var="commUdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />


        <c:out value="${blog.content}" />


            <p>
                <a href="<c:url value='?action=${actBlo}&command=${commEdt}&id=${blog.id}' />">編集</a>
            </p>

