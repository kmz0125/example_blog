<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actBlo" value="${ForwardConst.ACT_BLOG.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />

<!DOCTYPE html>
<html lang=ja>
<head>
<meta charset="UTF-8">
    <title><c:out value="Blog" /></title>
    <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>

<body>

<c:choose>
    <c:when test="${not empty blog}">
        <c:forEach var="b" items="${blog}">
            <!-- ブログ記事の表示コード -->
            <div id=blog_article>

                <div id=blog_title>
                <h3>
                    <p>${b.title}</p>
                </h3>
                </div>

                <div id=blog_content>
                    <p>${b.content}</p>
                </div>

                <div id=blog_date>
                    <p>投稿日時：${b.blogDate}</p>
                </div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>ブログ記事がありません。</p>
    </c:otherwise>
</c:choose>

    <div id="pagenation">
        (全 ${blog_count} 件) <br>
        <c:forEach var="i" begin="1" end="${((blog_count - 1) / maxRow) + 1}" step="1">
            <c:choose>
                <c:when test="${i ==page}">
                    <c:out value="${i}" />&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='?action=${actBlo}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>

</body>
</html>