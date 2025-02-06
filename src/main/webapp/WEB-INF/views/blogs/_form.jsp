<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>

<fmt:parseDate value="${blog.blogDate}" pattern="yyyy-MM-dd" var="blogDay" type="date" />
<label for="${Attribute.Const.BLOG_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.BLOG_DATE.getValue()}" id="${AttributeConst.BLOG_DATE.getValue()}" value="<fmt:formatDate value='${blogDay}' pattern='yyyy-MM-dd' type='date'/>" />
<br /><br />

<label for="${AttributeConst.BLOG_TITLE.getValue()}">タイトル</label><br />
<input type="text" name="${AttributeConst.BLOG_TITLE.getValue()}" id="${AttributeConst.BLOG_TITLE.getValue()}" value="${blog.title}" />
<br /><br />

<label for="${AttributeConst.BLOG_CONTENT.getValue()}">内容</label><br />
<textarea name="${AttributeConst.BLOG_CONTENT.getValue()}" id="${AttributeConst.BLOG_CONTENT.getValue()}" rows="10" cols="50">${blog.content}</textarea>
<br /><br />
<input type="hidden" name="${AttributeConst.BLOG_ID.getValue()}" value="${blog.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>
